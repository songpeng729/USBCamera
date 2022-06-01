/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class centipede_livescan_MosaicNative */

#ifndef _Included_centipede_livescan_MosaicNative
#define _Included_centipede_livescan_MosaicNative
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    FastInit
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_centipede_livescan_MosaicNative_FastInit
  (JNIEnv *, jclass, jint vid, jint pid, jint fd, jint busNum, jint devAddr, jstring usbfs_str);
  //(JNIEnv *, jclass, jint);

JNIEXPORT jboolean JNICALL Java_centipede_livescan_MosaicNative_FastInitIsFinger
  (JNIEnv *, jclass);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    FastReadSendorImg
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_centipede_livescan_MosaicNative_FastReadSendorImg
  (JNIEnv *, jclass, jint);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    FastMosaicNew
 * Signature: (I[B)I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_FastMosaicNew
  (JNIEnv *, jclass, jint, jbyteArray);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    FastEnd
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_centipede_livescan_MosaicNative_FastEnd
  (JNIEnv *, jclass);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    ReadInit
 * Signature: (I)V
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_ReadInit
  (JNIEnv *, jclass, jint vid, jint pid, jint fd, jint busNum, jint devAddr, jstring usbfs_str);
  // (JNIEnv *, jclass, jint);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    ReadImg
 * Signature: ([B)V
 */
JNIEXPORT void JNICALL Java_centipede_livescan_MosaicNative_ReadImg
  (JNIEnv *, jclass, jbyteArray);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    ReadEnd
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_centipede_livescan_MosaicNative_ReadEnd
  (JNIEnv *, jclass);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    IsSupportIdentifyFinger
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsSupportIdentifyFinger
  (JNIEnv *, jclass);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    IsSupportImageQuality
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsSupportImageQuality
  (JNIEnv *, jclass);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    IsSupportFingerQuality
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsSupportFingerQuality
  (JNIEnv *, jclass);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    IsSupportImageEnhance
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsSupportImageEnhance
  (JNIEnv *, jclass);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    IsSupportRollCap
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsSupportRollCap
  (JNIEnv *, jclass);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    SetRollMode
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_SetRollMode
  (JNIEnv *, jclass, jint);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    ImageQuality
 * Signature: ([BII)I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_ImageQuality
  (JNIEnv *, jclass, jbyteArray, jint, jint);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    FingerQuality
 * Signature: ([BII)I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_FingerQuality
  (JNIEnv *, jclass, jbyteArray, jint, jint);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    ImageEnhance
 * Signature: ([BII[B)I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_ImageEnhance
  (JNIEnv *, jclass, jbyteArray, jint, jint, jbyteArray);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    IsFinger
 * Signature: ([BII)I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_IsFinger
  (JNIEnv *, jclass, jbyteArray, jint, jint);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    GetErrorInfo
 * Signature: (I[B)I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_GetErrorInfo
  (JNIEnv *, jclass, jint, jbyteArray);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    GetVersion
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_GetVersion
  (JNIEnv *, jclass);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    GetDesc
 * Signature: ([B)I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_GetDesc
  (JNIEnv *, jclass, jbyteArray);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    SetGain
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_SetGain
  (JNIEnv *, jclass, jint);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    GetGain
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_GetGain
  (JNIEnv *, jclass);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    SetExposure
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_SetExposure
  (JNIEnv *, jclass, jint);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    GetExposure
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_GetExposure
  (JNIEnv *, jclass);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    SetContrast
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_SetContrast
  (JNIEnv *, jclass, jint);

/*
 * Class:     centipede_livescan_MosaicNative
 * Method:    GetContrast
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_centipede_livescan_MosaicNative_GetContrast
  (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif
