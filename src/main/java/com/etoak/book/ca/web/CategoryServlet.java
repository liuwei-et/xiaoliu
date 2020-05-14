package com.etoak.book.ca.web;

import com.alibaba.fastjson.JSONArray;
import com.etoak.book.ca.entity.Category;
import com.etoak.book.ca.service.CaService;
import com.etoak.book.commons.page.Page;
import com.etoak.book.commons.utils.ETJSONUtils;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(value="/ca")
public class CategoryServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        if(method==null){return;}
        Class cls = this.getClass();
        try {
            Method m = cls.getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class);
            m.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void add(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=utf-8");
        String name = request.getParameter("name");

        Category ca = new Category();
        if(name!=null&&!name.equals("")){
        ca.setName(name);
        }

        CaService caa = new CaService();
        caa.addCategory(ca);
        response.sendRedirect("addCa.jsp");
    }
    protected void checkName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        CaService ca = new CaService();
        ca.queryName(name);
        response.sendRedirect("addCa.jsp");
    }
    protected void checkCategory(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("type");
        System.out.println(name);
        CaService aa = new CaService();
        PrintWriter out = response.getWriter();
        if(name ==null || name.equals("")){
            out.print("null");
        }else if(aa.queryName(name)){
            out.print("suc");
        }else{
            out.print("default");
        }
        out.close();
    }
    private void queryCateSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        Page<Category> page = new Page<Category>();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        CaService cs = new CaService();



        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        //查询数据库获得数据
        List<Category> data = cs.queryCateSome(name,pageNumber,pageSize);
        //通过PageInfo 转换一下
        PageInfo<Category> info = new PageInfo<Category>(data);
        page.setTotal(((Long)info.getTotal()).intValue());
        page.setRows(info.getList());

        ETJSONUtils.writeObject(response,page);

    }



}
