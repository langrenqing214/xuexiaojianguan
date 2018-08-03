package com.cxzy.xxjg.bean;

import java.io.Serializable;

/**
 * Created by demo on 2018/8/2.
 */

public class PersonsBean implements Serializable {
    public String canteenId ;// 食堂ID ,
    public String personId ;// 人员ID ,
    public String personName ;// 人员名称 ,
    public String personState ;// 人员状态（正常：NORMAL，异常：ERROR）

    @Override
    public String toString() {
        return "PersonsBean{" +
                "canteenId='" + canteenId + '\'' +
                ", personId='" + personId + '\'' +
                ", personName='" + personName + '\'' +
                ", personState='" + personState + '\'' +
                '}';
    }
}
