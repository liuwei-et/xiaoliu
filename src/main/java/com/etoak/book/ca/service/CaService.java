package com.etoak.book.ca.service;

import com.etoak.book.ca.entity.Category;
import com.etoak.book.ca.mapper.CategoryMapper;
import com.github.pagehelper.PageHelper;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Liu on 2020/1/11.
 */
public class CaService {
    public void addCategory(Category ca) {
        SqlSession session = null;
        try {
            session = SF.getSession();
            CategoryMapper dao = session.getMapper(CategoryMapper.class);
            dao.addCa(ca);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) session.rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public boolean queryName(String name) {
        SqlSession session = null;
        try {
            session = SF.getSession();
            CategoryMapper dao = session.getMapper(CategoryMapper.class);
            Category cate = dao.queryName(name);
            session.commit();
            if (cate == null) return true;
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.rollback();
            return false;
        } finally {
            if (session != null) session.commit();
        }
    }

    public List<Category> queryCateSome(String name, int pageNumber, int pageSize) {
            SqlSession session = null;
            try {
                session = SF.getSession();
                CategoryMapper dao = session.getMapper(CategoryMapper.class);
                PageHelper.startPage(pageNumber, pageSize);
                List<Category> data = dao.queryCateSome(name);
                session.commit();
                return data;
            } catch (Exception e) {
                e.printStackTrace();
                if (session != null) session.rollback();
                return null;
            } finally {
                if (session != null) session.close();
            }
        }
    public List<Category> queryAllCas() {
        SqlSession session = null;
        try {
            session = SF.getSession();
            CategoryMapper dao = session.getMapper(CategoryMapper.class);
            List<Category> data = dao.queryAllCas();
            session.commit();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) session.rollback();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }
    }
