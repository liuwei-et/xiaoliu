package com.etoak.book.books.entity;

import java.sql.Timestamp;

/**
 * Created by 78610 on 2020/1/10.
 */
public class Book {
    private String id;
    private String name;
    private String author;
    private Double price;
    private Timestamp publishdate;
    private String gysid;
    private String gysname;
    private String categoryid;
    private String category;
    private int sl;
    private int status;
    private int del;

    public Book(String id, String name, String author, Double price, Timestamp publishdate, String gysid, String gysname, String categoryid, String category, int sl, int status, int del) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.publishdate = publishdate;
        this.gysid = gysid;
        this.gysname = gysname;
        this.categoryid = categoryid;
        this.category = category;
        this.sl = sl;
        this.status = status;
        this.del = del;
    }

    public Book() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Timestamp publishdate) {
        this.publishdate = publishdate;
    }

    public String getGysid() {
        return gysid;
    }

    public void setGysid(String gysid) {
        this.gysid = gysid;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public String getGysname() {
        return gysname;
    }

    public void setGysname(String gysname) {
        this.gysname = gysname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", publishdate=" + publishdate +
                ", gysid='" + gysid + '\'' +
                ", gysname='" + gysname + '\'' +
                ", categoryid='" + categoryid + '\'' +
                ", category='" + category + '\'' +
                ", sl=" + sl +
                ", status=" + status +
                ", del=" + del +
                '}';
    }
}
