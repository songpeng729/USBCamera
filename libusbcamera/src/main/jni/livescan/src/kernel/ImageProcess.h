#pragma once

class ImageProcess
{	
public:

	void HistogramEq(int width, int height, unsigned char *pIn, unsigned char *pOut, unsigned char *pRoi=0);
	void Normalize(int width, int height, unsigned char *pIn, unsigned char *pOut, unsigned char *pRoi=0);
	void Binary(int width, int height, unsigned char *pIn, unsigned char thresh, unsigned char *pOut, unsigned char foreground=255, unsigned char background =0, unsigned char *pRoi=0);
	void Skeleton(int width, int height, unsigned char *pIn, unsigned char *pOut, unsigned char *pRoi=0);
	unsigned char Mean(int length, unsigned char *pIn, unsigned char *pRoi=0);
	long Variance(int length, unsigned char *pIn, unsigned char *pRoi=0);
	void Dilate(int width, int height, unsigned char *pIn, unsigned char ntimes, unsigned char *pOut=0, unsigned char *pRoi =0);//膨胀
	void IsSame(int length, unsigned char *p1, unsigned char *p2, unsigned char *pOut = 0);//像素是否相同
	void MaximumPoint(int width, int height, unsigned char *pIn, unsigned char ntimes, unsigned char *pOut = 0);//是否为局部最大值，是则取原来的值，否则取0
	void Smooth(int width, int height, unsigned char *pIn, unsigned char *pOut, int algorithm, int para1 = -1, int para2 = -1, unsigned char *pRoi=0);//各种平滑滤波
	void MinMaxLoc(int width, int height, unsigned char *pIn, unsigned char *pMinvalue, int *pMinx, int *pMiny, unsigned char *pMaxvalue,  int *pMaxx, int *pMaxy );//从图像中得到最小值和最大值及其位置。
	void CountLocalInterestPoints(int width, int height, unsigned char *pIn, unsigned char *pOut, unsigned char ch, int blocksize);
	// 子图处理
	void GetLocalImage(unsigned char* pWholeImg, int wholewidth, int startwidth, int endwidth, int startheight, int endheight, unsigned char* pLocalImg);//得到子图
	void SetLocalImage(unsigned char* pWholeImg, int wholewidth, int startwidth, int endwidth, int startheight, int endheight, unsigned char* pLocalImg);
	void GetLineFromImage(unsigned char* pWholeImg, int width, int height, int linepos, int dim, unsigned char *pLine);//得到图像中的某条直线。
	template <typename T>
	void SetLineInImage(T *pWholeImg, int width, int height, int linepos, int dim, T *pLine);//设置图像中的某条直线。
	// 空间，距离
	void DistanceTransform(unsigned char* pImg, int width,int height, unsigned char subimg, double *pOutImg, int distype = 1);//计算图像中每个点到图像子集（由subimg给出）的最短距离，距离计算方式由distype给出。

private:
	void Convlt(int width, int height, unsigned char *pIn, int widthF, int heightF, float *pFilter, unsigned char *pOut, unsigned char *pRoi);//卷积
	void Smooth_Mean(int width, int height, unsigned char *pIn, unsigned char *pOut, int nBlockRadius = 1, unsigned char *pRoi = 0);
	void Smooth_Middle(int width, int height, unsigned char *pIn, unsigned char *pOut, int nBlockRadius = 1, unsigned char *pRoi = 0);
	void Smooth_Gauss(int width, int height, unsigned char *pIn, unsigned char *pOut,  int sigma = 6, unsigned char *pRoi = 0);
	void Smooth_Bilateral(int width, int height, unsigned char *pIn, unsigned char *pOut,  int sigma = 6, unsigned char *pRoi = 0);//利用周围相似度的信息进行加权平均
};

//用给定直线修改图像,linepos代表第几行（列），dim代表行(1)或列(2)
template <typename T1>
void ImageProcess::SetLineInImage(T1 *pWholeImg, int width, int height, int linepos, int dim, T1 *pLine){
	if (dim == 1){//行
		memcpy(pWholeImg + linepos * width, pLine, width*sizeof(T1));
	}
	else if (dim == 2){
		T1* pImg = pWholeImg + linepos;
		for (int i = 0 ; i < height; i++,pImg += width,pLine++){
			*pImg = *pLine;
		}
	}
}