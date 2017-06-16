package com.dzq.rxjava_retrofit.application;


import com.dzq.rxjava_retrofit.BuildConfig;


/**
 * Created by dingzuoqiang on 2017/6/16.
 * Email: 530858106@qq.com
 */


public class AppEnvHelper {

    public static AppEnvEnum currentEnv() {

        if (BuildConfig.BUILD_TYPE.equals("release")) {
            return AppEnvEnum.ONLINE;
        }

        return AppEnvEnum.DEBUG;
    }

}
