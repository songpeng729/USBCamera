//
// Created by pc on 2021/3/1.
//

#include "FingerAPI.h"


FingerAPI::FingerAPI(){

}

FingerAPI::~FingerAPI(){

}

int FingerAPI::sensor_int(int nDelay){
    return nDelay;
}

int FingerAPI::sensor_exit(){
    return RET_SUCCESS;
}

int FingerAPI::sensor_LEDOn(){
    return RET_SUCCESS;
}

int FingerAPI::sensor_LEDOff(){
    return RET_SUCCESS;
}

int FingerAPI::sensor_On(){
    return RET_SUCCESS;
}

int FingerAPI::sensor_Off(){
    return RET_SUCCESS;
}

int FingerAPI::sensor_getHeight(){
    return IMG_HEIGHT;
}

int FingerAPI::sensor_getWidth(){
    return IMG_WIDTH;
}

//增益
int FingerAPI::sensor_setGain(int value){
    //1-48
    UVCCamera *camera1 = reinterpret_cast<UVCCamera *>(camera);
    int result = camera1->setGain(value);
    return result;
}

int FingerAPI::sensor_getGain(){
UVCCamera *camera1 = reinterpret_cast<UVCCamera *>(camera);
int result = camera1->getGain();
return result;
}

//曝光
int FingerAPI::sensor_setExp(int exposure){
//1-1048
    UVCCamera *camera1 = reinterpret_cast<UVCCamera *>(camera);
//请注意：是否需要设置曝光模式，这里设置成1
    camera1->setExposureMode(1);
	int result = camera1->setExposure(exposure);
return result;
}

int FingerAPI::sensor_getExp(){
    UVCCamera *camera1 = reinterpret_cast<UVCCamera *>(camera);
    int result = camera1->getExposure();

return result;
}

int FingerAPI::sensor_readimg(uint8_t *buf){
        UVCCamera *camera1 = reinterpret_cast<UVCCamera *>(camera);

        /*unsigned char inputData[656 * 1024];
        int result = camera1->getCurFrame(inputData);

        unsigned char outputData[640 * 640];
        CFingerSensor::CImplicit().CorrectDistortion(inputData, outputData);

        for (int h = 0; h < 640; h++) {
            for (int w = 0; w < 640; w++) {
                (*buf) = outputData[h * 640 + w]; //0x98&0xFF;
                buf ++;
                //LOGE("capture ing %d " , (*buf));
            }
        }*/
        //delete inputData;
        //delete outputData;

        int result = camera1->getCurFrame(buf);

    return result;
}