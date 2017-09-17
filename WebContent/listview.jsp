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
LoginManager logm = LoginManager.hasLogin(request, response, false);
ClassType db = logm.getUserSelection();
%>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
<!-- <xsl:value-of select="header/author"/> -->
<html xsl:version="1.0">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Meroz1.0 ListeView</title>
<script type="text/javascript" src="listview.js"></script>
<style type="text/css">

body{
	font-family: 맑은 고딕;
	font-size: 13px;
}
 #left1
    {
    	margin-left:70px;
    }
    .listview {
	width:598px;
	text-align:center;
	font-weight: bold;
	text-align: left;
	}
.chkbox{
	background-color: #B3D9FF;
}
.listdata{
	padding:2px 0px 2px 0px;
	text-align: left;
	vertical-align: top;
}
.record{
	padding:3px;
	text-align:right;
	font-weight: bold;
	background-color:#D2E8FF;
	text-valign:middle;
	width: 150px;
	font-style: italic;
	font-size: 13px;
}
.recorddata{
	padding:3px;
	color:#000;
	text-align:left;
	background-color:#E8F3FF;
	width: 250px;
	font-size: 13px;
}
    .image
    {
        padding: 2px 7px 2px 7px;
        text-align: center;
		vertical-align: middle;
    }
   
</style>

</head>

<body background="img/BG.jpg" onload="chkList_init(<%=PageContentsMaker.getCheckBox_InitValue(db) %>)">
<form id="frm" method="post" action="search.jsp">
<p>
  <input type="button" id="btnRowAll" value="전체선택" onclick="chkRowAll(true)"/>
  <input type="button" id="btnRowDe" value="전체선택해제" onclick="chkRowAll(false)"/>
</p>

<xsl:for-each select="body/<%=PageContentsMaker.getXSLForEachElement(db) %>">
<table class="listview" border="0" cellpadding="2" cellspacing="3">
<tr>
    <td align="center" valign="middle" class="chkbox">
		<input type="checkbox" name="chkRow" onclick="chkRow_Change(this)"/>
	</td>
    
	<td class="image">
		<img src="" width="120" height="150">
			<xsl:attribute name="src"><xsl:value-of select="picURL"/></xsl:attribute>
		</img>
	</td>

    <td class="listdata">
         <table border="0">
            <%=PageContentsMaker.getXSLValueOf_ListView(db) %>
        </table>
   </td>
</tr>
</table>
<hr width="650" align="left" style="color:#6699CC"/>
</xsl:for-each>

		<div id="left">
		<%=PageContentsMaker.getFooterCheckBox(db) %>
	    </div>
<div id="left1">
<p> 
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
<input type="text" name="val"/>
<input type="submit" value="검색" />

<input type="hidden" id="hdnDb" name="db" value="<%=db.value() %>" />
<input type="hidden" id="hdnAtt" name="att" value="0"/>
<input type="hidden" id="hdnOpr" name="opr" value="2"/>
<input type="hidden" id="hdnPg" name="pg" value="1"/>
<input type="hidden" id="hdnMd" name="md" value="0"/>
</p>
</div>

</form>

</body>

</html>

	</xsl:template>
</xsl:stylesheet>