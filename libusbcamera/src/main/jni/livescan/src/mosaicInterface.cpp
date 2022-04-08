#define MOSAIC_API __declspec(dllexport)
#include <time.h>
#include <string.h>
#include "mosaicInterface.h"
#include "kernel/FPMosaic.h"
#include "sensor.h"

FPMosaic g_FPMosaic;
int g_IsDllInitialized = 0;
int g_IsMosaicFunInitialized = 0;
//#define MOSAIC_ERROR_NOTINITIAL -8
//1 初始化拼接动态库

int MOSAIC_Init()
{
	time_t now;   
	struct tm *timenow; 
  
	now = time(NULL);
	timenow = localtime(&now);
	int year = timenow->tm_year + 1900;
	int month = timenow->tm_mon ;
	
	if(year > 2020) // || (year == 2016 && month > 1))
	{
		return MOSAIC_ERROR_NOTAUTHORIZATION;
	}
	g_IsDllInitialized = 1;

	return 1;
}

//2 释放拼接动态库
int MOSAIC_Close()
{
	if(0 == g_IsDllInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	g_FPMosaic.Clear();
	g_IsDllInitialized = 0;
	return 1;
}

//3 拼接接口是否提供判断图像为指纹的函数
int MOSAIC_IsSupportIdentifyFinger()
{
	if(0 == g_IsDllInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	return 1;
}

//4 拼接接口是否提供判断图像质量的函数
int MOSAIC_IsSupportImageQuality()
{
	if(0 == g_IsDllInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	return 1;
}

//5 拼接接口是否提供判断指纹质量的函数
int MOSAIC_IsSupportFingerQuality()
{
	if(0 == g_IsDllInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	return 0;
}

//6 接口是否提供拼接指纹的图像增强功能
int MOSAIC_IsSupportImageEnhance()
{
	if(0 == g_IsDllInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	return 0;
}

//7 判断是否支持滚动采集函数
int MOSAIC_IsSupportRollCap()
{
	if(0 == g_IsDllInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	return 3;
}

//8 选择拼接方式的函数
int MOSAIC_SetRollMode(int nRollMode)
{
	if(0 == g_IsDllInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	if(0 != nRollMode && 1 != nRollMode && 2 != nRollMode && 3 != nRollMode)
	{
		return MOSAIC_ERROR_PARAMETER;
	}
	/*if(0 == nRollMode)
	{
	return MOSAIC_ERROR_NOTSPPORT_PLANCAPTURE;
	}*/
	int rlt = g_FPMosaic.SetRollMode(nRollMode);
	return rlt;
}

//9 初始化拼接过程
int MOSAIC_Start(unsigned char* pFingerBuf, int nWidth, int nHeight)
{
	if(0 == g_IsDllInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}

	bool bRlt = g_FPMosaic.Create(nWidth, nHeight);
	if(!bRlt)
	{
		return MOSAIC_ERROR_MEMORY;
	}
	int rlt = g_FPMosaic.StartMosaic(pFingerBuf, nWidth, nHeight);
	if(1 == rlt)
		g_IsMosaicFunInitialized = 1;
	return rlt;
}

//10 拼接过程
int MOSAIC_DoMosaic(unsigned char* pDataBuf, int nWidth, int nHeight)
{
	if(0 == g_IsDllInitialized || 0 == g_IsMosaicFunInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}

  //sensor_getWidth();
	int rlt = g_FPMosaic.Mosaicing(pDataBuf);
	return rlt;
}

//11 结束拼接
int MOSAIC_Stop()
{
	if(0 == g_IsDllInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	g_FPMosaic.Clear();
	g_IsMosaicFunInitialized = 0;
	return 1;
}

//12 判断图像质量
int MOSAIC_ImageQuality(unsigned char* pDataBuf, int nWidth, int nHeight)
{
	if(0 == g_IsDllInitialized || 0 == g_IsMosaicFunInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	int score = g_FPMosaic.AssessImageQuality(pDataBuf, nWidth, nHeight);
	return score;
}

//13 判断指纹质量
int MOSAIC_FingerQuality(unsigned char* pDataBuf, int nWidth, int nHeight)
{
	if(0 == g_IsDllInitialized || 0 == g_IsMosaicFunInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	return -3;//功能还未实现
}

//14 对图像进行增强
int MOSAIC_ImageEnhance(unsigned char* pSrcImg, int nWidth, int nHeight, unsigned char* pTargetImg)
{
	if(0 == g_IsDllInitialized || 0 == g_IsMosaicFunInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	return -3;//功能还未实现
}

//15 判断图像是否为指纹
int MOSAIC_IsFinger(unsigned char* pDataBuf, int nWidth, int nHeight)
{
	if(0 == g_IsDllInitialized)
	{
		return MOSAIC_ERROR_NOTINITIAL;
	}
	int rlt = g_FPMosaic.NullImgCheck(pDataBuf, nWidth, nHeight);
	return rlt;
}

//16 取得拼接接口错误信息
int MOSAIC_GetErrorInfo(int nErrorNo, char pszErrorInfo[256])
{
	int rlt = g_FPMosaic.FPM_GetErrorInfo(nErrorNo, pszErrorInfo);
	return rlt;
}

//17 取得接口的版本
int MOSAIC_GetVersion()
{
	return 100;
}

//18 获得拼接接口的说明
int MOSAIC_GetDesc(char pszDesc[1024])
{
	char pDesc[] = "活体指纹图像拼接动态库, 公安部GA/T 626.2-2010 标准";
	strncpy(pszDesc, pDesc, 1024);
	return 1;
}

int MOSAIC_GetRollMode()
{
	int r = g_FPMosaic.GetRollMode();
	return r;
}
