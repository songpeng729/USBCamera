#ifndef FINGER_SENSOR_H
#define FINGER_SENSOR_H

#ifdef API_EXPORTS
#define DLL_API __declspec(dllexport)
#else
#define DLL_API //__declspec(dllimport)
#endif

#define IN
#define OUT
#define IO

// Camera Type Code in hardware 
enum DLL_API CAMERA_TYPE_CODE {
	CAM_A1000 = 0x1038,
    CAM_A2000 = 0x1018,
    CAM_A3000 = 0x1028,
};

// Standard Capture Window Size according to GA standard
enum DLL_API STD_WINDOW_SIZE {
	A1000_WIDTH  = 300,
	A1000_HEIGHT = 400,
    A2000_WIDTH  = 440,
    A2000_HEIGHT = 440,
    A3000_WIDTH  = 640,
    A3000_HEIGHT = 640,
};

class DLL_API CFingerSensor {
public:
    typedef struct _tagDeviceParameter {
        int nCamType;
        int nMinGain;
        int nMaxGain;
        int nMinExposure;
        int nMaxExposure;
        int nMaxWindowWidth;
        int nMaxWindowHeight;
    } DEVICEPARAMS, *pDEVICEPARAMS;

public:
    CFingerSensor();
    ~CFingerSensor();

    // ��ȡ�����Ӳɼ��ǵĸ���
	int     GetCameraCount(void) ;

    // ��ȡָ���ɼ��ǵ��豸���Ƽ����ͺ�
	bool    GetCameraName(IN int nIndex, OUT char* pszDevName, OUT int& nType);

    // open/close device
	bool    Open(IN int nIndex = 0, 
                 IN bool bCorrectDistortion = true,
                 IN float fColResRatio = 0.0f,
                 IN float fRowResRatio = 0.0f);
    bool    Close(void);

    // get device parameters
    bool    GetDeviceParams(IO DEVICEPARAMS* pDeviceParams);

    // set gain in range [0, 127], or use range via GetDeviceParams
    bool    SetGain(IN int nGain = 25);

    // set exposure time, use range via GetDeviceParams
	//      A1000: 1~511
    //      A2000: 1~511
    //      A3000: 1~ Height-5 
    bool    SetExposure(IN int nExp = 400);
    // inverse image data
    bool    SetReverse(IN bool bReverse = true);
    // set register, reserved for expert's usage
    bool    SetRegister(IN unsigned char bReg, IN unsigned short wVal);
	bool    GetRegister(IN unsigned char bReg, OUT unsigned short &wVal);

    // set serial number
    bool    SetSerialNo(int nSerialNo);
    bool    GetSerialNo(int& nSerialNo);

    // read image, the image buffer should be allocated by caller, and the size 
    // should be (defined by enum STD_WINDOW_SIZE):
	//      A1000:  256 * 360 bytes
    //      A2000:  440 * 440 bytes
    //      A3000:  640 * 640 bytes
	//      A3001:  640 * 640 bytes
	// for distortion version scanner, 
	// distortion is corrected by internal default arguments automatically.
    bool    ReadImage(IO unsigned char *pImageBuff);

    // filter out background noise and do automatic enhancement to image data
    // for pixels lower than nLowTh��0 will be assigned;
    // for pixels higher than nHighTh, 255 will be assigned.
    bool    NoiseFilterByThreshold(IO unsigned char *pSrcData, IN int nSrcWidth, IN int nSrcHeight,
            IN unsigned char nLowTh = 0, IN unsigned char nHighTh = 224);
public:
    class       CImplicit;
    CImplicit*  pImpl;
};

//
#endif