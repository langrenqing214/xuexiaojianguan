package com.cxzy.xxjg.bean;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by demo on 2018/7/26.
 */

public class LoginBean implements Serializable {
//    public String code;// 0,
//    public String message;// public String public String ,
//    public boolean ok;// true
//    @Nullable
//    public Data data = new Data();

//    public static class Data {
        public String access_token;// token码 ,
        public String expires_in;// token有效时间 ,
        public String token_type;// token类型
//    }
}
