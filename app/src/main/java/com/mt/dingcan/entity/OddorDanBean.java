package com.mt.dingcan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dingxujun on 2018/4/28.
 *
 * @project dingcan
 */

public class OddorDanBean implements Serializable{


    private static final long serialVersionUID = -291631205283112342L;
    /**
     * userid : 2
     * data : [{"vegetid":"1","num":1,"price":8.9},{"vegetid":"2","num":2,"price":8.9}]
     */

    private String userid;
    private List<DataBean> data;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
}
