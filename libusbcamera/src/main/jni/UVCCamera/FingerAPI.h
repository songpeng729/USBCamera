//
// Created by pc on 2021/3/1.
//

#ifndef USBCAMERA_FINGERAPI_H
#define USBCAMERA_FINGERAPI_H

#include "UVCCamera.h"
#include "libUVCCamera.h"

#include "FingerSensorImpl.h"
#include "FingerAPI.h"


#define RET_SUCCESS 1

/*
return 0 is Success,other is Failure
default Sensor is running,LED On
*/
#define IMG_WIDTH	(640)
#define IMG_HEIGHT	(640)

#define MAXSIZE (1024*656)


class FingerAPI {

public:
    UVCCamera *camera;

public:

	FingerAPI();
	~FingerAPI();


	int sensor_int(int nDelay);
    int sensor_exit();

    int sensor_readimg(unsigned char* buf);
    int sensor_setGain(int value); //1-48
    int sensor_getGain();
    int sensor_setExp(int value);//1-1048
    int sensor_getExp();
    int sensor_getHeight(); // 640
    int sensor_getWidth();	// 640

    int sensor_LEDOn();
    int sensor_LEDOff();
    int sensor_On();
    int sensor_Off();
};



#endif //USBCAMERA_FINGERAPI_H
