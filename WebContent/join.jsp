<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body onload="cmbBind()">
	<h2>회원 가입</h2>
	<form id="frm" method="post" enctype="multipart/form-data">
		아이디 : <input type="text" name="val"></input><br/>
		비밀번호 : <input type="password" name="val"></input><br/>
		이름 : <input type="text" name="val"></input><br/>
		주민번호 : <input type="text" name="val"></input><br/>
		이메일 : <input type="text" name="val"></input><br/>
		<input type="hidden" id="hdnAdmin" name="val" value="false"/>
		<input type="button" value="입력 완료" onclick="btnIns_Click()"/>
		<input type="hidden" name="db" value="0"/>
		<input type="hidden" id="hdnMd" name="md" value="upd"/>
	</form>
</body>
</html>