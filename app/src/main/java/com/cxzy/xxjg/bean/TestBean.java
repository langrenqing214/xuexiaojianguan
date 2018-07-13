package com.cxzy.xxjg.bean;

import java.io.Serializable;

/**
 * Created by demo on 2018/6/26.
 */

public class TestBean implements Serializable {
    public String result;// 1,
    public String version;// public String 4.1.0public String ,
    public String update_content;// public String 1、优化【APP界面】快要把设计师逼吐血的诚意之作，全新界面，全新体验！<br>2、升级【特价商城】模块，自营商品震撼来袭，线上预订享实惠，线下提车放心购！<br>3、新增【小白买车】模块，买车不当无头苍蝇，买车问题随时问，老司机带你装x带你飞！<br>2016年最大版本升级，呕心沥血，诚意满满。各位大大一定要赏脸试试，谁用谁知道！public String ,
    public String download_url;// public String http://img.amishii.com/file/d/amishii.apkpublic String

    @Override
    public String toString() {
        return "TestBean{" +
                "result='" + result + '\'' +
                ", version='" + version + '\'' +
                ", update_content='" + update_content + '\'' +
                ", download_url='" + download_url + '\'' +
                '}';
    }
}
