<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.lang.reflect.Type"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.reflect.TypeToken" %>
<%@page import="com.group.tiantian.server.entity.ChildType"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" charset="UTF-8">
<!--使用ajax动态局部动态更新一级菜单的二级菜单-->
$(document).ready(function(){
    $("#parentTypeId").change(function(){
        $.get("QuerryChildTypeServlet",{parentTypeId : $("#parentTypeId").val()},
                function(data){
            var jsons=JSON.parse(data);
            <!--先移除原有的节点-->
            var elements=document.getElementsByClassName("childAdd");
            for(var i = elements.length - 1; i >= 0; i--) { 
              elements[i].parentNode.removeChild(elements[i]); 
            }
            for (var i=0;i<jsons.length;i++){
                $("#childTypeId").append("<option class=\"childAdd\" value="+jsons[i].id+">"+jsons[i].classifyName+"</option>");

            }
        })
    })
});
</script>
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
					<ul class="idiom_menu" id="click_idiom_menu" style="display: block">
						<li onclick="showMenu_type2()" id="idiom_type"><a
							href="GetIdiomTypesServlet?userName=${userName }">成语分类</a></li>
						<li onclick="showMenu_add_type2()" id="idiom_add_type"><a
							href="AddIdiomType?userName=${userName }">新增分类</a></li>
						<li onclick="showMenu_info2()" id="idiom_info"><a
							href="GetIdiomInfoServlet?userName=${userName }">成语展示</a></li>
						<li onclick="showMenu_add2()" id="idiom_add"
							style="background: #009688"><a href="AddIdiom?userName=${userName }">新增成语</a></li>
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

			<div id="info" style="height: 550px;">
				<!-- 显示表格 -->
				<br>
				<div
					style="height: 40px; width: 1100px; text-align: center; line-height: 40px; font-weight: 600; font-size: 20px; color: #009688; margin-top: 10px">
					<p>新增成语</p>
				</div>
				<br>
				<br>
				<div style="width: 1100px; text-align: center; margin-bottom: 20px">
					<form action="AddIdiomServlet">
						<p>
							<span>成语：</span> <input type="text" name="idiom"
								style="width: 200px; height: 25px;">
						</p>
						<br>
						<br>
						<p>
							<span>成语一级类型：</span> 
							<select name="idiomParentType" id="parentTypeId"
								style="width: 200px; height: 25px;">
								 <option value="0" selected="selected">请选择</option>
								<c:forEach var="idiomType" items="${idiomTypes }">
								<c:if test="${idiomType.childType == '空'}">
									<option value="${idiomType.id }">${idiomType.parentType}</option>
								</c:if>
								</c:forEach>
							</select>
						</p>
						<br>
						<br>
						<p>
							<span>成语二级类型：</span> 
							<select name="idiomType" id="childTypeId"
								style="width: 200px; height: 25px;">
								 <option value="0" selected="selected">请选择</option>
								<%--使用ajax实现异步加载选中父类型的子类型 --%>
								<%-- <c:forEach var="idiomType" items="${idiomTypes }">
									<c:if test="${idiomType.childType!='空' }">
										<option class="childAdd" value="${idiomType.childType }">${idiomType.childType }</option>
									</c:if>
								</c:forEach> --%>
							</select>
						</p>
						<br>
						<br>
						<p style="line-height: 40px;">
						
							<input type="hidden" name="userName" value="${userName }">
							<input type="submit" value="提交"
								style="width: 100px; height: 35px; background: #009688; color: white">
						</p>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>