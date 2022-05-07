package centipede.livescan;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * MosaicCallback callback = new MosaicCallback{
 *   void afterMosaic(int ret,byte[] buffer){
 *        //do something
 *   }
 * }
 *
 * MosaicFetcher fetcher = new MosaicFetcher();
 * fetcher.start(callback);
 *
 * fetch image from sensor
 */
public class MosaicFetcher {
    private final String LOG_TAG = "MosaicFetcher";

    /**
     * callback interface
     */
    public static interface MosaicCallback{
      /**
       * initialize mosaic
       */
      void initMosaic();
        /**
         * called after doing mosaic.
         * @param ret mosaic code.if 0 ,finish fetch
         * @param buffer image buffer
         */
        void afterMosaic(int ret, byte[] buffer);
    }
    /** waiting merge queue **/
    private static final ArrayBlockingQueue<Integer> waitingMerge = new ArrayBlockingQueue<Integer>(2);
    /** waiting reading queue **/
    private static final ArrayBlockingQueue<Integer> waitingRead = new ArrayBlockingQueue<Integer>(2);
    /** thread pool **/
    private static final ExecutorService executor = Executors.newCachedThreadPool();
    private Future<?> readFuture;
    private Future<?> mergeFuture;
    /** mosaic image buffer **/
    private final byte[] buffer = new byte[640 * 640];
    //callback instance
    private MosaicCallback callback;
    /** thread flag **/
    private volatile boolean threadRunning = false;

    /**
     * start fetcher
     */
    public void start(MosaicCallback callback) {
        if(threadRunning)
            throw new IllegalStateException("fetcher already started");
        this.callback = callback;
        threadRunning = true;
        try {

            waitingMerge.clear();
            waitingRead.clear();

            //initial waiting read queue
            waitingRead.put(1);
            waitingRead.put(2);

            readFuture = executor.submit(new ReadThread());
            mergeFuture = executor.submit(new MergeThread());

            //mergeFuture.get();
            waitingShutdown();
        } catch (Throwable e) {
            Log.e(LOG_TAG, "fail to start executor",e);
        }
    }

    /**
     * stop fetcher
     */
    public void stop() {
        if (threadRunning) {
            Log.e(LOG_TAG, "stop mosaic fetcher");
            threadRunning = false;
        }
    }
    public void waitingShutdown(){
        Log.e(LOG_TAG, "waiting thread shutdown ");
        try {
            Log.d(LOG_TAG, "waiting read thread shutdown...");
            readFuture.get();
            Log.d(LOG_TAG, "waiting merge thread shutdown...");
            mergeFuture.get();
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, "fail to stop executor", e);
        } catch (ExecutionException e) {
            Log.e(LOG_TAG, "fail to stop executor", e);
        } finally {
            Log.d(LOG_TAG, "close mosaic native...");
            MosaicNative.FastEnd();
        }
    }
    public boolean isRunning(){
        return  threadRunning;
    }
    class ReadThread implements Runnable{
        @Override
        public void run() {
            // init初始化放到线程外调用
//            MosaicNative.FastInit(800);
//            if (callback != null)
//                callback.initMosaic();
            while (threadRunning) {
                if (MosaicNative.FastInitIsFinger())
                    break;
            }
            while (threadRunning) {
                try {
                    Integer dataSeq = waitingRead.poll(1, TimeUnit.SECONDS);
                    if (dataSeq == null)
                        continue;
                    Log.d(LOG_TAG, "read sensor image dataSeq:"+dataSeq);
                    MosaicNative.FastReadSendorImg(dataSeq);
                    waitingMerge.put(dataSeq);
                } catch (Throwable e) {
                    Log.e(LOG_TAG, "fail to read sensor image", e);
                    break;
                }
            }
            Log.d(LOG_TAG, "exit read thread");
            stop();
        }
    }
    class MergeThread implements Runnable{
        @Override
        public void run() {
            while (threadRunning) {
                try {
                    Integer dataSeq = waitingMerge.poll(1, TimeUnit.SECONDS);
                    if (dataSeq == null)
                        continue;
                    int ret = MosaicNative.FastMosaicNew(dataSeq, buffer);
                    waitingRead.put(dataSeq);
                    if (callback != null) {
                        callback.afterMosaic(ret, buffer);
                    }
                    if (ret <= 0) {
                        break;
                    }
                } catch (Throwable e) {
                    Log.e(LOG_TAG, "fail to mosaic", e);
                    break;
                }
            }
            Log.d(LOG_TAG, "exit merge thread");
            stop();
        }
    }
}
