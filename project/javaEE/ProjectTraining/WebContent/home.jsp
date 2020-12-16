<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
<script type="text/javascript" src="js/index.js"></script>
<!-- 引入 echarts.js -->
<script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
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

			<!-- 站点数据统计 -->
			<div id="info">
				<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
				<br><br>
				<div id="main1" style="width: 700px; height: 400px; margin: 0 auto"></div>
                <br>
				<div id="main2" style="width: 600px; height: 400px; margin: 0 auto"></div>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
	var myChart1 = echarts.init(document.getElementById('main1'));
	var userData = new Array();
	var localData = new Array();
	<% List<Integer> data1=(List<Integer>)request.getAttribute("userData");
	   if(data1 != null){
		   for(int i = 0 ;i<data1.size();i++){
	%>
              userData[<%=i%>]= <%=data1.get(i)%>;
	<%
           }
	   }
	%>
	
	<% List<Integer> data2=(List<Integer>)request.getAttribute("localData");
	    if(data2 != null){
	        for(int i = 0 ;i<data2.size();i++){
	 %>
	           localData[<%=i%>]= <%=data2.get(i)%>;
	 <%
	        }
	    }
	 %>

	// 指定图表的配置项和数据
	var option1 = {
		title : {
			text : '用户使用数据统计'
		},
		tooltip : {},
		legend : {
			data : [ '数量' ]
		},
		xAxis : {
			data : [ "用户注册", "孩子", "成语收藏", "书籍收藏", "加入书架" ]
		},
		yAxis : {},
		series : [ {
			name :  '数量' ,
			type : 'bar',
			itemStyle: {
                normal: {
                   
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#00D5C1'
                    }, {
                        offset: 1,
                        color: '#009688'
                    }]),
                    
                }
            },
			data : userData
		} ]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart1.setOption(option1);

	// 基于准备好的dom，初始化echarts实例
	var myChart2 = echarts.init(document.getElementById('main2'));

	// 指定图表的配置项和数据
	var option2 = {
        title : {
            text : '站点数据统计'
        },
        tooltip : {},
        legend : {
            data : [ '数量' ]
        },
        xAxis : {
            data : [ "成语类型", "成语", "书籍类型", "书籍" ]
        },
        yAxis : {},
        series : [ {
            name :  '数量' ,
            type : 'bar',
            itemStyle: {
                normal: {
                   
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#00D5C1'
                    }, {
                        offset: 1,
                        color: '#009688'
                    }]),
                    
                }
            },
            data : localData
        } ]
    };

	// 使用刚指定的配置项和数据显示图表。
	myChart2.setOption(option2);
</script>
</html>