<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
 <script type="text/javascript" src="common.js" ></script>
<link rel="stylesheet" type="text/css" href="documentview.css"/>
</head>
<body background="img/BG.jpg" tracingopacity="100">
<form id="frm" method="post" action="search.jsp" enctype="multipart/form-data">
<br/>
<h2>경주말 데이터</h2>
<hr style="color: #aaccff"/>
<br/>
<table width="584" border="0" cellpadding="0" cellspacing="3" id="tblInput">
  <tr>
    <td width="80" align="right" bgcolor="#C8E8FF"> 아이디</td>
    <td bgcolor="#E6F4FF"><xsl:value-of select="body/horses/horse/id"/></td>
     <td  align="center" width="293" rowspan="8" valign="top"><br />
		<img id="imgPic" width="150" height="200">
			<xsl:attribute name="src"><xsl:value-of select="body/horses/horse/picURL"/></xsl:attribute>
		</img><br/>
     </td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF"> 이름</td>
    <td bgcolor="#E6F4FF"><xsl:value-of select="body/horses/horse/name"/></td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF"> 나이</td>
    <td bgcolor="#E6F4FF"><xsl:value-of select="body/horses/horse/age"/></td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF">성별</td>
    <td bgcolor="#E6F4FF"><xsl:value-of select="body/horses/horse/gender"/></td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF">신장</td>
    <td bgcolor="#E6F4FF"><xsl:value-of select="body/horses/horse/height"/></td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF">몸무게</td>
    <td nowrap="nowrap" bgcolor="#E6F4FF"><xsl:value-of select="body/horses/horse/weight"/></td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF">별명</td>
    <td nowrap="nowrap" bgcolor="#E6F4FF"><xsl:value-of select="body/horses/horse/nick"/></td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF">품종</td>
    <td nowrap="nowrap" bgcolor="#E6F4FF"><xsl:value-of select="body/horses/horse/type"/></td>
  </tr>
</table>
   <hr style="color: #09F"/>
<p>
<a href="#" onclick="javascript: history.go(-1);">목록으로</a>
</p>
<p></p>
</form>
</body>
</html>
	</xsl:template>
</xsl:stylesheet>