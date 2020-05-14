
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- 页头-->
<div class="page-header">
    <image src="image/timg.gif" width="100%" height="130px" ></image>
</div>
<!--导航条-->

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">书店管理</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">首页</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">更多<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">注册</a></li>
                        <li class="divider"></li>
                        <li><a href="#">登录</a></li>

                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="#" class="list-group-item active">书籍管理</a>
                <a href="addCa.jsp" target="et" class="list-group-item">书籍类型添加</a>
                <a href="cateList.jsp" target="et" class="list-group-item">类型列表</a>
                <a href="addGys.jsp" target="et" class="list-group-item">添加供应商</a>
                <a href="listgys.jsp" target="et" class="list-group-item">供应商列表</a>
                <a href="addbook.jsp" target="et" class="list-group-item">添加供应商</a>
                <a href="bookList.jsp" target="et" class="list-group-item">书籍信息列表</a>
                <%--
               <a href="schoollist.jsp" target="et" class="list-group-item">学校列表</a>
               <a href="addstudent.jsp" target="et" class="list-group-item">添加学生</a>
               <a href="addschool.jsp" target="et" class="list-group-item">添加学校</a>--%>
            </div>
        </div>
        <div class="col-md-10">
            <iframe src="welcome.jsp" width="100%" height="800px" name="et" frameborder="0"></iframe>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>