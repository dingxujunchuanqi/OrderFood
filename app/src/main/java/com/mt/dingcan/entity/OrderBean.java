package com.mt.dingcan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dingxujun on 2018/4/28.
 *
 * @project dingcan
 */

public class OrderBean implements Serializable{
    private static final long serialVersionUID = 922937718195202604L;
    private String returnCode;
    private String returnMsg;
    private List<ReturnDataBean> returnData;

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

    public List<ReturnDataBean> getReturnData() {
        return returnData;
    }

    public void setReturnData(List<ReturnDataBean> returnData) {
        this.returnData = returnData;
    }

    public static class ReturnDataBean {
        /**
         * orderinfo : [{"num":"1","price":"8.9","typename":"冷菜类","shopname":"南京店","vegetname":"凉拌黄瓜"},{"num":"2","price":"5.5","typename":"冷菜类","shopname":"南京店","vegetname":"花生米"},{"num":"3","price":"10.0","typename":"热菜类","shopname":"南京店","vegetname":"青椒土豆丝"}]
         * orderid : 123456789
         */

        private String orderid;
        private List<OrderinfoBean> orderinfo;

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public List<OrderinfoBean> getOrderinfo() {
            return orderinfo;
        }

        public void setOrderinfo(List<OrderinfoBean> orderinfo) {
            this.orderinfo = orderinfo;
        }

        public static class OrderinfoBean {
            /**
             * num : 1
             * price : 8.9
             * typename : 冷菜类
             * shopname : 南京店
             * vegetname : 凉拌黄瓜
             */

            private String num;
            private String price;
            private String typename;
            private String shopname;
            private String vegetname;

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public String getVegetname() {
                return vegetname;
            }

            public void setVegetname(String vegetname) {
                this.vegetname = vegetname;
            }
        }
    }
}
