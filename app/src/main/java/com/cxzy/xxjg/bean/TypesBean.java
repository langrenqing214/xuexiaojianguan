package com.cxzy.xxjg.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by demo on 2018/8/2.
 */

public class TypesBean implements Serializable {
    public List<ItemsBean> items = new ArrayList<>();// 检查项 ,
    public String typeCode ;// 类型编码（晨检：MORNING_CHECK, 环境：ENV_CHECK） ,
    public String typeDesc ;// 类型说明 ,
    public String typeId ;// 类型ID ,
    public String typeName ;// 类型名称
}
