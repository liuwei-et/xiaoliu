package com.etoak.book.gys.mapper;

import com.etoak.book.gys.entity.Gys;
import com.etoak.book.gys.entity.Locations;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Liu on 2020/1/13.
 */
public interface GysMapper {
    public void addGys(Gys gys);


    public List<Gys> querySome(Gys gys);

    public List<Locations> queryAllProvince();
    public List<Locations> queryAllByname(@Param("name")String name);
    public List<Gys> queryAll();

}
