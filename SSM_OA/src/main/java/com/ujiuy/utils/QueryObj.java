package com.ujiuy.utils;

public class QueryObj {

    private  int cid;
    private String key;
    private int order;

    public QueryObj() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public QueryObj(int cid, String key, int order) {
        this.cid = cid;
        this.key = key;
        this.order = order;
    }

    @Override
    public String toString() {
        return "QueryObj{" +
                "cid=" + cid +
                ", key='" + key + '\'' +
                ", order=" + order +
                '}';
    }
}
