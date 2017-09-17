<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
 <script type="text/javascript" src="common.js" ></script>
<link rel="stylesheet" type="text/css" href="documentview.css"/>
</head>
<body onload="docInit(); bindHorseType()" background="img/BG.jpg" tracingopacity="100">
<form id="frm" method="post" action="search.jsp" enctype="multipart/form-data">
<br/>
<h2>경주말 데이터</h2>
<hr style="color: #aaccff"/>
<br/>
<table width="584" border="0" cellpadding="0" cellspacing="3" id="tblInput">
  <tr>
    <td width="80" align="right" bgcolor="#C8E8FF"> 아이디</td>
    <td bgcolor="#E6F4FF">
        <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/id"/></xsl:attribute></input>
    </td>
     <td  align="center" width="293" rowspan="8" valign="top"><br />
		<img id="imgPic" width="150" height="200">
			<xsl:attribute name="src"><xsl:value-of select="body/horses/horse/picURL"/></xsl:attribute>
		</img><br/>
		<input type="file" name="picURL"/>
     </td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF"> 이름</td>
    <td bgcolor="#E6F4FF">
      <label>
        <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/name"/></xsl:attribute></input>
      </label>
    </td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF"> 나이</td>
    <td bgcolor="#E6F4FF">
      <label>
        <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/age"/></xsl:attribute></input>
      </label>
    </td>
  </tr>
<input type="hidden" id="hdnGender">
	<xsl:attribute name="value"><xsl:value-of select="body/horses/horse/gender"/></xsl:attribute>
</input>
  <tr>
    <td align="right" bgcolor="#C8E8FF">성별</td>
    <td bgcolor="#E6F4FF">
      <label>
        <select id="cmbGender" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/gender"/></xsl:attribute>
			<option value="true">수컷</option>
			<option value="false">암컷</option>
		</select>
      </label>
    </td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF">신장</td>
    <td bgcolor="#E6F4FF"><input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/height"/></xsl:attribute></input></td>
  </tr>
  <tr>
    <td align="right" bgcolor="#C8E8FF">몸무게</td>
    <td nowrap="nowrap" bgcolor="#E6F4FF"><input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/weight"/></xsl:attribute></input></td>
  </tr>
<input type="hidden" name="val">
	<xsl:attribute name="value"><xsl:value-of select="body/horses/horse/picURL"/></xsl:attribute>
</input>
  <tr>
    <td align="right" bgcolor="#C8E8FF">별명</td>
    <td nowrap="nowrap" bgcolor="#E6F4FF">
      <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/nick"/></xsl:attribute></input>
    </td>
  </tr>
<input type="hidden" id="hdnType">
	<xsl:attribute name="value"><xsl:value-of select="body/horses/horse/type"/></xsl:attribute>
</input>
  <tr>
    <td align="right" bgcolor="#C8E8FF">품종</td>
    <td nowrap="nowrap" bgcolor="#E6F4FF">
      <label>
        <select id="cmbType" name="val">
			<option value="AmericanQuarter">AmericanQuarter</option>
			<option value="AmericanSaddle">AmericanSaddle</option>
			<option value="AngloArab">AngloArab</option>
			<option value="Appaloosa">Appaloosa</option>
			<option value="Arabian">Arabian</option>
			<option value="Asian">Asian</option>
			<option value="ClevelandBay">ClevelandBay</option>
			<option value="EnglishHackney">EnglishHackney</option>
			<option value="GermanHolstein">GermanHolstein</option>
			<option value="Hanoverian">Hanoverian</option>
			<option value="Lipizzaner">Lipizzaner</option>
			<option value="MissouriFoxTrotting">MissouriFoxTrotting</option>
			<option value="Morgan">Morgan</option>
			<option value="Noriker">Noriker</option>
			<option value="Percheron">Percheron</option>
			<option value="Shire">Shire</option>
			<option value="Standardbred">Standardbred</option>
			<option value="Suffolk">Suffolk</option>
			<option value="TennesseeWalking">TennesseeWalking</option>
			<option value="Thoroughbred">Thoroughbred</option>
		</select>
      </label>
    </td>
  </tr>
  
</table>
   <hr style="color: #09F"/>
<p>
<input type="button" id="btnIns" value="입력 완료" onclick="btnIns_Click()"/>
<input type="button" id="btnUpd" value="수정 완료" onclick="btnUpd_Click()"/>
<input type="button" id="btnDel" value="삭제" onclick="btnDel_Click()"/>
<input type="hidden" name="db" value="2"/>
<input type="hidden" id="hdnMd" name="md" value="upd"/>
<a href="#" onclick="javascript: history.go(-1);">목록으로</a>
</p>
<p></p>
</form>
</body>
</html>
	</xsl:template>
</xsl:stylesheet>