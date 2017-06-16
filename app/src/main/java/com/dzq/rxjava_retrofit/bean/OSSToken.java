package com.dzq.rxjava_retrofit.bean;


/**
 * Created by dingzuoqiang on 2017/6/16.
 * Email: 530858106@qq.com
 */

public class OSSToken {


    /**
     * requestId : E8CC371A-1D49-4DE6-BE50-466C58F3FCBC
     * credentials : {"securityToken":"CAISsgJ1q6Ft5B2yfSjIqfTeGvv6pql047qbQEzrjFhtaLdGnqzakTz2IHBKfnNuB+8fs/wxnW5S6PcYlrhpTJtIAFfJdtBx6ZIS9wqkbtJLFGQbQ/9W5qe+EE2/VjTivqaLEeGbIfrZfvCyEQ6m8gZ43br9cxi7QlWhKufnoJV7b9MRLH/aCD1dH4VuOxdFos0XPmezUPG2KUzFj3b3BkhlsRYGygEZ06mkxdCG4RfzlUDzzvRvx778OZ+5dcJhTvAdNt6+x75xbbGTknwSuVpojP4m0fEdp26e4oDCXgMAuUm8XrCKooE1HmhQfbMnHqNIlv/4mMBjt/bb/4aNkEYTZ7sLDXuGGdj+mZWeRrLzLKUwcbfqJnLKycuTMZr4tQwpZ38BPR9NYcAmLnJgvfiQyPxa58QagAEk5IEVnl2Z+L3NtV+CZ3Dt/cyLc28H1YMqHIuA6+ia54Tzs4UL4ZlQWvzs9U7UYn0iTwLd+6On9u0KZyEO+44y4GQ2WFm4RzJKV77iYU9P8wwZNKN18xwfeF4MdrKVXnoxnA0Ga8/o7FXcVmsqq2kXuiRVqQdCrcRYJWSO6ooeLg==","accessKeySecret":"5jG9DmGtxC9x3wrWvtL9fcQBGz75bLAuvMTGqjNbbbum","accessKeyId":"STS.H7kQANKvUTxpBjZhX8d8jqiqs","expiration":"2017-06-02T12:38:10Z"}
     * assumedRoleUser : {"arn":"acs:ram::1855161149752924:role/aliyunosstokengeneratorrole/role-session-name","assumedRoleId":"386226671506162692:role-session-name"}
     */

    public String requestId;
    public CredentialsBean credentials;
    public AssumedRoleUserBean assumedRoleUser;


    public static class CredentialsBean {
        /**
         * securityToken : CAISsgJ1q6Ft5B2yfSjIqfTeGvv6pql047qbQEzrjFhtaLdGnqzakTz2IHBKfnNuB+8fs/wxnW5S6PcYlrhpTJtIAFfJdtBx6ZIS9wqkbtJLFGQbQ/9W5qe+EE2/VjTivqaLEeGbIfrZfvCyEQ6m8gZ43br9cxi7QlWhKufnoJV7b9MRLH/aCD1dH4VuOxdFos0XPmezUPG2KUzFj3b3BkhlsRYGygEZ06mkxdCG4RfzlUDzzvRvx778OZ+5dcJhTvAdNt6+x75xbbGTknwSuVpojP4m0fEdp26e4oDCXgMAuUm8XrCKooE1HmhQfbMnHqNIlv/4mMBjt/bb/4aNkEYTZ7sLDXuGGdj+mZWeRrLzLKUwcbfqJnLKycuTMZr4tQwpZ38BPR9NYcAmLnJgvfiQyPxa58QagAEk5IEVnl2Z+L3NtV+CZ3Dt/cyLc28H1YMqHIuA6+ia54Tzs4UL4ZlQWvzs9U7UYn0iTwLd+6On9u0KZyEO+44y4GQ2WFm4RzJKV77iYU9P8wwZNKN18xwfeF4MdrKVXnoxnA0Ga8/o7FXcVmsqq2kXuiRVqQdCrcRYJWSO6ooeLg==
         * accessKeySecret : 5jG9DmGtxC9x3wrWvtL9fcQBGz75bLAuvMTGqjNbbbum
         * accessKeyId : STS.H7kQANKvUTxpBjZhX8d8jqiqs
         * expiration : 2017-06-02T12:38:10Z
         */

        public String securityToken;
        public String accessKeySecret;
        public String accessKeyId;
        public String expiration;


    }

    public static class AssumedRoleUserBean {
        /**
         * arn : acs:ram::1855161149752924:role/aliyunosstokengeneratorrole/role-session-name
         * assumedRoleId : 386226671506162692:role-session-name
         */

        public String arn;
        public String assumedRoleId;

    }
}
