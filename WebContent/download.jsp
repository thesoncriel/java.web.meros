<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="
hr.member.*,
hr.manager.*,
hr.web.*
"%>
<%
request.setCharacterEncoding("UTF-8"); 
response.setCharacterEncoding("UTF-8");
try{
LoginManager logm = LoginManager.hasLogin(request, response, false);
if(logm.isAdmin() == false) throw new AdminOnlyException();

String fileType = request.getParameter("ft");
AbstractFileManager fm = null;
if(fileType.equals("xml")){
	fm = new XMLManager();
	response.setContentType("application/x-msdownload;");
}
else if(fileType.equals("xls")){
	fm = new ExcelManager();
	response.setContentType("Content-type: application; charset=utf-8");
}
else{
	
}

DBManager dbm = new DBManager();
dbm.setViewMax(100);
String filePath = "/temp/" + logm.getUser().getId() + "." + fileType;
for(int i = 1; i <= 3; i++){
	dbm.setDb(i);
	fm.setData(dbm.search());
}
fm.write(AbstractFileManager.DEFAULT_PATH + filePath);

response.sendRedirect(AbstractFileManager.WEB_PATH + filePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>

</body>
</html>
<%
}catch(AdminOnlyException ex){
	response.sendRedirect("admin_only.htm");
}catch(Exception ex){ex.printStackTrace();}
%>