package com.mt.dingcan.httpnet;

/**
 * Created by dingxujun on 2018/4/28.
 *
 * @project dingcan
 */

public class HttpNetApi {
    public static final String BaseApi ="http://47.98.116.146:8080/order_online/api/";
    public static final String register=BaseApi+"register/";
    public static final String accountLogin=BaseApi+"accountLogin/";
    public static final  String queryCanpinType=BaseApi+"queryCanpinType/";
    public static final String queryCanpin=BaseApi+"queryCanpin/";
    public static final String addOrder=BaseApi+"addShopCart/";
    public static final String deleShopCart=BaseApi+"deleShopCart/";
    public static final String queryShopCart= BaseApi+"queryShopCart/";
    public static final String XaddOrder=BaseApi+"addOrder/";//订单
    public static final String getAllOrderList=BaseApi+"getAllOrderList/";//获取所有订单
    public static final String placeOrder=BaseApi+"placeOrder/";
}
