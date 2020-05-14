package com.etoak.book.gys.web;

import com.etoak.book.commons.page.Page;
import com.etoak.book.commons.utils.ETJSONUtils;
import com.etoak.book.gys.entity.Gys;
import com.etoak.book.gys.entity.Locations;
import com.etoak.book.gys.service.GysService;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Liu on 2020/1/13.
 */
@WebServlet("/gys")
public class GysServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        if (method == null) {
            return;
        }
        //1.获得当前类的class文件
        Class cls = this.getClass();
        //2.从class文件中寻找指定名字和参数的方法
        try {
            Method m = cls.getDeclaredMethod(method,
                    HttpServletRequest.class,
                    HttpServletResponse.class);
            //执行方法
            m.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addGys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String proname = request.getParameter("proname");
        String cityname = request.getParameter("cityname");
        String info = request.getParameter("info");
        GysService gs = new GysService();
        Gys gys = new Gys();
        gys.setName(name);
        gys.setLoc(proname + "-" + cityname + "-" + info);
        gs.addGys(gys);
        response.sendRedirect("addGys.jsp");
    }

    protected void queryAllProvince(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GysService ca = new GysService();
        List<Locations> pros = ca.queryAllProvince();
        //返回客户端是什么 [{idvalue }]
        ETJSONUtils.writeArray(response, pros);
    }

    protected void queryAllByname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        GysService ca = new GysService();
        List<Locations> date = ca.queryAllByname(name);
        ETJSONUtils.writeArray(response, date);
    }

    protected void querySome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String name = request.getParameter("name");
        String loc = request.getParameter("loc");
        Gys tj = new Gys();
        tj.setName(name);
        tj.setLoc(loc);

        GysService cs = new GysService();

        Page<Gys> page = new Page<Gys>();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        //查询数据库获得数据
        List<Gys> data = cs.querySome(tj,pageNumber,pageSize);
        //通过PageInfo 转换一下
        PageInfo<Gys> info = new PageInfo<Gys>(data);
        page.setTotal(((Long)info.getTotal()).intValue());
        page.setRows(info.getList());

        ETJSONUtils.writeObject(response,page);


    }
}
