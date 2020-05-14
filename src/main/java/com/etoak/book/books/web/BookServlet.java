package com.etoak.book.books.web;

import com.alibaba.fastjson.JSONObject;
import com.etoak.book.books.entity.Book;
import com.etoak.book.books.entity.BookPic;
import com.etoak.book.books.service.BookService;
import com.etoak.book.ca.entity.Category;
import com.etoak.book.ca.service.CaService;
import com.etoak.book.commons.ETDateUtils;
import com.etoak.book.commons.utils.ETJSONUtils;
import com.etoak.book.gys.entity.Gys;
import com.etoak.book.gys.service.GysService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        if (null == method) {
            String ce = request.getHeader("Content-Type");
            if (ce.indexOf("multipart") >= 0) {
                this.add(request, response);
            }
        }
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
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("add");

        //SmartUpload su = new SmartUpload();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try{
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();
            Book book = new Book();
            List<BookPic> pics = new ArrayList<>();
            int i = 0;
            while (iterator.hasNext()){
                FileItem fi = iterator.next();
                if(fi.isFormField()){
                    String name = fi.getFieldName();
                    String value = fi.getString("utf-8");
                    if("name".equals(name)){
                        book.setName(value);
                    }else if("price".equals(name)){
                        book.setPrice(Double.parseDouble(value));
                    }else if("author".equals(name)){
                        book.setAuthor(value);
                    }else if("publishdate".equals(name)){
                        book.setPublishdate(ETDateUtils.string2Timestamp(value));
                    }else if("sl".equals(name)){
                        book.setSl(Integer.parseInt(value));
                    }else if("status".equals(name)){
                        book.setStatus(Integer.parseInt(value));
                    }else if("categoryid".equals(name)){
                        book.setCategoryid(value);
                    }else if("gyss".equals(name)){
                        book.setGysid(value);
                    }
                }else {
                    ServletContext application = this.getServletContext();
                    //文件名字
                    String fileName = fi.getName();
                    //拿取文件名字或者后缀
                    String fileEXt = FilenameUtils.getExtension(fileName);
                    String newName = UUID.randomUUID().toString().replaceAll("-","")+"."+fileEXt;
                    String path = application.getRealPath("image/");
                    File f = new File(path,newName);
                    fi.write(f);
                    //以上是把文件改名之后保存到服务器端
                    //把文件放到数据库中
                    BookPic bp = new BookPic();
                    bp.setFm(i==0?1:0);
                    bp.setSavepath("image/"+newName);
                    bp.setShowname(fileName);
                    bp.setUploadtime(new Timestamp(System.currentTimeMillis()));
                    pics.add(bp);
                    i++;
                }
            }
            BookService b = new BookService();
            b.addBokAndPics(book,pics);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        response.sendRedirect("addbook.jsp");

            /*String name = request.getParameter("name");
            String author = request.getParameter("author");
            Double price = Double.parseDouble(request.getParameter("price"));
            String publishdate = request.getParameter("publishdate");
            String gysid = request.getParameter("gysid");
            String categoryid = request.getParameter("categoryid");
            Integer sl = Integer.parseInt(request.getParameter("sl"));

            //Book book = new Book();
            book.setName(name);
            book.setAuthor(author);
            book.setPrice(price);
            book.setPublishdate(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(publishdate).getTime()));
            book.setGysid(gysid);
            book.setCategoryid(categoryid);
            book.setSl(sl);

            BookService b = new BookService();
            b.addBook(book);*/
    }

  /*  protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileItemFactory F = new DiskFileItemFactory();
        ServletFileUpload su = new ServletFileUpload(F);
        try {
            List<FileItem> fs = su.parseRequest(request);
            Iterator<FileItem> car = fs.iterator();
            int i = 0;
            while (car.hasNext()) {
                FileItem fi = car.next();
                Book b = new Book();
                List<Bookpic> bps = new ArrayList<>();
                if (fi.isFormField()) {
                    String name = fi.getFieldName();
                    String value = fi.getString();
                    if ("name".equals(name)) {
                        b.setName(value);
                    } else if ("price".equals(name)) {
                        b.setPrice(Double.parseDouble(value));
                    } else if ("author".equals(name)) {
                        b.setAuthor(value);
                    } else if ("publishdate".equals(name)) {
                        b.setPublishdate(ETDateUtils.string2Timestamp(value));
                    } else if ("sl".equals(name)) {
                        b.setSl(Integer.parseInt("value"));
                    } else if ("status".equals(name)) {
                        b.setStatus(Integer.parseInt("value"));
                    } else if ("categoryid".equals(name)) {
                        b.setCategoryid(value);
                    } else if ("gyss".equals(name)) {
                        b.setGysid(value);
                    }

                } else {
                    String fName = fi.getName();
                    String fExt = FilenameUtils.getExtension(fName);
                    String newName = UUID.randomUUID().toString().replaceAll("-", "") + fExt;
                    ServletContext applicatio = this.getServletContext();
                    String path = applicatio.getRealPath("/inage");
                    fi.write(new File(path, newName));
                    Bookpic bp = new Bookpic();

                    bp.setFm(i == 0 ? 1 : 0);
                    bp.setShowname(fName);
                    bp.setSavepath("/image/" + newName);
                    bp.setUploadtime(new Timestamp(System.currentTimeMillis()));
                    bps.add(bp);
                    i++;
                }
                BookService bs = new BookService();
                bs.addBokAndPics(b,bps);
            }
            response.sendRedirect("addbook.jsp");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }*/
    protected void querya(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        GysService gys = new GysService();
        List<Gys> gs = gys.queryAll();
        CaService ca = new CaService();
        List<Category> a = ca.queryAllCas();
        JSONObject jo  = new JSONObject();
        jo.put("gys",gs);
        jo.put("cas",a);
        ETJSONUtils.writeObject(response,jo);
    }

}
  /*  private void queryCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BookService bs = new BookService();
        List<Category> list = bs.queryCate();
        System.out.println(list);
        JSONArray ja = new JSONArray();
        ja.add(list);
        PrintWriter out = response.getWriter();
        out.print(ja);
        out.close();
    }
    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{

            response.setContentType("text/plain;charset=utf-8");
            request.setCharacterEncoding("utf-8");
            FileItemFactory f = new DiskFileItemFactory();
            ServletFileUpload su = new ServletFileUpload(f);

            try{
                List<FileItem> fis = su.parseRequest(request);
                //FileItem 代表一个表单项 可以是普通参数  也可以是文件参数
                Iterator<FileItem> car = fis.iterator();
                Book book = new Book();
                List<BookPic> list = new ArrayList<>();

                while(car.hasNext()){
                    FileItem fi = car.next();
                    //System.out.println(fi);
                    //判断表单是否为一个普通表单项
                    boolean flag = fi.isFormField();
                    if(flag){
                        String name = fi.getFieldName();
                        String value = fi.getString("utf-8");
                      *//*  System.out.println(name);
                        System.out.println(value);*//*
                        if("name".equals(name)){
                            book.setName(value);
                        }else if("author".equals(name)){
                            book.setAuthor(value);
                        }else if("price".equals(name)){
                            book.setPrice(Double.parseDouble(value));
                        }else if("count".equals(name)){
                            book.setSl(Integer.parseInt(value));
                        }else if("type".equals(name)){
                            book.setCategoryid(value);
                        }else if("gys".equals(name)){
                            book.setGysid(value);
                        }
                        book.setPublishdate(new Timestamp(System.currentTimeMillis()));
                        book.setStatus(1);

                    }else{
                        BookPic bp = new BookPic();
                        String fName = fi.getName();
                        System.out.println(fName);
                        ServletContext application = this.getServletContext();
                        String path = application.getRealPath("/image/"+fName);
                        //System.out.println(path);
                        fi.write(new File(path));
                        bp.setFm(0);
                        bp.setSavepath(path);
                        bp.setUploadtime(new Timestamp(System.currentTimeMillis()));
                        bp.setShowname(fName);
                        list.add(bp);
                    }
                }
                System.out.println(book);
                System.out.println(list);
                BookService bs = new BookService();
                bs.addBookAndPic(book,list);
                response.sendRedirect("addBook.jsp");
            }catch(Exception e){
                e.printStackTrace();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void checkCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        System.out.println(type);
        BookService bs = new BookService();
        PrintWriter out = response.getWriter();
        if(type ==null || type ==""){
            out.print("null");
        }else if(bs.checkCate(type)){
                out.print("suc");
        }else{
            out.print("default");
        }
        out.close();
    }

    private void queryCateSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        List<Category> data = new BookService().queryCateSome(name,pageNumber,pageSize);
        PageInfo<Category> info = new PageInfo<>(data);
        info.setPageNum(pageNumber);
        info.setPageSize(pageSize);
        //System.out.println(info);
        PrintWriter out = response.getWriter();
        JSONArray ja = JSONArray.fromObject(info);
        out.print(ja);
        out.close();
    }

    private void queryBookSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进来了");
        String name = request.getParameter("name");
        System.out.println("name="+name);
        String gys = request.getParameter("gys");
        String cate =request.getParameter("cate");
        String price = request.getParameter("price");
        String time = request.getParameter("time");
        String stime = null;
        String etime = null;
        if(time != null){
            stime = time.substring(0,time.indexOf(" - ")) +" 00:00:00";
            etime = time.substring(time.indexOf(" - ")+3)+" 23:59:59";
            //  System.out.println(stime+"   "+etime);
        }
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        BookPage bp = new BookPage();
        bp.setName(name);
        bp.setGys(gys);
        bp.setCate(cate);
        bp.setPrice(price);
        bp.setStime(stime);
        bp.setEtime(etime);

        List<Book> data = new BookService().queryBookSome(bp,pageNumber,pageSize);
        //System.out.println(data);

        PageInfo<Book> info = new PageInfo<>(data);
        info.setPageNum(pageNumber);
        info.setPageSize(pageSize);
        PrintWriter out = response.getWriter();
        JSONArray ja = JSONArray.fromObject(info);
        out.print(ja);
        out.close();
    }*/

