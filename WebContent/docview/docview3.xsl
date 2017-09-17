<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
	<script type="text/javascript" src="common.js" />
<link rel="stylesheet" type="text/css" href="documentview.css"/>
</head>
<body background="img/BG.jpg" tracingopacity="100">
<form id="frm" method="post" enctype="multipart/form-data">
<br/>
<h2>경주 결과 데이터</h2>
<hr style="color: #aaccff"/>
<br/>
<table width="337" border="0" cellpadding="0" cellspacing="3" id="tblInput">
  <tr>
    <td width="78" align="right" bgcolor="#C8E8FF"> 아이디</td>
    <td bgcolor="#E6F4FF"><xsl:value-of select="body/results/result/id"/></td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF"> 날짜</td>
    <td bgcolor="#E6F4FF"><xsl:value-of select="body/results/result/date"/></td>
  </tr>
  <tr>
    <td  align="right" bgcolor="#C8E8FF"> 승률</td>
    <td bgcolor="#E6F4FF"><xsl:value-of select="body/results/result/betRate"/></td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF">배팅율</td>
    <td bgcolor="#E6F4FF"><xsl:value-of select="body/results/result/winRate"/></td>
  </tr>
</table>
<hr style="color: #09F"/>
<p>
<a href="#" onclick="javascript: history.go(-1);">목록으로</a>
</p>
</form> 
</body>
</html>
	</xsl:template>
</xsl:stylesheet>