package com.lgwen.mybatisredismysql.beans;

import java.io.Serializable;

public class Right implements Serializable {
    
    private int id;
    private String rightids;
    private int userid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRightids() {
        return rightids;
    }

    public void setRightids(String rightids) {
        this.rightids = rightids;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Right{" +
                "id=" + id +
                ", rightids='" + rightids + '\'' +
                ", userid=" + userid +
                '}';
    }
}
