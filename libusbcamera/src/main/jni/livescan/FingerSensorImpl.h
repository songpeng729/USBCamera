
#ifndef FINGER_SENSOR_IMPL_H
#define FINGER_SENSOR_IMPL_H

#include "FingerSensor.h"
//#include "CyUsbSDK.h"

/************************************************************************/
/*    ����Ĵ�������                                                    */
/************************************************************************/

//define capture mode
#define     CAPTURE_CONTI						0
#define     CAPTURE_SNAP_SOFT					1
#define     CAPTURE_SNAP_SHUTTER				2
#define     CAPTURE_SNAP						3
#define     CAPTURE_A_FRAME                     0x01

// ��CAM_A1000��IDΪ0x88��KAC9638��
// ֵ����Χ
#define     A1000_PID							0x1038	// PID 
#define     A1000_SENSOR_ID                     0x77    // sensor ID
#define     A1000_MIN_GAIN						0		// ��С����
#define     A1000_MAX_GAIN						255		// �������
#define     A1000_MIN_EXPOSURE					0		// ��С�ع�
#define     A1000_MAX_EXPOSURE					511		// ����ع�
#define     A1000_MAX_WIDTH						640		// ����ͼ���
#define     A1000_MAX_HEIGHT					480		// ����ͼ�߶�

// �����Ĵ���
#define     A1000_REG_SIZE					   1		// �Ĵ����ֽ�����8λ��
#define     A1000_REG_DEVICE_ID                0xcc		// ID
#define     A1000_REG_INTEGRATIONTIME_HIG      0x08		// �ع�߰�λ
#define     A1000_REG_INTEGRATIONTIME_LOW      0x10		// �ع�Ͱ�λ
#define     A1000_REG_PGA                      0x00		// ����
#define     A1000_REG_SOFT_TRIGGER             0xb7     // soft trigger


//��CAM_A2000��IDΪ0x77��KAC9638��	
#define     A2000_PID							0x1018	// PID
#define     A2000_SENSOR_ID                     0x77    // sensor ID
#define     A2000_MIN_GAIN						0		// ��С����
#define     A2000_MAX_GAIN						255		// �������
#define     A2000_MIN_EXPOSURE					0		// ��С�ع�
#define     A2000_MAX_EXPOSURE					511		// ����ع�
#define     A2000_MAX_WIDTH						640		// ����ͼ���
#define     A2000_MAX_HEIGHT					480		// ����ͼ�߶�

// �����Ĵ���
#define     A2000_REG_SIZE					   1		// �Ĵ����ֽ�����8λ��
#define     A2000_REG_DEVICE_ID                0x0a		// ID
#define     A2000_REG_INTEGRATIONTIME_HIG      0x08		// �ع�߰�λ
#define     A2000_REG_INTEGRATIONTIME_LOW      0x10		// �ع�Ͱ�λ
#define     A2000_REG_PGA                      0x00		// ����
#define     A2000_REG_SOFT_TRIGGER             0xb7     // soft trigger

// ��CAM_A3000��IDΪ0x8431��MICRO_9M001��
#define     A3000_PID							0x1028	// PID
#define     A3000_SENSOR_ID                     0x8431  // sensor ID
#define     A3000_MIN_GAIN						0		// ��С����
#define     A3000_MAX_GAIN						48		// �������
#define     A3000_MIN_EXPOSURE					0		// ��С�ع�
#define     A3000_MAX_EXPOSURE					1049	// ����ع�
#define     A3000_MAX_WIDTH						1280	// ����ͼ���
#define     A3000_MAX_HEIGHT					1024	// ����ͼ�߶�
#define     A3000_WND_HEIGHT                    656     // �ɼ����߶�
#define     A3000_WND_WIDTH                     1024    // �ɼ������

#define     A3000_ACTIVE_WINDOW_OFFSET_H		20		// ��ʼ�е�ƫ��ֵ
#define     A3000_ACTIVE_WINDOW_OFFSET_V		12		// ��ʼ�е�ƫ��ֵ
#define     A3000_READ_MODE_SOFT_TRIGERR	    0x8100	// ģʽֵ

#define     A3000_REG_SIZE					    2		// �Ĵ����ֽ�����16λ��
#define     A3000_REG_DEVICE_ID					0x00	// ID
#define     A3000_REG_ACTIVE_WINDOW_ROW_START	0x01	// ��ʼ��
#define     A3000_REG_ACTIVE_WINDOW_COL_START	0x02	// ��ʼ��
#define     A3000_REG_ACTIVE_WINDOW_ROW			0x03	// ROI��
#define     A3000_REG_ACTIVE_WINDOW_COL			0x04	// ROI��
#define     A3000_REG_SHUTTER_WIDTH				0x09	// �ع�
#define     A3000_REG_READMODE1					0x1e	// ������ģʽ1
#define     A3000_REG_READMODE2					0x20	// ������ģʽ2
#define     A3000_REG_GAIN_GLOBAL				0x35	// ����
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