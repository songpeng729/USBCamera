
#include "StdAfx.h"
#include "FingerSensorImpl.h"

int CFingerSensor::CImplicit::
GetSensorId()
{
    int nResult = -1;
    nResult = m_CyUsbSDK.ReadCameraRegister(0x00, A3000_REG_SIZE) ; // A3000
    if (nResult == A3000_SENSOR_ID)
        return nResult;

    nResult = m_CyUsbSDK.ReadCameraRegister(0x0A, A2000_REG_SIZE) ; // A2000
    if (nResult == A2000_SENSOR_ID)
        return nResult;

    nResult = m_CyUsbSDK.ReadCameraRegister(0x0A, A1000_REG_SIZE) ; // A1000
    if (nResult == A1000_SENSOR_ID)
        return nResult;

    return nResult;
}

bool CFingerSensor::CImplicit::
Active()
{
    //m_CyUsbSDK.ResetDevice();
    if (!m_bOpenSate)
        return false;

    if (m_nDeviceID == CAM_A3000) {
        // 设置为软件触发模式
        m_CyUsbSDK.WriteCameraRegister(A3000_REG_READMODE1, 
            A3000_READ_MODE_SOFT_TRIGERR, A3000_REG_SIZE) ;

        // 设置640*640， 硬件支持ROI设置
        int nStartRow = m_distortionArgu.nCaptureStartY;
        int nStartCol = m_distortionArgu.nCaptureStartX;
        m_CyUsbSDK.WriteCameraRegister(A3000_REG_ACTIVE_WINDOW_ROW_START, 
            nStartRow + A3000_ACTIVE_WINDOW_OFFSET_V, A3000_REG_SIZE);
        m_CyUsbSDK.WriteCameraRegister(A3000_REG_ACTIVE_WINDOW_COL_START, 
            nStartCol + A3000_ACTIVE_WINDOW_OFFSET_H, A3000_REG_SIZE);
        m_CyUsbSDK.WriteCameraRegister(A3000_REG_ACTIVE_WINDOW_ROW, 
            m_distortionArgu.nCaptureHeight-1, A3000_REG_SIZE);
        m_CyUsbSDK.WriteCameraRegister(A3000_REG_ACTIVE_WINDOW_COL,
            m_distortionArgu.nCaptureWidth-1, A3000_REG_SIZE);
    }
    else if (m_nDeviceID == CAM_A2000) {    
        /* 0x0d and 0x11寄存器的值决定了采集帧率
           0x0d = 0x41 0x11 = 0x00 60fps
           0x0d = 0x41 0x11 = 0x01 30fps   // default
           0x0d = 0x41 0x11 = 0x03 15fps
           0x0d = 0x81 0x11 = 0x00 100fps
           0x0d = 0x81 0x11 = 0x01 50fps
           0x0d = 0x81 0x11 = 0x03 25fps
           如果这里任何寄存器不设置，就是默认的30fps，即 0x0d=0x41 0x11=0x01
        */ 
        /*
        m_CyUsbSDK.WriteCameraRegister( 0x0d, 0x81, A2000_REG_SIZE);       
        m_CyUsbSDK.WriteCameraRegister( 0x11, 0x03, A2000_REG_SIZE);   
        m_CyUsbSDK.WriteCameraRegister( 0x2a, 0x00, A2000_REG_SIZE);   
        m_CyUsbSDK.WriteCameraRegister( 0x2b, 0x00, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x33, 0x0f, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x34, 0x00, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x2d, 0x00, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x2e, 0x00, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x0e, 0x65, A2000_REG_SIZE);  */
		m_CyUsbSDK.WriteCameraRegister( 0x4d, 0x55, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x42, 0x62, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x43, 0x62, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x44, 0x62, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x45, 0x62, A2000_REG_SIZE);
    }
    else if (m_nDeviceID == CAM_A1000) {    
        /* 0x0d and 0x11寄存器的值决定了采集帧率
           0x0d = 0x41 0x11 = 0x00 60fps
           0x0d = 0x41 0x11 = 0x01 30fps   // default
           0x0d = 0x41 0x11 = 0x03 15fps
           0x0d = 0x81 0x11 = 0x00 100fps
           0x0d = 0x81 0x11 = 0x01 50fps
           0x0d = 0x81 0x11 = 0x03 25fps
           如果这里任何寄存器不设置，就是默认的30fps，即 0x0d=0x41 0x11=0x01
        */ 
        /*
        m_CyUsbSDK.WriteCameraRegister( 0x0d, 0x81, A1000_REG_SIZE);
        m_CyUsbSDK.WriteCameraRegister( 0x11, 0x03, A1000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x2a, 0x00, A2000_REG_SIZE);   
        m_CyUsbSDK.WriteCameraRegister( 0x2b, 0x00, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x33, 0x0f, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x34, 0x00, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x2d, 0x00, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x2e, 0x00, A2000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x0e, 0x65, A2000_REG_SIZE);  */
		m_CyUsbSDK.WriteCameraRegister( 0x4d, 0x55, A1000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x42, 0x28, A1000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x43, 0x28, A1000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x44, 0x28, A1000_REG_SIZE);  
        m_CyUsbSDK.WriteCameraRegister( 0x45, 0x28, A1000_REG_SIZE);
    }

    // 复位相机
    m_bFirstTrigger = true;
    m_CyUsbSDK.ResetDevice();
    return true;	
}

bool CFingerSensor::CImplicit::
Reopen()
{
    if (!m_bOpenSate)
        return false;

    Sleep(200);
    m_CyUsbSDK.CloseDevice();
    m_bOpenSate = false;
    Sleep(200);

    if (!m_CyUsbSDK.OpenDevice(m_nDeviceNo))
        return false;

    m_bOpenSate = true;
    Active();

    return true;
}

bool CFingerSensor::CImplicit::
IfDifferentData(unsigned char *pSrcData, int nSize)
{
    unsigned char *pTmp = pSrcData;
    for (int i = 1; i < nSize; ++ i, ++ pTmp) {
        if (*pTmp != *(pTmp - 1))
            return true;
    }
    return false;
}

bool CFingerSensor::CImplicit::
CorrectDistortion(unsigned char *pSrcData, unsigned char *pDstData)
{
    int nSrcHeight = m_distortionArgu.nCaptureHeight;
    int nSrcWidth  = m_distortionArgu.nCaptureWidth;
    int nDstHeight = m_distortionArgu.nCorrectedHeight;
    int nDstWidth  = m_distortionArgu.nCorrectedWidth;
    if (!m_distortionArgu.ifNeedCorrection) {
        memcpy(pDstData, pSrcData, nSrcHeight * nSrcWidth * sizeof(unsigned char));
        return true;
    }

    int nTmpDstWidth = (int)(nSrcWidth / m_distortionArgu.fColResRatio + 0.5f) / 2 * 2;
    int nTmpDstHeight = (int)(nSrcHeight / m_distortionArgu.fRowResRatio + 0.5f) / 2 * 2;
    int nStartX = (nTmpDstWidth - nDstWidth) / 2;
    int nStartY = (nTmpDstHeight - nDstHeight) / 2;

    float fTmp = 0.0f;
    BYTE* pDst = pDstData;
    for (int i = 0; i < nDstHeight; ++ i) {
        fTmp = (float)(i + nStartY) * m_distortionArgu.fRowResRatio;
        int posI = (int)fTmp;
        if (posI >= nSrcHeight) posI = nSrcHeight - 1;
        int posI_1 = posI + 1;
        if (posI_1 >= nSrcHeight) posI_1 = nSrcHeight - 1;
        float deltaI = fTmp - posI;

        for (int j = 0; j < nDstWidth; ++ j, ++ pDst) {
            fTmp = (float)(j + nStartX) * m_distortionArgu.fColResRatio;
            int posJ = (int)fTmp;
            if (posJ >= nSrcWidth) posJ = nSrcWidth - 1;
            int posJ_1 = posJ + 1;
            if (posJ_1 >= nSrcWidth) posJ_1 = nSrcWidth - 1;
            float deltaJ = fTmp - posJ;

            float pre = *(pSrcData+posI*nSrcWidth+posJ) * (1 - deltaJ);
            float nxt = *(pSrcData+posI*nSrcWidth+posJ_1) * deltaJ;
            float up  = pre + nxt;

            pre = *(pSrcData+posI_1*nSrcWidth+posJ) * (1 - deltaJ);
            nxt = *(pSrcData+posI_1*nSrcWidth+posJ_1) * deltaJ;
            float down = pre + nxt;

            *pDst =(unsigned char)(up * (1 - deltaI) + down * deltaI);
        }
    }

    return true;
}

void CFingerSensor::CImplicit::
SetDistortionArgu(float colResRatio, float rowResRatio,
                  bool bCorrectDistortion)
{
    const float resRatioThreshold = 0.5f;
    bool validResRatio = false;
    if (colResRatio > resRatioThreshold && rowResRatio > resRatioThreshold)
        validResRatio = true;

    int nMaxCaptureWidth = A3000_MAX_WIDTH;
    int nMaxCaptureHeight = A3000_MAX_HEIGHT;
    if (CAM_A1000 == m_nDeviceID) {
        nMaxCaptureWidth = A1000_MAX_WIDTH;
        nMaxCaptureHeight = A1000_MAX_HEIGHT;
        m_distortionArgu.nCorrectedWidth = A1000_WIDTH;
        m_distortionArgu.nCorrectedHeight = A1000_HEIGHT;
    }
    else if (CAM_A2000 == m_nDeviceID) {
        nMaxCaptureWidth = A2000_MAX_WIDTH;
        nMaxCaptureHeight = A2000_MAX_HEIGHT;
        m_distortionArgu.nCorrectedWidth = A2000_WIDTH;
        m_distortionArgu.nCorrectedHeight = A2000_HEIGHT;
    }
    else if (CAM_A3000 == m_nDeviceID) {
        nMaxCaptureWidth = A3000_MAX_WIDTH;
        nMaxCaptureHeight = A3000_MAX_HEIGHT;
        m_distortionArgu.nCorrectedWidth = A3000_WIDTH;
        m_distortionArgu.nCorrectedHeight = A3000_HEIGHT;
    }
    else {
        nMaxCaptureWidth = A1000_MAX_WIDTH;
        nMaxCaptureHeight = A1000_MAX_HEIGHT;
        m_distortionArgu.nCorrectedWidth = A1000_WIDTH;
        m_distortionArgu.nCorrectedHeight = A1000_HEIGHT;
    }

    // get wanted width, height, and resolution ratio
    if (bCorrectDistortion) {
        if (CAM_A1000 == m_nDeviceID) {
            if (!validResRatio) {
                colResRatio = 138.3f * 25.4f / 5.0f / 500.0f;
                rowResRatio = 98.4252f * 25.4f / 5.0f / 500.0f;
            }
        }
        else if (CAM_A2000 == m_nDeviceID) {
            if (!validResRatio) {
                colResRatio = 130.0f * 25.4f / 5.0f / 500.0f;
                rowResRatio = 93.0f * 25.4f / 5.0f / 500.0f;
            }
        }
        else if (CAM_A3000 == m_nDeviceID) {
            if (!validResRatio) {
                colResRatio = 139.194f * 25.4f / 5.0f / 500.0f;
                rowResRatio = 98.4252f * 25.4f / 5.0f / 500.0f;
            }
        }
        else {
            colResRatio = 1.0;
            rowResRatio = 1.0f;
        }
        m_distortionArgu.ifNeedCorrection = true;
    }
    else
    {
        colResRatio = 1.0;
        rowResRatio = 1.0f;
        m_distortionArgu.ifNeedCorrection = false;
    }

    m_distortionArgu.fColResRatio = colResRatio;
    m_distortionArgu.fRowResRatio = rowResRatio;

    // get the size should be captured
    // for A2000 and A1000, whole window will be captured
    // because hardware ROI is not supported
    if (CAM_A3000 == m_nDeviceID) {
        // use following window size to make sure the data size
        // transfered via USB will be 512*4's multiple
        m_distortionArgu.nCaptureHeight = A3000_WND_HEIGHT; //nMaxCaptureHeight;
        m_distortionArgu.nCaptureWidth = A3000_WND_WIDTH;   //nMaxCaptureWidth;
    }
    else {
        m_distortionArgu.nCaptureHeight = nMaxCaptureHeight;
        m_distortionArgu.nCaptureWidth = nMaxCaptureWidth;
    }

    // start point of capture window
    m_distortionArgu.nCaptureStartX =
        (nMaxCaptureWidth - m_distortionArgu.nCaptureWidth) / 2;
    m_distortionArgu.nCaptureStartY = 
        (nMaxCaptureHeight - m_distortionArgu.nCaptureHeight) / 2;

    return;
}
