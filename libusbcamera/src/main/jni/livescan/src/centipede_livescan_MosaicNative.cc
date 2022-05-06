#include "centipede_livescan_MosaicNative.h"

#include <android/log.h>
#include <stdint.h>
#include <string.h>
#include <stdio.h>

#include "sensor.h"

#include "mosaicInterface.h"
#define LOG_TAG "MOSAIC"

const uint32_t img_crack_len = 1821;
const uint32_t img_crack[img_crack_len]={167904,168010,168540,168644,169176,169974,167409,169812,168572,169912,168663,169290,168020,169184,167895,169260,
169806,169332,171798,173716,168575,171875,174350,171120,173052,168693,173679,169302,175696,176252,177642,169911,
174375,174933,178281,169960,173040,175000,178920,173096,178154,173712,169234,171215,176875,178856,169264,174376,
178920,180056,169290,171855,174990,180120,170456,172458,167895,169330,169904,170478,167904,170496,176256,182016,
169932,179469,181492,182648,169940,173710,179510,169944,167900,169944,173156,177536,180748,181332,183376,183960,
167889,169940,174335,176972,177558,180781,181367,184590,185176,169932,177576,178164,169330,174935,177000,182015,
185850,169904,170496,174936,178192,182040,185296,169290,170478,174933,178200,178794,182655,186516,189783,169264,
170456,187144,190422,169234,173719,174317,178802,181493,183287,186576,187772,191061,177000,178200,191700,173075,
173677,178192,178794,183309,188426,176972,177576,189052,189656,173013,177558,178164,187254,190284,169328,169936,
171152,171760,177536,181488,182096,189696,190304,169275,170495,182695,183305,183915,190930,167994,176256,180846,
182682,183294,188496,190332,190944,168543,174376,174990,180209,182665,183893,189726,190954,191568,173096,173712,
179564,182028,188496,190344,191576,169332,173040,180147,187254,189726,190344,191580,168020,169260,171120,178870,
180110,191580,169184,169806,175715,179447,190332,190954,191576,175656,176280,178776,186576,189696,190944,191568,
169333,169959,173715,175593,185296,188426,189052,190304,190930,167990,168618,169874,171758,173014,181492,183376,
186516,187144,187772,189656,190284,167895,168525,178920,183960,184590,185850,175696,178856,180120,182016,182648,
185176,168010,168644,169912,173716,174350,176252,178154,180056,167904,168540,169176,169812,186615,187253,187891,
190443,191081,191719,192357,168525,169167,169809,171093,171735,177513,178155,178797,180723,181365,182007,182649,
183933,184575,187143,187785,188427,189014,189658,190302,169898,173774,176358,173016,174960,176256,177552,178200,
179496,180144,180792,182736,185976,169325,184600,190450,167890,168542,169194,171150,187776,191688,175599,176253,
177561,178215,178869,189660,190314,191622,167936,168592,169904,171216,179416,180728,182040,182696,185976,172396,
173054,173712,177002,183253,184569,185227,185885,189175,168630,169290,176880,178200,178860,187770,190410,191730,
173775,174437,180726,181388,183374,189663,167992,168656,169320,175628,178284,183928,184592,185256,191564,169164,
170496,172494,178155,180153,185814,187146,189810,173012,173680,175016,180694,182030,182698,188376,191048,191716,
169175,170515,171855,176880,177550,179560,183915,184585,185255,173040,173712,175056,180096,188496,169174,182654,
190405,191079,167986,168662,173056,178802,179478,180154,185224,186576,187252,190294,191646,169839,170517,175602,
176280,177636,181365,182043,187128,187806,169320,178840,191080,169818,174933,175615,176297,182094,187891,169290,
169974,173052,173736,178866,188442,189810,171157,175616,180075,180761,182133,184534,190365,191051,169936,173720,
178192,179568,181976,187136,168015,171120,176295,180780,184575,190440,169194,173692,175076,178190,181996,186494,
190300,191684,172459,180093,180787,184604,189809,169824,171216,173652,178176,178872,179568,182004,189660,191052,
169265,177641,180084,180782,171150,175000,185850,169182,187785,190944,191646,167904,171776,178816,185856,173676,
176853,177559,180736,183913,184619,190973,169212,172398,177000,179478,183372,185850,189036,190452,167915,168625,
175015,178920,180695,184600,191700,169812,171236,173016,177644,180136,180848,184052,187256,189036,169932,172431,
176358,178857,181356,184569,188496,167902,168618,169334,176852,178284,180074,180790,183296,184012,186518,168012,
169807,173038,176269,179500,182013,185244,187757,174960,178200,178920,183960,190440,171114,177612,180139,182666,
185193,188442,191691,168692,173036,178828,183896,190412,175692,185856,188397,190938,168532,169260,174356,179452,
187096,189644,167900,168630,170455,176295,183960,186515,191625,167994,169824,182634,185196,191052,171756,176894,
184601,185335,189739,191574,168544,171120,171856,173696,176272,186576,188416,191728,167895,168633,170478,171216,
182655,185238,185976,190404,167980,182040,189810,169176,171773,172515,179564,181419,183274,184016,185871,186613,
169260,171120,178188,180792,182652,185256,187116,191580,168596,173072,177548,179413,182770,190976,191722,172414,
178772,181390,185878,188496,190366,169875,174375,177000,180750,183375,191625,173712,182736,184616,186496,188376,
191008,169273,171158,173797,179452,180206,183976,186615,170478,178794,185976,191646,167897,168655,176235,180783,
184573,191016,171760,180120,168021,169164,169926,173736,175641,181356,189738,171136,174956,175720,178776,169286,
178861,180776,186521,168576,170496,172416,174336,176256,178176,180096,182016,183936,185856,187776,189696,191616,
168630,178255,181335,189035,191730,168682,175630,177560,190298,178794,179568,184599,190404,191565,168004,169168,
169944,178868,181972,185852,168048,169215,178162,180107,189054,169260,176280,182130,169303,170476,172431,177514,
179469,185334,186507,190417,173656,175616,177576,179536,191688,168597,177636,178815,180780,187854,189033,168632,
169814,174936,180846,182028,190302,191090,180120,182095,185255,188415,191575,192365,167904,173052,178200,181368,
184536,186516,188496,175077,176268,178253,179444,183414,184605,189766,190957,167956,179498,180692,181488,182682,
185866,187856,189050,167979,169176,169974,176358,180747,185934,187131,172400,175600,168019,172430,174435,175638,
177643,180851,189673,172458,176880,180096,187734,168051,169260,173693,176917,180141,184574,189813,173720,174932,
178164,190284,191092,169290,174960,178200,179415,182655,185895,189135,191565,169302,179452,187166,190414,171754,
183964,189662,191697,169320,176256,185232,186456,188496,190944,169326,175052,176279,180778,182005,186504,189776,
191003,169330,171790,182040,191060,169332,171798,177552,183306,190293,169332,173040,176336,184576,185812,190344,
191580,169330,178829,182133,183372,185850,169326,173052,174294,181332,187128,190440,169320,173055,178865,180110,
181355,187165,188410,189655,191730,173056,181376,189696,190944,168051,169302,173055,177642,178893,180144,182646,
189735,190986,169290,171798,173052,182666,185174,189772,168019,171790,182684,186455,189807,191064,169260,173040,
178920,183960,192360,167979,174294,178925,183977,185240,186503,190292,167956,171754,185258,186524,189056,182736,
167904,169176,184016,190376,191648,178925,186575,169974,177642,178920,190422,191700,190442,191723,168632,175052,
176336,187892,168597,172458,178893,186615,172430,173720,169814,172400,173693,176279,178865,190933,168048,174960,
176256,177552,180144,182736,189648,190944,168004,169303,174932,178829,189654,190953,169260,180110,184016,189658,
169215,174435,186615,189660,169168,170476,181376,182684,189660,168682,173052,181355,182666,183977,189658,168630,
169944,175638,181332,182646,183960,189654,168576,175600,176917,185258,186575,187892,189648,176880,178200,185240,
168021,172431,175077,178164,182133,190953,169286,176358,179452,180778,186524,190944,167897,173656,177643,179415,
185174,186503,190933,169164,170496,174936,176268,182040,183372,168655,182005,186455,169926,183306,190442,180141,
190422,171136,175616,180096,184576,189056,169273,172416,178253,190376,191723,178200,185850,191700,170478,179498,
180851,182655,185812,187165,168596,171760,176280,177636,179444,183964,187128,190292,191648,169875,177576,180747,
189807,167980,171158,174336,177514,180692,185232,188410,189772,167895,169260,175630,178815,189735,169176,173736,
180120,181488,186504,189696,191064,168633,186456,189655,168544,174956,178162,179536,181368,184574,190986,192360,
167994,176256,179469,180846,182682,185895,190944,167900,171120,175720,177560,180780,190440,172414,173797,175641,
178868,182095,187166,168630,170478,173712,178794,182028,183414,188496,190344,191730,168532,171773,178255,180107,
190293,169824,171216,173072,178176,179568,189776,169260,171120,172515,174375,176235,184605,191580,168692,184536,
185934,189662,191060,170455,171856,178861,182130,185866,187734,189135,191003,168012,171756,178776,190944,167902,
180096,181972,185255,187131,190414,168618,181335,186516,189813,190284,191697,173696,177000,180776,187856,167915,
169334,169807,178794,189673,191092,191565,171114,180120,182016,185334,168625,189050,169932,177548,179452,181356,
188496,167904,169812,180783,184599,186507,188415,169212,173036,178772,180206,187854,189766,174356,176272,178188,
183936,185852,171236,179413,189033,190957,169182,173038,176894,180750,172431,176295,190302,175692,179564,185856,
169265,181390,191090,191575,173016,174960,180792,182736,189054,168015,172398,184573,186521,190417,192365,169824,
171776,183976,169194,171150,181419,183375,187776,191688,182770,176269,182652,184616,189035,171216,173676,177612,
182040,185976,190404,175015,179452,190298,169936,176358,176852,178828,183274,189696,169290,178200,182655,186615,
191565,171120,184016,186496,167986,169974,172459,178920,185878,169320,169818,178284,185256,189738,191730,168662,
171157,173652,177644,180139,182634,191616,175000,177000,179500,169839,176853,178857,185871,188376,169174,173692,
185238,170517,177559,180074,184601,186613,187116,178920,183960,185976,188496,191016,169175,173720,180790,185335,
167992,173052,175076,180136,182666,185196,179478,182013,186576,191646,168656,169164,173736,178816,180848,181356,
183896,191008,170515,177641,180695,190366,169320,168630,176295,183960,186515,191625,167936,170496,173056,175616,
178176,180736,183296,185856,188416,190976,169290,171855,174933,185193,189810,168592,178872,184012,187096,191722,
167890,173040,175615,178190,191580,179568,180084,185244,190404,168542,173712,176297,184052,184569,189739,169904,
172494,173012,175602,178192,180782,183372,169194,180093,188397,173680,176280,184600,169325,175056,180787,183913,
186518,189644,191728,171216,179568,188442,191052,191574,178866,182004,184619,187757,172396,175016,177636,180780,
168525,171150,173775,180075,185850,191625,169898,173054,178840,181996,187256,190412,190938,169167,174437,180761,
167904,173712,176880,185856,188496,169809,175628,178802,181976,190440,168010,168540,177550,179478,182133,185850,
189036,191691,168644,169176,184604,167895,171093,180154,169812,173016,182094,189036,167990,168525,171735,178155,
181365,184575,187785,169912,176880,179560,180096,168618,175599,178284,182043,173774,177002,184534,190452,176253,
186494,174960,178200,191700,169333,169874,180153,180694,190973,168020,178860,182654,169959,177561,169184,176256,
187136,190944,178215,182030,189660,169260,169806,176358,180726,191646,171758,178869,179416,182698,189809,169332,
173716,177552,181388,185224,167994,168543,183915,191052,174350,178200,190300,173014,180728,184585,187891,188442,
171120,186576,187128,190440,177513,185255,179496,183374,183928,187252,187806,191684,169275,173715,178155,182040,
189810,190365,175696,176252,180144,184592,169328,178797,182696,183253,191051,180792,185256,185814,169936,170495,
173040,175593,184569,188496,173096,178154,187146,191080,171152,175656,180723,185227,190294,173712,182736,188376,
171760,175715,176280,181365,185885,190405,169234,178856,182007,185976,191079,191646,169264,174376,178920,180056,
184600,182649,187770,169290,174990,180120,189810,173013,170456,191048,167889,178776,183933,189663,169330,169904,
170478,185976,191716,167900,173075,184575,189175,170496,176256,182016,187776,173677,178870,179447,190410,191564,
169932,181492,182648,169940,189660,173719,180110,191730,169944,190314,167904,174317,179564,180147,187143,169944,
177536,183376,183960,167895,186615,187785,169940,176972,177558,184590,185176,190450,191622,180209,187253,188427,
189014,169932,177576,178164,191688,174933,187891,189658,169330,177000,185850,174936,180846,182028,190302,169904,
170496,178192,185296,173156,174935,169290,170478,178200,178794,186516,174335,182665,169264,170456,187144,181488,
182682,190443,169234,178802,186576,187772,173710,182096,182695,183294,183893,191081,177000,178200,183305,191719,
178192,178794,188426,171855,172458,183915,192357,176972,177576,189052,189656,171215,177558,178164,187254,190284,
169960,181493,177536,189696,190304,168693,169302,169911,183309,190930,176256,188496,190332,190944,168575,183287,
174376,174990,189726,190954,191568,167895,182040,182655,173096,173712,188496,190344,191576,180781,182015,169332,
173040,187254,189726,190344,191580,179510,180748,181367,168020,169260,171120,191580,179469,181332,169184,169806,
190332,190954,191576,186576,189696,190944,191568,171875,174375,175000,176875,185296,188426,189052,190304,190930,
167409,168663,169290,171798,173052,173679,174933,181492,183376,186516,187144,187772,189656,190284,168572,178920,
183960,184590,185850,175696,178856,180120,182016,182648,185176,168010,168644,169912,173716,174350,176252,178154,
180056,167904,168540,169176,169812,169974,177642,178281,178920,189783,190422,191061,191700};


#define WIDTH 640
#define HEIGHT 640
#define IMG_SIZE  WIDTH * HEIGHT
static unsigned char buffer_1[IMG_SIZE];
static unsigned char buffer_2[IMG_SIZE];
static unsigned char dest_buffer[IMG_SIZE];

static void crack_tmp_img(unsigned char* buf_tmp,int ret){
      if(ret != 0){
        for(int i=0;i<img_crack_len;i++){
          int index = img_crack[i];
          buf_tmp[index] = 200;
        }
      }
}
static void direct_crack_img(){
  for(int i=0;i<img_crack_len;i++){
    int index = img_crack[i];
    dest_buffer[index] = 200;
  }
}
static void ThrowJavaException(JNIEnv *jenv,const char *msg) {
  jclass excep;
  jenv->ExceptionClear();
  excep = jenv->FindClass("java/lang/ArithmeticException");
  if (excep)
    jenv->ThrowNew(excep, msg);
}
/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    ReadInit
 * Signature: (I)V
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_ReadInit
		(JNIEnv *env, jclass, jint vid, jint pid, jint fd, jint busNum, jint devAddr, jstring usbfs_str){
	__android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"Begin init sensor ...");
	const char *c_usbfs = env->GetStringUTFChars(usbfs_str, JNI_FALSE);
    //if (LIKELY(camera && (fd > 0))) {
	    int ret = sensor_int(vid, pid, fd, busNum, devAddr, c_usbfs);
    //}
    env->ReleaseStringUTFChars(usbfs_str, c_usbfs);
	__android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"init sensor result :%d...",ret);
	return ret;
}

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    ReadImg
 * Signature: ([B)V
 */
JNIEXPORT void JNICALL Java_centipede_livescan_MosaicNative_ReadImg
		(JNIEnv *jenv, jclass, jbyteArray buffer){
	int buffer_size = jenv->GetArrayLength(buffer);
	unsigned char * buffer_bin = (unsigned char *)jenv->GetByteArrayElements(buffer, JNI_FALSE);
	sensor_readimg(buffer_bin);
	jenv->ReleaseByteArrayElements(buffer, (jbyte *) buffer_bin, 0);
}

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    ReadEnd
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_centipede_livescan_MosaicNative_ReadEnd
		(JNIEnv *, jclass){
	sensor_exit();
	__android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"Finish Read Image. ");
}

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    start
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_centipede_livescan_MosaicNative_FastInit
		(JNIEnv *env, jclass, jint vid, jint pid, jint fd, jint busNum, jint devAddr, jstring usbfs_str){

   //add by wumin 20161116
   memset(buffer_1,0,IMG_SIZE);
   memset(buffer_2,0,IMG_SIZE);
   //end by wumin 20161116

   __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"Begin init sensor ...");
   const char *c_usbfs = env->GetStringUTFChars(usbfs_str, JNI_FALSE);
   //int ret = sensor_int(delay);
   int ret = sensor_int(vid, pid, fd, busNum, devAddr, c_usbfs);
   __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"init sensor result :%d...",ret);
   __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"Begin init mosaic ...");
   MOSAIC_Init();
   __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"Finish init! ");
   env->ReleaseStringUTFChars(usbfs_str, c_usbfs);
   /*
  	for(;;){
  		ret = sensor_readimg(buffer_1);
			__android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"read image ret :%d ",ret);
  		if(MOSAIC_IsFinger(buffer_1,WIDTH,HEIGHT) > 0) break;
  	}
    memset(dest_buffer,0,IMG_SIZE);
  	MOSAIC_Start(dest_buffer,WIDTH,HEIGHT);
    */
  }
JNIEXPORT jboolean JNICALL Java_centipede_livescan_MosaicNative_FastInitIsFinger
  (JNIEnv *env, jclass){

    int ret = sensor_readimg(buffer_1);
    __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"read image ret :%d ",ret);
    if(MOSAIC_IsFinger(buffer_1,WIDTH,HEIGHT) > 0) {
      memset(dest_buffer,0,IMG_SIZE);
      MOSAIC_Start(dest_buffer,WIDTH,HEIGHT);
      return true;
    }else{
      return false;
    }
  }

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    FastReadSendorImg
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_centipede_livescan_MosaicNative_FastReadSendorImg
  (JNIEnv *, jclass, jint seq){
    if(seq == 1)
      sensor_readimg(buffer_1);
    else if(seq == 2)
      sensor_readimg(buffer_2);
 }

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    FastMosaicNew
 * Signature: (I[B)V
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_FastMosaicNew
  (JNIEnv * jenv, jclass, jint seq, jbyteArray buffer){
  	int32_t ret=0;
    if(seq == 1)
  		ret = MOSAIC_DoMosaic(buffer_1, WIDTH, HEIGHT);
    else if(seq == 2)
      ret = MOSAIC_DoMosaic(buffer_2, WIDTH, HEIGHT);
	if(0 != MOSAIC_GetRollMode() || 0 >= ret){
      int buffer_size = jenv->GetArrayLength(buffer);
      unsigned char * buffer_bin = (unsigned char *)jenv->GetByteArrayElements(buffer, JNI_FALSE);
      memcpy(buffer_bin,dest_buffer,buffer_size);
      crack_tmp_img(buffer_bin,ret);
      jenv->ReleaseByteArrayElements(buffer, (jbyte *) buffer_bin, 0);
	}
    return ret;
}

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    FastEnd
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_centipede_livescan_MosaicNative_FastEnd
  (JNIEnv *, jclass){
  	MOSAIC_Close();
    sensor_exit();
   __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,"Finish Exist Fast mosaic. ");
}


//3 Æ´½Ó½Ó¿ÚÊÇ·ñÌá¹©ÅÐ¶ÏÍ¼ÏñÎªÖ¸ÎÆµÄº¯Êý
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsSupportIdentifyFinger
	(JNIEnv * jenv, jclass)
{
	int32_t ret=0;
	ret = MOSAIC_IsSupportIdentifyFinger();
	return ret;
}

//4 Æ´½Ó½Ó¿ÚÊÇ·ñÌá¹©ÅÐ¶ÏÍ¼ÏñÖÊÁ¿µÄº¯Êý
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsSupportImageQuality
	(JNIEnv * jenv, jclass)
{
	int32_t ret = MOSAIC_IsSupportImageQuality();
	return ret;
}

//5 Æ´½Ó½Ó¿ÚÊÇ·ñÌá¹©ÅÐ¶ÏÖ¸ÎÆÖÊÁ¿µÄº¯Êý
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsSupportFingerQuality
	(JNIEnv * jenv, jclass)
{
	int32_t ret = MOSAIC_IsSupportFingerQuality();
	return ret;
}

//6 ½Ó¿ÚÊÇ·ñÌá¹©Æ´½ÓÖ¸ÎÆµÄÍ¼ÏñÔöÇ¿¹¦ÄÜ
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsSupportImageEnhance
	(JNIEnv * jenv, jclass)
{
	int32_t ret = MOSAIC_IsSupportImageEnhance();
	return ret;
}

//7 ÅÐ¶ÏÊÇ·ñÖ§³Ö¹ö¶¯²É¼¯º¯Êý
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsSupportRollCap
	(JNIEnv * jenv, jclass)
{
	int32_t ret = MOSAIC_IsSupportRollCap();
	return ret;
}

//8 Ñ¡ÔñÆ´½Ó·½Ê½µÄº¯Êý
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_SetRollMode
	(JNIEnv * jenv, jclass, jint nRollMode)
{
	int32_t ret = MOSAIC_SetRollMode((int)nRollMode);
	return ret;
}

//12 ÅÐ¶ÏÍ¼ÏñÖÊÁ¿
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_ImageQuality
	(JNIEnv * jenv, jclass, jbyteArray pDataBuf, jint nWidth, jint nHeight)
{
	jbyte *pDataBufInn = jenv->GetByteArrayElements(pDataBuf, 0);
	int32_t score = MOSAIC_ImageQuality((unsigned char*)pDataBufInn, (int)nWidth, (int)nHeight);
	jenv->ReleaseByteArrayElements(pDataBuf, pDataBufInn, 0);
	return score;
}

//13 ÅÐ¶ÏÖ¸ÎÆÖÊÁ¿
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_FingerQuality
	(JNIEnv * jenv, jclass, jbyteArray pDataBuf, jint nWidth, jint nHeight)
{
	jbyte *pDataBufInn = jenv->GetByteArrayElements(pDataBuf, 0);
	int32_t score = MOSAIC_FingerQuality((unsigned char*)pDataBufInn, (int)nWidth, (int)nHeight);
	jenv->ReleaseByteArrayElements(pDataBuf, pDataBufInn, 0);
	return score;
}

//14 ¶ÔÍ¼Ïñ½øÐÐÔöÇ¿
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_ImageEnhance
	(JNIEnv * jenv, jclass, jbyteArray pSrcImg, jint nWidth, jint nHeight, jbyteArray pTargetImg)
{
	jbyte *pDataBufInn = jenv->GetByteArrayElements(pSrcImg, 0);
	jbyte *pDataBufOut = jenv->GetByteArrayElements(pTargetImg, 0);
	int32_t ret = MOSAIC_ImageEnhance((unsigned char*)pDataBufInn, (int)nWidth, (int)nHeight, (unsigned char*)pDataBufOut);
	jenv->ReleaseByteArrayElements(pSrcImg, pDataBufInn, 0);
	jenv->ReleaseByteArrayElements(pTargetImg, pDataBufOut, 0);
	return ret;
}

//15 ÅÐ¶ÏÍ¼ÏñÊÇ·ñÎªÖ¸ÎÆ
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsFinger
	(JNIEnv * jenv, jclass, jbyteArray pDataBuf, jint nWidth, jint nHeight)
{
	jbyte *pDataBufInn = jenv->GetByteArrayElements(pDataBuf, 0);
	int32_t ret = MOSAIC_IsFinger((unsigned char*)pDataBufInn, (int)nWidth, (int)nHeight);
	jenv->ReleaseByteArrayElements(pDataBuf, pDataBufInn, 0);
	return ret;
}

//16 È¡µÃÆ´½Ó½Ó¿Ú´íÎóÐÅÏ¢ pszErrorInfo 256 ¸ö×Ö½Ú
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_GetErrorInfo
	(JNIEnv * jenv, jclass, jint nErrorNo, jbyteArray pszErrorInfo)
{
	jbyte *pDataBufInn = jenv->GetByteArrayElements(pszErrorInfo, 0);
	char pStr[256];
	int32_t ret = MOSAIC_GetErrorInfo( (int)nErrorNo, pStr);
	strncpy((char*)pDataBufInn, pStr, 256);
	jenv->ReleaseByteArrayElements(pszErrorInfo, pDataBufInn, 0);
	return ret;
}

//17 È¡µÃ½Ó¿ÚµÄ°æ±¾
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_GetVersion
	(JNIEnv * jenv, jclass)
{
	int32_t ret = MOSAIC_GetVersion();
	return ret;
}

//18 »ñµÃÆ´½Ó½Ó¿ÚµÄËµÃ÷ pszDesc 1024 ¸ö×Ö½Ú
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_GetDesc
	(JNIEnv * jenv, jclass, jbyteArray pszDesc)
{
	jbyte *pDataBufInn = jenv->GetByteArrayElements(pszDesc, 0);
	char pStr[1024];
	int32_t ret = MOSAIC_GetDesc(pStr);
	strncpy((char*)pDataBufInn, pStr, 1024);
	jenv->ReleaseByteArrayElements(pszDesc, pDataBufInn, 0);
	return ret;
}
