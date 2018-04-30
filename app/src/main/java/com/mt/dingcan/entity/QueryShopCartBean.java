package com.mt.dingcan.entity;
import java.util.List;

/**
 * Created by dingxujun on 2018/4/28.
 *
 * @project dingcan
 */

public class QueryShopCartBean {


    /**
     * returnCode : 1
     * returnMsg : 成功
     * returnData : [{"vegetname":"凉拌黄瓜","price":8.9,"shopname":"南京店","num":1,"id":1,"typename":"冷菜类"},{"vegetname":"花生米","price":5.5,"shopname":"南京店","num":2,"id":2,"typename":"冷菜类"},{"vegetname":"青椒土豆丝","price":10,"shopname":"南京店","num":1,"id":3,"typename":"热菜类"},{"vegetname":"凉拌黄瓜","price":8.9,"shopname":"南京店","num":2,"id":7,"typename":"冷菜类"}]
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
         * vegetname : 凉拌黄瓜
         * price : 8.9
         * shopname : 南京店
         * num : 1
         * id : 1
         * typename : 冷菜类
         */

        private String vegetname;
        private double price;
        private String shopname;
        private int num;
        private int id;
        private String typename;
        private String vegetid;

        public String getVegetid() {
            return vegetid;
        }
        public void setVegetid(String vegetid) {
            this.vegetid = vegetid;
        }

        public String getVegetname() {
            return vegetname;
        }

        public void setVegetname(String vegetname) {
            this.vegetname = vegetname;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }
}
