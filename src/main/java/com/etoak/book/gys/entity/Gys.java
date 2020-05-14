package com.etoak.book.gys.entity;

/**
 * Created by 帅健健 on 2020/1/10.
 */
public class Gys {
    private String id;
    private String name;
    private String loc;

    public Gys() {
    }

    public Gys(String id, String name, String loc) {
        this.id = id;
        this.name = name;
        this.loc = loc;
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

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Gys{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
