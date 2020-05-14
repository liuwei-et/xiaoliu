package com.etoak.book.books.entity;


import java.sql.Timestamp;

public class BookPic {
    private String id;
    private String savepath;
    private String showname;
    private Timestamp uploadtime;
    private int fm;
    private String bookid;

    public BookPic(String id, String savepath, String showname, Timestamp uploadtime, int fm, String bookid) {
        this.id = id;
        this.savepath = savepath;
        this.showname = showname;
        this.uploadtime = uploadtime;
        this.fm = fm;
        this.bookid = bookid;
    }

    public BookPic() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSavepath() {
        return savepath;
    }

    public void setSavepath(String savepath) {
        this.savepath = savepath;
    }

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public Timestamp getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Timestamp uploadtime) {
        this.uploadtime = uploadtime;
    }

    public int getFm() {
        return fm;
    }

    public void setFm(int fm) {
        this.fm = fm;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    @Override
    public String toString() {
        return "BookPic{" +
                "id='" + id + '\'' +
                ", savepath='" + savepath + '\'' +
                ", showname='" + showname + '\'' +
                ", uploadtime='" + uploadtime + '\'' +
                ", fm=" + fm +
                ", bookid='" + bookid + '\'' +
                '}';
    }
}
