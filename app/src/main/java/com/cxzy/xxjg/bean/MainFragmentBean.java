package com.cxzy.xxjg.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by demo on 2018/7/27.
 */

public class MainFragmentBean implements Serializable {
    public String alarmTotal ;// 告警总数 ,
    public ArrayList<SchoolCanteenBean> canteenList = new ArrayList<>() ;// 所属食堂 ,
    public String dealTotal ;// 处理总数 ,
    public UserBean user = new UserBean() ;// 用户信息 ,
    public String warnTotal ;// 预警总数
    public DictMapBean dictMap = new DictMapBean();//食材类别 反应时间
    public String appName ;// "智慧食堂"
    public String appSubName ;// "凉山州版"
}
