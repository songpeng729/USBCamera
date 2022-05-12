#/*
# * UVCCamera
# * library and sample to access to UVC web camera on non-rooted Android device
# * 
# * Copyright (c) 2014-2017 saki t_saki@serenegiant.com
# * 
# * File name: Android.mk
# * 
# * Licensed under the Apache License, Version 2.0 (the "License");
# * you may not use this file except in compliance with the License.
# *  You may obtain a copy of the License at
# * 
# *     http://www.apache.org/licenses/LICENSE-2.0
# * 
# *  Unless required by applicable law or agreed to in writing, software
# *  distributed under the License is distributed on an "AS IS" BASIS,
# *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# *  See the License for the specific language governing permissions and
# *  limitations under the License.
# * 
# * All files in the folder are under this Apache License, Version 2.0.
# * Files in the jni/libjpeg, jni/libusb, jin/libuvc, jni/rapidjson folder may have a different license, see the respective files.
#*/

######################################################################
# Make shared library liblivescan.so
######################################################################
LOCAL_PATH	:= $(call my-dir)

#libGBFP prebuilt
include $(CLEAR_VARS)
GBFP_NAME := GBFP
LOCAL_MODULE           := $(GBFP_NAME)
LOCAL_SRC_FILES        := $(PWD)/src/main/jni/libs/$(TARGET_ARCH_ABI)/lib$(GBFP_NAME).so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

######################################################################
# Make shared library liblivescan.so
######################################################################
CFLAGS := -Werror

LOCAL_C_INCLUDES := \
		$(LOCAL_PATH)/ \
		$(LOCAL_PATH)/../ \
		$(LOCAL_PATH)/../rapidjson/include \

LOCAL_CFLAGS := $(LOCAL_C_INCLUDES:%=-I%)
LOCAL_CFLAGS += -DANDROID_NDK
LOCAL_CFLAGS += -DLOG_NDEBUG
LOCAL_CFLAGS += -DACCESS_RAW_DESCRIPTORS
LOCAL_CFLAGS += -O3 -fstrict-aliasing -fprefetch-loop-arrays

LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -ldl
LOCAL_LDLIBS += -llog
LOCAL_LDLIBS += -landroid

LOCAL_SHARED_LIBRARIES += usb100 uvc GBFP

LOCAL_ARM_MODE := arm

LOCAL_SRC_FILES := \
		_onload.cpp \
		utilbase.cpp \
		UVCCamera.cpp \
		UVCPreview.cpp \
		UVCButtonCallback.cpp \
		UVCStatusCallback.cpp \
		Parameters.cpp \
		serenegiant_usb_UVCCamera.cpp \
        Correction.cpp \
        FingerAPI.cpp \
        sensor.cpp \
        src/mosaicInterface.cpp \
        src/kernel/FPMosaic.cpp \
        src/kernel/ImageProcess.cpp \
        src/kernel/FptQualityAssessment.cpp \
        src/centipede_livescan_MosaicNative.cc

LOCAL_MODULE    := livescan
include $(BUILD_SHARED_LIBRARY)
