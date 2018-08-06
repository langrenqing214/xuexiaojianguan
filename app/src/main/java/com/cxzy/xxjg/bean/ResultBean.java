package com.cxzy.xxjg.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianwei on 2018/8/6.
 */

public class ResultBean implements Serializable {
    public String key ;
    public List<ResultItemBean> value = new ArrayList<>();
}
