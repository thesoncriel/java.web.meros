<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="
hr.manager.*,
hr.member.*,
hr.web.*
" %>

<% request.setCharacterEncoding("UTF-8");%>
<%
LoginManager logm = LoginManager.hasLogin(request, response, true);

if(logm == null){
	out.println("하하?");
	response.sendRedirect("index.htm");
}
try{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript" src="common.js"></script>
<style type="text/css">
	body{
	margin: 0px;
	}
	span.log, span.menu{width:190px;}
	span.log, span.top{height:120px;}
	span.top, span.main{width:1130px;}
	span.menu, span.main, iframe#content{height:650px;}
	div.user{height: 120px;}
	
	span.log, span.top, span.menu, span.main{
	float:left;
	overflow:scoll;
	}
	
	span.log {
	background-color:transparent;
	padding:10px 10px 10px 30px;
	}
	span.top {
	background-image: url('img/headbg.jpg');
	background-position:0px -20px;
	}
	span.menu{ 

	}
	div.user{
	position: relative;
	top:0px;
	left:0px;
	background-color:#f8f8f8;
	}
	div.me{
	text-align: center;
	}
	div.me a img{
	margin-top:2px;
	margin-bottom:2px;
	border-width:0px;
	}
	a:link img, a:visited img{
	border-width:0px;
	width: 120px;
	height: 30px;
	}
	
	span.top a:link, span.top a:visited{
	border-style: dashed;
	border-color: #f8f8f8;
	border-width: 2px;
	}
	iframe#content{
	text-align: center;
	width: 900px;
	background-color:#ff88bb;
	}
</style>
</head>
<body>

<span class="top">
<span class="log">
<div style="font-family: arial black; font-size:20px; font-weight:bold">[Web]</div>
<img src="img/ssss.png">
</span>
<br/>
<div align="right" style="font-size:13px; color:#ffffff">copyleftⓒ 2010 Team Blink - all right reserved</div>
<br/><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="today.jsp" target="content"><img src="img/home.jpg" alt="홈" width="100" height="30" align="bottom" style="margin-right: 15px"/></a>
<a href="search.jsp?view=0" target="content"><img src="img/table.jpg" alt="표보기" width="100" height="30" style="margin-right: 15px"/></a>
<a href="search.jsp?view=1" target="content"><img src="img/list.jpg" alt="문서보기" width="100" height="30" style="margin-right: 15px"/></a>
</span>
<span class="main">
	<span class="menu">
	<div class="user">
<hr  color="33cccc" size="3" align="left" width="180">
<font face="휴먼모음T">
<p>
<!-- user.name 부분--> 
<%=logm.getName() %>님 환영합니다.
</p>
<input type="button" id="btnMod" value="회원 정보 수정" onclick="btnMod_Click()"/>
<a href="logout.jsp">로그아웃</a>
	</div>
	<div>
		<div class="me">
<% if(logm.isAdmin()){%>
		<img src="img/download.jpg" alt="다운로드"/>
		<a href="download.jsp?ft=xls"><img src="img/excel.jpg" alt="excel"/></a>
		<a href="download.jsp?ft=xml"><img src="img/xml.jpg" alt="xml"/></a>
		<a href="upload.jsp" target="content"><img src="img/upload.jpg" alt="업로드"/></a>
		
		<hr style="color:#33cccc"/>

		<a href="search.jsp?db=0&att=0&opr=6&val=&pg=1" target="content"><img src="img/user.jpg" alt="사용자"/></a>
<%} %>
		<a href="search.jsp?db=1&att=0&opr=6&val=&pg=1" target="content"><img src="img/player.jpg" alt="경마선수"/></a>
		<a href="search.jsp?db=2&att=0&opr=6&val=&pg=1" target="content"><img src="img/racehorse.jpg" alt="경주마"/></a>
		<a href="search.jsp?db=3&att=0&opr=6&val=&pg=1" target="content"><img src="img/result.jpg" alt="경주결과"/></a>
		</div>
	</div>
	</span>
	<div>
	<iframe id="content" name="content" src="today.jsp"></iframe>
	</div>
</span>

</body>
</html>
<%}catch(Exception ex){}%>