package com.dzq.rxjava_retrofit.application;


/**
 * Created by dingzuoqiang on 2017/6/16.
 * Email: 530858106@qq.com
 */

public class Urls {

    public static String BASE_URL_TEST = "http://10.200.7.5:10095/";//test    http://hz.dev.huzhuchinaa.cn/            http://ybb.s3.natapp.cc/
    public static String BASE_URL_ONLINE = "https://api.yiqihuzhu.com/";

    public static String H5_URL_TEST = "http://hz.dev.huzhuchinaa.cn/";//test   http://ybb.s3.natapp.cc/
    public static String H5_URL_ONLINE = "https://m.yiqihuzhu.com/";

    public static String objectKey_URL_TEST = "http://s2.huzhuchinaa.cn/";
    public static String objectKey_URL_ONLINE = "https://s1.yiqihuzhu.com/";

    public static String objectKey_directory_TEST = "offline/upload/";// oss 虚拟目录
    public static String objectKey_directory_ONLINE = "www/public/upload/";

//    个人信息采集授权声明  互助域名/yidaibao/userinfogatherstatement.html
//    借款合同  互助域名/yidaibao/registrationprotocol.html
//    平台注册协议  互助域名/yidaibao/loancontract.html
//    咨询服务协议  互助域名/yidaibao/consultancy.html

    private static String userinfogatherstatementUrl = "yidaibao/userinfogatherstatement.html";
    private static String jiekuanhetongUrl = "yidaibao/registrationprotocol.html";
    private static String regProtocolUrl = "yidaibao/loancontract.html";
    private static String consultancyUrl = "yidaibao/consultancy.html";


    public static String dynamicBaseUrl() {
        if (AppEnvHelper.currentEnv() == AppEnvEnum.ONLINE) {
            return BASE_URL_ONLINE;
        }
        return BASE_URL_TEST;
    }

    public static String dynamicBaseUrlForH5() {
        if (AppEnvHelper.currentEnv() == AppEnvEnum.ONLINE) {
            return H5_URL_ONLINE;
        }
        return H5_URL_ONLINE;
    }

    public static String refererUrl() {
        if (AppEnvHelper.currentEnv() == AppEnvEnum.ONLINE) {
            return BASE_URL_ONLINE;
        }
        return BASE_URL_TEST;
    }

    public static String getObjectKey_URL() {
        if (AppEnvHelper.currentEnv() == AppEnvEnum.ONLINE) {
            return objectKey_URL_ONLINE;
        }
        return objectKey_URL_TEST;
    }


    public static String getConsultancyUrl() {
        return dynamicBaseUrlForH5() + consultancyUrl;
    }

    public static String getUserinfogatherstatementUrl() {
        return dynamicBaseUrlForH5() + userinfogatherstatementUrl;
    }

    public static String getJiekuanhetongUrl() {
        return dynamicBaseUrlForH5() + jiekuanhetongUrl;
    }

    public static String getRegProtocolUrl() {
        return dynamicBaseUrlForH5() + regProtocolUrl;
    }

    public static String getObjectKey_directory() {
        if (AppEnvHelper.currentEnv() == AppEnvEnum.ONLINE) {
            return objectKey_directory_ONLINE;
        }
        return objectKey_directory_TEST;
    }
}
