package com.cxzy.xxjg.bean;

import java.io.Serializable;

/**
 * Created by demo on 2018/8/1.
 */

public class WarningItemBean implements Serializable {
    public String bizId ;// 告警业务ID ,
    public String canteenId ;// 食堂ID ,
    public String configDesc ;// 告警配置项描述 ,
    public String configId ;// 告警配置项ID ,
    public String createDate ;//
    public String createDateEnd ;// 告警时间结束 ,
    public String createDateStart ;// 告警时间开始 ,
    public String createUserId ;//
    public String dealDate ;// 处置时间 ,
    public String dealState ;// 处置状态 , （未处理：DEAL_NOT，已处理：DEAL_END）
    public String dealUserId ;// 处置人 ,
    public String dealUserName ;// 处置人姓名 ,
    public String id ;//
    public String level ;// 告警等级 ,
    public String remarks ;//
    public String searchKeys ;//
    public String updateDate ;//
    public String updateUserId ;//
    public String levelColor ;//颜色值
}
