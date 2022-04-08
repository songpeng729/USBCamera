#pragma once

#ifndef _XV_TRAPPING_INSPECTION_TOOL_H_
#define _XV_TRAPPING_INSPECTION_TOOL_H_


#ifdef __cplusplus
extern "C" {
#endif

//1 初始化拼接动态库
int MOSAIC_Init();

//2 释放拼接动态库
int MOSAIC_Close();

//3 拼接接口是否提供判断图像为指纹的函数
int MOSAIC_IsSupportIdentifyFinger();

//4 拼接接口是否提供判断图像质量的函数
int MOSAIC_IsSupportImageQuality();

//5 拼接接口是否提供判断指纹质量的函数
int MOSAIC_IsSupportFingerQuality();

//6 接口是否提供拼接指纹的图像增强功能
int MOSAIC_IsSupportImageEnhance();

//7 判断是否支持滚动采集函数
int MOSAIC_IsSupportRollCap();

//8 选择拼接方式的函数
int MOSAIC_SetRollMode(int nRollMode);

//9 初始化拼接过程
int MOSAIC_Start(unsigned char* pFingerBuf, int nWidth, int nHeight);

//10 拼接过程
int MOSAIC_DoMosaic(unsigned char* pDataBuf, int nWidth, int nHeight);

//11 结束拼接
int MOSAIC_Stop();

//12 判断图像质量
int MOSAIC_ImageQuality(unsigned char* pDataBuf, int nWidlh, int nHeight);

//13 判断指纹质量
int MOSAIC_FingerQuality(unsigned char* pDataBuf, int nWidth, int nHeight);

//14 对图像进行增强
int MOSAIC_ImageEnhance(unsigned char* pSrcImg, int nWidth, int nHeight, unsigned char* pTargetImg);

//15 判断图像是否为指纹
int MOSAIC_IsFinger(unsigned char* pDataBuf, int nWidth, int nHeight);

//16 取得拼接接口错误信息
int MOSAIC_GetErrorInfo(int nErrorNo, char pszErrorInfo[256]);

//17 取得接口的版本
int MOSAIC_GetVersion();

//18 获得拼接接口的说明
int MOSAIC_GetDesc(char pszDesc[1024]);

int MOSAIC_GetRollMode();
#ifdef __cplusplus
}
#endif

#endif