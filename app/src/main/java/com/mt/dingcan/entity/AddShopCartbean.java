package com.mt.dingcan.entity;

import java.io.Serializable;

/**
 * Created by dingxujun on 2018/4/28.
 *
 * @project dingcan
 */

public class AddShopCartbean implements Serializable{

    private static final long serialVersionUID = -3276760707533863577L;
    /**
     * returnCode : 1
     * returnMsg : 成功
     * returnData : {}
     */

    private String returnCode;
    private String returnMsg;
    private ReturnDataBean returnData;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public ReturnDataBean getReturnData() {
        return returnData;
    }

    public void setReturnData(ReturnDataBean returnData) {
        this.returnData = returnData;
    }

    public static class ReturnDataBean {
    }
}
