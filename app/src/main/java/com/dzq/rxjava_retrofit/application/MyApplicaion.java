package com.dzq.rxjava_retrofit.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by dingzuoqiang on 2017/6/16.
 * Email: 530858106@qq.com
 */

public class MyApplicaion extends Application {
    public static MyApplicaion instance;
    private static String loanKey;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        if (AppEnvHelper.currentEnv() == AppEnvEnum.DEBUG) {

        }
    }

    public MyApplicaion() {
        instance = this;
    }

    public static Context getContext() {
        return MyApplicaion.instance;
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
//        switch (level) {
//            case TRIM_MEMORY_COMPLETE:
//                LogUtil.LogD(MyApplicaion.class, "内存不足，并且该进程在后台进程列表最后一个，马上就要被清理,4.0增加。" +
//                        "系统运行在低内存状态，如果系统没有恢复内存，你的进程是首先被杀死的进程之一。你应该释放所有不重要的资源来恢复你的app状态。");
//                break;
//
//            case TRIM_MEMORY_MODERATE:
//                LogUtil.LogD(MyApplicaion.class, "内存不足，并且该进程在后台进程列表的中部,4.0增加。" +
//                        "系统运行在低内存状态，你的进程在LRU列表中间附近。如果系统变得内存紧张，可能会导致你的进程被杀死");
//                break;
//
//            case TRIM_MEMORY_BACKGROUND:
//                LogUtil.LogD(MyApplicaion.class, "内存不足，并且该进程是后台进程,4.0增加。" +
//                        "系统运行在低内存状态，并且你的进程已经接近LRU列表的顶端(即将被清理).虽然你的app进程还没有很高的被杀死风险，系统可能已经清理LRU里的进程，你应该释放那些容易被恢复的资源，如此可以让你的进程留在缓存里，并且当用户回到app时快速恢复.");
//                break;
//
//            case TRIM_MEMORY_UI_HIDDEN:
//                LogUtil.LogD(MyApplicaion.class, "内存不足，并且该进程的UI已经不可见了,4.0增加");
//                break;
//
//            case TRIM_MEMORY_RUNNING_CRITICAL:
//                LogUtil.LogD(MyApplicaion.class, "内存不足(后台进程不足3个)，并且该进程优先级比较高，需要清理内存。" +
//                        "你的应用还在运行，但系统已经杀死了LRU缓存里的大多数进程，所以你应该在此时释放所有非关键的资源。如果系统无法回收足够的内存，它会清理掉所有LRU缓存，并且开始杀死之前优先保持的进程，像那些运行着service的。4.1增加");
//                break;
//
//            case TRIM_MEMORY_RUNNING_LOW:
//                LogUtil.LogD(MyApplicaion.class, "内存不足(后台进程不足5个)，并且该进程优先级比较高，需要清理内存， " +
//                        "你的应用正在运行，并且不会被杀死，但设备处于内存更低的状态，所以你应该释放无用资源以提高系统性能(直接影响app性能),4.1增加");
//                break;
//
//            case TRIM_MEMORY_RUNNING_MODERATE:
//                LogUtil.LogD(MyApplicaion.class, "内存不足(后台进程超过5个)，并且该进程优先级比较高，需要清理内存。" +
//                        "你的应用正在运行，并且不会被杀死，但设备已经处于低内存状态，并且开始杀死LRU缓存里的内存,4.1增加");
//                break;
//        }
    }


}
