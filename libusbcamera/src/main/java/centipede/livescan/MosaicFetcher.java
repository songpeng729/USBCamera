package centipede.livescan;

import android.util.Log;

import java.util.concurrent.*;

/**
 * MosaicCallback callback = new MosaicCallback{
 * void afterMosaic(int ret,byte[] buffer){
 * //do something
 * }
 * }
 * <p>
 * MosaicFetcher fetcher = new MosaicFetcher();
 * fetcher.start(callback);
 * fetch image from sensor
 */
public class MosaicFetcher {
    private final String LOG_TAG = "MOSAIC_FETCHER";

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
         * @param ret    mosaic code.if 0 ,finish fetch
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
        if (threadRunning)
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

            waitingShutdown();
        } catch (Throwable e) {
            Log.e(LOG_TAG, "fail to start executor", e);
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

    public void waitingShutdown() {
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

    public boolean isRunning() {
        return threadRunning;
    }

    class ReadThread implements Runnable {
        @Override
        public void run() {
            Log.d(LOG_TAG, "ReadThread run");
            MosaicNative.FastInit(100);
            Log.d(LOG_TAG, "FastInit ok");
            if (callback != null){
                Log.d(LOG_TAG, "callback.initMosaic()");
                callback.initMosaic();
            }
            while (threadRunning) {
                Log.d(LOG_TAG, "begin fastInitIsFinger ");
                Boolean fastInitIsFinger = MosaicNative.FastInitIsFinger();
                Log.d(LOG_TAG, "fastInitIsFinger: "+fastInitIsFinger);
                if (fastInitIsFinger)
                    break;
            }
            while (threadRunning) {
                try {
                    Integer dataSeq = waitingRead.poll(1, TimeUnit.SECONDS);
                    Log.d(LOG_TAG, "run: "+dataSeq);
                    if (dataSeq == null)
                        continue;
                    Log.d(LOG_TAG, "read sensor image");
                    MosaicNative.FastReadSendorImg(dataSeq);
                    waitingMerge.put(dataSeq);
                } catch (Throwable e) {
                    Log.e(LOG_TAG, "fail to read sensor image", e);
                    //threadRunning = false;//add by wumin 20161116
                    break;
                }
            }
            Log.d(LOG_TAG, "exit read thread");
            stop();
        }
    }

    class MergeThread implements Runnable {
        @Override
        public void run() {
            threadRunning = false;
            while (threadRunning) {
                try {
                    Integer dataSeq = waitingMerge.poll(1, TimeUnit.SECONDS);
                    if (dataSeq == null)
                        continue;

                    Log.d(LOG_TAG, "merge FastMosaicNew");
                    int ret = MosaicNative.FastMosaicNew(dataSeq, buffer);
                    waitingRead.put(dataSeq);
                    if (callback != null) {
                        callback.afterMosaic(ret, buffer);
                    }
                    if (ret <= 0) {
                        //threadRunning = false;//add by wumin 20161116
                        break;
                    }
                } catch (Throwable e) {
                    Log.e(LOG_TAG, "fail to mosaic", e);
                    //threadRunning = false;//add by wumin 20161116
                    break;
                }
            }
            Log.d(LOG_TAG, "exit merge thread");
            stop();
        }
    }
}
