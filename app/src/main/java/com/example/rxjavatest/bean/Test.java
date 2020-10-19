package com.example.rxjavatest.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fan.zhengxi on 2020/9/21
 * Describe:
 */
class Test {

    /**
     * vin : LK6ADCE29HB019918
     * serial : GUID
     * respond : 0xxx
     * x : X轴坐标
     * y : Y轴坐标
     * code : E0001
     * msg : msg
     * routeNames : xxxx
     * 53E_ID : xxx
     */

    private String vin;
    private String serial;
    private String respond;
    private String x;
    private String y;
    private String code;
    private String msg;
    private String routeNames;
    @SerializedName("53E_ID")
    private String _$53E_ID;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getRespond() {
        return respond;
    }

    public void setRespond(String respond) {
        this.respond = respond;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRouteNames() {
        return routeNames;
    }

    public void setRouteNames(String routeNames) {
        this.routeNames = routeNames;
    }

    public String get_$53E_ID() {
        return _$53E_ID;
    }

    public void set_$53E_ID(String _$53E_ID) {
        this._$53E_ID = _$53E_ID;
    }
}
