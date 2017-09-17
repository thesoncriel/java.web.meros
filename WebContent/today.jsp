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

try{
	DBManager dbm = new DBManager();
	LoginManager logm = LoginManager.hasLogin(request, response, false);
	if(logm == null) throw new Exception();
	if(logm.isAdmin() == false && dbm.getDb() == 0) throw new AdminOnlyException();
	
	 
	StringBuffer sb = new StringBuffer();
	String sql = "SELECT h.id AS id, h.name as name, h.nick AS nick, r.winRate AS winRate, r.betRate AS betRate, h.picURL AS pic FROM tplayer AS p, thorse AS h, tresult AS r WHERE p.id = r.id and p.hId = h.id and fdate = (select max(fdate) as mdate from tresult) ORDER BY winRate DESC;";
	sb.append("<?xml-stylesheet type=\"text/xsl\" href=\"today.xsl\" ?>");
	sb.append("<today>");
	List<IMember> list = dbm.search(sql, ClassType.TODAY_HORSE);
	int index = 0;
	for(IMember imbr : list){
		String[] str = imbr.toStrings();
		sb.append("<data>");
		sb.append("<id>" + str[0] + "</id>");
		sb.append("<name>" + str[1] + "</name>");
		sb.append("<nick>" + str[2] + "</nick>");
		sb.append("<winRate>" + str[3] + "</winRate>");
		sb.append("<betRate>" + str[4] + "</betRate>");
		sb.append("<pic>" + str[5] + "</pic>");
		sb.append("</data>");
		index++;
		if(index > 3) break;
	}
	sb.append("</today>");
	out.println(sb.toString());
}
catch(AdminOnlyException ex){
	response.sendRedirect("admin_only.htm");
}
catch(Exception ex){
	out.println("<error>");
	out.println("<subject>그냥은 못쓴데이 -_-);;</subject>");
	out.println("<comment>아마 매개 변수가 잘못 되었을끼야. 아니면 로긴 안했던가..</comment>");
	out.println("<detail class=\"" + ex.toString() + "\">");
	out.println("</detail>");
	out.println("</error>");
	ex.printStackTrace();
}
%>