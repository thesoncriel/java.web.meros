<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="
hr.member.*,
hr.manager.*
"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" scope="page" class="hr.member.User"/>
<jsp:setProperty name="user" property="*"/>
<%
LoginManager logm = new LoginManager();
logm.delete(user);
%>
<html>
<body>
<%
logm.logout(request, response, true);
%>
</body>
</html>