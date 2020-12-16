<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
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
					<ul class="user_menu" id="click_user_menu" style="display: none">
						<li onclick="showMenu_info1()" id="user_info"><a
							href="AllParentsInfoServlet">用户信息</a></li>
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

			<div id="info">
				<h3>admin信息展示</h3>
			</div>
		</div>
	</div>


</body>
</html>