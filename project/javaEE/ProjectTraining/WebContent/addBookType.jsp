<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <title>主页</title>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <script type="text/javascript" src="js/index.js"></script>
</head>
<body>
    <div id="box">
        <div id="header">
            <div id="header_content">
                <img src="imgs-server/home/logo.png">
                <span class="left_content_text">
                    <b>管理平台</b>
                </span>
                <span class="right_content_user_settings">
                    <ul>
                        <li onmouseout="hiddenAdminSetting()" onmouseover="showAdminSetting()" id="admin">
                            <span><b>admin</b></span><span><img src="imgs-server/home/xiala.png" id="admin_img"></span>
                        </li>
                    </ul>
                        <ul id="admin_settings" style="display: none">
                            <li onmouseout="hiddenAdminSetting()" onmouseover="showAdminSetting()"><a href="adminInfo.jsp">个人信息</a></li>
                            <li onmouseout="hiddenAdminSetting()" onmouseover="showAdminSetting()"><a href="index.jsp">切换账户</a></li>
                        </ul>
                </span>
            </div>
        </div>
        <div id="index_home">
            <div id="menu">
                <ul class="menu">
                    <li id="user" onclick="showUserMenu()">
                        <a>用户信息管理</a>
                        <img src="imgs-server/home/xiala.png" id="xiala1">
                    </li>
                    <ul class="user_menu"  id="click_user_menu" style="display: none">
                        <li onclick="showMenu_info1()" id="user_info"><a href="userInfo.jsp">用户信息</a></li>
                        <li onclick="showMenu_add1()" id="user_add"><a href="addUser.jsp">新增用户</a></li>
                    </ul>
                    <li id="idiom" onclick="showIdiomMenu()">
                        <a>成语信息管理</a><img src="imgs-server/home/xiala.png" id="xiala2">
                    </li>
                    <ul class="idiom_menu" id="click_idiom_menu" style="display: none">
                        <li onclick="showMenu_type2()" id="idiom_type"><a href="idiomType.jsp">成语分类</a></li>
                        <li onclick="showMenu_add_type2()" id="idiom_add_type"><a href="addIdiomType.jsp">新增分类</a></li>
                        <li onclick="showMenu_info2()" id="idiom_info"><a href="idiomInfo.jsp">成语展示</a></li>
                        <li onclick="showMenu_add2()" id="idiom_add"><a href="addIdiom.jsp">新增成语</a></li>
                    </ul>
                    <li id="book" onclick="showBookMenu()">
                        <a>图书信息管理</a>
                        <img src="imgs-server/home/xiala.png" id="xiala3">
                    </li>
                    <ul class="book_menu" id="click_book_menu" style="display: block">
                        <li onclick="showMenu_type3()" id="book_type"><a href="bookType.jsp">图书分类</a></li>
                        <li onclick="showMenu_add_type3()" id="book_add_type" style="background: #009688"><a href="addBookType.jsp">新增分类</a></li>
                        <li onclick="showMenu_info3()" id="book_info"><a href="bookInfo.jsp">图书展示</a></li>
                        <li onclick="showMenu_add3()" id="book_add"><a href="addBook.jsp">新增图书</a></li>
                    </ul>
                    <li id="active" onclick="showActiveMenu()">
                        <a>动态信息管理</a><img src="imgs-server/home/xiala.png" id="xiala4">
                    </li>
                    <ul class="active_menu" id="click_active_menu" style="display: none">
                        <li onclick="showMenu_info4()" id="active_info"><a>动态展示</a></li>
                        <li onclick="showMenu_add4()" id="active_add"><a>新增动态</a></li>
                    </ul>
                </ul>
            </div>

            <div id="info">
                <h3>新增图书分类</h3>
            </div>
        </div>
    </div>
    
    
</body>
</html>