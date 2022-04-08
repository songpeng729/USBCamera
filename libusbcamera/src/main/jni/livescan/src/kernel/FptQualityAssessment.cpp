#include <math.h>
//#include <memory>
#include <string.h>
#include <stdlib.h>
using namespace std;
#include "FptQualityAssessment.h"

FptQualityAssessment::FptQualityAssessment()
{
	m_QualityScore = 0;
	m_WRatio =   0.2;
	m_WDrywet =  0.1;
	m_WClarity = 0.3;
	m_WCore  =   0.4;

	m_WBack = -0.05;
	m_nBlock = 16;

	m_width =0;
	m_height = 0;
	m_gradWidth = 0;
	m_gradHeight = 0;

	m_pGradx = 0;
	m_pGrady = 0;
	//m_pSegFlag = 0;
}

bool FptQualityAssessment::Create(const int width, const int height)
{
	if(width == m_width && height == m_height)
		return true;
	Clear();
	m_width = width;
	m_height = height;
	m_gradWidth = width + m_nBlock;
	m_gradHeight = height + m_nBlock;
	int gradSize = m_gradWidth * m_gradHeight;

	m_pGradx=new double[gradSize];
	if(0 == m_pGradx)
	{
		Clear();
		return false;
	}
	
	m_pGrady=new double[gradSize];
	if(0 == m_pGrady)
	{
		Clear();
		return false;
	}
	int imgSize = width * height;
	/*m_pSegFlag = new unsigned char[imgSize];
	if(0 == m_pSegFlag)
	{
		Clear();
		return false;
	}*/
	return true;
}
void FptQualityAssessment::Clear(void)
{
	if(m_pGradx)
	{
		delete []m_pGradx;
		m_pGradx = 0;
	}
	
	if(m_pGrady)
	{
		delete []m_pGrady;
		m_pGrady = 0;
	}
	/*if(m_pSegFlag)
	{
		delete []m_pSegFlag;
		m_pSegFlag = 0;
	}*/

	m_width =0;
	m_height = 0;
	m_gradWidth = 0;
	m_gradHeight = 0;
}
FptQualityAssessment::~FptQualityAssessment(void)
{
	Clear();
}
void FptQualityAssessment::InitWeight()
{
	m_QualityScore = 0;
	m_WRatio =   0.3;
	m_WDrywet =  0.1;
	m_WClarity = 0.3;
	m_WCore  =   0.3;

	m_sizeFlag = 0;
	m_massxFlag = 0;
	m_massyFlag = 0;
	m_dyrwetFlag = 0;
	m_clarityFlag = 0;
	m_corexFlag = 0;
	m_coreyFlag = 0;
}
int FptQualityAssessment::
getQualityScore(const unsigned char* pFPImg, const unsigned char* pSegFlag, 
				const int width, const int height)
{
	InitWeight();
	double segScore = getSegmentScore(pSegFlag, width, height);
	double wetScore = getWetDryScore(pFPImg, pSegFlag, width, height);
	getGrade(pFPImg, width, height, m_pGradx, m_pGrady);
	double clarityScore = getClarityScore(pFPImg, width, height, pSegFlag, m_pGradx, m_pGrady);
	double posScore = getPositionScore(pFPImg, pSegFlag, width, height);
	m_QualityScore = m_QualityScore + m_WRatio * segScore + m_WDrywet * wetScore
						+ m_WClarity * clarityScore + m_WCore * posScore;
	double qualityscore = m_QualityScore * 100;
	if(100 > qualityscore)
		qualityscore += 0.5;
	/*CString str;
	str.Format(_T("图像总得分为 %f \n前景大小得分为 %f 干湿度得分为 %f 纹线清晰度得分为 %f core点位置得分为 %f\n"),
	qualityscore, segScore, wetScore, clarityScore, posScore);
	OutputDebugString(str);
	str.Format(_T("前景大小指标为 %d 按压水平位置指标为 %d, 按压垂直位置指标为 %d 干湿度指标为 %d 纹线清晰度指标为 %d core点水平位置指标为 %d, core点垂直位置指标为 %d\n"),
	m_sizeFlag, m_massxFlag, m_massyFlag, m_dyrwetFlag, m_clarityFlag, m_corexFlag, m_coreyFlag);
	OutputDebugString(str);*/


	/*
	int size = width * height;
	for(int i = 0; i < size; ++ i)
	{
		if(0 == pSegFlag[i])
			pFPImg[i] = 255;
	}*/

	return (int)qualityscore;
}

void FptQualityAssessment::getGrade(const BYTE* pImg, const int width, const int height,
									double* pGradx, double* pGrady)
{
	int gradSize = m_gradWidth * m_gradHeight;
	memset(pGradx, 0, sizeof(double) * gradSize);
	memset(pGrady, 0, sizeof(double) * gradSize);
	
	const unsigned char* pImageTmp = pImg;
	double* PGradxTmp = pGradx + m_gradWidth + 1;
	double* PGradyTmp = pGrady + m_gradWidth + 1;
	int gradRowNum = height-1;
	int gradColNum = width-1;
	for(int y=1; y<gradRowNum; ++y, PGradxTmp += m_gradWidth,
		PGradyTmp += m_gradWidth, pImageTmp += width)
	{
		double* PGradxTmpTmp = PGradxTmp;
		double* PGradyTmpTmp = PGradyTmp;
		const unsigned char* pImageTmpTmp = pImageTmp;
		for(int x=1; x<gradColNum; ++x, ++PGradxTmpTmp, ++PGradyTmpTmp, ++pImageTmpTmp)
		{
			double dGradx=0;
			double dGrady=0;
			int row = width;
			dGradx += (pImageTmpTmp[2] - pImageTmpTmp[0]);
			dGrady += (pImageTmpTmp[0] + (pImageTmpTmp[1] << 1) + pImageTmpTmp[2]);

			dGradx += ((pImageTmpTmp[row + 2] << 1) - (pImageTmpTmp[row] << 1));
			
			row += width;
			dGradx += (pImageTmpTmp[row + 2] - pImageTmpTmp[row]);
			dGrady -= (pImageTmpTmp[row] + (pImageTmpTmp[row + 1] << 1) + pImageTmpTmp[row + 2]);
			*PGradxTmpTmp=dGradx;
			*PGradyTmpTmp=dGrady;
		}
	}
}

double FptQualityAssessment::
getBlockCoh(const double* pGradx, const double* pGrady, const int blockSize, const int gradWidth)
{
	int num = 0;
	double dCohx=0;
	double dCohy=0;
	double dCohz=0;

	const double* pGradxRow = pGradx;
	const double* pGradyRow = pGrady;
	for( int i = 0; i < blockSize; ++ i, 
		pGradxRow += gradWidth, pGradyRow += gradWidth)
	{
		const double* pGradxCurr = pGradxRow;
		const double* pGradyCurr = pGradyRow;
		for( int j = 0; j < blockSize; ++ j, ++ pGradxCurr, ++ pGradyCurr)
		{
			 double dx = *pGradxCurr;
			 double dy = *pGradyCurr;
			/* double dxx = dx * dx;
			 double dyy = dy * dy;
			 double dxy = dx * dy;*/

			 dCohx = dCohx + dx*dy;//dxy;//
			 dCohy = dCohy + dx*dx - dy*dy;//dxx - dyy;//
			 dCohz = dCohz + (dx+dy)*(dx+dy);//dxx + dyy + 2 * dxy;//
		}
	}
	double dcoh = 0;
	if(0 != dCohz)
	{
		dcoh = (dCohx * dCohx + dCohy * dCohy)/(blockSize * blockSize * dCohz);
		dcoh = sqrt(dcoh);
	}
	return dcoh;
}
//分割图像
double FptQualityAssessment::getSegmentScore(const unsigned char* pSegFlag, const int width, const int height)
{
	m_massx=0;
	m_massy=0;
	int k=0;
	const unsigned char* pSegFlagRow = pSegFlag;
	for(int i = 0; i < height; ++ i, pSegFlagRow += width)
	{
		const unsigned char* pSegFlagCurr = pSegFlagRow;
		for(int j = 0; j < width; ++ j, ++ pSegFlagCurr)
		{
			if(forGroundFlag == *pSegFlagCurr)
			{
				m_massx = m_massx + j;
				m_massy = m_massy + i;
				k++;
			}
		}
	}
	if(0 < k)
	{
		m_massx=m_massx/k;
		m_massy=m_massy/k;
	}
	double centerX = width / 2.0;
	double centerY = height / 2.0;
	if((m_massx - centerX)/(double)width >= ThresholdMassX)
		m_massxFlag = 2;
	else if((centerX-m_massx)/(double)width >= ThresholdMassX)
		m_massxFlag = 1;
	else
		m_massxFlag = 0;

	if((m_massy - centerY)/(double)height >= ThresholdMassY)
		m_massyFlag = 2;
	else if((centerY-m_massy)/(double)height >= ThresholdMassY)
		m_massyFlag = 1;
	else
		m_massyFlag = 0;
		
	double segscore=(double)k/(double)(width*height*0.8);
	if(segscore >= 0.45/0.8)
	{
		//m_QualityScore=m_QualityScore+m_WRatio*segscore;
	}
	else if(segscore>0.3/0.8)
	{
		//m_QualityScore=m_QualityScore+2*m_WRatio*segscore;
		m_WClarity=m_WClarity-m_WRatio/2;
		m_WCore=m_WCore-m_WRatio/2;
		m_WRatio = 2*m_WRatio;
	}
	else
	{
		//m_QualityScore=m_QualityScore+3*m_WRatio*segscore;
		m_WClarity=m_WClarity-m_WRatio;
		m_WCore=m_WCore-m_WRatio;
		m_WRatio = 3*m_WRatio;
	}
	if(segscore >= ForegroundRatio)
	{
		m_sizeFlag = 0;
	}
	else 
	{
		m_sizeFlag = 1;
	}

	return segscore;
}

double FptQualityAssessment::getWetDryScore(const BYTE *pImg, const BYTE *pSegFlag, int width, int height)
{
	int i,j;
	double GraySum, GrayMean, GrayVar, sum;
	
	GraySum=0;
	GrayMean=0;
	GrayVar=0;
	sum=0;
	const unsigned char* pSegFlagRow = pSegFlag;
	const unsigned char* pImgRow = pImg;
	for(i = 0; i < height; ++ i, pSegFlagRow += width, pImgRow += width)
	{
		const unsigned char* pSegFlagCurr = pSegFlagRow;
		const unsigned char* pImgCurr = pImgRow;
		for(j = 0; j < width; ++ j, ++ pSegFlagCurr, ++ pImgCurr)
		{
			if(forGroundFlag == *pSegFlagCurr)
			{
				GraySum=GraySum + *pImgCurr;
				sum++;
			}
		}
	}
	GrayMean=GraySum/sum;
	
	pSegFlagRow = pSegFlag;
	pImgRow = pImg;
	for(i = 0; i < height; ++ i, pSegFlagRow += width, pImgRow += width)
	{
		const unsigned char* pSegFlagCurr = pSegFlagRow;
		const unsigned char* pImgCurr = pImgRow;
		for(j = 0; j < width; ++ j, ++ pSegFlagCurr, ++ pImgCurr)
		{
			if(forGroundFlag == *pSegFlagCurr)
			{
				unsigned char imgValue = *pImgCurr;
				GrayVar += (imgValue - GrayMean)*(imgValue - GrayMean)/sum;
			}
		}
	}

	GrayVar=sqrt(GrayVar);
	//fptquality->var = GrayVar;

	double drywetScore=0;

	if(GrayMean < Wet)
	{   
		drywetScore=0.2;
		m_dyrwetFlag = 2;
		return drywetScore;//2
	}
	if(GrayMean > Dry)
	{
		drywetScore=0.2;
		m_dyrwetFlag = 1;
		return drywetScore;//1
	}
	if(GrayVar < Var2)
	{
		drywetScore=0.2;
		m_dyrwetFlag = 0;
		return drywetScore;//0
	}

	if((GrayMean >= Smean1) && (GrayMean <= Emean1) && (GrayVar >= Var1))
	{
		drywetScore=1;
		m_dyrwetFlag = 0;
		return drywetScore;//0
	}

	if((GrayMean >= Wet) && (GrayMean <= Smean1))
	{
		if(GrayVar >= ((GrayMean-Smean1)*tan(K1)+Var1+Add1))
		{
			drywetScore=0.8;
			m_dyrwetFlag = 0;
			return drywetScore;//0
		}
	}
	if((GrayMean >= Emean1) && (GrayMean <= Dry))
	{
		if(GrayVar >= ((GrayMean-Emean1)*tan(K2)+Var1+Add1))
		{
			drywetScore=0.8;
			m_dyrwetFlag = 0;
			return drywetScore;//0
		}
	}


	if((GrayMean >= Wet) && (GrayMean <= Smean2))
	{
		if(GrayVar <= ((GrayMean-Smean2)*tan(K3)+Var2+Add2))
		{ 
			drywetScore=0.3;
			m_dyrwetFlag = 0;
			return drywetScore;//0
		}
	}
	if((GrayMean >= Emean2) && (GrayMean <= Dry))
	{
		if(GrayVar <= ((GrayMean-Emean2)*tan(K4)+Var2+Add2))
		{
			drywetScore=0.3;
			m_dyrwetFlag = 1;
			return drywetScore;//1
		}
	}
	drywetScore=0.5;
	m_dyrwetFlag = 0;
	return drywetScore;
}
/*const gradWidth, const int width, const int height*/
void FptQualityAssessment::
getBlockClarityValue(const double* pGradx, const double* pGrady, const unsigned char* pSegFlag, 
				const int blockSize, double &backCoh, double &foreCoh)
{
	double M11,M12,M21,M22;
	double backM11,backM12,backM21,backM22;
	
	M11=0;M12=0;
	M21=0;M22=0;
	backM11 = 0;backM12 = 0;
	backM21 = 0;backM22 = 0;
	int forePixNum=0;
	int backPixNum = 0;

	const double* pGradxRow = pGradx;
	const double* pGradyRow = pGrady;
	const unsigned char* pSegFlagRow = pSegFlag;
	for( int i = 0; i < blockSize; ++ i, 
		pGradxRow += m_gradWidth, pGradyRow += m_gradWidth, pSegFlagRow += m_width)
	{
		const double* pGradxCurr = pGradxRow;
		const double* pGradyCurr = pGradyRow;
		const unsigned char* pSegFlagCurr = pSegFlagRow;
		for( int j = 0; j < blockSize; ++ j, ++ pGradxCurr, ++ pGradyCurr, ++ pSegFlagCurr)
		{
			double dx = *pGradxCurr;
			double dy = *pGradyCurr;
			if(forGroundFlag == *pSegFlagCurr)
			{
				M11=M11 + dx * dx;
				M22=M22 + dy * dy;
				M12=M12 + dx * dy;
				forePixNum++;
			}
			else
			{
				backM11 = backM11 + dx * dx;
				backM22=backM22 + dy * dy;
				backM12=backM12 + dx * dy;
				backPixNum++;
			}
		}
	}

	if(forePixNum>0)
	{
		M11=M11/forePixNum;
		M22=M22/forePixNum;
		M12=M12/forePixNum;
		M21=M12;
		foreCoh = 0;
		if(0 != (M11+M22))
		{
			foreCoh = ((M11-M22)*(M11-M22)+4*M12*M12)/((M11+M22)*(M11+M22));
			foreCoh = sqrt(foreCoh);
		}
	}
	if(backPixNum > 0)
	{
		backM11=backM11/backPixNum;
		backM22=backM22/backPixNum;
		backM12=backM12/backPixNum;
		backM21=backM12;
		backCoh = 0;
		if(0 != (backM11+backM22))
		{
			backCoh = ((backM11-backM22)*(backM11-backM22)+4*backM12*backM12)/
						((backM11+backM22)*(backM11+backM22));
			backCoh = sqrt(backCoh);
		}
	}
}

double FptQualityAssessment::
getClarityScore(const BYTE *pImg, const int width, const int height, const BYTE *pSegFlag, 
		 const double* pGradx, const double* pGrady)
{
	int w, h;
	h = height / m_nBlock;
	if(height%m_nBlock)
	{
		++h;
	}

	w = width / m_nBlock;
	if(width%m_nBlock)
	{
		++w;
	}

	int badForeBlocks = 0;
	int totalForeBlocks = 0;
	int backBlocks = 0;
	double clarityScore = 0;
	double backCoh = 0;
	const double* pGradxTmp = pGradx;
	const double* pGradyTmp = pGrady;
	const unsigned char* pSegFlagTmp = pSegFlag;
	int gradBlockOff = m_gradWidth * m_nBlock;
	int imgBlockOff = width * m_nBlock;
	for(int i = 0; i < h; ++ i, pGradxTmp += gradBlockOff, 
		pGradyTmp += gradBlockOff, pSegFlagTmp += imgBlockOff)
	{
		const double* pGradxBlockStart = pGradxTmp;
		const double* pGradyBlockStart = pGradyTmp;
		const unsigned char* pSegFlagBlockStart = pSegFlagTmp;
		for(int j=0; j<w; j++, pGradxBlockStart += m_nBlock, 
			pGradyBlockStart += m_nBlock, pSegFlagBlockStart += m_nBlock)
		{
			double blockForeCoh = -1;
			double blockBackCoh = -1;
			getBlockClarityValue(pGradxBlockStart, pGradyBlockStart, pSegFlagBlockStart, m_nBlock, blockBackCoh, blockForeCoh);
			if(-1 != blockForeCoh)
			{
				totalForeBlocks++;
				clarityScore=clarityScore + blockForeCoh;
			}
			
			if(-1 != blockForeCoh && blockForeCoh < Thresholdclarity)
			{
				badForeBlocks++;
			}

			if(-1 != blockBackCoh)
			{
				backBlocks ++;
				backCoh += blockBackCoh;
			}
		}
	}
	if(0 < backBlocks)
	{
		backCoh = backCoh / backBlocks;
	}

	if(0 < totalForeBlocks)
	{
		clarityScore=clarityScore/totalForeBlocks;
	}
	
	m_QualityScore = /*m_QualityScore+*/m_WBack*backCoh;

	if(clarityScore>Thresholdclarity)
	{
		//m_QualityScore=m_QualityScore+m_WClarity*clarityScore;
	}
	else if(clarityScore>Thresholdclarity2)
	{
		m_WClarity = m_WClarity+m_WCore/2;
		//m_QualityScore=m_QualityScore+(m_WClarity+m_WCore/2)*clarityScore;
		m_WCore=m_WCore/2;
	}
	else
	{
		m_WClarity = m_WClarity+m_WCore;
		//m_QualityScore=m_QualityScore+(m_WClarity+m_WCore)*clarityScore;
		m_WCore=0;
	}

	if(((double)badForeBlocks/(double)totalForeBlocks)>Thresholdclarity_ratio && clarityScore < 0.45)
	{
		m_clarityFlag = 1;
	}
	else
	{
		m_clarityFlag = 0;
	}

	return clarityScore;
}

int FptQualityAssessment::
getQuasiCorePosition(const BYTE* pImg, const BYTE *SegFlag, const int width, const int height, int &posX, int &posY)
{
	int result = 0;

	int i,j,x,y,ii,jj,maxi,maxj;

	const unsigned char *image = pImg;

	const BYTE *m_pSegImg = SegFlag;

	//------先分块求方向场--
	int oriStep = 8;
	int oriBolder = 14;
	int oriWindowsize = 4;
	int ntemp;
	double dOriValid = 0.001;
	double dtemp;

	int gridWidth = (width - oriBolder * 2 + oriStep - 1) / oriStep;
	int gridHeight = (height - oriBolder * 2 + oriStep - 1) / oriStep;
	BYTE *pOriGrid = (BYTE*)malloc(gridWidth* gridHeight * sizeof(BYTE));
	BYTE *pImgtemp = (BYTE*)malloc(gridWidth* gridHeight * sizeof(BYTE));

	BYTE *pOriValid = (BYTE*)malloc(gridWidth* gridHeight * sizeof(BYTE));//储存是否有效，根据均值判断

	for (j = 0;j < gridHeight ;j++)
	{
		for (i = 0;i < gridWidth ; i++)
		{
			dtemp = 0;

			if (m_pSegImg[(oriBolder + j * oriStep) * width + oriBolder + i * oriStep ] == 0)
			{
				pOriValid[j*gridWidth+i] = 0;
				continue;
			}

			for (jj = oriBolder+j*oriStep - oriStep + 1;jj < oriBolder+j*oriStep + oriStep;jj++)
			{
				for (ii = oriBolder+i*oriStep - oriStep + 1;ii < oriBolder+i*oriStep + oriStep;ii++)
				{
					image = pImg + jj * width + ii;

					dtemp += *image;
				}
			}

			if (dtemp >= 240 * ( 2 * oriStep - 1) * ( 2 * oriStep - 1))
			{
				pOriValid[j*gridWidth+i] = 0;
			}
			else
			{
				pOriValid[j*gridWidth+i] = 255;
			}
		}
	}

	int count[4];
	int nCountbottom = 10,nCountup = 40;
	int mincount;
	int flag = 1;//用作标识

	for (i = 0; i < gridWidth* gridHeight; i++)
	{
		pOriGrid[i] = 0;
	}

	double dx,dy,Gxy,Gxxyy;

	for (j = 0;j < gridHeight ;j++)
	{
		for (i = 0;i < gridWidth ; i++)
		{
			Gxy = 0;
			Gxxyy = 0;

			for (jj = oriBolder+j*oriStep - oriBolder + 1;jj < oriBolder+j*oriStep + oriBolder;jj++)
			{
				for (ii = oriBolder+i*oriStep - oriBolder + 1;ii < oriBolder+i*oriStep + oriBolder;ii++)
				{
					image = pImg + jj * width + ii;

					dy = - *(image-width-1) - *(image-width)*2 - *(image-width+1)
						+ *(image+width-1) + *(image+width)*2 + *(image+width+1);
					dx = - *(image-width-1) + *(image-width+1)
						- *(image-1) *2          + *(image+1) *2
						- *(image+width-1)  + *(image+width+1);
					//dy = -dy;//因为图像是反着的
					Gxy += 2 * dx * dy;
					dx *= dx; dy *= dy;
					Gxxyy += dx - dy;
				}
			}


			if (Gxxyy == 0)//<0.0001 ayong?
			{
				if(Gxy > 0)
				{
					pOriGrid[j*gridWidth+i] = 255;
				}
				else if(Gxy < 0)
				{
					pOriGrid[j*gridWidth+i] = 0;
				}
				else
				{
					pOriGrid[j*gridWidth+i] = 0;//背景怎么设置? ayong?
				}

				continue;
			}
			else if(Gxxyy > 0)
			{
				pOriGrid[j*gridWidth+i] = 128 + (int)((long)(atan(Gxy/Gxxyy)*256/2/3.1415926));						
			}
			else
			{
				pOriGrid[j*gridWidth+i] = 256 + (int)((long)(atan(Gxy/Gxxyy)*256/2/3.1415926)) > 255 ? (int)((long)(atan(Gxy/Gxxyy)*256/2/3.1415926)): 256 + (int)((long)(atan(Gxy/Gxxyy)*256/2/3.1415926));
			}
		}
	}

	for ( i = 0 ; i < gridWidth * gridHeight ; i++ )
	{
		pImgtemp[i] = pOriGrid[i];
	}

	for (j = 0;j < gridHeight ;j++)
	{
		for (i = 0;i < gridWidth ; i++)
		{//如果该点被各种点包围，判断该点是否为候选点
			for ( ii = 0 ; ii < 4; ii ++)
			{
				count[ii] = 0;
			}
			for (jj = j - oriWindowsize ; jj <= j + oriWindowsize ; jj++)
			{
				for ( ii = i - oriWindowsize ; ii <= i + oriWindowsize ; ii++ )
				{
					if ( jj < 0 || jj >= gridHeight || ii < 0 || ii >= gridWidth)
					{
						continue;
					}

					if ( pOriValid[j*gridWidth+i] == 0 )
					{
						continue;
					}

					count[pImgtemp[jj*gridWidth+ii]/64] += 1;
				}
			}

			for ( ii = 0 ; ii < 4; ii ++)
			{
				if (count[ii] < nCountbottom || count[ii] > nCountup)
				{
					break;
				}
			}

			if (ii == 4 && m_pSegImg[(oriBolder + j * oriStep) * width + oriBolder + i * oriStep ] == forGroundFlag)
			{//如果ii为4，则说明改点为候选点
				if ( pOriValid[ j * gridWidth + i ] == 0)
				{//肯定是背景啊
					pOriGrid[ j * gridWidth + i ] = 0;
				}
				else
				{//如果是前景，选择最小的作为分数
					mincount = ( 2 * oriBolder - 1) * ( 2 * oriBolder - 1);
					for (jj = 0 ; jj < 4 ; jj++)
					{
						if (count[jj] < mincount)
						{
							mincount = count[jj];
						}
					}
					pOriGrid[j*gridWidth+i] =(BYTE)( 255.0 / ( 2 * oriBolder - 1) / ( 2 * oriBolder - 1) * 4 * mincount );
				}				
			}
			else
			{
				pOriGrid[j*gridWidth+i] = 0;
			}
		}
	}


	//将虚假点去掉，即在边缘的点
	for (j = 0;j < gridHeight ;j++)
	{
		for (i = 0;i < gridWidth ; i++)
		{
			mincount = 0;
			ntemp = 0;

			if (  pOriGrid[j*gridWidth+i] == 0 )
			{
				continue;
			}

			for (jj = j - oriWindowsize ; jj <= j + oriWindowsize ; jj++)
			{
				for ( ii = i - oriWindowsize ; ii <= i + oriWindowsize ; ii++ )
				{
					if ( jj < 0 || jj >= gridHeight || ii < 0 || ii >= gridWidth)
					{
						continue;
					}

					if ( pOriValid[jj*gridWidth+ii] == 0 )
					{
						mincount++;
					}

					ntemp ++;					
				}
			}

			if (mincount > ntemp / 3)
			{//如果背景块超过了1/5
				pOriGrid[j*gridWidth+i] = 0 ;
			}

		}
	}

	//筛选出区域内最大值
	for (j = 0;j < gridHeight ;j++)
	{
		for (i = 0;i < gridWidth ; i++)
		{
			flag = 1;

			if (  pOriGrid[j*gridWidth+i] == 0 )
			{
				continue;
			}

			for (jj = j - oriWindowsize ; jj <= j + oriWindowsize ; jj++)
			{
				for ( ii = i - oriWindowsize ; ii <= i + oriWindowsize ; ii++ )
				{
					if ( jj < 0 || jj >= gridHeight || ii < 0 || ii >= gridWidth)
					{
						continue;
					}

					if ( pOriGrid[j*gridWidth+i] < pOriGrid[jj*gridWidth+ii] )
					{
						flag = 0;
					}
					else if ( pOriGrid[j*gridWidth+i] == pOriGrid[ jj*gridWidth+ii ] && j*gridWidth+i > jj*gridWidth+ii )
					{
						flag = 0;
					}
				}
			}

			if ( flag == 0)
			{
				pOriGrid[j*gridWidth+i] = 0;
			}
			else
			{
				pOriGrid[j*gridWidth+i] = 255;
			}

		}
	}



	//如果没有最大值，选出一个来
	flag = 0;
	for (j = 0;j < gridHeight ;j++)
	{
		if (flag == 1)
		{
			break;
		}
		for (i = 0;i < gridWidth ; i++)
		{		
			if (flag == 1)
			{
				break;
			}

			if (  pOriGrid[j*gridWidth+i] != 0 )
			{
				flag = 1;
				break;
			}
		}
	}

	if (flag == 0)
	{//如果没有合适的点，选出一个最合适的
		dtemp = ( 2 * oriBolder - 1) * ( 2 * oriBolder - 1);
		dtemp = dtemp * dtemp;
		maxi = 0;
		maxj = 0;

		for (j = 0;j < gridHeight ;j++)
		{
			for (i = 0;i < gridWidth ; i++)
			{				
				mincount = 0;
				ntemp = 0;

				if(  m_pSegImg[(oriBolder + j * oriStep) * width + oriBolder + i * oriStep ] != forGroundFlag)
				{//如果该位置不是前景
					continue;
				}

				for ( ii = 0 ; ii < 4; ii ++)
				{
					count[ii] = 0;
				}

				for (jj = j - oriWindowsize ; jj <= j + oriWindowsize ; jj++)
				{
					for ( ii = i - oriWindowsize ; ii <= i + oriWindowsize ; ii++ )
					{
						if ( jj < 0 || jj >= gridHeight || ii < 0 || ii >= gridWidth)
						{
							continue;
						}

						count[pImgtemp[jj*gridWidth+ii]/64] += 1;
					}
				}

				ntemp = count[0] + count[1] + count[2] + count[3];
				for ( ii = 0 ; ii < 4; ii ++)
				{
					mincount += ( count[ii] - ntemp / 4 ) * ( count[ii] - ntemp / 4 );
				}

				mincount =(int)( (mincount+0.0) / ntemp /ntemp * 1000);

				if ( mincount < dtemp  )
				{
					dtemp = mincount;
					maxi = i;
					maxj = j;
				}

			}
		}

		pOriGrid[ maxj * gridWidth + maxi] = 255;

		//看看是否处在边缘？
		mincount = 0;
		ntemp = 0;

		for (jj = maxj - oriWindowsize ; jj <= maxj + oriWindowsize ; jj++)
		{
			for ( ii = maxi - oriWindowsize ; ii <= maxi + oriWindowsize ; ii++ )
			{
				if ( jj < 0 || jj >= gridHeight || ii < 0 || ii >= gridWidth)
				{
					continue;
				}

				if ( pOriValid[jj*gridWidth+ii] == 0 )
				{
					mincount++;
				}

				ntemp ++;					
			}
		}

		if (mincount > ntemp / 3)
		{//如果背景块超过了1/5
			pOriGrid[maxj*gridWidth+maxi] = 0 ;
			maxi = 0;
			maxj = 0;
		}
	}

	//选出最上面的点，根据其位置判断结果
	//如果没有最大值，选出一个来
	flag = 0;
	maxi = 0;
	maxj = 0;

	for (j = 0;j <= gridHeight - 1  ;j++)
	{
		if (flag == 1)
		{
			break;
		}
		for (i = 0;i < gridWidth ; i++)
		{		
			if (flag == 1)
			{
				break;
			}

			if (  pOriGrid[j*gridWidth+i] != 0 )
			{
				flag = 1;
				maxi = i;
				maxj = j;
				break;
			}
		}
	}

	//判断该点是否为上core,不是的话采用另一种参数
	ntemp = 0;

	for ( ii = 0 ; ii < 4; ii ++)
	{
		count[ii] = 0;
	}

	for (jj = j - oriWindowsize * 2; jj <= j  ; jj++)
	{
		for ( ii = i - oriWindowsize ; ii <= i + oriWindowsize ; ii++ )
		{
			if ( jj < 0 || jj >= gridHeight || ii < 0 || ii >= gridWidth)
			{
				continue;
			}

			count[pImgtemp[jj*gridWidth+ii]/64] += 1;

			ntemp ++;
		}
	}

	if ( maxi == 0 || maxj == 0)
	{
		result = -1;
	}
	else if ( count[1] + count[2] > ntemp / 2)
	{
		result = 1;
	}
	else
	{
		if ( maxj >= 18 )
		{
			result += 2;
		}
		else if ( maxj <= 2 )
		{
			result += 1;
		}

		if ( maxi <= 6)
		{
			result += 10;
		}
		else if ( maxi >= 22)
		{
			result += 20;
		}
	}

	double delta;
	double coreScore;
	delta=(double)width*height/20;
	coreScore=0;
	//后续处理
	///////////////////////////////////
	posX = 0;
	posY = 0;
	int coreNum = 0;
	for(y = 0 ; y < gridHeight; y++)
	{
		if(0 != coreNum)
			break;
		for (x = 0; x < gridWidth; x++)
		{
			if(255 == pOriGrid[y*gridWidth + x] && 0 == coreNum)
			{
				//ii, jj 是core点坐标
				posY = oriBolder+y*oriStep;
				posX = oriBolder+x*oriStep;
				++ coreNum;
				break;
			}
		}
	}
	//fptquality->CoreScore = coreScore;
	free(pOriGrid);
	free(pImgtemp);
	free(pOriValid);
	return coreNum;
}
double FptQualityAssessment::
getPositionScore(const BYTE* pImg, const BYTE *SegFlag, const int width, const int height/*, FptQuality *fptquality*/)
{	
	int corePosX = 0;
	int corePosY = 0;
	int coreNum = 0;
	coreNum = getSglPosition(pImg, width, height, corePosX, corePosY);
	if(0 == coreNum)
	{
		coreNum = getQuasiCorePosition(pImg, SegFlag, width, height, corePosX, corePosY);
	}
	if(0 == coreNum)
	{
		m_corexFlag = 3;
		m_coreyFlag = 3;
		return 0;
	}
	double delta=(double)width * height / 20;
	double coreScore = (corePosY-(m_massy-60))*(corePosY-(m_massy-60)) + 
						(corePosX-m_massx)*(corePosX-m_massx);
	coreScore=-coreScore;
	coreScore=exp(coreScore/delta);

	if(coreScore<ThresholdCore)
	{
		if((m_massx - corePosX)/(double)width >= ThresholdcoreX)
			m_corexFlag = 1;
		else if((corePosX - m_massx)/(double)width >= ThresholdcoreX)
			m_corexFlag = 2;
		else
			m_corexFlag = 0;

		if((m_massy - corePosY)/(double)height >= ThresholdcoreY)
			m_coreyFlag = 1;
		else if((corePosY - m_massy)/(double)height >= ThresholdcoreY)
			m_coreyFlag = 2;
		else
			m_coreyFlag = 0;
	}
				
	//将core点 灰度置为255	
	/*for(int i = corePosY - 4; i <= corePosY + 4; i++)
	{
	for(int j = corePosX - 4; j <= corePosX + 4; j++) 
	{
	if(i < height && 0 <= i && j < width && 0 <= j)
	{
	pImg[i*width+j]=255;
	}
	}
	}*/
	return coreScore;
}

int FptQualityAssessment::getSglPosition(const unsigned char *pRaw, int width, int height, int &corex, int &corey)
{
	/*HINSTANCE HDLL = LoadLibrary(_T("SglExtractionDLL.dll"));

	typedef int (*pSglFunc) (unsigned char *, int , int, int &, int &);
	pSglFunc SglExtraction = (pSglFunc)GetProcAddress(HDLL,"GetSglPosition");
	if (SglExtraction == NULL){
	return -1;
	}
	return SglExtraction( pRaw, width,height,corex,corey);*/
	return 0;
}