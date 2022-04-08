//#include "stdafx.h"
#include "FPMosaic.h"
#include <cmath>
#include <algorithm>
#include "ImageProcess.h"
using namespace std;
const int cstTRIDGEPOINT = 180;


//const int MOSAIC_ERROR_ROLLBACK = -3;
//const int MOSAIC_ERROR_BLURRED = -6;
//
//const int MOSAIC_ERROR_SUCCESS = 3;
//const int MOSAIC_ERROR_SIZE = 4;
//const int MOSAIC_ERROR_DRY = 5;
//const int MOSAIC_ERROR_WET = 6;
//const int MOSAIC_ERROR_CLARRITY = 7;
//
//const int MOSAIC_ERROR_LEFT_COREX = 8;
//const int MOSAIC_ERROR_RIGHT_COREX = 9;
//const int MOSAIC_ERROR_TOP_COREY = 10;
//const int MOSAIC_ERROR_BOTOOM_COREY = 11;
//const int MOSAIC_ERROR_NOCORE = 12;
//
//const int MOSAIC_ERROR_LEFT_MASSX = 13;
//const int MOSAIC_ERROR_RIGHT_MASSX = 14;
//const int MOSAIC_ERROR_TOP_MASSY = 15;
//const int MOSAIC_ERROR_BOTOOM_MASSY = 16;

const int cstMaxWidth = 2000;
const int cstMid = 128;
//-----合并矩阵-------------

double coff[cstMaxWidth * 2 + 1];
int InitCoff(const int p)
{
	int i;
	if((0 >= p) || (p >= cstMaxWidth))
		return -1;
	double k = 1.0 / p / 2;
	for(i = -p+1; i < p; ++ i)
	{
		coff[cstMaxWidth + i] =  1 - k * (i+p);		
	}
	for(i = p; i <= cstMaxWidth; ++ i)
	{
		coff[cstMaxWidth + i] = 0;
		coff[cstMaxWidth - i] = 1;
	}
	//memset(coff,0,(cstMaxWidth*2+1)*sizeof(double));
	return 0;
}

int FPMosaic::NullImgCheck(unsigned char* pImg, int width, int height)
{
	int i, j;
	int nSum, nTmp;
	const int nThreshold = 28;
	const int nQlt = 5000;

	nSum = 0;
	int pos;
	unsigned char cTemp;
	for (i = 1; i < height - 1; i ++) {
		for (j = 1; j < width - 1; j ++) {
			pos = i*width + j;
			cTemp = pImg[pos];
			nTmp = abs(cTemp - pImg[pos+1]);//分别和后面（同行下列）没有参与的像素点进行比较。如果任何一个大于阀值就搞定，计数器加一
			if (nTmp > nThreshold) {
				nSum ++; continue;
			}
			nTmp = abs(cTemp - pImg[pos+width]);//同列，上下行
			if (nTmp > nThreshold) {
				nSum ++;
				continue;
			}
			nTmp = abs(cTemp - pImg[pos+width+1]);//相邻列，相邻行(右下斜着)
			if (nTmp > nThreshold) {
				nSum ++; continue;
			}
			nTmp = abs(cTemp - pImg[pos+width -1]);//相邻列，相邻行（左下）
			if (nTmp > nThreshold) {
				nSum ++; continue;
			}
		}
		if (i > 100){
			if (nSum >= nQlt )
				return 1;
		}
	}

	// if the valid points is too few ,then return SGFP_RV_NULLIMAGE
	//按用户事先指定的质量要求。如果图像质量达不到，则报错
	if (nSum < nQlt )
		return 0;

	return 1;
}

//  根据灰度图像得到前景区域。
//  Ayong 20130515
int FPMosaic::FingerZone(unsigned char *pImg, int width, int height, unsigned char *pOut, int value)
{
	unsigned char* pTemp = new unsigned char[width * height];
	memset(pTemp,0,width*height);
	//memset(pOut,0,width*height);
	// 判断周围像素是否有白点来决定
	const int TRidgePoint = cstTRIDGEPOINT;
	const int blocksize = 8;
	memset(pOut,0,width*height);
	/*int neibor[4] = { 
		-(blocksize+1)*width - (blocksize+1), 
		-(blocksize+1)*width + (blocksize+1),
		 (blocksize+1)*width - (blocksize+1),
		 (blocksize+1)*width + (blocksize+1),
	};*/

	//提取原始图像的纹线部分。
	ImageProcess().Binary(width, height, pImg, TRidgePoint, pTemp, 0, 255, 0);
	
	
	//cTA.StartTime(0);
	//ImageProcess().Smooth( width, height, pTemp, pOut, 1, blocksize );
	//cTA.StopTime(0);
	//cTA.StartTime(1);
	ImageProcess().CountLocalInterestPoints( width, height, pTemp, pOut, 255, blocksize);
	//cTA.StopTime(1);


	ImageProcess().Binary( width, height, pOut, 20, pOut, value, 0);
	
	////考虑四周的四个点，如果达到三个，则视为指纹区域。
	//int T_blockwhite = 0;
	//for (int j = blocksize+1 ; j < height-blocksize - 1 ; j++ ){
	//	for (int i = blocksize+1; i < width - blocksize - 1; i++ ){
	//		int count = 0;
	//		int pos = j * width + i;
	//		for (int k = 0 ; k < 4; k++){
	//			if (pTemp[ pos + neibor[k] ] > T_blockwhite) count++;
	//		}
	//		if (count >= 1)
	//			pOut[pos] = value;
	//	}		
	//}

	////向内缩小2个像素。
	//ImageProcess().Smooth( width, height, pOut, pTemp, 1, 2 );
	//for (int j = blocksize+1 ; j < height-blocksize - 1 ; j++ ){
	//	for (int i = blocksize+1; i < width - blocksize - 1; i++ ){
	//		int count = 0;
	//		int pos = j * width + i;			
	//		if (pOut[pos] == value && pTemp[pos] < value)
	//			pOut[pos] = 0;
	//	}		
	//}

	delete[] pTemp;
	return 1;
}


FPMosaic::FPMosaic()
{
	m_nWidth =0;
	m_nHeight = 0;
	m_bNullImg = 0;
	m_nFrame = 0;
	m_fuzzyNum = 0;
	m_nfuzzyFrame = 0;
	m_nBackRollNum = 0;
	m_nBackRollFrame = 0;
	m_overlapSize = 0;
	m_overlapXpos = 0;
	m_additionSize = 0;
	m_additionXpos = 0;
	m_dir = 0;
	m_currFrameXpos = 0;
	m_preFrameXpos = 0;

	m_pRstImg = 0;
	m_pDiffImg = 0;
	m_pMosaicedImg = 0;
	m_pFingerZone = 0;
	m_pOverlap = 0;
	m_pFingerZoneNew = 0;
	m_pSuturePos = 0;
	m_pSutureWeight = 0;

	m_ignoreFrameNum = 4;
	m_RollMode = 1;
	InitCoff(20);
}
FPMosaic::~FPMosaic()
{
	Clear();
}

bool FPMosaic::Create(const int width, const int height)
{
	if(width == m_nWidth && height == m_nHeight)
		return true;
	Clear();
	m_pRstImg = 0;
	int size = width * height;
	m_pDiffImg = new unsigned char[size];
	if(0 == m_pDiffImg){
		Clear();
		return false;
	}
	m_pMosaicedImg = new unsigned char[size];
	if(0 == m_pMosaicedImg){
		Clear();
		return false;
	}
	m_pFingerZone = new unsigned char[size];
	if(0 == m_pFingerZone){
		Clear();
		return false;
	}
	m_pOverlap = new unsigned char [size];
	if(0 == m_pOverlap){
		Clear();
		return false;
	}
	m_pFingerZoneNew = new unsigned char [size];
	if(0 == m_pFingerZoneNew){
		Clear();
		return false;
	}
	m_pSuturePos = new int[height];
	if(0 == m_pSuturePos){
		Clear();
		return false;
	}
	m_pSutureWeight = new unsigned char [size];
	if ( 0 == m_pSutureWeight){
		Clear();
		return false;
	}
	bool bRlt = m_assesQuality.Create(width, height);
	if(!bRlt){
		Clear();
		return false;
	}

	m_maxImgQuality = 0;
	m_nWidth = width;
	m_nHeight = height;
	
	return true;
}
void FPMosaic::Clear(void)
{
	if(m_pDiffImg)
	{
		delete []m_pDiffImg;
		m_pDiffImg = 0;
	}
	if(m_pMosaicedImg)
	{
		delete []m_pMosaicedImg;
		m_pMosaicedImg = 0;
	}
	if(m_pFingerZone)
	{
		delete []m_pFingerZone;
		m_pFingerZone = 0;
	}

	if(m_pOverlap)
	{
		delete []m_pOverlap;
		m_pOverlap = 0;
	}

	if(m_pFingerZoneNew)
	{
		delete []m_pFingerZoneNew;
		m_pFingerZoneNew = 0;
	}
	if(m_pSuturePos)
	{
		delete []m_pSuturePos;
		m_pSuturePos = 0;
	}
	if(m_pSutureWeight)
	{
		delete []m_pSutureWeight;
		m_pSutureWeight = 0;
	}

	m_assesQuality.Clear();
	m_pRstImg = 0;
	m_nWidth =0;
	m_nHeight = 0;
	m_bNullImg = 0;
	m_nFrame = 0;
	m_fuzzyNum = 0;
	m_nfuzzyFrame = 0;
	m_nBackRollNum = 0;
	m_nBackRollFrame = 0;
	m_overlapSize = 0;
	m_overlapXpos = 0;
	m_additionSize = 0;
	m_additionXpos = 0;
	m_dir = 0;
	m_currFrameXpos = 0;
	m_preFrameXpos = 0;

	m_maxImgQuality = 0;
	
}
int FPMosaic::SetRollMode(int nRollMode)
{
	int oldMode = m_RollMode;
	m_RollMode = nRollMode;
	return oldMode;
}

int FPMosaic::GetRollMode()
{
	return m_RollMode;
}

int FPMosaic::GetMinDiffPos(const unsigned char *pDiffImg, const unsigned char *pOverlapImg, 
							const int width,const int startPos, const int endPos)
{
	int posOffSet = 3;
	int width_1 = width - 1;
	int minDiffPos = startPos;
	float minDiff  = 255;
	for(int i = startPos; i < endPos; ++ i)
	{
		int s = max(i - posOffSet, 0);
		int e = min(i + posOffSet, width_1);
		const unsigned char* pDiffImgTmp = pDiffImg + s;
		const unsigned char* pOverlapImgTmp = pOverlapImg + s;
		float aveDiff = 0;
		int count = 0;
		for(int j = s; j <= e; ++ j, ++ pDiffImgTmp, ++ pOverlapImgTmp)
		{
			if(3 == *pOverlapImgTmp)
			{
				aveDiff += *pDiffImgTmp;
				++ count;
			}
		}
		if(0 < count)
		{
			aveDiff = aveDiff / count;
			if(aveDiff < minDiff)
			{
				minDiffPos = i;
				minDiff = aveDiff;
			}
		}
	}
	return minDiffPos;
}

void FPMosaic::
GetSuturePos(const unsigned char *pDiffImg, const unsigned char *pOverlapImg, 
				const int width, const int height, int *pSuturePos)
{
	//const unsigned char* pDiffImgRow = pDiffImg;
	//const unsigned char* pOverlapImgRow = pOverlapImg;
	//int* pSuturePosTmp = pSuturePos;
	//for(int i = 0; i < height; ++ i, pDiffImgRow += width, pOverlapImgRow += width, ++ pSuturePosTmp)
	//{
	//	int midPos = 0;
	//	int countOverNum = 0;
	//	for(int j = 0; j < width; ++ j)
	//	{
	//		if(3 == pOverlapImgRow[j])
	//		{
	//			midPos += j;
	//			++ countOverNum;
	//		}
	//	}
	//	if(0 == countOverNum){
	//		continue;
	//	}
	//	midPos = (float)midPos / countOverNum + 0.5;//gg
	//	*pSuturePosTmp = midPos;
	//	/*int posOff = 3;
	//	int startPos = max(midPos - posOff, 0);
	//	int endPos = min( midPos + posOff, width - 1);
	//	*pSuturePosTmp = GetMinDiffPos(pDiffImgRow, pOverlapImgRow, width, startPos, endPos);*/
	//}

	//---ayong 2010603---
	double *disImg1,*disImg2;
	disImg1 = new double [width * height];
	disImg2 = new double [width * height];
	ImageProcess().DistanceTransform( (unsigned char*)((void*)pOverlapImg), width, height, 1, disImg1, 1);
	ImageProcess().DistanceTransform( (unsigned char*)((void*)pOverlapImg), width, height, 2, disImg2, 1);
	//ImageProcess().MinDisImage_MinChessDistance( (unsigned char*)((void*)pOverlapImg), width, height, 1, disImg1);
	//ImageProcess().MinDisImage_MinChessDistance( (unsigned char*)((void*)pOverlapImg), width, height, 2, disImg2);
	for (int i = 0 ; i < width*height; i++) {
		this->m_pSutureWeight[i] = (unsigned char)(int)max(0.0,min((double)cstMid + (disImg1[i] - disImg2[i]),255.0));
	}
	delete[] disImg1;
	delete[] disImg2;


	//const unsigned char* pDiffImgRow = pDiffImg;
	//const unsigned char* pOverlapImgRow = pOverlapImg;
	//int* pSuturePosTmp = pSuturePos;
	//for(int i = 0; i < height; ++ i, pDiffImgRow += width, pOverlapImgRow += width, ++ pSuturePosTmp)
	//{
	//	int midPos = 0;
	//	int countOverNum = 0;
	//	for(int j = 0; j < width; ++ j)
	//	{
	//		if(3 == pOverlapImgRow[j])
	//		{
	//			midPos += j;
	//			++ countOverNum;
	//		}
	//	}
	//	if(0 == countOverNum){
	//		continue;
	//	}
	//	midPos = (float)midPos / countOverNum + 0.5;//gg
	//	*pSuturePosTmp = midPos;
	//	/*int posOff = 3;
	//	int startPos = max(midPos - posOff, 0);
	//	int endPos = min( midPos + posOff, width - 1);
	//	*pSuturePosTmp = GetMinDiffPos(pDiffImgRow, pOverlapImgRow, width, startPos, endPos);*/
	//}
}
//double g_overlapAveDiff;
//int g_overlapSize;
void FPMosaic::GetMosaicedImg(const unsigned char *pImg1, const unsigned char *pImg2, const int width, const int height, unsigned char *pImgOut)
{
	//const unsigned char* pImgL = pImg1;
	//const unsigned char* pImgR = pImg2;
	//if (m_dir > 0){
	//	pImgL = pImg2;
	//	pImgR = pImg1;
	//}
	//const int* pSuturePos = m_pSuturePos;
	//const unsigned char* pImgLRow = pImgL;
	//const unsigned char* pImgRRow = pImgR;
	//const unsigned char* pOverlapImgRow = m_pOverlap;
	//unsigned char* pOutImgRow = pImgOut;
	////g_overlapAveDiff = 0;
	////g_overlapSize = 0;
	//for(int i = 0; i < height; ++ i, ++ pSuturePos, pImgLRow += width,
	//	pImgRRow += width, pOverlapImgRow += width, pOutImgRow += width)
	//{
	//	const unsigned char* pOverlapImgCurr = pOverlapImgRow;
	//	unsigned char* pOutImgCurr = pOutImgRow;
	//	for(int j = 0; j < width; ++ j, ++ pOverlapImgCurr, ++ pOutImgCurr)
	//	{
	//		if (1 == *pOverlapImgCurr){
	//			if(m_dir > 0)
	//				*pOutImgCurr = pImgRRow[j];
	//			else
	//				*pOutImgCurr = pImgLRow[j];
	//		}
	//		else if(2 == *pOverlapImgCurr){
	//			if(m_dir > 0)
	//				*pOutImgCurr = pImgLRow[j];
	//			else
	//				*pOutImgCurr = pImgRRow[j];
	//		}
	//		else if(3 == *pOverlapImgCurr){
	//			//int SuturePos = *pSuturePos;
	//			//int z = j - SuturePos;
	//			//int p = 20;
	//			//z = min( p, z);
	//			//z = max(-p, z);
	//			//double w = 1 - (z + p) / 2.0/p;
	//			////g_overlapAveDiff += abs(pImgLRow[j] - pImgRRow[j]);
	//			////++ g_overlapSize;
	//			//*pOutImgCurr = (unsigned char)(int)(w * pImgLRow[j] + (1-w) * pImgRRow[j]);
	//			
	//			int SuturePos = *pSuturePos;
	//			int z = j - SuturePos + cstMaxWidth;
	//			*pOutImgCurr = (unsigned char)(int)(pImgRRow[j] + coff[z] * (pImgLRow[j] - pImgRRow[j]));
	//		}
	//	}
	//}
	//int total = width * height;
	//for ( int i = 0 ; i < total; i++ ){
	//	if ( m_pOverlap[i] != 0)	continue;
	//	pImgOut[i] = (pImg1[i] > pImg2[i]) ? pImg2[i] : pImg1[i];
	//}

	const unsigned char* pImgL = pImg1;
	const unsigned char* pImgR = pImg2;
	
	const unsigned char* pImgLRow = pImgL;
	const unsigned char* pImgRRow = pImgR;
	const unsigned char* pOverlapImgRow = m_pOverlap;
	unsigned char* pOutImgRow = pImgOut;
	//g_overlapAveDiff = 0;
	//g_overlapSize = 0;
	for(int i = 0; i < height; ++ i,  pImgLRow += width,
		pImgRRow += width, pOverlapImgRow += width, pOutImgRow += width)
	{
		const unsigned char* pOverlapImgCurr = pOverlapImgRow;
		unsigned char* pOutImgCurr = pOutImgRow;
		for(int j = 0; j < width; ++ j, ++ pOverlapImgCurr, ++ pOutImgCurr)
		{
			if (1 == *pOverlapImgCurr){
					*pOutImgCurr = pImgLRow[j];				
			}
			else if(2 == *pOverlapImgCurr){
					*pOutImgCurr = pImgRRow[j];
			}
			else if(3 == *pOverlapImgCurr){				
				int z = this->m_pSutureWeight[i*width+j] + cstMaxWidth - cstMid;
				*pOutImgCurr = (unsigned char)(int)(pImgRRow[j] + coff[z] * (pImgLRow[j] - pImgRRow[j]));
			}
		}
	}
	int total = width * height;
	for ( int i = 0 ; i < total; i++ ){
		if ( m_pOverlap[i] != 0)	continue;
		pImgOut[i] = (pImg1[i] > pImg2[i]) ? pImg2[i] : pImg1[i];
	}
}
int FPMosaic::mosaic2frame(unsigned char *pImg1, unsigned char *pImg2, int width, int height, unsigned char *pImgOut)
{
	if(0 == m_nFrame)
	{
		//m_Timer.Reset();
		//m_Timer.Start();
		gettimeofday(&m_startTime, NULL);

	}
	
	if(m_ignoreFrameNum == m_nFrame)
	{
		//modified by wumin 20151018
		//m_Timer.Stop();
		//m_frameRatio = m_nFrame*1000/m_Timer.Msec();
		gettimeofday(&m_endTime, NULL);
		double time  = 1000000*(m_endTime.tv_sec - m_startTime.tv_sec) + (m_endTime.tv_usec - m_startTime.tv_usec);
		m_frameRatio = m_nFrame*1000/time;
		//end by wumin 20151018


		/*CString str;
		str.Format(_T("帧率是 %f 帧每秒\n"), m_frameRatio);
		OutputDebugString(str);*/
	}
	++ m_nFrame;
	FingerZone(pImg2, width, height, m_pFingerZoneNew, 2);
	GetOverlapInfo(m_pFingerZoneNew, width, height, m_pFingerZone, m_overlapSize,
					m_overlapXpos, m_additionSize, m_additionXpos);
	m_dir = m_overlapXpos - m_additionXpos;
	GetDiffImg(pImg1, pImg2, width, height, m_pOverlap, m_overlapSize, m_pDiffImg);
	GetSuturePos(m_pDiffImg, m_pOverlap, width, height, m_pSuturePos);
	GetMosaicedImg(pImg1, pImg2, width, height, pImgOut);

	GetOverlapMeanVar(pImg1, m_pOverlap, width, height, m_overlapMean, m_overlapVar);
	
	int rlt = 1;
	if(1 == m_RollMode)
	{
		bool bIsBackRoll = IsBackRoll(m_overlapSize, m_overlapXpos, m_additionSize, m_additionXpos);
		if(bIsBackRoll)
		{
			//TRACE(_T("出现回滚情况或者滚动太慢\n"));
			rlt = MOSAIC_ERROR_ROLLBACK;
		}
	}
	
	if(0 != m_RollMode/*1 == m_RollMode || 2 == m_RollMode*/)
	{
    /*
		bool bIsTrans = IsTrans(m_pDiffImg, m_pOverlap, width, height, m_overlapSize);
		if(bIsTrans)
		{
			//TRACE("由于手指拖动或者用力不均造成模糊！\n");
			rlt = MOSAIC_ERROR_BLURRED;
		}
    */
	}
	
	return rlt;
}

void FPMosaic::
GetDiffImg(const unsigned char *pImg1, const unsigned char *pImg2, const int width, const int height, 
			const unsigned char *pOverlap, const int overlapSize, unsigned char *pDiffImg)
{
	const unsigned char* pImg1Row = pImg1;
	const unsigned char* pImg2Row = pImg2;
	const unsigned char* pOverlapRow = pOverlap;
	unsigned char* pDiffImgRow = pDiffImg;
	if(0 < overlapSize)
	{
		for(int i = 0; i < height; ++ i, pImg1Row += width, pImg2Row += width,
			pOverlapRow += width, pDiffImgRow += width)
		{
			for(int j = 0; j < width; ++ j)
			{
				if(3 == pOverlapRow[j])
				{
					pDiffImgRow[j] = abs(pImg1Row[j] - pImg2Row[j]);
				}
			}
		}
	}
}

void FPMosaic::
GetOverlapInfo(const unsigned char *pFptZoneNew, const int width, const int height, unsigned char *pFptZone, 
		   int &overlapSize, double &overlapXpos, int &additionSize, double &additionXpos)
{
	additionSize = 0;
	additionXpos = 0;
	overlapSize = 0;
	overlapXpos = 0;
	const unsigned char* pFptZoneNewRow = pFptZoneNew;
	unsigned char* pFptZoneRow = pFptZone;
	unsigned char* pOverlapRow = m_pOverlap;

	m_currFrameXpos = 0;
	int currFrameSize = 0;
	for(int i = 0; i < height; ++ i, pFptZoneRow += width,
		pOverlapRow += width, pFptZoneNewRow += width)
	{
		for(int j = 0; j < width; ++ j)
		{
			pOverlapRow[j] = pFptZoneRow[j] + pFptZoneNewRow[j];
			if(2 == pOverlapRow[j]){
				pFptZoneRow[j] = 1;
				additionXpos += j;
				++ additionSize;
				m_currFrameXpos += j;
				++ currFrameSize;
			}
			else if(3 == pOverlapRow[j]){
				overlapXpos += j;
				++ overlapSize;
				m_currFrameXpos += j;
				++ currFrameSize;
			}
		}
	}

	if(0 < overlapSize)
	{
		overlapXpos = overlapXpos / overlapSize;
	}

	if(0 < additionSize)
	{
		additionXpos = additionXpos / additionSize;
	}
	if(0 < currFrameSize)
	{
		m_currFrameXpos = m_currFrameXpos / currFrameSize;
	}
}
void FPMosaic::
GetOverlapMeanVar(const unsigned char* pImg, const unsigned char *pOverlap, const int width, const int height, double &mean, double &var)
{
	const unsigned char* pImgRow = pImg;
	const unsigned char* pOverlapRow = pOverlap;
	mean = 0;
	for(int i = 0; i < height; ++ i, pImgRow += width, pOverlapRow += width)
	{
		for(int j = 0; j < width; ++ j)
		{
			if(3 == pOverlapRow[j])
			{
				mean += pImgRow[j];
			}
		}
	}
	mean = mean / m_overlapSize;
}
bool FPMosaic::IsBackRoll(const int overlapSize, const double overlapXpos, const int additionNum, const double additionXpos)
{
	bool bIsBackRoll = false;
	double preCurrPosDiff = fabs(m_currFrameXpos - m_preFrameXpos);
	//CString str;
	

	m_preFrameXpos = m_currFrameXpos;
	if(m_ignoreFrameNum < m_nFrame)
	{
		if(30 > fabs(m_dir) && 1 < preCurrPosDiff)
		{
			if(m_nFrame - m_nBackRollFrame > 6){
				m_nBackRollNum = 1;
			}
			else{
				++ m_nBackRollNum;
			}
			m_nBackRollFrame = m_nFrame;
		}
		double ratio = (double)additionNum / (overlapSize);
		//str.Format(_T("相邻两帧位置差异 %f, 质心差异 %f, 比例 %f\n"), preCurrPosDiff, m_dir, ratio);
		//OutputDebugString(str);
		
		if(0 != m_nFastBackRollFrame && 2 <= m_nFrame - m_nFastBackRollFrame)
		{
			return true;
		}

		if((4 <= m_nBackRollNum && 0.05 > ratio) ||6 <= m_nBackRollNum)
		{
			bIsBackRoll = true;
			m_nBackRollNum = 0;
		}
		if((0.005 > ratio && 5 < preCurrPosDiff)  && 0 == m_nFastBackRollFrame)
		{
			m_nFastBackRollFrame = m_nFrame;
			/*str.Format(_T("出现了快速回滚的情况\n"));
			OutputDebugString(str);*/
		}
	}
	return bIsBackRoll;
}
bool FPMosaic::IsTrans(const unsigned char *pDiffImg, const unsigned char *pOverlap, const int width, 
						const int height, const int overlapSize)
{
	
	const unsigned char* pDiffImgRow = pDiffImg;
	const unsigned char* pOverlapRow = pOverlap;
	double overlapAveDiff = 0;
	if(0 < overlapSize)
	{
		for(int i = 0; i < height; ++ i, pDiffImgRow += width, pOverlapRow += width)
		{
			for(int j = 0; j < width; ++ j)
			{
				if(3 == pOverlapRow[j])
				{
					overlapAveDiff += (int)(pDiffImgRow[j]);
				}
			}
		}
		overlapAveDiff = overlapAveDiff/overlapSize;
	}
	double addTh = 0;
	if(m_ignoreFrameNum < m_nFrame && 180 > m_overlapMean)
	{

		addTh = (180 - m_overlapMean)/10;
		addTh *= 2;
	}
	//CString str;
	if(m_ignoreFrameNum + 1 == m_nFrame)
	{
		if(0 < m_frameRatio)
		{
			double base = 8;//base 越小 coff越大
			if(m_frameRatio < 8)
			{
				//根据帧率调整误差阈值
				double coff = log(8 / m_frameRatio) / log(base) + 1;
				m_overlapAveDiffTh = m_overlapAveDiffTh * coff;
			}
			
		}
		m_transFramTh = m_transFramTh * log10(m_frameRatio * 10 / 9) + 0.5;
		m_fastOverlapAveDiffTh = 42;
		if(m_frameRatio < 7.8)
		{
			//根据帧率调整快速平移阈值
			m_fastOverlapAveDiffTh += (7.8 - m_frameRatio)*3.3;
		}
		
		/*str.Format("原始误差阈值为 %f\n 平移帧数阈值为 %d\n 快速平移原始误差阈值为 %f\n",
			m_overlapAveDiffTh, m_transFramTh, m_fastOverlapAveDiffTh);
		OutputDebugString(str);*/
	}

	/*str.Format(_T("重合区域误差 %f, 误差阈值为 %f, 重合区域均值 %f, 均值调整的阈值增量 %f, 快速平移误差阈值为 %f\n"),
		overlapAveDiff, m_overlapAveDiffTh + addTh, m_overlapMean, addTh, m_fastOverlapAveDiffTh + addTh);
	OutputDebugString(str);*/

	if(0 != m_nfastTransedFrame && 3 <= m_nFrame - m_nfastTransedFrame)
	{
		return true;
	}
	bool bIsTrans = false;
	if(m_ignoreFrameNum < m_nFrame)
	{
		if(m_overlapAveDiffTh + addTh < overlapAveDiff)
		{
			if(m_nFrame - m_nfuzzyFrame > 6){
				m_fuzzyNum = 1;
			}
			else{
				++ m_fuzzyNum;
			}
			m_nfuzzyFrame = m_nFrame;
		}
		if(m_transFramTh <= m_fuzzyNum)
		{
			bIsTrans = true;
		}
		if(m_fastOverlapAveDiffTh + 1.0*addTh < overlapAveDiff && 0 == m_nfastTransedFrame)//release 40, debug 50
		{
			m_nfastTransedFrame = m_nFrame;
		}
	}
	return bIsTrans;
}



int FPMosaic::StartMosaic(unsigned char *pResultImg, int width, int height){
	m_bNullImg = 1;
	m_nFrame = 0;
	m_fuzzyNum = 0;
	m_nfuzzyFrame = 0;
	m_nBackRollNum = 0;
	m_nBackRollFrame = 0;
	m_currFrameXpos = 0;
	m_preFrameXpos = 0;
	m_overlapAveDiffTh = 30;
	m_transFramTh = 6;//6
	m_meanOverlapAveDiff = 0;
	//m_isPreFastTransed = false;
	m_nfastTransedFrame = 0;
	m_nFastBackRollFrame = 0;
	this->m_pRstImg = pResultImg;
	this->m_nHeight = height;
	this->m_nWidth = width;
	memset(this->m_pRstImg, 255, width*height);
	memset(m_pMosaicedImg, 255, width*height);
	memset(m_pFingerZone, 0, width*height);
	return 1;
}

int FPMosaic::Mosaicing(unsigned char *pNewImg){
	if (!NullImgCheck(pNewImg, m_nWidth,m_nHeight)){
		return 0;
	}

	//add by wumin 20151130
	if(0 == m_RollMode)
	{
		FingerZone(pNewImg, m_nWidth,m_nHeight, m_pFingerZone, forGroundFlag);
		int score = m_assesQuality.getQualityScore(pNewImg, m_pFingerZone, m_nWidth,m_nHeight);
		if(score > m_maxImgQuality)
		{
			memcpy( m_pRstImg, pNewImg,this->m_nWidth * this->m_nHeight);
			m_maxImgQuality = score;
		}
		return 1;
	}
	//end by wumin 20151130
	
	int rlt = mosaic2frame( m_pMosaicedImg, pNewImg, this->m_nWidth, this->m_nHeight, m_pMosaicedImg);
	memcpy( m_pRstImg, m_pMosaicedImg,this->m_nWidth * this->m_nHeight);
	
	return rlt;
}

int FPMosaic::FPM_GetErrorInfo(int nErrorNo, char pszErrorInfo[256]) {
	char pQualityError[30][50] = 
	{
		"系统保留", "参数错误,", "内存分配失败", "功能未实现", "保留", "保留",
		"非法的错误号", "没有授权", "拼接未初始化", "指纹回滚", "手指拖动造成模糊", 
		"前景太小", "指纹太干", "指纹太湿", "纹线不清晰", 
		"core点偏左", "core点偏右", "core点偏上", "core点偏下", "没有core点",
		"按压位置偏左", "按压位置偏右", "按压位置偏上", "按压位置偏下", "不支持平面采集"
	};
	int nFPMerrorLength = 256;
	int errorNo = -nErrorNo;
	if(1 <= errorNo && 116 >= errorNo)
	{
		if(9 <= errorNo && 100 >= errorNo)
		{
			errorNo = 0;
		}
		if(100 < errorNo)
		{
			errorNo -= 92;
		}
		strncpy(pszErrorInfo, pQualityError[errorNo], nFPMerrorLength);
		return 1;
	}
	errorNo = 6;
	strncpy(pszErrorInfo, pQualityError[nErrorNo], nFPMerrorLength);

	return MOSAIC_ERROR_ILLEGAL;
}

int FPMosaic::
AssessImageQuality(unsigned char* pDataBuf, int nWidth, int nHeight)
{

	//add by wumin 20170213
	if (!NullImgCheck(pDataBuf, nWidth, nHeight)){
		m_qualityFlag = 0;
		m_qualityScore = 0;
		return m_qualityScore;
	}
	//end by wumin 20170213

	m_qualityFlag = 0;
	FingerZone(pDataBuf, m_nWidth,m_nHeight, m_pFingerZone, forGroundFlag);//add by wumin 20151204
	int score = m_assesQuality.getQualityScore(pDataBuf, m_pFingerZone, nWidth, nHeight);
	//add by wumin 20170213
	m_qualityScore = score;
	return score;
	//end by wumin 20170213
	if(1 == m_assesQuality.m_sizeFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_SIZE;
	}
	else if(1 == m_assesQuality.m_massxFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_LEFT_MASSX;
	}
	else if(2 == m_assesQuality.m_massxFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_RIGHT_MASSX;
	}
	else if(1 == m_assesQuality.m_massyFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_TOP_MASSY;
	}
	else if(2 == m_assesQuality.m_massyFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_BOTOOM_MASSY;
	}
	else if(1 ==  m_assesQuality.m_dyrwetFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_DRY;
	}
	else if(2 ==  m_assesQuality.m_dyrwetFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_WET;
	}
	else if(1 == m_assesQuality.m_clarityFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_CLARRITY;
	}
	else if(3 == m_assesQuality.m_corexFlag && 3 == m_assesQuality.m_coreyFlag)
	{
		m_qualityFlag = MOSAIC_ERROR_NOCORE;
	}
	else if(1 == m_assesQuality.m_corexFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_LEFT_COREX;
	}
	else if(2 == m_assesQuality.m_corexFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_RIGHT_COREX;
	}
	else if(1 == m_assesQuality.m_coreyFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_TOP_COREY;
	}
	else if(2 == m_assesQuality.m_coreyFlag)
	{
		m_qualityFlag =  MOSAIC_ERROR_BOTOOM_COREY;
	}
	m_qualityScore = score;
	if(0 > m_qualityFlag)
	{
		return m_qualityFlag;
	}
	return score;
}
