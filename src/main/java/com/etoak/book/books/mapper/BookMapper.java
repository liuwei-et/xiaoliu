package com.etoak.book.books.mapper;


import com.etoak.book.books.entity.Book;
import com.etoak.book.books.entity.BookPic;


/**
 * Created by Liu on 2020/1/11.
 */
public interface BookMapper {
    public void addBook(Book book);
    public void addBookPic(BookPic pic);

}
