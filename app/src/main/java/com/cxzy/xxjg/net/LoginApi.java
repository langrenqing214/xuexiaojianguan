package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.LoginBean;

import java.util.Map;

import io.reactivex.Observable;


/**
 * 登录
 * Created by demo on 2018/7/26.
 */

public class LoginApi {

    private LoginService service ;
    public static LoginApi intance ;

    public LoginApi(LoginService service){
        this.service = service ;
    }

    public static synchronized LoginApi getIntance(LoginService service){
        if (intance == null){
            intance = new LoginApi(service);
        }
        return intance ;
    }

    public Observable<BaseBean<LoginBean>> login(Map<String , String> params) {
        return service.login(params);
    }
}
