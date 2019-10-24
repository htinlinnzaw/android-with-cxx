#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_mycapplication_MainActivity_stringFromJni(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C" JNIEXPORT jint JNICALL
Java_com_example_mycapplication_MainActivity_add(
        JNIEnv *env,
jobject, jint x, jint y /* this */) {

return x + y;
}
extern "C" JNIEXPORT jint JNICALL
Java_com_example_mycapplication_MainActivity_sub(
        JNIEnv *env,
        jobject, jint x, jint y /* this */) {

return x - y;
}
extern "C" JNIEXPORT jint JNICALL
Java_com_example_mycapplication_MainActivity_multiply(
        JNIEnv *env,
        jobject, jint x, jint y /* this */) {

return x * y;
}
extern "C" JNIEXPORT jint JNICALL
Java_com_example_mycapplication_MainActivity_divide(
        JNIEnv *env,
        jobject, jint x, jint y /* this */) {

return x / y;
}

