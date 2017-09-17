<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">

body{padding:30px;}

.left, .right{
	border-color: #aaccff;
	border-style: dashed;
	border-width: 3px;
	width: 25px;
}
.left{
	border-right-width: 0px;
}
.right{
	border-left-width: 0px;
}
table.cd tr td{
	padding: 6px;
}
</style>

</head> 
<body background="img/BG.jpg"> 
<%
request.setCharacterEncoding("UTF-8");
MultipartRequest multi = null;

try{
	LoginManager logm = LoginManager.hasLogin(request, response, false);
	if(logm == null) throw new Exception();
	if(logm.isAdmin() == false) throw new AdminOnlyException();
	
	String path = AbstractFileManager.DEFAULT_PATH + "/temp";
	String pic = AbstractFileManager.DEFAULT_PATH + "/" + AbstractFileManager.SUB_FOLDER_NAME;
	File f = new File(path);
	if(f.exists() == false){
		f.mkdirs();
	}
	f = new File(pic);
	if(f.exists() == false){
		f.mkdirs();
	}
	
	multi = new MultipartRequest(request, path, AbstractFileManager.UPLOAD_LIMIT, "utf-8");
	String md = multi.getParameter("md");
	if(md == null){
		throw new IOException();
	}
	else{
		String fileName = path + "/" + multi.getFilesystemName("file");
		
		if(fileName == null){
			response.sendRedirect("upload.jsp");
		}
		else{
			File file = new File(fileName);
			if(file.exists() && file.isFile()){
				AbstractFileManager fm = null;
				if(file.getAbsolutePath().contains(".xml")){
					fm = new XMLManager();
				}
				else if(file.getAbsolutePath().contains(".xls")){
					fm = new ExcelManager();
				}
				else{
					throw new Exception(fileName + "은 XML 혹은 Excel 파일이 아닙니다 ㅡ.ㅡ;;");
				}
				fm.read(file);
				
				DBManager dbm = new DBManager();
				dbm.insert((List<IMember>)fm.getMembers(ClassType.PLAYER));
				dbm.insert((List<IMember>)fm.getMembers(ClassType.HORSE));
				dbm.insert((List<IMember>)fm.getMembers(ClassType.RESULT));
				
				response.sendRedirect("search.jsp?db=" + logm.getUserSelection().value() + "&att=0&opr=6&val=&pg=1");
			}
			else{
				throw new Exception(fileName + "파일이 존재하지 않거나 파일이 아닙니다 'ㅅ')/");
			}
		}
	}
}
catch(IOException ex){
	%>
	<h2 class="h2">▶ 파일로 입력 ◀</h2> 
	※ XML 혹은 XLS(엑셀) 파일만을 지원 합니다 :)
	<form id="frmFile" method="post" enctype="multipart/form-data" action="upload.jsp">
	  <table class="cd" border="0" cellpadding="2" cellspacing="3">
	    <tr>
			<td rowspan="2" class="left">&nbsp;&nbsp;</td>
			<td bgcolor="#C8E8FF"><input type="file" name="file" /></input></td>
			<td rowspan="2" class="right">&nbsp;&nbsp;</td>
	    </tr>
	    <tr>
	      <td bgcolor="#E8F3FF" align="center"><input type="submit" value="보내기"></input> </td>
	    </tr>
	  </table>
	  <input type="hidden" name="md" value="file"/>
	</form> 
	<%
}
catch(AdminOnlyException ex){
	response.sendRedirect("admin_only.htm");
}
catch(Exception ex){
	out.println(ex.toString() + "<br/>");
	out.println("<a href=\"#\" onclick=\"history.go(-1);\">뒤로 가기</a>");
}
%>
</body>
</html>