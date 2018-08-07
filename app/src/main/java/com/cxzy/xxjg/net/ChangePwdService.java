package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * 修改密码
 * Created by demo on 2018/8/7.
 */

public interface ChangePwdService {
    @POST("")
    Observable<BaseBean<Object>> changePwd();
}
