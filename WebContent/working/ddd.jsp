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

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript" src="common.js" />
<style type="text/css">
	body{
	margin: 0px;
	}
	span.log, span.menu{width:230px;}
	span.log, span.top{height:170px;}
	span.top, span.main{width:950px;}
	span.menu, span.main{height:500px;}
	
	span.log, span.top, span.menu, span.main{
	float:left;
	overflow:scoll;
	}
	
	span.log {
	background-color:#ff8800;
	}
	span.top {
	background-color:#0088ff;
	}
	span.menu{ 
	background-color:#88ff44;
	}
	span.main{
	background-color:#ff88bb;
	}
</style>
</head>
<body>

<span class="top">
<span class="log">
<br/><br/>
<%=logm.getName() %>님~ 어서오세요 ^^<br/><br/>
<input type="button" id="btnMod" value="회원 정보 수정" onclick="btnMod_Click()"/>
<a href="logout.jsp">로그아웃</a>
</span>
상단 메뉴
</span>
<span class="main">
	<span class="menu">
		<a href="search.jsp?db=0&att=0&opr=6&val=&pg=1">사용자</a><br/>
		<a href="search.jsp?db=1&att=0&opr=6&val=&pg=1">경마선수</a><br/>
		<a href="search.jsp?db=2&att=0&opr=6&val=&pg=1">경주말</a><br/>
		<a href="search.jsp?db=3&att=0&opr=6&val=&pg=1">경주결과</a><br/>
	</span>
	본문
</span>

</body>
</html>