# android build setup
#
# cmake -DCMAKE_TOOLCHAIN_FILE=../android-cmake/android.toolchain.cmake \
#       -DANDROID_NDK=~/dev/android-ndk-r10e \
#       -DCMAKE_BUILD_TYPE=Release                     \
#       -DANDROID_ABI="arm64-v8a"          \
#      <source dir>
#
# 
PROJECT(nirvana-mosaic)
CMAKE_MINIMUM_REQUIRED(VERSION 2.8)

SET(NIRVANA_VERSION_MAJOR 1)
SET(NIRVANA_VERSION_MINOR 15)
SET(NIRVANA_VERSION_PATCH 11)
SET(NIRVANA_VERSION_PATCH_1 11) #validate it on application

INCLUDE(CheckIncludeFiles)
INCLUDE(CheckIncludeFileCXX)

SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${PROJECT_SOURCE_DIR}/libs/${ANDROID_ABI}")

#SET(CMAKE_LIBRARY_PATH ${PROJECT_SOURCE_DIR}/libs/armeabi)
INCLUDE_DIRECTORIES(include)
LIST(APPEND MOSAIC_SOURCE
  src/mosaic_jni_MosaicNative.h
        src/centipede_livescan_MosaicNative.cc
  src/mosaicInterface.cpp
  src/kernel/FPMosaic.cpp
  src/kernel/FPMosaic.h
  src/kernel/FptQualityAssessment.cpp
  src/kernel/ImageProcess.cpp)


ADD_LIBRARY(mosaic SHARED ${MOSAIC_SOURCE})
TARGET_LINK_LIBRARIES(mosaic log usbsensor)

