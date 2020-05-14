package com.etoak.book.ca.mapper;

import com.etoak.book.ca.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by Liu on 2020/1/11.
 */
public interface CategoryMapper {
    public void addCa(Category ca);

    public List<Category> queryAllCas();

    public Category queryName(@Param("name")String name);
    public List<Category> queryCateSome(@Param("name") String name);

}
