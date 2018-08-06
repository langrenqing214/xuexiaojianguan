package com.cxzy.xxjg.base;

import com.cxzy.xxjg.bean.ResultBean;
import com.cxzy.xxjg.bean.ResultItemBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by demo on 2018/8/6.
 */

public class ScanResultBean implements Serializable {
    public List<ResultItemBean> viewList = new ArrayList<>();
    public List<ResultItemBean> hiddenList = new ArrayList<>();
}
