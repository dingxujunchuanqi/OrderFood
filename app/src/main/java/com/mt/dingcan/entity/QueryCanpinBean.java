package com.mt.dingcan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dingxujun on 2018/4/28.
 *
 * @project dingcan
 */

public class QueryCanpinBean implements Serializable {
    private static final long serialVersionUID = 8374164433908778140L;


    /**
     * returnCode : 1
     * returnMsg : 登录成功
     * returnData : [{"id":1,"price":8.9,"typename":"冷菜类","shopname":"南京店","vegetname":"凉拌黄瓜"},{"id":2,"price":5.5,"typename":"冷菜类","shopname":"南京店","vegetname":"花生米"},{"id":3,"price":10,"typename":"热菜类","shopname":"南京店","vegetname":"青椒土豆丝"}]
     */

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
         * id : 1
         * price : 8.9
         * typename : 冷菜类
         * shopname : 南京店
         * vegetname : 凉拌黄瓜
         */

        private int id;
        private double price;
        private String typename;
        private String shopname;
        private String vegetname;
        private int mumber;

        public int getMumber() {
            return mumber;
        }

        public void setMumber(int mumber) {
            this.mumber = mumber;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
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
