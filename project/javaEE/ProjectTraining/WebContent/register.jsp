<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>天天学堂</title>
    <link rel="stylesheet" type="text/css" href="css/login.css" />
    <script type="text/javascript" src="js/login.js"></script>
    <style type="text/css">
        body {
            background-image: url("imgs-server/login/bg.jpg");
            background-size: 100%;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
    <div id="login_frame">
        <p id="image_logo"><img src="imgs-server/login/logo.png" height="100px" width="260px"></p>
     
        <form method="post" action="register">
            <p><label class="label_input">用户名</label><input type="text" id="user_name" name="userName" value="${userName }" class="text_field"/></p>
            <p><label class="label_input">密码</label><input type="password" id="user_pwd" name="userPwd" value="${userPwd }" class="text_field"/></p>
     
            <div id="login_control">
                <input type="submit" id="btn_login" value="注册"/>
                <br>
                <a id="forget_pwd" href="index.jsp">登录</a>
                <div style="color:#990033;font-size: 14px;font-family: 瀹嬩綋;margin-top:10px">${errorInfo }</div>
            </div>
        </form>
    </div>
 
</body>
</html>