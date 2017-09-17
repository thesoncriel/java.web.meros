<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="
hr.manager.*,
hr.member.*,
hr.web.*,
java.util.*,
java.io.*
"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dbm" scope="page" class="hr.manager.DBManager"/>
<jsp:setProperty name="dbm" property="db"/>
<jsp:setProperty name="dbm" property="att"/>
<jsp:setProperty name="dbm" property="opr"/>
<jsp:setProperty name="dbm" property="val"/>
<jsp:setProperty name="dbm" property="pg"/>
<%
try{
	LoginManager logm = LoginManager.hasLogin(request, response, false);
	if(logm == null) throw new Exception();
	if(logm.isAdmin() == false && dbm.getDb() == 0) throw new AdminOnlyException();
	
	//logm.setUserSelection(dbm.getDBNum());
	String view = request.getParameter("view");
	XMLManager xmlm = new XMLManager();
	
	List<IMember> list = null;
	if(view != null){
		if(view.equals("0")){
			logm.setViewSelection("tableview.jsp");
		}
		else{
			logm.setViewSelection("listview.jsp");
		}
		list = logm.getUserResult();
		if(list == null){
			list = dbm.search();
		}
	}
	else{
		list = dbm.search();
		logm.setUserResult(list);
	
		int db = Integer.parseInt(request.getParameter("db"));
		logm.setUserSelection(ClassType.parse(db));
	}
	logm.setSelectedData(null);
	xmlm.setData(list);
	xmlm.setXslPath(logm.getViewSelection());
	out.println(xmlm.getXML());
}
catch(AdminOnlyException ex){
	response.sendRedirect("admin_only.htm");
}
catch(Exception ex){
	out.println("<error>");
	out.println("<subject>그냥은 못쓴데이 -_-);;</subject>");
	out.println("<comment>아마 매개 변수가 잘못 되었을끼야. 아니면 로긴 안했던가..</comment>");
	out.println("<detail class=\"" + ex.toString() + "\">");
	out.println("<db>" + request.getParameter("db") + "</db>");
	out.println("<val>" + request.getParameter("val") + "</val>");
	out.println("<pg>" + request.getParameter("pg") + "</pg>");
	out.println("<att>" + request.getParameter("att") + "</att>");
	out.println("<opr>" + request.getParameter("opr") + "</opr>");
	out.println("</detail>");
	out.println("</error>");
	ex.printStackTrace();
}
%>