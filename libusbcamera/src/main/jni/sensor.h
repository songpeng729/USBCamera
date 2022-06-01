#ifndef  _SENSOR_H_
#define _SENSOR_H_
/*
return 0 is Success,other is Failure
default Sensor is running,LED On
*/
#define IMG_WIDTH	(640)
#define IMG_HEIGHT	(640)

#define MAXSIZE (1024*656)
#ifdef __cplusplus
extern "C" {
#endif 
//nDelay [0,2048]
int sensor_int(int vid, int pid, int fd, int busnum, int devaddr, const char *usbfs);
int sensor_exit();
int sensor_readimg(unsigned char* buf);
int sensor_setGain(int value); //1-48
int sensor_getGain();
int sensor_setExp(int value);//1-1048
int sensor_getExp();
int sensor_setContrast(int value);//1-100
int sensor_getContrast();
int sensor_getHeight(); // 640
int sensor_getWidth();	// 640
int sensor_LEDOn();
int sensor_LEDOff();
int sensor_On();
int sensor_Off();
#ifdef __cplusplus
}
#endif
#endif
