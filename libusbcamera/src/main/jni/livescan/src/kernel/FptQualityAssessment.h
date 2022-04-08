#pragma once
#ifndef _FPT_QUALITY_ASSESSMENT_H_
#define _FPT_QUALITY_ASSESSMENT_H_

#define PI 3.1415926
#define ThresholdSegCoh 30//����һ���Էָ���ֵ
#define Thresholdclarity          0.55//��������ֵ0.6
#define Thresholdclarity2          0.40//��������ֵ0.5
////////////////////////////////////
#define Wet      76 //ʪ��ֵ80
#define Dry      230 //����ֵ

#define Smean1    120         //�жϺþ�ֵ��ʼֵ
#define Emean1    220         //�жϺþ�ֵ����ֵ0
#define Var1      25         //�жϺ÷���ֵ
#define K1        3*PI/4       //б��1
#define K2        PI/4        //б��2
#define Add1      0           //

#define Smean2    120 //�жϻ���ֵ��ʼֵ
#define Emean2    220 //�жϻ���ֵ����ֵ
#define Var2      10 //�жϻ�����ֵ
#define K3        3*PI/4       //б��3
#define K4        PI/4        //б��4
#define Add2      0           //

#define forGroundFlag 1

#define ForegroundRatio 0.375//ǰ���������ֵ
#define ThresholdMassX   0.25       //����ǰ������λ����ֵ
#define ThresholdMassY   0.25       //��ֱǰ������λ����ֵ
#define Thresholdclarity_ratio   0.5//������ռǰ��������ֵ0.5
#define ThresholdcoreX   0.2       //����core��λ����ֵ
#define ThresholdcoreY   0.2       //��ֱcore��λ����ֵ
#define ThresholdCore   0.45         //core ��λ�÷�����ֵ

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
	unsigned char m_sizeFlag; //ǰ����Сָ�꣬0:������1:̫С
	unsigned char m_massxFlag; //��ѹˮƽλ��ָ�꣬0:������1:̫ƫ��2:̫ƫ��
	unsigned char m_massyFlag; //��ѹ��ֱλ��ָ�꣬0:������1:̫ƫ�ϣ�2:̫ƫ��
	unsigned char m_dyrwetFlag; //��ʪ��ָ�꣬0:������1:̫�ɣ�2:̫ʪ
	unsigned char m_clarityFlag; //����������ָ�꣬0:������1:���߲�����
	unsigned char m_corexFlag; //core��ˮƽλ��ָ�꣬0:������1:̫ƫ��2:̫ƫ��
	unsigned char m_coreyFlag; //core����ֱλ��ָ�꣬0:������1:̫ƫ�ϣ�2:̫ƫ��
	
};

#endif