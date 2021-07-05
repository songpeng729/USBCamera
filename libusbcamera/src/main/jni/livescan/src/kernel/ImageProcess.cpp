//#include "StdAfx.h"
#include "ImageProcess.h"
#include <algorithm>
#include <cmath>
using namespace std;


unsigned char ImageProcess::Mean(int length, unsigned char *pIn, unsigned char *pRoi)
{
	long result = 0, count = 0;
	for (int i = 0 ; i < length; i++) 
	{
		if (pRoi && !pRoi[i] ) continue;
		result += pIn[i];
		count++;
	}
	return (unsigned char)(result/count);
}
long ImageProcess::Variance(int length, unsigned char *pIn, unsigned char *pRoi)
{
	unsigned char mean = Mean(length,pIn,pRoi);
	long long result = 0, count = 0;
	for (int i = 0 ; i < length; i++) 
	{
		if (pRoi && !pRoi[i] ) continue;
		result += (pIn[i] - mean) * (pIn[i] - mean);
		count++;
	}
	return result/count;
}

void ImageProcess::Normalize(int width, int height, unsigned char *pIn, unsigned char *pOut, unsigned char *pSeg)
{
	ImageProcess ip;
	long var, var_wt;
	unsigned char mean, mean_wt;

	mean = ip.Mean(width*height, pIn, pSeg);
	var = ip.Variance(width*height, pIn, pSeg);

	var_wt = 400;  /*目标的均值和方差*/
	mean_wt = 100;

	for ( int i = 0 ; i < width; i++) 
	{
		for ( int j = 0 ; j < height; j++) 
		{
			if (pSeg && pSeg[j*width+i])
			{
				if(pIn[j*width+i] > mean)
					pOut[j*width+i] = mean_wt+(int)sqrt((float)(var_wt* (pIn[j*width+i] -mean) * (pIn[j*width+i] -mean)/var ));
				else
					pOut[j*width+i] = mean_wt-(int)sqrt((float)(var_wt* (pIn[j*width+i] -mean) * (pIn[j*width+i] -mean)/var ));
			}
		}
	}
}


void ImageProcess::HistogramEq(int width, int height, unsigned char *pIn, unsigned char *pOut, unsigned char *pSeg)
{
	int histogram[256];
	int i, j;
	unsigned char *ptr1, *ptr2;
	int av, avNum;
	int tsPercentage;
	int pixelNum, gradeNum, threshold;
	int tmp;
	unsigned char minGV, maxGV;
	unsigned char* pRstRaw = new unsigned char [width * height];//用来储存结果
	unsigned char* pRstRaw2 = new unsigned char [width * height];//用来储存结果
	tsPercentage = 15;  //25	
	
	for (i = 0; i < 256; i ++)
		histogram[i] = 0;

	//已经做了切割，对ROI区域进行统计
	av = 0;
	avNum = 1;
	ptr1 = pRstRaw2 ;
	ptr2 = pSeg ;
	for (i = 0; i < width; i ++)
	{
		for (j = 0; j < height; j ++, ptr1 ++, ptr2++)
		{
			if ( *ptr2 == 0 ) continue;
			av += *ptr1;//累计灰度
			avNum ++;//累计个数
		}
	}

	av /= avNum;//平均灰度
	av += av/2;
	if ( av > 220 ) av = 220;

	ptr2 = pSeg ;
	for (i = 0; i < width * height; i ++, ptr2++ )//全图操作
	{
		if ( *ptr2 == 0 ) continue;
		if (pIn[i] > av) 
			pRstRaw2[i] = av;//调整灰度，灰度最大值为av,即平均灰度的3/2倍。
		else pRstRaw2[i] = pIn[i];
	}	
		
		
	ptr1 = pRstRaw2 ;
	ptr2 = pSeg ;
	for (i = 0; i < width; i ++) {
		for (j = 0; j < height; j ++, ptr1 ++, ptr2++ )
		{
			if ( *ptr2 == 0 ) continue;
			histogram[*ptr1] ++;//开始做统计
		}
	}

	pixelNum = gradeNum = 0;
	for (i = 0; i < 256; i++)
	{
		if (histogram[i] != 0) 
		{
			pixelNum += histogram[i];
			gradeNum ++;
		}
	}


	threshold = pixelNum / tsPercentage;

	minGV = 0; //记录点的个数达到threshold的最小的灰度值。
	int TmpSum = 0;
	for (i = 0; i < 256; i++)
	{
		TmpSum += histogram[i];

		if ( TmpSum < threshold) minGV = i;
		else break;
	}

	maxGV = 255; //记录点的个数达到threshold的最大的灰度值。
	TmpSum = 0;
	for (i = 255; i >= 0; i--)
	{
		TmpSum += histogram[i];
		if ( TmpSum < threshold) maxGV = i;
		else break;
	}


	//灰度调整,minGV以下的灰度都变为0, maxGV以上的灰度都变为255.其实就是增强了对比度
	ptr1 = pRstRaw2;
	ptr2 = pRstRaw;
	unsigned char *ptr3 = pSeg;
	
	for (i = 0; i < width; i ++)
	{
		for (j = 0; j < height; j ++, ptr1 ++, ptr2 ++, ptr3++ )
		{
			if ( *ptr3 == 0 ) {
				//*ptr2 = 255;  modified by wuspark
				*ptr2 = 0;
				continue;
			}
			//下面这行代码是整个函数的核心，其实就是拉大对比度的目的
			tmp = (*ptr1 - minGV) * 255 / (maxGV - minGV);
			if (tmp > 255) tmp = 255;
			if (tmp < 0) tmp = 0;
			*ptr2 = (unsigned char)tmp;
		}
	}

	memcpy(pOut,pRstRaw,width * height);

	delete []pRstRaw;
	delete []pRstRaw2;
}

//----------------子图处理----------------
void ImageProcess::GetLocalImage(unsigned char* pWholeImg, int wholewidth, int startwidth, int endwidth, int startheight, int endheight, unsigned char* pLocalImg){
	int localwidth = endwidth - startwidth + 1;
	for ( int j = startheight,k=0; j <= endheight; j++,k++) {
			memcpy( pLocalImg+k*localwidth, pWholeImg + j * wholewidth + startwidth, localwidth);
	}
}

void ImageProcess::SetLocalImage(unsigned char* pWholeImg, int wholewidth, int startwidth, int endwidth, int startheight, int endheight, unsigned char* pLocalImg){
	int localwidth = endwidth - startwidth + 1;
	for ( int j = startheight,k=0; j <= endheight; j++,k++) {
			memcpy( pWholeImg + j * wholewidth + startwidth, pLocalImg+k*localwidth,  localwidth);
	}
}

void GenerateDis(double *pDis, int distype){
    double haha[8] = {1,sqrt(3.0),2,sqrt(3.0),1,sqrt(3.0),2,sqrt(3.0)};
	switch(distype){
	case 1://Euclidean 伪
		pDis[0] = pDis[2] = pDis[4] = pDis[6] = 1.0;
		pDis[1] = pDis[3] = pDis[5] = pDis[7] = sqrt(2.0);
		break;
	case 2://City Block
		pDis[0] = pDis[2] = pDis[4] = pDis[6] = 1.0;
		pDis[1] = pDis[3] = pDis[5] = pDis[7] = 2.0;
		break;
	case 3:
		memcpy( pDis, haha, 8*sizeof(double));
		break;
	default:
		pDis[0] = pDis[2] = pDis[4] = pDis[6] = sqrt(2.0);
		pDis[1] = pDis[3] = pDis[5] = pDis[7] = 1.0;
		break;
	}
}

//计算图像中每个点到图像子集（由subimg给出）的最短距离，距离计算方式由distype给出。
void ImageProcess::DistanceTransform(	unsigned char* pImg, int width,int height, unsigned char subimg, double *pOutImg, int distype ){
	// 八邻域基本情况，从左下开始为0，一次顺时针旋转得到。
	double dis[8] = {0}; 
	GenerateDis(dis,distype);
	int pos[8] = { 
		-1, -width-1, -width,  -width+1,  1,  width+1,  width, width-1, 
	};
	// 结果初始化
	int maxdis = width + height;
	for (int i = 0; i < width*height; i++){
		pOutImg[i] = (pImg[i] == subimg) ? 0 : maxdis;
	}
	// 从左上到右下
	for (int j = 1; j < height - 1; j++) {
		double *pTemp = pOutImg+j*width+1;
		for (int i = 1; i < width-1 ; i++,pTemp++){
			/*for (int k = 0; k < 4; k++){
				*pTemp = min( *pTemp, *(pTemp+pos[k])+dis[k] );				
			}*/
			*pTemp = min( *pTemp, *(pTemp+pos[1])+dis[1] );	
			*pTemp = min( *pTemp, *(pTemp+pos[2])+dis[2] );	
			*pTemp = min( *pTemp, *(pTemp+pos[3])+dis[3] );	
			*pTemp = min( *pTemp, *(pTemp+pos[0])+dis[0] );	
		}
	}
	// 从右下到左上
	for (int j = height - 2; j > 0; j--) {
		double *pTemp = pOutImg+j*width+ width - 2;
		for (int i = width - 2; i > 0 ; i--,pTemp--){
			/*for (int k = 4; k < 8; k++){
				*pTemp = min( *pTemp, *(pTemp+pos[k])+dis[k] );				
			}*/
			*pTemp = min( *pTemp, *(pTemp+pos[5])+dis[5] );	
			*pTemp = min( *pTemp, *(pTemp+pos[6])+dis[6] );	
			*pTemp = min( *pTemp, *(pTemp+pos[7])+dis[7] );	
			*pTemp = min( *pTemp, *(pTemp+pos[4])+dis[4] );	
		}
	}
}
//得到图中的直线,linepos代表第几行（列），dim代表行(1)或列(2)
void ImageProcess::GetLineFromImage(unsigned char* pWholeImg, int width, int height, int linepos, int dim, unsigned char *pLine){
	if (dim == 1){//行
		memcpy(pLine, pWholeImg + linepos * width, width);
	}
	else if (dim == 2){
		unsigned char* pImg = pWholeImg + linepos;
		for (int i = 0 ; i < height; i++,pImg += width,pLine++){
			*pLine = *pImg;
		}
	}
}

void ImageProcess::Skeleton(int width, int height, unsigned char *pIn, unsigned char *pOut, unsigned char *pRoi)
{
	int i, j, k, count, flag;
	int n[9];
	unsigned char * ptr1;
	unsigned char * ptr2;

	while(1)
	{
		flag = 0;
		//ptr1  ptr2 不知如何来的，这是原程序的定义
		//ptr1 = m_pRstImg;
		//ptr2 = m_pSkeletonImg;

		//lilshaw版本 不确定
		ptr1 = pIn;
		ptr2 = pOut;

		for(i = 0; i < height; ++ i)
		{
			for(j = 0; j < width; ++j, ++ ptr1, ++ ptr2)
			{
				if (* ptr1 == 3)
					* ptr1 = 0;
				* ptr2 = * ptr1;
			}
		}
		//原始版本
		//ptr1 = m_pRstImg + m_nWidth;
		//ptr2 = m_pSkeletonImg + m_nWidth;
		//lilshaw版本 不确定
		ptr1 = pIn + width;
		ptr2 = pOut + width;

		for(i = 1; i < height - 1; ++ i)
		{
			++ ptr1;
			++ ptr2;
			for(j = 1; j < width - 1; ++ j, ++ ptr1, ++ ptr2)
			{
				//非1，不予处理
				if (*ptr1 != 1) continue;
				//四邻域全1，不予处理
				if (*(ptr2 - width) && *(ptr2 - 1) && *(ptr2 + width) && *(ptr2 + 1))
					continue;
				//八邻域只有一个白点（端点），不予处理
				count = *(ptr2 - 1) + *(ptr2 + 1) + *(ptr2 - width) + *(ptr2 + width) +
						*(ptr2 + width -1 ) + *(ptr2 + width + 1) +
						*(ptr2 - width - 1) + *(ptr2 - width + 1);
				if (count <= 1)
				{
					*ptr1 = 2; 
					continue;
				}

				n[3] = *(ptr1 - width - 1); n[2] = *(ptr1 - width); n[1] = *(ptr1 - width + 1);
				n[4] = *(ptr1 - 1);								n[0] = *(ptr1 + 1);
				n[5] = *(ptr1 + width - 1); n[6] = *(ptr1 + width);	n[7] = *(ptr1 + width + 1);
				n[8] = n[0];

				count = 0;
				k = 0;
				while (k <= 6) {
					if(!n[k] && (n[k+1] || n[k+2]))
						++ count;
					k += 2;
				}
				//交叉点，不予处理
				if(count != 1){
					*ptr1 = 2;
					continue;
				}
				if(n[2] == 3)
				{
					n[2] = * (ptr1 - width) = 0;
					count = 0;
					k = 0;
					while (k <= 6)
					{
						if (!n[k] && (n[k+1] || n[k+2]))
							 ++ count;
						k += 2;
					}
					if(count != 1)
					{
						* (ptr1 - width) = 3;
						continue;
					}
					n[2] = *(ptr1 - width) = 3;
				}//if(n[2] == 3)
				if(n[4] != 3)
				{
					* ptr1 = 3;
					flag = 1;
					continue;
				}
				n[4] = *(ptr1 - 1) = 0;
				count = 0;
				k = 0;
				while (k <= 6)
				{
					if (!n[k] && (n[k+1] || n[k+2]))
						++ count;
					k += 2;
				}
				if (count == 1)
				{
					* (ptr1 - 1) = 3;
					* ptr1 = 3;
					flag = 1;
				}
				else
					*(ptr1 - 1) = 3;
			}//for(j = 1; j < width - 1; ++ j)
			++ ptr1;
			++ ptr2;
		}//for(i = 1; i < height - 1; ++ i)
		if (flag == 0)
			break;
	}// while(1)
	//原始版本
	//ptr1 = m_pRstImg;
	//ptr2 = m_pSkeletonImg;
	//lilshaw版本 不确定
	ptr1 = pIn;
	ptr2 = pOut;
	for (i = 0; i < height; ++ i)
	{
		for (j = 0; j < width; ++ j, ++ ptr1, ++ ptr2)
		{
			if (*ptr1 == 1 || *ptr1 == 2)
				* ptr2 = 1;
			else
				* ptr2 = 0;
			pOut[i * width + j] = (* ptr2) * 255;
		}
	}
}

void ImageProcess::Binary(int width, int height, unsigned char *pIn, unsigned char thresh, unsigned char *pOut, unsigned char foreground, unsigned char background, unsigned char *pRoi)
{
	for (int i = 0; i < width*height ; i++){
		if ( !pRoi || pRoi[i]!=0 )
			pOut[i] = ( pIn[i] >= thresh ) ? foreground: background;
		else
			pOut[i] = 0;
	}
}

void ImageProcess::Dilate(int width, int height, unsigned char *pIn, unsigned char ntimes, unsigned char *pOut, unsigned char *pRoi ){
	//膨胀函数。ntimes代表膨胀次数。
	if (!pOut) pOut = pIn;
	unsigned char *pTempImg = new unsigned char [ width * height ];
	memcpy(pTempImg, pIn, width*height);
	int pos[3][3] = {-width-1,-width,-width+1,-1,0,1,width-1,width,width+1};
	unsigned char dmax,*p=pTempImg,c,*p1=pOut;
	for (int k = 0 ; k < ntimes; k++) {
		p=pTempImg;
		p1=pOut;
		for (int j = 0 ; j < height ; j++){
			for (int i = 0 ; i < width ; i++) {
				dmax = 0;			 
				for (int jj = 0 ; jj <= 2; jj++){
					if ( j+jj-1 < 0 || j+jj-1 >= height) continue;
					for (int ii = 0 ; ii <= 2; ii++){
						if ( i+ii-1 < 0 || i+ii-1 >= width ) continue;
						c = *(p+pos[jj][ii]);
						if ( c > dmax ) dmax = c;
					}
				}
				*p1 = dmax;
				p1++;
				p++;
			}
		}
		if (k<ntimes-1){
			memcpy(pTempImg, pOut, width*height);
		}
	}
	delete[] pTempImg;
}

void ImageProcess::IsSame(int length, unsigned char *p1, unsigned char *p2, unsigned char *pOut){
	if (!pOut) pOut = p1;
	// 两个图像相同则取其值， 否则取0
	for (int i = 0 ; i < length; i++){
		pOut[i] = ( p1[i] == p2[i] ) ? p1[i] : 0;
	}
}

void ImageProcess::MaximumPoint(int width, int height, unsigned char *pIn, unsigned char ntimes, unsigned char *pOut){
	//是否为局部最大值，是则取原来的值，否则取0
	unsigned char *pTempImg = new unsigned char [ width * height ];
	Dilate( width, height, pIn, ntimes, pTempImg);
	IsSame( width*height, pIn, pTempImg, pOut);
	delete[] pTempImg;
}

//平滑滤波
void ImageProcess::Convlt(int width, int height, unsigned char *pIn, int widthF, int heightF, float *pFilter, unsigned char *pOut, unsigned char *pRoi){
	//Ayong, 卷积，直接做法。
	int sideW = widthF / 2;
	int sideH = heightF / 2;
	int M = sideW*2 + width;
	int N = sideH*2 + height;
	unsigned char * pRaw = new unsigned char [ M * N];
	unsigned char * p = pOut,*p1;
	float *p2;

	SetLocalImage( pRaw, M, sideW, sideW+width-1, sideH, sideH+height-1, pIn);
	for ( int j = 0 ; j < height; j++ ){
		for ( int i = 0 ; i < width; i++,p++){
			double result = 0;
			p1 = pRaw + j * M + i;
			p2 = pFilter;
			for ( int jj = j; jj < j + heightF; jj++, p1+=M-widthF ){
				for ( int ii = i; ii < i + widthF; ii++,p2++,p1++ ){
					result += (*p1) * (*p2);
				}
			}
			*p = (unsigned char) (int )result;
		}
	}
	delete[] pRaw;
};
void ImageProcess::Smooth(int width, int height, unsigned char *pIn, unsigned char *pOut, int algorithm, int para1, int para2, unsigned char *pRoi)
{
	switch(algorithm){
	case 1://均值滤波
		if ( -1 == para1) 
			Smooth_Mean(width, height, pIn, pOut );
		else
			Smooth_Mean(width, height, pIn, pOut, para1, pRoi);
		break;
	case 2:
		if ( -1 == para1) 
			Smooth_Middle(width, height, pIn, pOut );
		else
			Smooth_Middle(width, height, pIn, pOut, para1, pRoi);
		break;
	case 3:
		if ( -1 == para1)
			Smooth_Gauss(width, height, pIn, pOut);
		else
			Smooth_Gauss(width, height, pIn, pOut, para1, pRoi);
		break;
	default:
		if (para1 = -1) 
			Smooth_Mean(width, height, pIn, pOut );
		else
			Smooth_Mean(width, height, pIn, pOut, para1, pRoi);
		break;
	}
}
void ImageProcess::Smooth_Mean(int width, int height, unsigned char *pIn, unsigned char *pOut, int nBlockRadius, unsigned char *pRoi){	
//函数功能： 图像以像素点为中心的块的信息收集处理,均值滤波
//参数说明； width,height-------图像的长和高
//           pIn-------------图像数据指针
//           nBlockRadius----------块半径减1，默认为1
//           nBolder-------------不需计算的边界长度，默认为0
//返回值：   成功否

//-----------可执行条件判断--------
	int nBolder = 0;
	if (!pOut) pOut = pIn;
	if (width <= nBolder *2)
	{
		//AfxMessageBox(_T("没有不是边界的像素！"));
		return;
	}

	if (width <= (nBolder + nBlockRadius) * 2)
	{
		//AfxMessageBox(_T("没有可以完整收集的块"));
		return;                                       //若想继续计算，可以去掉
	}
	//---------------------------------


	////-------------申请内存-------------
	int* pscore = new int [width-nBolder*2];   //存储行间的信息
	unsigned char* pRstRaw = new unsigned char [width * height] ;//用来储存结果
	////----------------------------------


	//-------------声明变量------------
	int temp_i,temp_j,temp_k; //遍历用临时变量
	int num_x = 0,num_y = 0;//存储块内行，列个数
	int scoretemp;//临时储存当前像素的得分值
	unsigned char *ptemp;//指向需要的像素位置
	int score; //记录每个像素的最终得分
	//---------------------------------


	////-------------申请内存初始化------

	for (temp_i = 0; temp_i < width-nBolder*2 ; temp_i++)
	{
		pscore[temp_i] = 0;
	}

	for (temp_i = 0; temp_i < width * height ; temp_i++)
	{
		pRstRaw[temp_i] = 0;
	}

	////---------------------------------


	//////-------------函数主体-----------

	for (temp_j = nBolder ; temp_j < height - nBolder ; temp_j++ )
	{
		if (temp_j == nBolder)   // 如果是起始行，要做初始准备
		{
			for (temp_k = 0 ; temp_k < width - nBolder*2 ; temp_k++)
			{
				pscore[temp_k] = 0;
			}
			num_y = 0;//初始化

			for (temp_k = nBolder ; temp_k < nBolder + nBlockRadius + 1 ; temp_k++)
			{// 收集影响初始行的所有行的信息
				for (temp_i = nBolder ; temp_i < width - nBolder ; temp_i++)
				{
					ptemp = pIn + temp_k * width + temp_i;

					//******计算该像素的属性值――改*****
					scoretemp = *ptemp;//此处仅用灰度值，其他情况可改。
					//*********************************

					pscore[temp_i - nBolder] += scoretemp;
				}

				num_y++; //收集一行数值加1，统计收集的行数；
			}
		}
		else
		{
			if (temp_j - nBlockRadius - 1 >= nBolder)
			{//去掉对当前行已经没有影响的一行的分数。
				for (temp_i = nBolder ; temp_i < width - nBolder ; temp_i++)
				{
					ptemp = pIn + (temp_j - nBlockRadius - 1) * width + temp_i;

					//******计算该像素的属性值――改*****
					scoretemp = *ptemp;//此处仅用灰度值，其他情况可改。
					//*********************************

					pscore[temp_i - nBolder] -= scoretemp;
				}

				num_y--;
			}

			if (temp_j + nBlockRadius < height - nBolder)
			{//加上对刚刚对当前行产生影响的一行的分数
				for (temp_i= nBolder ; temp_i < width - nBolder ; temp_i++)
				{
					ptemp = pIn + (temp_j + nBlockRadius) * width + temp_i;

					//******计算该像素的属性值――改*****
					scoretemp = *ptemp;//此处仅用灰度值，其他情况可改。
					//*********************************

					pscore[temp_i - nBolder] += scoretemp;
				}

				num_y++;
			}              
		}
		//到此将当前行像素所需的行信息准备完毕，下面收集列信息以完成块的收集。

		for (temp_i = nBolder ; temp_i < width - nBolder ; temp_i++)
		{//对每个像素求得分

			if (temp_i == nBolder) // 如果是起始列，要做初始准备
			{
				score = 0; //对每行初始化一下。
				num_x = 0;

				for (temp_k = nBolder ; temp_k < nBolder + nBlockRadius + 1 ; temp_k++)
				{
					score += pscore[temp_k - nBolder];
					num_x++;
				}          
			}
			else
			{
				if (temp_i - nBlockRadius - 1 >= nBolder)
				{//去掉对当前像素已经没有影响的一列的分数。
					score -= pscore[temp_i - nBlockRadius - 1];
					num_x--;
				}

				if (temp_i + nBlockRadius < width - nBolder)
				{//加上对刚刚对当前像素产生影响的一列的分数
					score += pscore[temp_i + nBlockRadius];
					num_x++;
				}
			}
			//到此以i,j像素为中心的块的信息收集完毕，得分储存到了score中。

			//******此处根据得分进行相应的处理。***********
			pRstRaw[temp_j * width + temp_i] = score/num_x/num_y;
			//*********************************************
		}

	}

	for (temp_i = 0; temp_i < width * height ; temp_i++)
	{
		pOut[temp_i] = pRstRaw[temp_i];
	}
	////-------------释放内存-------------
	delete []pRstRaw;
	delete []pscore;
	////----------------------------------
}
unsigned char GetNum(unsigned char *p, unsigned char *pend, int n){
	sort(p,pend);
	return p[n];	
};
void ImageProcess::Smooth_Middle(int width, int height, unsigned char *pIn, unsigned char *pOut, int nBlockRadius, unsigned char *pRoi){ 
	unsigned char *pTempImg = new unsigned char [width*height];
	unsigned char *pLocalImg = new unsigned char [(2*nBlockRadius+1)*(2*nBlockRadius+1)];
	int startposi, startposj, endposi, endposj;
	unsigned char *p = pTempImg;
	for (int j = 0 ; j < height; j++){
		for (int i = 0 ; i < width; i++,p++) {
			startposi = max(0, i - nBlockRadius);
			endposi = min(width-1, i + nBlockRadius);
			startposj = max(0, j - nBlockRadius);
			endposj = min(height-1, j + nBlockRadius);
			GetLocalImage( pIn, width, startposi, endposi, startposj, endposj, pLocalImg);
			int n = (endposi-startposi)*(endposj-startposj);
			*p = GetNum( pLocalImg, pLocalImg+n, n/2);
		}
	}
	if(!pOut) pOut = pIn;
	memcpy( pOut, pTempImg, width*height);
	delete[] pLocalImg;
	delete[] pTempImg;
}
void ImageProcess::Smooth_Gauss(int width, int height, unsigned char *pIn, unsigned char *pOut,  int sigma, unsigned char *pRoi){
		//sigma由于为偶数，除以10使用。如需要真正地sigma为0.5，则传入5即可。
	float fsigma = sigma / 10.0;
	//准备模板
	int size = (6 * fsigma + 1 + 0.5);
	fsigma *= fsigma;
	size = size / 2 * 2 + 1;//变为奇数	
	int mid = size / 2 ;
	float *pFilter = new float [size*size];
	float *p = pFilter;
	double sum = 0;
	for ( int j = 0 ; j < size; j++) {
		for ( int i = 0 ; i < size; i++,p++) {
			*p = exp(-((j - mid) * (j - mid) + (i - mid) * (i - mid))/fsigma/2);
			sum += *p;
		}
	}
	p = pFilter;
	for (int i = 0 ; i < size*size; i++,p++){
		*p /= sum;
	}
	//
	Convlt( width, height, pIn, size, size, pFilter, pOut, pRoi);
	delete[] pFilter;
}
void ImageProcess::Smooth_Bilateral(int width, int height, unsigned char *pIn, unsigned char *pOut,  int sigma , unsigned char *pRoi ){
	
}


//从图像中得到最小值和最大值及其位置。
void ImageProcess::MinMaxLoc(int width, int height, unsigned char *pIn, unsigned char *pMinvalue, int *pMinx, int *pMiny, unsigned char *pMaxvalue,  int *pMaxx, int *pMaxy ){
	if ( pMinvalue || pMinx || pMiny ){
		unsigned char cmin = 255;
		int minx = -1,miny = -1;
		unsigned char *p = pIn;
		for (int j = 0 ; j < height ; j++){
			for (int i = 0 ; i < width ; i++,p++){
				if ( *p < cmin ){
					cmin = *p;
					minx = i;
					miny = j;
				}
			}
		}
		if (pMinvalue) *pMinvalue = cmin;
		if (pMinx) *pMinx = minx;
		if (pMiny) *pMiny = miny;
	}
	if ( pMaxvalue || pMaxx || pMaxy ){
		unsigned char cmax = 0;
		int maxx = -1,maxy = -1;
		unsigned char *p = pIn;
		for (int j = 0 ; j < height ; j++){
			for (int i = 0 ; i < width ; i++,p++){
				if ( *p > cmax ){
					cmax = *p;
					maxx = i;
					maxy = j;
				}
			}
		}
		if (pMaxvalue) *pMaxvalue = cmax;
		if (pMaxx) *pMaxx = maxx;
		if (pMaxy) *pMaxy = maxy;
	}
}

//函数功能： 统计临域内兴趣点的个数
//参数说明； width,height-------图像的长和高
//           pIn-------------图像数据指针
//           pOut: 输出图像
//           ch----------兴趣点大小
//           blocksize-------------块半径。
//返回值：   
void ImageProcess::CountLocalInterestPoints(int width, int height, unsigned char *pIn, unsigned char *pOut, unsigned char ch, int blocksize){


	//-----------可执行条件判断--------
	int nBolder = 0;
	if (!pOut) pOut = pIn;
	//---------------------------------


	////-------------申请内存-------------
	int* pscore = new int [width];   //存储行间的信息
	unsigned char* pRstRaw = new unsigned char [width * height] ;//用来储存结果
	////----------------------------------


	//-------------声明变量------------
	int temp_i,temp_j,temp_k; //遍历用临时变量
	int scoretemp;//临时储存当前像素的得分值
	unsigned char *ptemp;//指向需要的像素位置
	int score; //记录每个像素的最终得分
	int kk;
	//---------------------------------


	////-------------申请内存初始化------
	memset(pscore, 0, width*sizeof(int));
	memset(pRstRaw,0, width*height);
	
	////---------------------------------


	//////-------------函数主体-----------
	for (temp_j = 0 ; temp_j < height  ; temp_j++ )
	{
		kk = temp_j*width;
		if (temp_j == 0)   // 如果是起始行，要做初始准备
		{
			memset(pscore, 0, width*sizeof(int));

			for (temp_k = 0 ; temp_k <= width*blocksize ; temp_k+=width)
			{// 收集影响初始行的所有行的信息
				for (temp_i = 0, ptemp = pIn + temp_k ; temp_i < width  ; temp_i++, ptemp++)
				{
					//******计算该像素的属性值――改*****
					scoretemp = (*ptemp == ch);//此处仅用灰度值，其他情况可改。
					//*********************************

					pscore[temp_i] += scoretemp;
				}

			}
		}
		else
		{
			if (temp_j > blocksize)
			{//去掉对当前行已经没有影响的一行的分数。
				for (temp_i = 0,ptemp= pIn+kk-blocksize*width ; temp_i < width  ; temp_i++, ptemp++)
				{
					//******计算该像素的属性值――改*****
					scoretemp = (*ptemp == ch);//此处仅用灰度值，其他情况可改。
					//*********************************
					pscore[temp_i] -= scoretemp;
				}

			}

			if (temp_j < height - blocksize)
			{//加上对刚刚对当前行产生影响的一行的分数
				for (temp_i = 0,ptemp= pIn+kk+blocksize*width ; temp_i < width  ; temp_i++, ptemp++)
				{
					//******计算该像素的属性值――改*****
					scoretemp = (*ptemp == ch);//此处仅用灰度值，其他情况可改。
					//*********************************

					pscore[temp_i] += scoretemp;
				}

			}              
		}
		//到此将当前行像素所需的行信息准备完毕，下面收集列信息以完成块的收集。

		for (temp_i = 0 ; temp_i < width ; temp_i++)
		{//对每个像素求得分

			if (temp_i == 0) // 如果是起始列，要做初始准备
			{
				score = 0; //对每行初始化一下。

				for (temp_k = 0 ; temp_k <= blocksize  ; temp_k++)
				{
					score += pscore[temp_k];
				}          
			}
			else
			{
				if (temp_i > blocksize)
				{//去掉对当前像素已经没有影响的一列的分数。
					score -= pscore[temp_i - blocksize - 1];
				}

				if (temp_i < width - blocksize)
				{//加上对刚刚对当前像素产生影响的一列的分数
					score += pscore[temp_i + blocksize];
				}
			}
			//到此以i,j像素为中心的块的信息收集完毕，得分储存到了score中。

			//******此处根据得分进行相应的处理。***********
			pRstRaw[kk + temp_i] = min(255,score);
			//*********************************************
		}

	}

	memcpy(pOut, pRstRaw, width*height);
	////-------------释放内存-------------
	delete []pRstRaw;
	delete []pscore;
	////----------------------------------
}