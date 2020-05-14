<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2020/1/10
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>书籍管理</title>
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-datetimepicker.min.css">
    </head>
    <body>
        <form action="book"  method="post" class="form-horizontal" role="form"
         enctype="multipart/form-data">
            <!--<div class="form-group">
                <label for="pic" class="col-sm-2 control-label">头像:</label>
                <div class="col-sm-10">
                    <input type="file" class="form-control" id="pic" name="pic" placeholder="输入业主姓名">
                </div>
            </div>-->
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">书籍名字:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name"  name="name" placeholder="输入书籍姓名">
                </div>
            </div>
            <div class="form-group">
                <label for="categoryid" class="col-sm-2 control-label">类别:</label>
                <div class="col-sm-10">
                    <select class="form-control" id="categoryid" name="categoryid">
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="gyss" class="col-sm-2 control-label">供应商:</label>
                <div class="col-sm-10">
                    <select class="form-control" id="gyss" name="gyss">
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="dtp_input2" class="col-md-2 control-label">Date Picking</label>
                <div class="input-group date form_date col-md-5" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" value="" readonly id="publishdate" name="publishdate">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <input type="hidden" id="dtp_input2" value="" /><br/>
            </div>

            <div class="form-group">
                <label for="author" class="col-sm-2 control-label">作者:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="author" name="author" placeholder="输入作者">
                </div>
            </div>
            <div class="form-group">
                <label for="price" class="col-sm-2 control-label">价格:</label>
                <div class="col-sm-10">
                    <input type="number" class="form-control" id="price"  name="price" placeholder="输入价格">
                </div>
            </div>

            <div class="form-group">
                <label for="sl" class="col-sm-2 control-label">数量:</label>
                <div class="col-sm-10">
                    <input type="number" class="form-control" id="sl" name="sl" >
                </div>
            </div>
            <div class="form-group">
                <label for="status" class="col-sm-2 control-label">状态:</label>
                <div class="col-sm-10">
                    <select type="text" class="form-control" id="status" name="status" >
                        <option value="1">上架</option>
                        <option value="0">下架</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <button type="button" onclick="addpic()">添加图片</button>
            </div>
            <div id="pics"></div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">添加书籍</button>
                </div>
            </div>
        </form>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap-datetimepicker.min.js"></script>
        <script src="js/bootstrap-datetimepicker.zh-CN.js"></script>

        <script>
            $(()=>{
                query();

            })
        function query() {
            $.post("book","method=querya",msg=>{
                let ops="<option value = '1'>==请选择==</option>";
                $.each(msg.cas,function (i,ca) {
                    ops+="<option value='"+ ca.id+"'>"+ca.name+"</option>";
                });
                $("#categoryid").html(ops);
                let ops1="<option value = '1'>==请选择==</option>";
                $.each(msg.gys,function (i,g) {
                    ops1+="<option value='"+ g.id+"'>"+g.name+"</option>";
                });
                $("#gyss").html(ops1);
            },'json');
        }

            $('.form_date').datetimepicker({
                language:  'fr',
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0
            });
            function addpic() {
                let pics =`<div class="form-group">
            <label for="status" class="col-sm-2 control-label">请选择:</label>
            <div class="col-sm-3">
            <input type="file" class="form-control" name="pic">
            </div>
             <label for="status" class="col-sm-2 control-label">是否封面:</label>
            <div class="col-sm-3">
            <input type="radio"  name="fm" value='0'>
            </div>
            </div>`;
                $("#pics").append(pics);
            }

        </script>

    </body>
</html>
