package com.etoak.book.gys.service;

import com.etoak.book.commons.factory.Factory;
import com.etoak.book.gys.entity.Gys;
import com.etoak.book.gys.entity.Locations;
import com.etoak.book.gys.mapper.GysMapper;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Liu on 2020/1/13.
 */
public class GysService {
    public List<Locations> queryAllByname(String name){
        SqlSession session = null;
        try{
            session = Factory.getSession();
            GysMapper dao = session.getMapper(GysMapper.class);
            List<Locations> cate  = dao.queryAllByname(name);
            session.commit();
            return  cate;
        }catch(Exception ex){
            ex.printStackTrace();
            if (session!=null)session.rollback();
            return null;
        }finally{
            if (session!=null)session.commit();
        }
    }

    public List<Locations> queryAllProvince() {
        SqlSession session = null;
        try{
            session = Factory.getSession();
            GysMapper dao = session.getMapper(GysMapper.class);
            List<Locations> cate  = dao.queryAllProvince();
            session.commit();
            return  cate;
        }catch(Exception ex){
            ex.printStackTrace();
            if (session!=null)session.rollback();
            return null;
        }finally{
            if (session!=null)session.commit();
        }
    }

    public void addGys(Gys gys) {

        SqlSession session = null;
        try{
            session = Factory.getSession();
            GysMapper dao = session.getMapper(GysMapper.class);
                dao.addGys(gys);
            session.commit();

        }catch(Exception ex){
            ex.printStackTrace();
            if (session!=null)session.rollback();

        }finally{
            if (session!=null)session.commit();
        }

    }

    public List<Gys> querySome(Gys tj, int pageNumber, int pageSize) {

        SqlSession session = null;
        try{
            session= Factory.getSession();
            GysMapper dao = session.getMapper(GysMapper.class);
            //pagehelper分页
            PageHelper.startPage(pageNumber,pageSize);
            List<Gys> data=dao.querySome(tj);
            session.commit();
            return data;
        }catch(Exception ex){
            ex.printStackTrace();
            if (session!=null)session.rollback();
            return null;
        }finally{
            if (session!=null)session.close();

        }
    }
    public List<Gys> queryAll() {

        SqlSession session = null;
        try{
            session= Factory.getSession();
            GysMapper dao = session.getMapper(GysMapper.class);
            List<Gys> data=dao.queryAll();
            session.commit();
            return data;
        }catch(Exception ex){
            ex.printStackTrace();
            if (session!=null)session.rollback();
            return null;
        }finally{
            if (session!=null)session.close();

        }
    }


}

