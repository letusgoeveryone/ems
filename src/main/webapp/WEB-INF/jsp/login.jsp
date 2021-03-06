<%-- 
    Document   : login
    Created on : 2017-1-6, 13:34:25
    Author     : wht
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //    将项目的根取出来，页面中不再使用相对路径
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="河南大学软件学院">
        <link rel="icon" href="favicon.ico">
        <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=path%>/css/login.css" rel="stylesheet">
        <!--[if lt IE 9]>
                    <script src="js/html5shiv.min.js"></script>
                    <script src="js/respond.min.js"></script>
        <![endif]-->
        <script src="<%=path%>/js/jquery-3.1.1.min.js"></script>
        <script src="<%=path%>/js/bootstrap.min.js"></script>
        <script src="<%=path%>/js/md5.js"></script>
        <script src="<%=path%>/js/login.js"></script>
        <script>
            function getBasePath() {
                return "<%=path%>";
            }
            // 设置obj的背景，有待改进为获取服务器中的背景图片文件列表，通过列表来设置背景
            //表达式中的10为背景文件名的最大编号
            function setBackground(obj) {
                var fileName = /*$("#base").text()*/ getBasePath() + "/images/background/" + (Math.floor(Math.random() * 10) + 1) + ".jpg";
                var fileUrl = "url(" + fileName + ")";
                obj.css("background-image", fileUrl);
            }
        </script>
        <title>软件学院登录页面</title>
    </head>

    <body>
        <header id="header">
            <img src="<%=path%>/images/rjxylogo.png" alt="软件学院的log">
            <span>⊙教學系統</span>
        </header>
        <section>
            <div id="body">
                <div id="login-container">
                    <!-- <form class="form-sigin" action="" method="post" role="form"> -->
                    <!--工号/学号-->
                    <div class="input-group input-group-lg">
                        <label id="id_label" class="input-group-addon" for="id">工号/学号</label>
                        <input type="text" class="form-control" id="id" onfocus="hideHint($('#id_reminder'))" name="id" placeholder="请输入工号/学号">
                    </div>
                    <div>
                        <label id="id_reminder" for="id">* 请输入工号/学号</label>
                    </div>
                    <!--密码-->
                    <div class="input-group input-group-lg">
                        <label id="pw_label" class="input-group-addon" for="pw">密码</label>
                        <input type="password" class="form-control" id="pw" onfocus="hideHint($('#pw_reminder'))" name="pw" placeholder="请输入密码">
                    </div>
                    <div>
                        <label id="pw_reminder" for="id">* 请输入密码</label>
                    </div>
                    <!--登录按钮-->
                    <div class="btn-group">
                        <button type="submit" class="btn btn-success btn-lg" onclick="login()">登录</button>
                    </div>
                    <!-- </form> -->
                </div>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only" onclick="closeModal()">关闭</span></button>
                            <h4 class="modal-title" id="myModalLabel">无法登录</h4>
                        </div>
                        <div class="modal-body">
                            对不起，您不是我们的用户，或者您输入的信息有误！
                            <br/>如果您是老师，请您联系管理员；如果您是学生，请联系您的老师！
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal()">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <footer>
            <div class="copyright">
                Copyright © 2017 河南大学软件学院
            </div>
        </footer>
    </body>

</html>

