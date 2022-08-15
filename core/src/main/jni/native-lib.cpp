#include <jni.h>
#include <string>

//
// Created by asani on 15/08/22.
//

using namespace std;

const string BASE_URL = "https://api.themoviedb.org/3/";
const string API_KEY = "ce3747f9814de0e3fc3292c9ef36fcdb";

extern "C"
JNIEXPORT jstring JNICALL
Java_com_amary_core_constant_BaseKey_baseURL(JNIEnv *env, jobject _) {
    return env->NewStringUTF(BASE_URL.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_amary_core_constant_BaseKey_apiKey(JNIEnv *env, jobject _) {
    return env->NewStringUTF(API_KEY.c_str());
}