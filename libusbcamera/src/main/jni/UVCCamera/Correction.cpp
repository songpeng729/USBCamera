#include <cstring>
#include "FingerSensorImpl.h"
#include <cmath>

bool CFingerSensor::CImplicit::
CorrectDistortion(unsigned char *pSrcData, unsigned char *pDstData)
// 	pSrcData 为1024*656图像 PDstData为矫正后的640*640图像
{
    m_distortionArgu.nCaptureHeight = 656;
    m_distortionArgu.nCaptureWidth = 1024;
    m_distortionArgu.nCorrectedHeight = 640;
    m_distortionArgu.nCorrectedWidth = 640;
    m_distortionArgu.ifNeedCorrection = true;
    m_distortionArgu.fColResRatio = 139.194f * 25.4f / 5.0f / 500.0f;
    m_distortionArgu.fRowResRatio = 98.4252f * 25.4f / 5.0f / 500.0f;

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
    unsigned char * pDst = pDstData;
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
        m_distortionArgu.nCorrectedWidth = A3000_WIDTH;   //   A3000_WIDTH  = 640,
        m_distortionArgu.nCorrectedHeight = A3000_HEIGHT; //   A3000_HEIGHT = 640,
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
   
    if (CAM_A3000 == m_nDeviceID) {
       
        m_distortionArgu.nCaptureHeight = A3000_WND_HEIGHT; //#define     A3000_WND_HEIGHT                    656     // 采集窗高度
        m_distortionArgu.nCaptureWidth = A3000_WND_WIDTH;   //#define     A3000_WND_WIDTH                     1024    // 采集窗宽度
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
