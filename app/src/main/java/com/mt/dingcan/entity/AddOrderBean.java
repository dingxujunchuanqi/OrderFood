package com.mt.dingcan.entity;

import java.io.Serializable;

/**
 * Created by dingxujun on 2018/4/28.
 *
 * @project dingcan
 */

public class AddOrderBean implements Serializable {

    private static final long serialVersionUID = 8045559751716695074L;
    /**
     * returnCode : 1
     * returnMsg : 成功
     * returnData : {"orderid":"1","total":"1"}
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
        /**
         * orderid : 1
         * total : 1
         */

        private String orderid;
        private String total;

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
