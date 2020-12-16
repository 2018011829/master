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
        <%@ include file="header.jsp"%>
        <div id="index_home">
            <div id="menu">
				<ul class="menu">
					<li id="user" onclick="showUserMenu()"><a>用户信息管理</a> <img
						src="imgs-server/home/xiala.png" id="xiala1"></li>
					<ul class="user_menu" id="click_user_menu" style="display: block">
						<li onclick="showMenu_info1()" id="user_info"
							style="background: #009688"><a href="AllParentsInfoServlet">用户信息</a></li>
						<li onclick="showMenu_add1()" id="user_add"><a
							href="addUser.jsp">新增用户</a></li>
					</ul>
					<li id="idiom" onclick="showIdiomMenu()"><a>成语信息管理</a><img
						src="imgs-server/home/xiala.png" id="xiala2"></li>
					<ul class="idiom_menu" id="click_idiom_menu" style="display: none">
						<li onclick="showMenu_type2()" id="idiom_type"><a
							href="GetIdiomTypesServlet?userName=${userName }">成语分类</a></li>
						<li onclick="showMenu_add_type2()" id="idiom_add_type"><a
							href="AddIdiomType?userName=${userName }">新增分类</a></li>
						<li onclick="showMenu_info2()" id="idiom_info"><a
							href="GetIdiomInfoServlet?userName=${userName }">成语展示</a></li>
						<li onclick="showMenu_add2()" id="idiom_add"><a
							href="AddIdiom?userName=${userName }">新增成语</a></li>
					</ul>
					<li id="book" onclick="showBookMenu()"><a>图书信息管理</a> <img
						src="imgs-server/home/xiala.png" id="xiala3"></li>
					<ul class="book_menu" id="click_book_menu" style="display: none">
						<li onclick="showMenu_type3()" id="book_type"><a
							href="GetBookTypesServlet?userName=${userName }">图书分类</a></li>
						<li onclick="showMenu_add_type3()" id="book_add_type"><a
							href="AddBookType?userName=${userName }">新增分类</a></li>
						<li onclick="showMenu_info3()" id="book_info"><a
							href="GetBookInfoServlet?userName=${userName }">图书展示</a></li>
						<li onclick="showMenu_add3()" id="book_add"><a
							href="AddBook?userName=${userName }">新增图书</a></li>
					</ul>
					<li id="active" onclick="showActiveMenu()"><a>动态信息管理</a><img
						src="imgs-server/home/xiala.png" id="xiala4"></li>
					<ul class="active_menu" id="click_active_menu"
						style="display: none">
						<li onclick="showMenu_info4()" id="active_info"><a>动态展示</a></li>
						<li onclick="showMenu_add4()" id="active_add"><a>新增动态</a></li>
					</ul>
				</ul>
			</div>

            <div id="info" style="height: 700px;">
            	<div
					style="height: 40px; width: 1100px; text-align: center; line-height: 40px; font-weight: 600; font-size: 20px; color: #009688; margin-top: 10px">
					<p>用户信息展示</p>
				</div>
				<div style="height: 40px; line-height: 40px; margin-bottom: 20px">
					<span> <input type="text" name="bookTypeSearch"
						style="height: 25px; width: 200px; vertical-align: bottom"
						placeholder="请输入类型的信息">
					</span> <span> <a href="SearchBookType"><img alt="搜索"
							src="imgs-server/home/search.png"
							style="width: 40px; height: 30px; vertical-align: bottom">
					</a>
					</span>
				</div>
				<div style="width: 1100px; text-align: center; margin-bottom: 20px">
	                 <table class="table">
	                 	<tr>
	                 		<td style="background: #F2F2F2"><input type="checkbox"></td>
						    <td style="background: #F2F2F2">用户Id</td>
						    <td style="background: #F2F2F2">手机号</td>
						    <td style="background: #F2F2F2">密码</td>
						    <td style="background: #F2F2F2">昵称</td>
						    <td style="background: #F2F2F2">头像</td>
	                 	</tr>
	                 	
					    <c:forEach items="${page.list}" var="parent">
						    <tr>
						    	<td><input type="checkbox"></td>
						        <td  style="text-align:center" width="100px">${parent.id}</td>
						        <td style="text-align:center" width="200px">${parent.phone}</td>
						        <td  style="text-align:center" width="200px">*******</td>
						        <td  style="text-align:center" width="200px">${parent.nickname }</td>
						        <td class="parent_td" style="text-align:center" cellpadding="10"><img alt="图片" src="${avatarPath}${parent.avator}" width="80px" margin="10" ></td>
						        
						    </tr>
					    </c:forEach>
			    	</table>
			    	<div style="margin-top: 30px">总共有${page.totalPageNum }页，总共有${page.totalCount }个数据；
					    <a href="AllParentsInfoServlet?page=1" style="color:#1f748a;">首页</a>
					    <a href="AllParentsInfoServlet?page=${page.prePageNum }" style="color:#1f748a;">上一页</a>
					    <a href="AllParentsInfoServlet?page=${page.nextPageNum }" style="color:#1f748a;">下一页</a>
					    <a href="AllParentsInfoServlet?page=${page.totalPageNum }" style="color:#1f748a;">末页</a>
			    	</div>
            	</div>
        </div>
    </div>
    </div>
</body>
</html>