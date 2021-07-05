//const int cstFPMBufferSize = 1024*1024;
#pragma once
#ifndef _FPMOSAIC_H_
#define _FPMOSAIC_H_

#include<sys/time.h>
#include<sys/stat.h>
#include "FptQualityAssessment.h"


#ifndef max
#define max(a,b)            (((a) > (b)) ? (a) : (b))
#endif

#ifndef min
#define min(a,b)            (((a) < (b)) ? (a) : (b))
#endif

enum { 
	MOSAIC_ERROR_PARAMETER = -1,
	MOSAIC_ERROR_MEMORY = -2,
	MOSAIC_ERROR_NOTREALIZE = -3,
	MOSAIC_ERROR_ILLEGAL = -6,
	MOSAIC_ERROR_NOTAUTHORIZATION = -7,
	MOSAIC_ERROR_NOTINITIAL = -8,

	MOSAIC_ERROR_ROLLBACK = -101,
	MOSAIC_ERROR_BLURRED = -102,

	MOSAIC_ERROR_SIZE = -103,
	MOSAIC_ERROR_DRY = -104,
	MOSAIC_ERROR_WET = -105,
	MOSAIC_ERROR_CLARRITY = -106,

	MOSAIC_ERROR_LEFT_COREX = -107,
	MOSAIC_ERROR_RIGHT_COREX = -108,
	MOSAIC_ERROR_TOP_COREY = -109,
	MOSAIC_ERROR_BOTOOM_COREY = -110,
	MOSAIC_ERROR_NOCORE = -111,

	MOSAIC_ERROR_LEFT_MASSX = -112,
	MOSAIC_ERROR_RIGHT_MASSX = -113,
	MOSAIC_ERROR_TOP_MASSY = -114,
	MOSAIC_ERROR_BOTOOM_MASSY = -115,

	MOSAIC_ERROR_NOTSPPORT_PLANCAPTURE = -116
};

class FPMosaic
{
	
public:
	FPMosaic();
	~FPMosaic();
	bool Create(const int width, const int height);
	void Clear(void);
	int NullImgCheck(unsigned char* pImg, int width, int height);
	int SetRollMode(int nRollMode);
	int GetRollMode();
	int StartMosaic(unsigned char *pResultImg, int width, int height);
	int Mosaicing(unsigned char *pNewImg);
	int StopMosaic(){return 1;};
	
	int FPM_GetErrorInfo(int nErrorNo, char pszErrorInfo[256]);
	int AssessImageQuality(unsigned char* pDataBuf, int nWidth, int nHeight);
private:
	unsigned char* m_pMosaicedImg;
	unsigned char *m_pRstImg;
	int *m_pSuturePos;
	unsigned char *m_pSutureWeight;
	unsigned char *m_pDiffImg;
	unsigned char* m_pFingerZone;
	unsigned char* m_pOverlap;
	unsigned char* m_pFingerZoneNew;
private:
	
	unsigned char m_bNullImg;
	int m_nWidth;
	int m_nHeight;

	int m_nFrame;

	int m_fuzzyNum;
	int m_nfuzzyFrame;

	int m_nBackRollNum;
	int m_nBackRollFrame;
	
	int m_overlapSize;
	double m_overlapXpos;
	int m_additionSize;
	double m_additionXpos;
	double m_dir;
	double m_currFrameXpos;
	double m_preFrameXpos;
	//modified by wumin 20151018
	//xvTimer m_Timer;
	struct timeval m_startTime;
	struct timeval m_endTime;
	//end by wumin 20151018

	double m_frameRatio;
	double m_overlapAveDiffTh;
	double m_fastOverlapAveDiffTh;
	double m_meanOverlapAveDiff;
	int m_transFramTh;

	int m_ignoreFrameNum;
	double m_overlapVar;
	double m_overlapMean;
	
	int m_nfastTransedFrame;
	FptQualityAssessment m_assesQuality;
	int m_qualityFlag;
	int m_qualityScore;

	int m_nFastBackRollFrame;
	int m_RollMode;
	int m_maxImgQuality;

private:
	
	int FingerZone(unsigned char *pImg, int width, int height, unsigned char *pOut, int value);
	int mosaic2frame(unsigned char *pImg1, unsigned char *pImg2, int width, int height, unsigned char *pImgOut);
	void GetMosaicedImg(const unsigned char *pImg1, const unsigned char *pImg2, const int width, const int height, unsigned char *pImgOut);
	void GetDiffImg(const unsigned char *pImg1, const unsigned char *pImg2, const int width, const int height, 
			const unsigned char *pOverlap, const int overlapSize, unsigned char *pDiffImg);
	void GetSuturePos(const unsigned char *pDiffImg, const unsigned char *pOverlapImg, 
						const int width, const int height, int *pSuturePos);
	int GetMinDiffPos(const unsigned char *pDiffImg, const unsigned char *pOverlapImg, 
		const int width,const int startPos, const int endPos);

	void GetOverlapInfo(const unsigned char *pFptZoneNew, const int width, const int height, unsigned char *pFptZone, 
		int &overlapSize, double &overlapXpos, int &additionSize, double &additionXpos);
	bool IsTrans(const unsigned char *pDiffImg, const unsigned char *pOverlap, 
					const int width, const int height, const int overlapSize);
	bool IsBackRoll(const int overlapSize, const double overlapXpos, const int additionNum, const double additionXpos);
	void GetOverlapMeanVar(const unsigned char* pImg, const unsigned char *pOverlap, 
							const int width, const int height, double &mean, double &var);
	
};

#endif