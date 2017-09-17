<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
 <script type="text/javascript" src="common.js" ></script>
<link rel="stylesheet" type="text/css" href="documentview.css"/>
</head>
<body onload="docInit()" background="img/BG.jpg" tracingopacity="100">
<form action="" method="post" enctype="multipart/form-data" id="frm">
<br/>
<h2>경마 선수 데이터</h2>
<hr style="color: #aaccff"/>
<br/>
  <table width="584" border="0" cellpadding="0" cellspacing="3" id="tblInput">
    <tr>
      <td width="200" align="right" bgcolor="#C8E8FF">선수식별번호</td>
      <td width="182" bgcolor="#E6F4FF">
        <input type="text" id="iid" name="val"><xsl:attribute name="value"><xsl:value-of select="body/players/player/id"/></xsl:attribute></input>
      </td>
      <td  align="center" width="264" rowspan="8" valign="top"><br />
        <img id="imgPic" width="150" height="200" src="">
			<xsl:attribute name="src"><xsl:value-of select="body/players/player/picURL"/></xsl:attribute>
		</img>
        <label>
          <input type="file" name="picURL"/>
        </label></td>
    </tr>
    <tr>
      <td align="right" bgcolor="#C8E8FF"> 이름</td>
      <td bgcolor="#E6F4FF"><label>
        <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of 

select="body/players/player/name"/></xsl:attribute></input>
      </label></td>
    </tr>
    <tr>
      <td align="right" bgcolor="#C8E8FF"> 나이</td>
      <td bgcolor="#E6F4FF"><label>
        <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of 

select="body/players/player/age"/></xsl:attribute></input>
      </label></td>
    </tr>
    <input type="hidden" id="hdnGender">
		<xsl:attribute name="value"><xsl:value-of select="body/players/player/gender"/></xsl:attribute>
	</input>
    <tr>
      <td align="right" bgcolor="#C8E8FF">성별</td>
      <td bgcolor="#E6F4FF"><label>
        <select id="cmbGender" name="val">
			<option value="true">남</option>
			<option value="false">여</option>
		</select>
      </label></td>
    </tr>
    <tr>
      <td align="right" bgcolor="#C8E8FF"> 신장</td>
      <td bgcolor="#E6F4FF"><input type="text" name="val"><xsl:attribute name="value"><xsl:value-of 

select="body/players/player/height"/></xsl:attribute></input></td>
    </tr>
    <tr>
      <td align="right" bgcolor="#C8E8FF">몸무게</td>
      <td bgcolor="#E6F4FF"><input type="text" name="val"><xsl:attribute name="value"><xsl:value-of 

select="body/players/player/weight"/></xsl:attribute></input></td>
    </tr>
    <input type="hidden" name="val"><xsl:attribute name="value"><xsl:value-of 

select="body/players/player/picURL"/></xsl:attribute></input>
    <tr>
      <td align="right" bgcolor="#C8E8FF">경주파트너</td>
      <td bgcolor="#E6F4FF"><input type="text" name="val"><xsl:attribute name="value"><xsl:value-of 

select="body/players/player/hId"/></xsl:attribute></input></td>
    </tr>
    <tr>
    	<td colspan="3">
    		<img class="imgBxx">
    		<xsl:attribute name="src">C:/temp_folder/pic/<xsl:value-of select="body/players/player/id"/>_g1.png</xsl:attribute></img>
			<img class="imgBxx">
			<xsl:attribute name="src">C:/temp_folder/pic/<xsl:value-of select="body/players/player/id"/>_g2.png</xsl:attribute></img>
    	</td>
    </tr>
  </table>
  <hr style="color: #09F"/>
<input type="button" id="btnIns" value="입력 완료" onclick="btnIns_Click()"/>
<input type="button" id="btnUpd" value="수정 완료" onclick="btnUpd_Click()"/>
<input type="button" id="btnDel" value="삭제" onclick="btnDel_Click()"/>
<input type="hidden" name="db" value="1"/>
<input type="hidden" id="hdnMd" name="md" value="upd"/>
<a href="#" onclick="javascript: history.go(-1);">목록으로</a>
</form>
</body>
</html>
</xsl:template>
</xsl:stylesheet>