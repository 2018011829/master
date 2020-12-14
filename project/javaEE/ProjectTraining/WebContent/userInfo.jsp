<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <ul class="user_menu"  id="click_user_menu" style="display: block">
                        <li onclick="showMenu_info1()" id="user_info" style="background: #009688"><a href="AllParentsInfoServlet">用户信息</a></li>
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
                    <ul class="book_menu" id="click_book_menu" style="display: none">
                        <li onclick="showMenu_type3()" id="book_type"><a href="bookType.jsp">图书分类</a></li>
                        <li onclick="showMenu_add_type3()" id="book_add_type"><a href="addBookType.jsp">新增分类</a></li>
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
                <h3>用户信息展示</h3>
                 <table border='2' bordercolor='#1f748a' cellspacing='0' style="margin-bottom: 20px">
    <th>用户Id</th>
    <th>手机号</th>
    <th>密码</th>
    <th>昵称</th>
    <th>头像</th>
    <c:forEach items="${page.list}" var="parent">
    <tr>
        <td  style="text-align:center" width="100px">${parent.id}</td>
        <td style="text-align:center" width="200px">${parent.phone}</td>
        <td  style="text-align:center" width="200px">*******</td>
        <td  style="text-align:center" width="200px">${parent.nickname }</td>
        <td class="parent_td" style="text-align:center" cellpadding="10"><img alt="图片" src="${avatarPath}${parent.avator}" width="80px" margin="10" ></td>
        
    </tr>
    </c:forEach>
    </table>
        总共有${page.totalPageNum }页，总共有${page.totalCount }个数据；
    <a href="AllParentsInfoServlet?page=1" style="color:#1f748a;">首页</a>
    <a href="AllParentsInfoServlet?page=${page.prePageNum }" style="color:#1f748a;">上一页</a>
    <a href="AllParentsInfoServlet?page=${page.nextPageNum }" style="color:#1f748a;">下一页</a>
    <a href="AllParentsInfoServlet?page=${page.totalPageNum }" style="color:#1f748a;">末页</a>
    
            </div>
        </div>
    </div>
</body>
</html>