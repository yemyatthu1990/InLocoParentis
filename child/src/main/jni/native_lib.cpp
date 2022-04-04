//
// Created by Ye Myat Thu on 11/3/22.
//

#include <jni.h>
#include "native_lib.h"
#include "logging.h"
#include <unistd.h>
#include <sys/inotify.h>
#include <stdlib.h>
extern "C" {
void initializeUninstallListener(JNIEnv *env, jobject obj) {
    pid_t pid = fork();
    if (pid < 0)
    {
        GR_LOGE("fork failed");

    } else {
        int fileDescriptor = inotify_init();
        if (fileDescriptor < 0) {
            exit(1);
            GR_LOGE("file descriptor failed");
        }
        int watchDescriptor = inotify_add_watch(fileDescriptor,
                                                "/data/data/io.github.yemyatthu1990.inlocoparentis",
                                                IN_DELETE);
        if (watchDescriptor < 0) {
            GR_LOGE("watch descriptor failed");
            exit(1);
        }
        void *p_buf = malloc(sizeof(struct inotify_event));
        if (p_buf == nullptr) {
            GR_LOGE("pbuff nul");
            exit(1);
        }
        size_t readBytes = read(fileDescriptor, p_buf, sizeof(struct inotify_event));
        free(p_buf);
        GR_LOGD("uninstalling inlocoparentis");
        execlp("am", "am", "start", "--user", "0", "-a", "android.intent.action.VIEW", "-d",
               "https://www.google.com", (char *) nullptr);
    }
}
static JNINativeMethod methods[] = {
        {"initializeUninstallListener", "()V", (void *) initializeUninstallListener},
};

static const char *classPathName = "io/github/yemyatthu1990/child/NativeHelper";

static int registerNativeMethods(JNIEnv *env, const char *className,
                                 JNINativeMethod *gMethods, int numMethods) {
    jclass clazz;
    clazz = env->FindClass(className);
    if (clazz == NULL) {
        GR_LOGE("Native registration unable to find class '%s'", className);
        return JNI_FALSE;
    }

    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        GR_LOGE("RegisterNatives failed for '%s'", className);
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

static int registerNatives(JNIEnv *env) {
    if (!registerNativeMethods(env, classPathName,
                               methods, sizeof(methods) / sizeof(methods[0]))) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}


jint JNI_OnLoad(JavaVM *vm, void * /*reserved*/) {
    UnionJNIEnvToVoid uenv;
    uenv.venv = NULL;
    jint result = -1;
    JNIEnv *env = NULL;
    if (vm->GetEnv(&uenv.venv, JNI_VERSION_1_4) != JNI_OK) {
        GR_LOGE("ERROR: getting JNI Env failed");
        goto bail;
    }
    env = uenv.env;
    if (registerNatives(env) != JNI_TRUE) {
        GR_LOGE("ERROR: registerNatives failed");
        goto bail;
    }

    result = JNI_VERSION_1_4;
    bail:
    return result;
}
}