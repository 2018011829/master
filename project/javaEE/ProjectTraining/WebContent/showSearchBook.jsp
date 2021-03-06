<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<ul class="book_menu" id="click_book_menu" style="display: block">
						<li onclick="showMenu_type3()" id="book_type"><a
							href="GetBookTypesServlet?userName=${userName }">图书分类</a></li>
						<li onclick="showMenu_add_type3()" id="book_add_type"><a
							href="AddBookType?userName=${userName }">新增分类</a></li>
						<li onclick="showMenu_info3()" id="book_info"
							style="background: #009688"><a
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

			<div id="info" style="height: 2500px;">
				<br>
				<!-- 显示表格 -->
				<div
					style="height: 40px; width: 1100px; text-align: center; line-height: 40px; font-weight: 600; font-size: 20px; color: #009688; margin-top: 10px">
					<p>图书展示</p>
				</div>
				<div style="height: 40px; line-height: 40px; margin-bottom: 20px">
					<form action="SearchBookServlet">
						<span> <input type="text" name="searchInfo"
							style="height: 25px; width: 200px; vertical-align: bottom"
							placeholder="请输入要查找的书籍信息" value="${searchInfo }">
						</span> 
						<span> 
							<input value="" type="submit" style="border:none;width:40px;height:30px;background: url('imgs-server/home/search.png');background-size: 40px 30px; vertical-align: bottom">
						</span>
						<input type="hidden" name="userName" value="${userName }">
					</form>
				</div>
				<div style="width: 1100px; text-align: center; margin-bottom: 20px">
					<table class="table">
						<tr>
							<td style="background: #F2F2F2"><input type="checkbox"></td>
							<td style="background: #F2F2F2">id</td>
							<td style="background: #F2F2F2">图书图片</td>
							<td style="background: #F2F2F2">图书名称</td>
							<td style="background: #F2F2F2">图书类型</td>
							<td style="background: #F2F2F2">作者</td>
							<td style="background: #F2F2F2">适合年级</td>
							<td style="background: #F2F2F2">电子书籍</td>
							<td style="background: #F2F2F2; width: 400px">图书介绍</td>
							<td style="background: #F2F2F2">操作1</td>
							<td style="background: #F2F2F2">操作2</td>
						</tr>
						<!-- 循环输出菜单 -->
						<c:forEach var="book" items="${page.list }">
							<tr>
								<td><input type="checkbox"></td>
								<td>${book.id }</td>
								<td><img alt="${book.name }" src="bookImgs/${book.img }"
									style="width: 90px; height: 120px"></td>
								<td>${book.name }</td>
								<td>${book.type }</td>
								<td>${book.author }</td>
								<td>${book.grades }</td>
								<td>${book.content }</td>
								<td style="font-size:12px">${book.introduce }</td>
								<td><a
									href="UpdateBook?id=${book.id }&userName=${userName }&page=${page.prePageNum+1 }"
									style="color: black">修改</a></td>
								<td><a
									href="DeleteBookServlet?id=${book.id }&userName=${userName }&page=${page.prePageNum+1 }"
									style="color: black">删除</a></td>
							</tr>
						</c:forEach>
					</table>
					<div style="margin-top: 30px">
						总共有${page.totalPageNum }页，总共有${page.totalCount }个数据； <a
							href="SearchBookServlet?page=1&userName=${userName }&searchInfo=${searchInfo }"
							style="color: black">首页</a> <a
							href="SearchBookServlet?page=${page.prePageNum }&userName=${userName }&searchInfo=${searchInfo }"
							style="color: black">上一页</a> <a
							href="SearchBookServlet?page=${page.nextPageNum }&userName=${userName }&searchInfo=${searchInfo }"
							style="color: black">下一页</a> <a
							href="SearchBookServlet?page=${page.totalPageNum }&userName=${userName }&searchInfo=${searchInfo }"
							style="color: black">末页</a>
					</div>
				</div>

			</div>
		</div>
	</div>


</body>
</html>