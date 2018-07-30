package com.cxzy.xxjg.bean;


import java.io.Serializable;

/**
 * Created by demo on 2018/7/30.
 */

public class BaseBean<T> implements Serializable{
    public int code ;
    public String message ;
    public T data ;
    public boolean ok ;
}
