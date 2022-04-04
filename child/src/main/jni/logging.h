//
// Created by Ye Myat Thu on 8/12/21.
//
#include <android/log.h>

#ifndef SHIELDSAMPLE_LOGGING_H
#define SHIELDSAMPLE_LOGGING_H

#define GR_LOG_TAG "SHIELD"
#ifdef NDEBUG
#define GR_LOGI(...)
#define GR_LOGE(...)
#define GR_LOGW(...)
#define GR_LOGD(...)
#define GR_LOGV(...)
#else
#define GR_LOGI(...) __android_log_print(ANDROID_LOG_INFO,GR_LOG_TAG,__VA_ARGS__)
#define GR_LOGE(...) __android_log_print(ANDROID_LOG_ERROR,GR_LOG_TAG,__VA_ARGS__)
#define GR_LOGW(...) __android_log_print(ANDROID_LOG_WARN,GR_LOG_TAG,__VA_ARGS__)
#define GR_LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,GR_LOG_TAG,__VA_ARGS__)
#define GR_LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE,GR_LOG_TAG,__VA_ARGS__)
#endif
#endif //SHIELDSAMPLE_LOGGING_H
