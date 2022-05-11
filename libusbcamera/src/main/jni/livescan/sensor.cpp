#include "sensor.h"

#include <android/log.h>
#include <stdint.h>
#include <string.h>
#include <stdio.h>

#include "libUVCCamera.h"
#include "UVCCamera.h"
#include "FingerSensorImpl.h"
#include "FingerAPI.h"

#define LOG_TAG "SENSOR"

UVCCamera *uvcCamera;

int sensor_int(int vid, int pid, int fd, int busnum, int devaddr, const char *usbfs){
   __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"sensor_int ");
    uvcCamera = new UVCCamera();
    if (LIKELY(uvcCamera && (fd > 0))) {
        int result =  uvcCamera->connect(vid, pid, fd, busnum, devaddr, usbfs);
        __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"connect result %d",result);
    }
	uvcCamera->setPreviewSize(1024, 656, 1, 31, 1, 1.0f);
	uvcCamera->startPreview();
    return 1;
}
int sensor_exit(){
    SAFE_DELETE(uvcCamera);
    return 1;
}
int sensor_readimg(unsigned char* buf){
    unsigned char inputData[656 * 1024];
    int result = uvcCamera->getCurFrame(inputData);
    CFingerSensor::CImplicit().CorrectDistortion(inputData, buf);
    return result;
}
int sensor_setGain(int value){
    //1-48
    return uvcCamera->setGain(value);
}
int sensor_getGain(int* pValue){
    return uvcCamera->getGain();
}
int sensor_setExp(int value){
    //1-1048
    //请注意：是否需要设置曝光模式，这里设置成1
    uvcCamera->setExposureMode(1);
    return uvcCamera->setExposure(value);
}
int sensor_getExp(int* pValue){
    return uvcCamera->getExposure();
}
int sensor_getHeight(){
    // 640
    return 640;
}
int sensor_getWidth(){
	// 640
    return 640;
}
int sensor_LEDOn(){
    return 1;
}
int sensor_LEDOff(){
    return 1;
}
int sensor_On(){
    return 1;
}
int sensor_Off(){
    return 1;
}