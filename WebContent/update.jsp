<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page
import="
java.util.*,
hr.manager.*,
hr.member.*,
hr.web.*,
com.oreilly.servlet.MultipartRequest,
com.oreilly.servlet.multipart.DefaultFileRenamePolicy,
java.util.*,
java.io.*
"%>
<%
request.setCharacterEncoding("UTF-8");
String path = AbstractFileManager.DEFAULT_PATH + "/" + AbstractFileManager.SUB_FOLDER_NAME;
File f = new File(path);
if(f.exists() == false){
	f.mkdirs();
}
MultipartRequest multi = new MultipartRequest(request, path, AbstractFileManager.UPLOAD_LIMIT, "utf-8");

try{
	
	LoginManager logm = LoginManager.hasLogin(request, response, false);
	if(logm == null) throw new Exception();
	
	ClassType type = ClassType.parse(Integer.parseInt(multi.getParameter("db")));
	IMember imbr = HorseRace.createInstance(type);
	String[] str = multi.getParameterValues("val");
	//Enumeration files = multi.getFileNames();
	String picURL = multi.getFilesystemName("picURL");
	String md = multi.getParameter("md");
	
	if(picURL != null){
		str[6] = "file:///" + path + "/" + picURL;
	}
	imbr.setData(str);
	
	DBManager dbm = new DBManager();
	int result = 0;
	result = dbm.update(logm.getSelectedData(), imbr);
	if(md.equals("mod")){
		String id = str[0];
		String pw = str[1];
		response.sendRedirect("login.jsp?id=" + id + "&pw=" + pw);
	}
	else if(result > 0 && logm.isAdmin()){
		response.sendRedirect("search.jsp?db=" + type.value() + "&att=0&opr=6&val=&pg=1");
	}
	else throw new Exception("뭥미??-_-;;");
}
catch(Exception ex){
	out.println("<error>");
	out.println("<subject>그냥은 못쓴데이 -_-);;</subject>");
	out.println("<comment>아마 매개 변수가 잘못 되었을끼야. 아니면 로긴 안했던가..</comment>");
	out.println("<detail class=\"" + ex.toString() + "\">");
	out.println("<db>" + multi.getParameter("db") + "</db>");
	out.println("<val>" + multi.getParameter("val") + "</val>");
	out.println("<md>" + multi.getParameter("md") + "</md>");
	out.println("</detail>");
	out.println("</error>");
	ex.printStackTrace();
}
%>
