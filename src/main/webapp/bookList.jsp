
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="true" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            #button_pg{
                margin:0 400px;
            }
            #showmsg{
                margin:0 350px;
            }
        </style>
        <title>书籍信息</title>
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
                                <label for="bname">书籍名称:</label>
                                <input type="text" class="form-control" id="bname" name='bname' autocomplete="off" placeholder="书籍名称">
                            </div>
                            <div class="form-group">
                                <label for="price">价格:</label>
                                <input type="number" class="form-control" id="price" name='price' autocomplete="off" placeholder="可接受的最高价格">
                            </div>
                            <div class="form-group">
                                <label for="gys">供应商:</label>
                                <select name="gys" id="gys">
                                    <option value="0" name="gys" id="gys0">请选择供应商</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="type">类别:</label>
                                <select name="type" id="type">
                                    <option value="0" name="type" id="type0">请选择书籍类别</option>
                                </select>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">输入日期:</label>
                                <input type="text" class="layui-input" name="mydate" id="test1"
                                       placeholder="日期区间" autocomplete="off" >
                            </div>

                            <button type="button" class="btn btn-default" onclick="queryByTj()">查询</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <table class="table table-bordered table-hover table-striped table-condensed">
            <thead>
            <tr>
                <th>
                    <input type="checkbox" id="checkall" name="et1910">
                    <label for="checkall">全选</label>
                </th>
                <th>序号</th>
                <th>书名</th>
                <th>作者</th>
                <th>价格</th>
                <th>发行日期</th>
                <th>供应商</th>
                <th>类型</th>
                <th>数量</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="data">

            </tbody>
        </table>
        <div id="button_pg">
            <button type="button" class="btn btn-primary" id="first">首页</button>
            <button type="button" class="btn btn-success" id="pre">上一页</button>
            <button type="button" class="btn btn-warning" id="next">下一页</button>
            <button type="button" class="btn btn-danger" id="end">末页</button>
        </div>
        <div id="showmsg"></div>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/axios.min.js"></script>
        <script src="layui/layui.js"></script>
        <script>
            layui.use(['form','layer','laydate'],function() {
                let form = layui.form;
                let layer = layui.layer;
                let laydate = layui.laydate;

                laydate.render({
                    //绑定哪个元素对应input中的id属性
                    elem: '#test1' //指定元素
                    ,trigger:'click'
                    ,range: true

                });
            })
        </script>
        <script>
            let pn = 1,ps=3
            let tj=""

            $(function(){
                queryData(1,3,tj);
                queryGys()
                queryCate()
                $("#first").on("click",function(){
                    queryData(1,ps,tj);
                })
                $("#pre").on("click",function(){
                    queryData(pre,ps,tj);
                })
                $("#next").on("click",function(){
                    queryData(next,ps,tj);
                })
                $("#end").on("click",function(){
                    queryData(end,ps,tj);
                })
            })

            function queryData(pageNumber,pageSize,tj){
                $.ajax({
                    type:"post",
                    url:"book",
                    data:"method=queryBookSome&pageNumber="+pageNumber+"&pageSize="+pageSize + tj,
                    dataType:"json",
                    success:msg=>{
                        let info = msg[0]
                        let array = info.list
                        let trs = ""
                        first = 1
                        if(info.isFirstPage){
                            pre = 1
                        }else{
                            pre = info.prePage
                        }
                        if(info.isLastPage){
                            next = info.pages
                        }else{
                            next =info.nextPage
                        }
                        end =info.pages
                       /* console.log("pre:"+pre+"    next:"+next+"   end:"+end)
                        console.log(array)*/
                        $.each(array,function(i,sch){
                            let year = sch.publishdate.year+1900
                            let month = sch.publishdate.month+1
                            let day =sch.publishdate.date
                            let birth = year+"-"+month+"-"+day
                            trs+=`<tr><td><input type="checkbox" name="et1910"></td><td>${info.startRow+i}</td><td>${sch.name}</td>
                            <td>${sch.author}</td><td>${sch.price}</td><td>${birth}</td><td>${sch.gysname}</td>
                            <td>${sch.category}</td><td>${sch.sl}</td>
                            <td><button onclick="queryById('${sch.id}')" type='button' class='btn btn-danger'>详情</button></td></tr>`
                        })
                        let mm=""
                        mm+=`当前页数为第${info.pageNum}页，共${info.pages}页，每页${info.pageSize}条记录数，共${info.size}条数据`
                        $("#data").html(trs)
                        $("#showmsg").html(mm)
                    },
                    error:err=>{
                    }
                })
            }


            function queryById(id){
                console.log(id)
            }
            function queryGys(){
                $.ajax({
                    type:"post",
                    url:"gys",
                    data:"method=queryGys",
                    dataType:"json",
                    success:msg=>{
                        let array = msg[0]
                        $.each(array,function(i,gys){
                            $("#gys").append(`<option id=${gys.id} value=${gys.id}>${gys.name}</option>`)
                        })
                    },
                    error:err=>{
                    }
                })
            }
            function queryCate() {
                $.ajax({
                    type:"post",
                    url:"book",
                    data:"method=queryCate",
                    dataType:"json",
                    success:msg=>{
                        let array = msg[0]
                        $.each(array,function(i,cate){
                            $("#type").append(`<option id=${cate.id} value=${cate.id}>${cate.name}</option>`)
                        })
                    },
                    error:err=>{
                    }
                })
            }
            function queryByTj(){
                tj=""
                let name = $("#bname").val()
                let gys = $("#gys").val()
                let cate = $("#type").val()
                let price = $("#price").val()
                let time = $("#test1").val()
                console.log(time)
                if(name != ""){
                    tj+="&name="+name
                }if(gys != 0){
                    tj+="&gys="+gys
                }if(cate != 0){
                    tj+="&cate="+cate
                }if(price != ""){
                    tj+="&price="+price
                }if(time != ""){
                    tj+="&time="+time
                }
                queryData(1,ps,tj)
            }
        </script>
    </body>
</html>
<%----%>
