#pragma once
#ifndef _FPT_QUALITY_ASSESSMENT_H_
#define _FPT_QUALITY_ASSESSMENT_H_

#define PI 3.1415926
#define ThresholdSegCoh 30//方向一致性分割阈值
#define Thresholdclarity          0.55//清晰度阈值0.6
#define Thresholdclarity2          0.40//清晰度阈值0.5
////////////////////////////////////
#define Wet      76 //湿阈值80
#define Dry      230 //干阈值

#define Smean1    120         //判断好均值起始值
#define Emean1    220         //判断好均值结束值0
#define Var1      25         //判断好方差值
#define K1        3*PI/4       //斜率1
#define K2        PI/4        //斜率2
#define Add1      0           //

#define Smean2    120 //判断坏均值起始值
#define Emean2    220 //判断坏均值结束值
#define Var2      10 //判断坏方差值
#define K3        3*PI/4       //斜率3
#define K4        PI/4        //斜率4
#define Add2      0           //

#define forGroundFlag 1

#define ForegroundRatio 0.375//前景块比例阈值
#define ThresholdMassX   0.25       //横向前景中心位置阈值
#define ThresholdMassY   0.25       //垂直前景中心位置阈值
#define Thresholdclarity_ratio   0.5//清晰块占前景比例阈值0.5
#define ThresholdcoreX   0.2       //横向core点位置阈值
#define ThresholdcoreY   0.2       //垂直core点位置阈值
#define ThresholdCore   0.45         //core 点位置分数阈值

typedef unsigned char BYTE;

class FptQualityAssessment
{
public:
	FptQualityAssessment(void);
	~FptQualityAssessment(void);
	bool Create(const int width, const int height);
	void Clear(void);
public:    
	int getQualityScore(const unsigned char* pFPImg, const unsigned char* pSegFlag, 
							const int width, const int height);
private:
	void getGrade(const BYTE *pImg, const int width, const int height, double *pGradx, double *pGrady);
	double getBlockCoh(const double* pGradx, const double* pGrady, const int blockSize, const int gradWidth);
	double getSegmentScore(const unsigned char* pSegFlag, const int width, const int height);
	double getWetDryScore(const BYTE *pImg,  const BYTE *SegFlag, int width, int height);
	void getBlockClarityValue(const double* pGradx, const double* pGrady, 
			const unsigned char* pSegFlag, const int blockSize, double &backCoh, double &foreCoh);
	double getClarityScore(const BYTE *pImg, const int width, const int height, const BYTE *SegFlag, 
			const double *pGradx, const double *pGrady);
	int getQuasiCorePosition(const BYTE* pImg, const BYTE *SegFlag, const int width, const int height, int &posX, int &posY);
	double getPositionScore(const BYTE* pImg, const BYTE *SegFlag, const int width, const int height);
	void InitWeight();
	//------add by ayong, singular extraction algorithm by WHF ---------------
	int getSglPosition(const unsigned char *pRaw, int width, int height, int &corex, int &corey);	
private:
	int m_width;
	int m_height;
	int m_gradWidth;
	int m_gradHeight;
	double* m_pGradx;
	double* m_pGrady;
	//unsigned char* m_pSegFlag;
	double m_QualityScore;
	double m_WClarity;
	double m_WCore;
	double m_WDrywet;
	double m_WRatio;
	double m_WBack;
	double m_massx;
	double m_massy;
	int m_nBlock;
public:
	unsigned char m_sizeFlag; //前景大小指标，0:正常，1:太小
	unsigned char m_massxFlag; //按压水平位置指标，0:正常，1:太偏左，2:太偏右
	unsigned char m_massyFlag; //按压竖直位置指标，0:正常，1:太偏上，2:太偏下
	unsigned char m_dyrwetFlag; //干湿度指标，0:正常，1:太干，2:太湿
	unsigned char m_clarityFlag; //纹线清晰度指标，0:正常，1:纹线不清晰
	unsigned char m_corexFlag; //core点水平位置指标，0:正常，1:太偏左，2:太偏右
	unsigned char m_coreyFlag; //core点竖直位置指标，0:正常，1:太偏上，2:太偏下
	
};

#endif