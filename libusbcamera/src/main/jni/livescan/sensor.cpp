#include "sensor.h"

#include <android/log.h>
#include <stdint.h>
#include <string.h>
#include <stdio.h>

#include "libUVCCamera.h"
#include "UVCCamera.h"
#include "FingerSensorImpl.h"
#include "FingerAPI.h"

//#define LOG_TAG "SENSOR"

UVCCamera *uvcCamera;
FingerAPI fingerAPI;

int sensor_int(int nDelay){
    uvcCamera = new UVCCamera();
	fingerAPI.camera = uvcCamera;
	//TODO 链接USB
	//camera->connect(vid, pid, fd, busNum, devAddr, c_usbfs);
    return 1;
}
int sensor_exit(){
    SAFE_DELETE(uvcCamera);
    return 1;
}
int sensor_readimg(unsigned char* buf){
    int ret = fingerAPI.sensor_readimg(buf);
    return ret;
}
int sensor_setGain(int value){
    //1-48
    return 1;
}
int sensor_getGain(int * pValue){
    return 1;
}
int sensor_setExp(int value){
    //1-1048
    return 1;
}
int sensor_getExp(int *pValue){

    return 1;
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