package com.cxzy.xxjg.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by demo on 2018/7/27.
 */

public class MainFragmentBean implements Serializable {
    public String alarmTotal ;// 告警总数 ,
    public ArrayList<SchoolCanteenBean> canteenList ;// 所属食堂 ,
    public String dealTotal ;// 处理总数 ,
    public UserBean user ;// 用户信息 ,
    public String warnTotal ;// 预警总数
}
