<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="
hr.manager.*,
hr.member.*,
hr.web.*,
java.util.*
"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
String[] row;
try{
	LoginManager logm = LoginManager.hasLogin(request, response, false);
	if(logm == null) throw new Exception();

	row = request.getParameterValues("chkRow");
	String prePage = request.getHeader("Referer");
	//request.
	//out.println("<오류 예상/>");
	ClassType type = null;
	//out.println("<오류 통과?/>");
	List<IMember> list = new ArrayList<IMember>();
	String url = null;
	int result = 0;
	if(row != null){
		type = logm.getUserSelection();
		url = "search.jsp?db=" + type.value() + "&att=0&opr=6&val=&pg=1";
		for(String num : row){
			List<IMember> r = logm.getUserResult();
			list.add(r.get(Integer.parseInt(num)));
		}
	}
	else if(logm.isAdmin() == false){
		IMember delmbr = logm.getSelectedData();
		type = delmbr.getClassType();
		url = "index.htm";
		list.add(delmbr);
		LoginManager.logout(request, response, false);
	}
	else{
		type = logm.getUserSelection();
		url = "search.jsp?db=" + type.value() + "&att=0&opr=6&val=&pg=1";
		list.add(logm.getSelectedData());
	}
	DBManager dbm = new DBManager();
	result = dbm.delete(list);
	
	if(result > 0) response.sendRedirect(url);
}
catch(Exception ex){
	out.println("<error>");
	out.println("<subject>그냥은 못쓴데이 -_-);;</subject>");
	out.println("<comment>아마 매개 변수가 잘못 되었을끼야. 아니면 로긴 안했던가..</comment>");
	out.println("<detail class=\"" + ex.toString() + "\">");
	out.println("<db>" + request.getParameter("db") + "</db>");
	out.println("<val>" + request.getParameter("val") + "</val>");
	out.println("<chkRow>" + request.getParameter("chkRow") + "</chkRow>");
	out.println("<md>" + request.getParameter("md") + "</md>");
	out.println("<pre><![CDATA[" + request.getHeader("Referer") + "]]></pre>");
	out.println("</detail>");
	out.println("</error>");
	ex.printStackTrace();
}
%>