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
	void Dilate(int width, int height, unsigned char *pIn, unsigned char ntimes, unsigned char *pOut=0, unsigned char *pRoi =0);//����
	void IsSame(int length, unsigned char *p1, unsigned char *p2, unsigned char *pOut = 0);//�����Ƿ���ͬ
	void MaximumPoint(int width, int height, unsigned char *pIn, unsigned char ntimes, unsigned char *pOut = 0);//�Ƿ�Ϊ�ֲ����ֵ������ȡԭ����ֵ������ȡ0
	void Smooth(int width, int height, unsigned char *pIn, unsigned char *pOut, int algorithm, int para1 = -1, int para2 = -1, unsigned char *pRoi=0);//����ƽ���˲�
	void MinMaxLoc(int width, int height, unsigned char *pIn, unsigned char *pMinvalue, int *pMinx, int *pMiny, unsigned char *pMaxvalue,  int *pMaxx, int *pMaxy );//��ͼ���еõ���Сֵ�����ֵ����λ�á�
	void CountLocalInterestPoints(int width, int height, unsigned char *pIn, unsigned char *pOut, unsigned char ch, int blocksize);
	// ��ͼ����
	void GetLocalImage(unsigned char* pWholeImg, int wholewidth, int startwidth, int endwidth, int startheight, int endheight, unsigned char* pLocalImg);//�õ���ͼ
	void SetLocalImage(unsigned char* pWholeImg, int wholewidth, int startwidth, int endwidth, int startheight, int endheight, unsigned char* pLocalImg);
	void GetLineFromImage(unsigned char* pWholeImg, int width, int height, int linepos, int dim, unsigned char *pLine);//�õ�ͼ���е�ĳ��ֱ�ߡ�
	template <typename T>
	void SetLineInImage(T *pWholeImg, int width, int height, int linepos, int dim, T *pLine);//����ͼ���е�ĳ��ֱ�ߡ�
	// �ռ䣬����
	void DistanceTransform(unsigned char* pImg, int width,int height, unsigned char subimg, double *pOutImg, int distype = 1);//����ͼ����ÿ���㵽ͼ���Ӽ�����subimg����������̾��룬������㷽ʽ��distype������

private:
	void Convlt(int width, int height, unsigned char *pIn, int widthF, int heightF, float *pFilter, unsigned char *pOut, unsigned char *pRoi);//���
	void Smooth_Mean(int width, int height, unsigned char *pIn, unsigned char *pOut, int nBlockRadius = 1, unsigned char *pRoi = 0);
	void Smooth_Middle(int width, int height, unsigned char *pIn, unsigned char *pOut, int nBlockRadius = 1, unsigned char *pRoi = 0);
	void Smooth_Gauss(int width, int height, unsigned char *pIn, unsigned char *pOut,  int sigma = 6, unsigned char *pRoi = 0);
	void Smooth_Bilateral(int width, int height, unsigned char *pIn, unsigned char *pOut,  int sigma = 6, unsigned char *pRoi = 0);//������Χ���ƶȵ���Ϣ���м�Ȩƽ��
};

//�ø���ֱ���޸�ͼ��,linepos����ڼ��У��У���dim������(1)����(2)
template <typename T1>
void ImageProcess::SetLineInImage(T1 *pWholeImg, int width, int height, int linepos, int dim, T1 *pLine){
	if (dim == 1){//��
		memcpy(pWholeImg + linepos * width, pLine, width*sizeof(T1));
	}
	else if (dim == 2){
		T1* pImg = pWholeImg + linepos;
		for (int i = 0 ; i < height; i++,pImg += width,pLine++){
			*pImg = *pLine;
		}
	}
}