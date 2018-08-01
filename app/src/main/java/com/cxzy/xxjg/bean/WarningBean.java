package com.cxzy.xxjg.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 历史告警
 * Created by demo on 2018/8/1.
 */

public class WarningBean implements Serializable {
    public List<WarningItemBean> list = new ArrayList<>();
}
