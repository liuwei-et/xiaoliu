package com.etoak.book.books.service;


import com.etoak.book.books.entity.Book;

import com.etoak.book.books.entity.BookPic;
import com.etoak.book.books.mapper.BookMapper;
import com.etoak.book.commons.factory.Factory;
import org.apache.ibatis.session.SqlSession;


import java.util.List;

/**
 * Created by Liu on 2020/1/13.
 */
public class BookService {
    public void addBokAndPics(Book b, List<BookPic> bps){

        SqlSession session = null;
        try {
            session = Factory.getSession();
            BookMapper dao = session.getMapper(BookMapper.class);
           dao.addBook(b);
           for(BookPic bp :bps){
               bp.setBookid(bp.getId());
               dao.addBookPic(bp);
           }
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) session.rollback();
        } finally {
            if (session != null) session.close();
        }
    }
    }

