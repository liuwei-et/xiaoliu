package com.etoak.book.gys.entity;

/**
 * Created by 帅健健 on 2020/1/10.
 */
public class Locations {
    private String id;
    private String name;
    private String type;
    private String pid;

    public Locations() {
    }

    public Locations(String id, String name, String type, String pid) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pid='" + pid + '\'' +
                '}';
    }
}
