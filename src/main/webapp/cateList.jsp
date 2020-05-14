<%@ page contentType="text/html;charset=UTF-8" isELIgnored="true" language="java" %>
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
<!--查询区域-->
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
    <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="headingOne">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    条件查询
                </a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
            <div class="panel-body">
                <form class="form-inline" role="form">
                    <div class="form-group">
                        <label for="qname">名字:</label>
                        <input type="text" class="form-control"
                               id="qname" placeholder="业主名字">
                    </div>


                    <button type="button" onclick="queryByTj()"  class="btn btn-primary">查询</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!--查询区域-->
<table class="table table-bordered table-hover
  table-striped table-condensed">
    <thead>
    <tr>
        <th><input type="checkbox" id="checkall">
            <label for="checkall">全选</label></th>
        <th>序号</th>
        <th>类别名字</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody  id="data"></tbody>

</table>
<button type="button" class="btn btn-primary" id="first">首页</button>
<button type="button" class="btn btn-success" id="pre">上一页</button>
<button type="button" class="btn btn-warning" id="next">下一页</button>
<button type="button" class="btn btn-danger"  id="end">末页</button>



<!-- Modal -->
<%--
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        <form action="yz" method="post" class="form-horizontal"
              role="form" enctype="multipart/form-data">

          <div class="form-group">
            <label for="pic" class="col-sm-2 control-label">头像:</label>
            <div class="col-sm-10">
              <input type="file" class="form-control" id="pic" name="pic"
              >
            </div>
          </div>
          <div class="form-group">
            <label for="name" class="col-sm-2 control-label">名字:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="name" name="name"
                     placeholder="输入业主名字">
            </div>
          </div>
          <!--身份证-->
          <div class="form-group">
            <label for="cardnum" class="col-sm-2 control-label">身份证号:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="cardnum"
                     name="cardnum" placeholder="输入业主身份证">
            </div>
          </div>
          <!--地址-->
          <div class="form-group">
            <label for="address" class="col-sm-2 control-label">身份证号:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="address"
                     name="address" placeholder="输入地址">
            </div>
          </div>
          <!--电话-->
          <div class="form-group">
            <label for="phone" class="col-sm-2 control-label">电话:</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="phone"
                     name="phone" placeholder="输入电话">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-primary">添加</button>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
--%>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<!--js 库-->
<script src="js/axios.min.js"></script>
<script>
    function queryByTj(){
        tj="";
        let name = $("#qname").val();

        if(name!="" && name!=null){
            tj+="&name="+name;
        }

        queryData(1,ps,tj);
    }
    let pn =1,ps=3;
    let pre,next,end;
    let tj="";
    $(function(){
        queryData(pn,ps,tj);
        //当整个页面加载完毕 ===》所有按钮加载完毕
        $("#first").on("click",function(){
            queryData(1,ps,tj);
        });
        $("#pre").on("click",function(){
            queryData(pre,ps,tj);
        });
        $("#next").on("click",function(){
            queryData(next,ps,tj);
        });
        $("#end").on("click",function(){
            queryData(end,ps,tj);
        });

    })
    /*  POST  /yz  Http/1.1
     accept:applicaion/json  json


     key=value&key=value  method:querySome pageNumber:1  pageSize:3
     *
     * */
    function queryData(pageNumber,pageSize,tj){
        $.ajax({
            type:"POST",
            url:"ca",                                                             // &name=xx&address=xxx
            data:"method=queryCateSome&pageNumber="+pageNumber+"&pageSize="+pageSize+tj,
            dataType:"json",
            success:msg=>{//msg==>{} {pageNumber:xx,pageSize:xx,pre:xx,next:xxx,rows:[{id:xx,name:xx,address:xx},{}]}   [{key:value,}]
                pre = msg.pre;
                next = msg.next;
                end = msg.pageCount;
                let trs="";
                $.each(msg.rows,function(i,yz){

                    trs+=`<tr><td><input type='checkbox' name='et1910'/></td>
                    <td>${msg.start+i+1}</td><td>${yz.name}</td><td><button onclick="queryById('${yz.id}')" type='button' class='btn btn-danger'>
                    详情</button></td></tr>`;

                });
                $("#data").html(trs);

            }
        });
    }
</script>
</body>
</html>
