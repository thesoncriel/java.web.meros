<?xml version="1.0" encoding="UTF-8"?>

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
LoginManager logm = LoginManager.hasLogin(request, response, false);
ClassType db = logm.getUserSelection();
%>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/horseRace">
<!-- <xsl:value-of select="header/author"/> -->
<html xsl:version="1.0">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Web Meroz 1.0 Table view</title>
<script type="text/javascript" src="common.js"></script>
<style type="text/css">

.customers {
	text-align=center;
	align=center;
	background-color:#FED8B4;
 	font-weight: bold;	
	text-align: center;	
	margin-top:100px; margin-bottom:100px;
}
.mod {
	text-align=center;
	align=center;
	background-color:#DEE2FE;
 	font-weight: normal;	
	text-align: center;	
	
}
.mod1 {
	color:#000;
	text-align:center;
	align=center;
	background-color:#EFF0FE;
 	font-weight: normal;	
	text-align: center;	
}
#tableback {

	align=center
}
#tableback {
	text-align: center;
}



</style>

</head>
<body background="img/BG.jpg">

	<form id="frm" method="post" action="search.jsp">




<table id="tableback" border="0" cellpadding="2" cellspacing="3">
    <tr class="customers">
		<th><input type="checkbox" id="chkAll" onclick="chkRowAll(this)"/></th>
		<%=PageContentsMaker.getColumns(db)%>
    </tr>
    <xsl:for-each select="body/<%=PageContentsMaker.getXSLForEachElement(db) %>">
		<xsl:if test="position() mod 2 = 1">
		<tr class="mod">
			<td>
			<input type="checkbox" name="chkRow" onchange="chkRow_Change(this)"/>
			</td>
			<%=PageContentsMaker.getXSLValueOf(db)%>
		</tr>
		</xsl:if>
		<xsl:if test="position() mod 2 = 0">
		<tr class="mod1">
			<td>
			<input type="checkbox" name="chkRow" onchange="chkRow_Change(this)"/>
			</td>
			<%=PageContentsMaker.getXSLValueOf(db)%>
		</tr>
		</xsl:if>
	</xsl:for-each>
	<tr>
		<td >
		</td>
	</tr>
</table>

<%if(logm.isAdmin()){%>
<input type="button" value="삭제" onclick="btnDel_Click()" />
<input type="button" value="새로 입력" onclick="btnNew_Click(<%=db.value() %>)" /><br/>
<%}%>
<select id="cmbAtt" onclick="javascript: cmbAtt_Change('Att');">
	<%=PageContentsMaker.getOptions(db)%>
</select>
<select id="cmbOpr" onclick="javascript: cmbAtt_Change('Opr');">
	<option>&gt;</option>
	<option>&gt;=</option>
	<option selected="selected">=</option>
	<option>&lt;=</option>
	<option>&lt;</option>
	<option>not</option>
	<option>like</option>
</select>
<input type="text" name="val" id="txtVAl" onkeyup="koko(event)"/>
<input type="submit" value="검색" />

<input type="hidden" id="hdnDb" name="db" value="<%=db.value() %>" />
<input type="hidden" id="hdnAtt" name="att" value="0"/>
<input type="hidden" id="hdnOpr" name="opr" value="2"/>
<input type="hidden" id="hdnPg" name="pg" value="1"/>
<input type="hidden" id="hdnMd" name="md" value="0"/>
</form>

</body>
</html>
	</xsl:template>
	
</xsl:stylesheet>