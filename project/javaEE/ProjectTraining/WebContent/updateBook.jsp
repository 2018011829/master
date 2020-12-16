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
<style type="text/css">
        .notice{
            height: 30px;
            line-height: 15px;
            font-size: 12px;
            color: #990033;
        }
</style>
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
                        <li onclick="showMenu_info3()" id="book_info"><a
                            href="GetBookInfoServlet?userName=${userName }">图书展示</a></li>
                        <li onclick="showMenu_add3()" id="book_add"
                            style="background: #009688"><a href="AddBook?userName=${userName }">新增图书</a></li>
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
                <!-- 显示表格 -->
                <br>
                <div
                    style="height: 40px; width: 1100px; text-align: center; line-height: 40px; font-weight: 600; font-size: 20px; color: #009688; margin-top: 10px">
                    <p>新增图书</p>
                </div>
                <div style="width: 1100px; margin-bottom: 20px">
                    <form action="AddBookServlet" method="post"
                        enctype="multipart/form-data">
                        <br> <span>图书名称：<input class="inp" type="text" name="bookName"
                            style="width: 200px; height: 25px;" value="${newBook.name }">
                        </span>
                        <p class="notice"></p>
                        <span>图书作者：<input class="inp" type="text" name="bookAuthor"
                            style="width: 200px; height: 25px;" value="${newBook.author }">
                        </span>
                        <p class="notice"></p>
                        <span>图书类型：<select name="bookType"
                            style="width: 200px; height: 25px;">
                                <c:forEach var="bookType" items="${bookTypes }">
                                    <option value="${bookType.type }">${bookType.type }</option>
                                </c:forEach>
                        </select></span><br>
                        <br> <span>图书图片：<input class="inp" type="file" name="bookImg"
                            style="width: 200px; height: 25px;"></span>
                        <p class="notice"></p>
                         <span>适合的年级：</span> <select name="bookGrades"
                            style="width: 200px; height: 25px">
                            <option value="small">1-3年级</option>
                            <option value="big">4-6年级</option>
                        </select><br>
                        <br> <span>电子书：<input class="inp" type="file" name="book"
                            style="width: 200px; height: 25px;" value="${newBook.content }"></span>
                        <p class="notice"></p>
                        <div style="margin: 0 auto; width: 302px">
                            <div style="width: 100px; line-height: 100px; float: left">图书简介:</div>
                            <div>
                                <textarea class="inp" name="bookIntroduce"
                                    style="width: 200px; height: 100px">${newBook.introduce }</textarea>
                            </div>
                            <p class="notice"></p>
                        </div>
                        <p style="line-height: 40px;">
                            <input type="hidden" name="userName" value="${userName }">
                            <input type="submit" value="提交"
                                style="width: 100px; height: 35px; background: #009688; color: white">
                        </p>
                        <br>
                        <p class="notice" style="font-size:20px">${errorInfo }</p>
                    </form>
                </div>
            </div>
        </div>
    </div>


</body>
<script>
    // 1）用户名和密码文本域在获得焦点时会分别在下方显示对应的提示文字，失去焦点时提示文字消失。
        var inp=document.getElementsByClassName("inp");
        var notice=document.getElementsByClassName("notice");
        var flag=0;
        inp[0].onblur=function(){
            if(inp[0].value.length<=0){
                notice[0].innerHTML="书名不能为空！";
            }
            if(inp[0].value.length>0){
                notice[0].innerHTML="";
            }
        }
        inp[1].onfocus=function(){
            if(inp[0].value.length<=0){
                notice[0].innerHTML="书名不能为空！";
            }
            if(inp[0].value.length>0){
                notice[0].innerHTML="";
            }
        }
        inp[1].onblur=function(){
            if(inp[1].value.length<=0){
                notice[1].innerHTML="书的作者不能为空！";
            }
            if(inp[1].value.length>0){
                notice[1].innerHTML="";
            }
        }
        inp[2].onfocus=function(){
            if(inp[1].value.length<=0){
                notice[1].innerHTML="书的作者不能为空！";
            }
            if(inp[1].value.length>0){
                notice[1].innerHTML="";
            }
        }
        inp[2].onblur=function(){
            if(inp[2].value.length<=0){
                notice[2].innerHTML="书的图片不能为空！";
            }
            if(inp[2].value.length>0){
                notice[2].innerHTML="";
            }
        }
        inp[3].onfocus=function(){
            if(inp[1].value.length<=0){
                notice[1].innerHTML="书的作者不能为空！";
            }
            if(inp[1].value.length>0){
                notice[1].innerHTML="";
            }
            if(inp[2].value.length<=0){
                notice[2].innerHTML="书的图片不能为空！";
            }
            if(inp[2].value.length>0){
                notice[2].innerHTML="";
            }
        }
        inp[3].onblur=function(){
            if(inp[3].value.length<=0){
                notice[3].innerHTML="电子书籍不能为空！";
            }
            if(inp[3].value.length>0){
                notice[3].innerHTML="";
            }
        }
        inp[4].onfocus=function(){
            if(inp[1].value.length<=0){
                notice[1].innerHTML="书的作者不能为空！";
            }
            if(inp[1].value.length>0){
                notice[1].innerHTML="";
            }
            if(inp[2].value.length<=0){
                notice[2].innerHTML="书的图片不能为空！";
            }
            if(inp[2].value.length>0){
                notice[2].innerHTML="";
            }
            if(inp[3].value.length<=0){
                notice[3].innerHTML="电子书籍不能为空！";
            }
            if(inp[3].value.length>0){
                notice[3].innerHTML="";
            }
        }
        inp[4].onblur=function(){
            if(inp[4].value.length<=0){
                notice[4].innerHTML="书籍简介不能为空！";
            }
            if(inp[4].value.length>0){
                notice[4].innerHTML="";
            }
        }
</script>
</html>