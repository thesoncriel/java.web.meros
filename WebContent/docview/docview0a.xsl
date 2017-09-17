<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
 <script type="text/javascript" src="common.js"></script>
<link rel="stylesheet" type="text/css" href="documentview.css"/>
</head>
<body onload="docInit()" background="img/BG.jpg" tracingopacity="100">

<form method="post" enctype="multipart/form-data" id="frm">
<br/>
<h2>사용자 데이터</h2>
<hr style="color: #aaccff"/>
<br/>
<table width="350" border="0" cellpadding="0" cellspacing="3" id="tblInput">
  <tr>
    <td width="" align="right" bgcolor="#C8E8FF">아이디</td>
    <td bgcolor="#E6F4FF">
      <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/users/user/id"/></xsl:attribute></input>
    </td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF"> 비밀번호</td>
    <td bgcolor="#E6F4FF">
      <label>
        <input type="password" name="val"><xsl:attribute name="value"><xsl:value-of select="body/users/user/pw"/></xsl:attribute></input>
      </label>
    </td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF"> 이름</td>
    <td bgcolor="#E6F4FF">
      <label>
        <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/users/user/name"/></xsl:attribute></input>
      </label>
    </td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF">주민번호</td>
    <td bgcolor="#E6F4FF">
      <label>
        <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/users/user/jumin"/></xsl:attribute></input>
      </label>
    </td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF"> 이메일</td>
    <td bgcolor="#E6F4FF">
      <label>
        <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/users/user/email"/></xsl:attribute></input>
        </label>
    </td>
  </tr>
<input type="hidden" id="hdnAdmin">
	<xsl:attribute name="value"><xsl:value-of select="body/users/user/admin"/></xsl:attribute>
</input>
  <tr>
    <td align="right" bgcolor="#C8E8FF">관리자여부</td>
    <td bgcolor="#E6F4FF">
		<select id="cmbAdmin" name="val">
			<option value="true" label="예"/>
			<option value="false" label="아니오" selected="selected"/>
		</select>
    </td>
  </tr>
</table>
<hr style="color: #09F"/>
<input type="button" id="btnIns" value="입력 완료" onclick="btnIns_Click()"/>
<input type="button" id="btnUpd" value="수정 완료" onclick="btnUpd_Click()"/>
<input type="button" id="btnDel" value="삭제" onclick="btnDel_Click()"/>
<input type="hidden" name="db" value="0"/>
<input type="hidden" id="hdnMd" name="md" value="upd"/>
<a href="#" onclick="javascript: history.go(-1);">목록으로</a>
</form>
</body>
</html>
</xsl:template>
</xsl:stylesheet>