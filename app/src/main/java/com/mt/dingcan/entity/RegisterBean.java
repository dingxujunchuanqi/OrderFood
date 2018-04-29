package com.mt.dingcan.entity;

/**
 * Created by dingxujun on 2018/4/28.
 *
 * @project dingcan
 */

public class RegisterBean {
    /**
     * returnCode : 1
     * returnMsg : 注册成功
     * returnData : {"username":"客户201804270944374","phone":"13815198123","address":"江苏省南京市雨花区软件大道228号","userid":"b0debf9c247c479eabab3cfca9061b06"}
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
         * username : 客户201804270944374
         * phone : 13815198123
         * address : 江苏省南京市雨花区软件大道228号
         * userid : b0debf9c247c479eabab3cfca9061b06
         */

        private String username;
        private String phone;
        private String address;
        private String userid;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
