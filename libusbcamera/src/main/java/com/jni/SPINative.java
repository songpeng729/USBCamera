package com.jni;

import android.util.Log;

public class SPINative {
    private static final String TAG = "sensor_so";
    
    public SPINative() {
    }
    private native int nativeInit(int nDelay);  
    private native void nativeExit();
 
    private native String nativeGetVer();
        
    private native int nativeGetFrame(byte a[],int size);
    private native int nativeSetGain(int nGain);//1-48
    private native int nativeGetGain();    
    private native int nativeSetExposure(int nExp);//1-1048
    private native int nativeGetExposure();
    private native int nativeGetFrameWidth(); //640
    private native int nativeGetFrameHeight();//640
	private native int nativeSetSensor(int isOn);	//isOn is true,that sensor is running 
	private native int nativeSetLED(int isOn);		//isOn is true,that LED is on 

    static {
        Log.d(TAG, "start load library");
        try {
//            System.loadLibrary("usbsensor");

        } catch (Exception e) {
            Log.d(TAG, "load library, exception=",e);
        }
        Log.d(TAG, "end load library");
    }
    
    public boolean init(int nDelay) {
        return nativeInit(nDelay) == 1;
    }
    public void exit() {
        nativeExit();
    }
    /**
     * @return true , SetGain successfully;
     *         false , SetGain failed.
     */
    public boolean SetGain(int nGain) {
        return nativeSetGain(nGain) == 1;
    }
    /**
     * @return 0 , GetGain failed;
     *         other , GetGain successfully.
     */  
    public int GetGain() {
        return nativeGetGain();
    }
    /**
     * @return true , SetExposure successfully;
     *         false , SetExposure failed.
     */
    public boolean SetExposure(int nExp) {
        return nativeSetExposure(nExp) == 1;
    }  
    /**
     * @return 0 , GetExposure failed;
     *         other , GetExposure successfully.
     */
    public int GetExposure() {
        return nativeGetExposure();
    }
    /**
     * @return ver
     */
    public String GetVer() {
    	String str = nativeGetVer();
        return str;
    }
    /**
     * @return frame width
     */
    public int GetFrameWidth() {
        return nativeGetFrameWidth();
    }
    /**
     * @return frame height
     */
    public int GetFrameHeight() {
        return nativeGetFrameHeight();
    } 
    
    /**
     * @return one frame data size
     */
    public boolean GetFrame(byte buf[],int size) {
        return size == nativeGetFrame(buf,size);
    }
	 /**
     * @return true , SetSensorOn successfully;
     *         false , SetSensorOn failed.
     */
    public boolean SetSensorOn() {
        return nativeSetSensor(1) == 1;
    }
	 /**
     * @return true , SetSensorOff successfully;
     *         false , SetSensorOff failed.
     */
    public boolean SetSensorOff() {
        return nativeSetSensor(0) == 1;
    }	
	 /**
     * @return true , SetLEDOn successfully;
     *         false , SetLEDOn failed.
     */
    public boolean SetLEDOn() {
        return nativeSetLED(1) == 1;
    }
	 /**
     * @return true , SetLEDOff successfully;
     *         false , SetLEDOff failed.
     */
    public boolean SetLEDOff() {
        return nativeSetLED(0) == 1;
    }		
}
