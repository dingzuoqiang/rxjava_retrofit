package com.dzq.rxjava_retrofit.utils;

import android.util.Log;

import com.dzq.rxjava_retrofit.application.AppEnvEnum;
import com.dzq.rxjava_retrofit.application.AppEnvHelper;

/**
 * Created by dingzuoqiang on 2017/6/16.
 * Email: 530858106@qq.com
 */

public class LogUtil {
    private final static String TAG = "LogUtil";

    public static void LogD(Class classz, String str) {
        if (AppEnvHelper.currentEnv() == AppEnvEnum.DEBUG)
            Log.d(TAG, classz.getCanonicalName() + "--->" + str);
    }

    public static void LogI(Class classz, String str) {
        if (AppEnvHelper.currentEnv() == AppEnvEnum.DEBUG)
            Log.i(TAG, classz.getCanonicalName() + "--->" + str);
    }

    public static void LogE(Class classz, String str) {
        if (AppEnvHelper.currentEnv() == AppEnvEnum.DEBUG)
            Log.e(TAG, classz.getCanonicalName() + "--->" + str);
    }

    public static void LogV(Class classz, String str) {
        if (AppEnvHelper.currentEnv() == AppEnvEnum.DEBUG)
            Log.v(TAG, classz.getCanonicalName() + "--->" + str);
    }

    public static void LogException(Class c, Throwable e) {
        if (AppEnvHelper.currentEnv() == AppEnvEnum.DEBUG) {
            try {
                StringBuilder exceptionInfo = new StringBuilder();
                if (e == null) {
                    exceptionInfo.append("Exception:"
                            + "e is null,probably null pointer exception"
                            + "\n");
                } else {
                    e.printStackTrace();
                    exceptionInfo.append(e.getClass().getCanonicalName() + ":"
                            + e.getMessage() + "\n");
                    StackTraceElement[] stes = e.getStackTrace();
                    for (StackTraceElement ste : stes) {
                        exceptionInfo.append("at " + ste.getClassName() + "$"
                                + ste.getMethodName() + "$" + ste.getFileName()
                                + ":" + ste.getLineNumber() + "\n");
                    }
                }
                LogE(c, exceptionInfo.toString());
            } catch (Exception ex) {
                LogE(c, ex.toString());
            }
        }

    }
}
