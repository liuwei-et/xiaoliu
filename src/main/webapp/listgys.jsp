<%--
  Created by IntelliJ IDEA.
  User: 帅健健
  Date: 2020/1/1
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="true" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">

  </head>

  <body>
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
                <label  for="qname">供应商名</label>
                <input type="text" class="form-control" id="qname" placeholder="供应商名">
              </div>
              <div class="form-group">
                <label  for="loc">地址</label>
                <input type="text" class="form-control" id="loc" placeholder="地址">
              </div>

              <button type="button" onclick="queryBtTj()" class="btn btn-primary">查询</button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <table class="table table-bordered table-hover table-striped table-condensend">
      <thead>
        <tr>
          <th>
            <input type="checkbox" id="checkall">
            <label for="checkall">全选</label>
          </th>
          <th>序号</th>
          <th>供应商名</th>
          <th>地址</th>
          <th>详情</th>

        </tr>
      </thead>
      <tbody id="data"></tbody>
    </table>
    <button type="button" class="btn btn-primary glyphicon glyphicon-fire" id="first">首页</button>
    <button type="button" class="btn btn-success glyphicon glyphicon-flash" id="pre">上一页</button>
    <button type="button" class="btn btn-warning glyphicon glyphicon-send"  id="next">下一页</button>
    <button type="button" class="btn btn-danger glyphicon glyphicon-leaf" id="end">末页</button>

    <!--模态-->
   <%--<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
          </div>
          <form class="form-horizontal" role="form" action="stu" method="post" enctype="multipart/form-data">
            <input type="hidden" name="method" value="add">
            <div class="form-group">
              <label for="pic" class="col-sm-2 control-label">头像:</label>
              <div class="col-sm-10">
                <input type="file" class="form-control" id="pic" name="pic" >
              </div>
            </div>
            <div class="form-group">
              <label for="name" class="col-sm-2 control-label">供应商名：</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="name" placeholder="请输入姓名" name="name">
              </div>
            </div>
            <div class="form-group">
              <label for="loc" class="col-sm-2 control-label">地址:</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="loc" placeholder="请输入身份证" name="loc">
              </div>
            </div>
            &lt;%&ndash;<div class="form-group">
              <label for="address" class="col-sm-2 control-label">生日:</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="address"  name="address" placeholder="请输入地址" >
              </div>
            </div>
            <div class="form-group">
              <label for="phone" class="col-sm-2 control-label">邮箱:</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号" >
              </div>
            </div>&ndash;%&gt;

            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">添加</button>
              </div>
            </div>
          </form>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>--%>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/axios.min.js"></script>

    <script>


      function queryBtTj() {
          tj="";
          let name= $("#qname").val();
          let loc=$("#loc").val();



          if(name!="") {
              tj += "&name=" + name
          }
          if(loc!="") {
              tj += "&loc=" + loc
          }


          queryData(1,ps,tj)
      }

      let pn=1,ps=3
      let pre,next,end
      let tj="";
      $(function () {
          queryData(pn,ps,tj);
          //
          $("#first").on("click",function () {
              queryData(1,ps,tj)
          })
          $("#pre").on("click",function () {
              queryData(pre,ps,tj)
          })
          $("#next").on("click",function () {
              queryData(next,ps,tj)
          })
          $("#end").on("click",function () {
              queryData(end,ps,tj)
          })
      })
      //
      function queryData(pageNumber,pageSize,tiaojian) {
          $.ajax({
              url:"gys",
              type:"post",
              data:"method=querySome&pageNumber="+pageNumber+"&pageSize="+pageSize+tiaojian,
              dataType:"json",
              success:msg=>{
                  pre=msg.pre
                  next=msg.next
                  end=msg.pageCount
                  let trs="";
                  $.each(msg.rows,function (i,g) {
                     trs+=`<tr><td><input type='checkbox' name='et1910'/></td>
                    <td>${msg.start+i+1}</td><td>${g.name}</td><td>${g.loc}</td>
                    <td><button onclick="queryById('${g.id}')" type='button' class='btn btn-danger'>详情</button></td>
                    </tr>`;



                  })
                  $("#data").html(trs)
              }
          })

      }
      /*function queryById(yzid) {
          alert(yzid)
          const  params = new URLSearchParams();
          params.append('id',yzid)
          params.append("method","queryById");
          axios.post('yz',params)
          .then(function (response) {
            console.log(response);
            $("#name").val(response.data.name);
            $("#address").val(response.data.address);
            $("#phone").val(response.data.phone);
            $("#cardnum").val(response.data.cardnum);
            })
            .catch(function (error) {
            console.log(error)
            })

          $("#myModal").modal("show")

      }*/
    </script>
  </body>
</html>
