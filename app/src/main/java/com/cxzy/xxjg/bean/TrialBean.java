package com.cxzy.xxjg.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 试吃管理
 * Created by demo on 2018/7/30.
 */

public class TrialBean {

    public String pageNum;// 1,
    public String pageSize;// 10,
    public String size;// 1,
    public String orderBy;// null,
    public String startRow;// 1,
    public String endRow;// 1,
    public int total;// 1,
    public String pages;// 1,
    public List<TrialListBean> list = new ArrayList<>();
    public String firstPage;// 1,
    public String prePage;// 0,
    public String nextPage;// 0,
    public String lastPage;// 1,
    public String isFirstPage;// true,
    public String isLastPage;// true,
    public String hasPreviousPage;// false,
    public String hasNextPage;// false,
    public String navigatePages;// 8,
    public List<Integer> navigatepageNums = new ArrayList<>();
}
