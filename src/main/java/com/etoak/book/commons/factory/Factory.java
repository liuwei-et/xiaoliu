package com.etoak.book.commons.factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * Created by 78610 on 2020/1/10.
 */
public class Factory {
    private static SqlSessionFactory f;
    private Factory(){}
    static{
        try{
            Reader reader = Resources.getResourceAsReader("config.xml");
            f = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static SqlSession getSession(){
        return f.openSession();
    }
}
