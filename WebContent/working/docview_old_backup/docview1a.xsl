<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
	<script type="text/javascript" src="common.js" />
	</head>
	<body onload="cmbBind()">
		<form id="frm" method="post" action="search.jsp" enctype="multipart/form-data">
			선수 식별 번호 : <input type="text" id="iid" name="val"><xsl:attribute name="value"><xsl:value-of select="body/players/player/id"/></xsl:attribute></input><br/>
			이름 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/players/player/name"/></xsl:attribute></input><br/>
			나이 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/players/player/age"/></xsl:attribute></input><br/>
			<input type="hidden" id="hdnGender">
				<xsl:attribute name="value"><xsl:value-of select="body/players/player/gender"/></xsl:attribute>
			</input>
			성별 : <select id="cmbGender" name="val">
				<option value="true">남</option>
				<option value="false">여</option>
			</select><br/>
			신장(m) : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/players/player/height"/></xsl:attribute></input><br/>
			몸무게(kg) : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/players/player/weight"/></xsl:attribute></input><br/>
			<input type="hidden" name="val"><xsl:attribute name="value"><xsl:value-of select="body/players/player/picURL"/></xsl:attribute></input>
			경주 파트너 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/players/player/hId"/></xsl:attribute></input><br/>
			
			<img id="imgPic" width="150" height="200" src="">
				<xsl:attribute name="src"><xsl:value-of select="body/players/player/picURL"/></xsl:attribute>
			</img><br/>
			<input type="file" name="picURL"/><br/>
			<input type="button" value="입력 완료" onclick="btnIns_Click()"/>
			<input type="button" value="수정 완료" onclick="btnUpd_Click()"/>
			<input type="button" value="삭제" onclick="btnDel_Click()"/><br/>
			<img><xsl:attribute name="src">C:/temp_folder/pic/<xsl:value-of select="body/players/player/id"/>_g1.png</xsl:attribute></img>
		<img><xsl:attribute name="src">C:/temp_folder/pic/<xsl:value-of select="body/players/player/id"/>_g2.png</xsl:attribute></img>
			<br/><a href="#" onclick="history.go(-1)">목록으로</a>
			<input type="hidden" id="hdnDb" name="db" value="1"/>
			<input type="hidden" id="hdnMd" name="md" value="upd"/>
		</form>
	</body>
	</html>
	</xsl:template>
</xsl:stylesheet>