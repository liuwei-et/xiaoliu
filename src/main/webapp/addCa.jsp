<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<form  action="ca" method="post" class="form-horizontal" role="form">
    <input type="hidden" name="method" value="add">

    <div class="form-group">
        <label for="type" class="col-sm-2 control-label">书籍类型</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="type" name="name" autocomplete="off" placeholder="请输入书籍类型">
        </div>

    </div>
    <div class="col-sm-2 control-label">
        <button type="submit" id='add' class="btn btn-primary" disabled="true">添加</button>
    </div>
    <div style="font-size: 10px" id="check">
    </div>

</form>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script>
    $('#type').blur(function(){
        let type = $('#type').val()
        $.ajax({
            type:'post',
            url:'ca',
            data:'method=checkCategory&type='+type,
            dataType:'text',
            success:msg=>{
                console.log(msg)
                if(msg == 'suc'){
                    $('#add').attr('disabled',false)
                    $('#check').html('<div style="color:green;font-size:20px">当前类型可以添加<div>')
                }else if(msg == 'null'){
                    $('#add').attr('disabled',true)
                    $('#check').html('<div style="color:red;font-size:20px">类型不能为空<div>')
                }else{
                    $('#add').attr('disabled',true)
                    $('#check').html('<div style="color:red;font-size: 20px">类型已经存在<div>')
                }
            }
        })
    })
</script>
</body>
</html>
