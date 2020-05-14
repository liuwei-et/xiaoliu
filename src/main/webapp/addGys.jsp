<%@ page contentType="text/html;charset=UTF-8" isELIgnored="true" language="java" %>
<% request.setCharacterEncoding("utf-8"); %>
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
        <form  action="gys" method="post" class="form-horizontal" role="form">
            <input type="hidden" name="method" value="addGys">

            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">供应商:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" name="name" autocomplete="off" placeholder="请输入供应商">
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">地址:</label>
                <div class="col-sm-3">
                    <select  class="form-control" id="pro" name="proname"  onchange="qeuryCt(this.value)">
                    </select>
                </div>
                <div class="col-sm-3">
                    <select class="form-control" id="city" name="cityname" ></select>
                </div>
                <div class="col-sm-4">
                    <input class="form-control"  type="text"  id="info" name="info" placeholder="请输入详细地址">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">添加</button>
                </div>
            </div>

        </form>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
        <script>
            $(function(){
                queryAllProvince();
            })
            function queryAllProvince() {
                $.post("gys","method=queryAllProvince",msg=>{
                    let  ops = `<option value='-1'>==请选择省份==</option>`
                    $.each(msg,function (i,loc) {
                         ops += `<option value='${loc.name}'>${loc.name}</option>`
                    })
                    $("#pro").html(ops);

                },"json");
            }/*
                function qeuryCt(proname) {
                    $.post("gys","method=queryAllByname&name"+proname,msg=>{
                        let  ops = `<option value='-1'>==请选择市/区==<option>`
                        $.each(msg,function (i,loc) {

                        })
                    $("#city").html(ops);
                    },"json");
                }*/
            function qeuryCt(proname) {
                $.ajax({
                    type:"post",
                    url:"gys",
                    data:"method=queryAllByname&name="+proname,
                    dataType:"json",
                    success:msg=>{
                        let  ops = `<option value='-1'>==请选择省份==</option>`
                        $.each(msg,function(i,city){
                            ops += `<option value='${city.name}'>${city.name}</option>`
                        })
                        $("#city").html(ops);
                    },
                    error:err=>{
                    }
                })
            }




        </script>
    </body>
</html>
