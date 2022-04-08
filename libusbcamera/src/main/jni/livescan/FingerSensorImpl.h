
#ifndef FINGER_SENSOR_IMPL_H
#define FINGER_SENSOR_IMPL_H

#include "FingerSensor.h"
//#include "CyUsbSDK.h"

/************************************************************************/
/*    相机寄存器定义                                                    */
/************************************************************************/

//define capture mode
#define     CAPTURE_CONTI						0
#define     CAPTURE_SNAP_SOFT					1
#define     CAPTURE_SNAP_SHUTTER				2
#define     CAPTURE_SNAP						3
#define     CAPTURE_A_FRAME                     0x01

// 【CAM_A1000】ID为0x88（KAC9638）
// 值及范围
#define     A1000_PID							0x1038	// PID 
#define     A1000_SENSOR_ID                     0x77    // sensor ID
#define     A1000_MIN_GAIN						0		// 最小增益
#define     A1000_MAX_GAIN						255		// 最大增益
#define     A1000_MIN_EXPOSURE					0		// 最小曝光
#define     A1000_MAX_EXPOSURE					511		// 最大曝光
#define     A1000_MAX_WIDTH						640		// 最大采图宽度
#define     A1000_MAX_HEIGHT					480		// 最大采图高度

// 参数寄存器
#define     A1000_REG_SIZE					   1		// 寄存器字节数（8位）
#define     A1000_REG_DEVICE_ID                0xcc		// ID
#define     A1000_REG_INTEGRATIONTIME_HIG      0x08		// 曝光高八位
#define     A1000_REG_INTEGRATIONTIME_LOW      0x10		// 曝光低八位
#define     A1000_REG_PGA                      0x00		// 增益
#define     A1000_REG_SOFT_TRIGGER             0xb7     // soft trigger


//【CAM_A2000】ID为0x77（KAC9638）	
#define     A2000_PID							0x1018	// PID
#define     A2000_SENSOR_ID                     0x77    // sensor ID
#define     A2000_MIN_GAIN						0		// 最小增益
#define     A2000_MAX_GAIN						255		// 最大增益
#define     A2000_MIN_EXPOSURE					0		// 最小曝光
#define     A2000_MAX_EXPOSURE					511		// 最大曝光
#define     A2000_MAX_WIDTH						640		// 最大采图宽度
#define     A2000_MAX_HEIGHT					480		// 最大采图高度

// 参数寄存器
#define     A2000_REG_SIZE					   1		// 寄存器字节数（8位）
#define     A2000_REG_DEVICE_ID                0x0a		// ID
#define     A2000_REG_INTEGRATIONTIME_HIG      0x08		// 曝光高八位
#define     A2000_REG_INTEGRATIONTIME_LOW      0x10		// 曝光低八位
#define     A2000_REG_PGA                      0x00		// 增益
#define     A2000_REG_SOFT_TRIGGER             0xb7     // soft trigger

// 【CAM_A3000】ID为0x8431（MICRO_9M001）
#define     A3000_PID							0x1028	// PID
#define     A3000_SENSOR_ID                     0x8431  // sensor ID
#define     A3000_MIN_GAIN						0		// 最小增益
#define     A3000_MAX_GAIN						48		// 最大增益
#define     A3000_MIN_EXPOSURE					0		// 最小曝光
#define     A3000_MAX_EXPOSURE					1049	// 最大曝光
#define     A3000_MAX_WIDTH						1280	// 最大采图宽度
#define     A3000_MAX_HEIGHT					1024	// 最大采图高度
#define     A3000_WND_HEIGHT                    656     // 采集窗高度
#define     A3000_WND_WIDTH                     1024    // 采集窗宽度

#define     A3000_ACTIVE_WINDOW_OFFSET_H		20		// 起始列的偏移值
#define     A3000_ACTIVE_WINDOW_OFFSET_V		12		// 起始行的偏移值
#define     A3000_READ_MODE_SOFT_TRIGERR	    0x8100	// 模式值

#define     A3000_REG_SIZE					    2		// 寄存器字节数（16位）
#define     A3000_REG_DEVICE_ID					0x00	// ID
#define     A3000_REG_ACTIVE_WINDOW_ROW_START	0x01	// 起始行
#define     A3000_REG_ACTIVE_WINDOW_COL_START	0x02	// 起始列
#define     A3000_REG_ACTIVE_WINDOW_ROW			0x03	// ROI宽
#define     A3000_REG_ACTIVE_WINDOW_COL			0x04	// ROI列
#define     A3000_REG_SHUTTER_WIDTH				0x09	// 曝光
#define     A3000_REG_READMODE1					0x1e	// 读数据模式1
#define     A3000_REG_READMODE2					0x20	// 读数据模式2
#define     A3000_REG_GAIN_GLOBAL				0x35	// 增益
#define     A3000_REG_SOFT_TRIGGER              0x0b     // soft trigger

#define     TRASH_DATA_LEN                      1024    // only for trigger mode

#define     DATA_ALIGN_BIT    4       // 2^3 = 8

struct SDistortionArgu {
	int     nCaptureStartX;
	int     nCaptureStartY;
	int     nCaptureWidth;
	int     nCaptureHeight;
	int     nCorrectedStartX;
	int     nCorrectedStartY;
	int     nCorrectedWidth;
	int     nCorrectedHeight;
	float   fColResRatio;
	float   fRowResRatio;
	bool    ifNeedCorrection;

	SDistortionArgu() { SetDefaultValue(); }
	void SetDefaultValue() {
		nCaptureStartX = 0;
		nCaptureStartY = 0;
		nCaptureWidth = 0;
		nCaptureHeight = 0;
		nCorrectedStartX = 0;
		nCorrectedStartY = 0;
		nCorrectedWidth = 0;
		nCorrectedHeight = 0;
		fColResRatio = 1.0f;
		fRowResRatio = 1.0f;
		ifNeedCorrection = false;
		return;
	}
};

class CFingerSensor::CImplicit {
public:
    CImplicit() {
		//m_CyUsbSDK.InitializeCyUSB() ;
        m_bOpenSate = false;
        m_pImgBuff = 0;
		m_nDeviceID = 0 ;
		m_bFirstTrigger = true;   //Added for Single Trigger Frame Mode at 20121127
    }
    ~CImplicit() {
		//m_CyUsbSDK.UnInitializeCyUSB() ;
        if (0 != m_pImgBuff)
            delete [] m_pImgBuff;
    }

    void CreateBuff(int nSize) {
        if (0 != m_pImgBuff)
            delete [] m_pImgBuff;
        m_pImgBuff = new unsigned char[nSize];
    }

	// Correct optical distortion
	bool    CorrectDistortion(unsigned char *pSrcData, unsigned char *pDstData);
	void    SetDistortionArgu(float colResRatio = 0.0f, float rowResRatio = 0.0f,
		bool bCorrectDistortion = false);
    int     GetSensorId(void);
    bool    Active();
    bool    Reopen();
    bool    IfDifferentData(unsigned char *pSrcData, int nSize);

public:
    bool	        m_bOpenSate;
	//CCyUsbSDK		m_CyUsbSDK;
	int				m_nDeviceID;
    int             m_nDeviceNo;
    unsigned char*  m_pImgBuff;
	SDistortionArgu m_distortionArgu;
    bool            m_bFirstTrigger;
};

#endif