package com.cxzy.xxjg.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 卫生检查
 * Created by demo on 2018/8/2.
 */

public class HealthExaminationBean implements Serializable {
    public List<PersonsBean> persons = new ArrayList<>();// 检查人员 ,
    public List<TypesBean> types = new ArrayList<>() ;// 检查类型
}
