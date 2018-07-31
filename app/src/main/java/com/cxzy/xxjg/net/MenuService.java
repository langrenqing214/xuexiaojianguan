package com.cxzy.xxjg.net;


import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.MenuBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 菜谱
 * Created by demo on 2018/7/31.
 */

public interface MenuService {
    @GET("api/foodRecipes/list")
    Observable<BaseBean<MenuBean>> getMenuList(@Query("canteenId") int canteenId ,
                                               @Query("pageNumber") int pageNumber ,
                                               @Query("pageSize") int pageSize);
}
