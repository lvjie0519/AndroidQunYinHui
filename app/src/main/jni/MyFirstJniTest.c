//
// Created by Administrator on 2018/6/26 0026.
//

#include <com_example_androidqunyinhui_jni_MyFirstJniTest.h>

//返回一个字符串
JNIEXPORT jstring JNICALL Java_com_example_androidqunyinhui_jni_MyFirstJniTest_getString
  (JNIEnv *env, jclass jobj){
    return (*env)->NewStringUTF(env,"MyFirstJniTest 我是用jni调用出来的字符串");
}
//返回 a+b的结果
JNIEXPORT jint JNICALL Java_com_example_androidqunyinhui_jni_MyFirstJniTest_add
  (JNIEnv *env, jclass jobj, jint a, jint b){
  return a+b;
}

