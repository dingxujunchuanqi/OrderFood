package com.mt.dingcan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dingxujun on 2018/4/28.
 *
 * @project dingcan
 */

public class QueryCanpinTypeBean implements Serializable{

    private static final long serialVersionUID = -2909077636818303688L;
    /**
     * returnCode : 1
     * returnMsg : 登录成功
     * returnData : {"vegetnameArray":[{"vegetname":"凉拌黄瓜"},{"vegetname":"花生米"},{"vegetname":"青椒土豆丝"},{"vegetname":"青椒鸡蛋"}],"shopnameArray":[{"shopname":"南京店"},{"shopname":"江西店"},{"shopname":"湖北店"}],"typenameArray":[{"typename":"冷菜类"},{"typename":"热菜类"}]}
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
        private List<VegetnameArrayBean> vegetnameArray;
        private List<ShopnameArrayBean> shopnameArray;
        private List<TypenameArrayBean> typenameArray;

        public List<VegetnameArrayBean> getVegetnameArray() {
            return vegetnameArray;
        }

        public void setVegetnameArray(List<VegetnameArrayBean> vegetnameArray) {
            this.vegetnameArray = vegetnameArray;
        }

        public List<ShopnameArrayBean> getShopnameArray() {
            return shopnameArray;
        }

        public void setShopnameArray(List<ShopnameArrayBean> shopnameArray) {
            this.shopnameArray = shopnameArray;
        }

        public List<TypenameArrayBean> getTypenameArray() {
            return typenameArray;
        }

        public void setTypenameArray(List<TypenameArrayBean> typenameArray) {
            this.typenameArray = typenameArray;
        }

        public static class VegetnameArrayBean {
            /**
             * vegetname : 凉拌黄瓜
             */

            private String vegetname;

            public String getVegetname() {
                return vegetname;
            }

            public void setVegetname(String vegetname) {
                this.vegetname = vegetname;
            }
        }

        public static class ShopnameArrayBean {
            /**
             * shopname : 南京店
             */

            private String shopname;

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }
        }

        public static class TypenameArrayBean {
            /**
             * typename : 冷菜类
             */

            private String typename;

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }
        }
    }
}
