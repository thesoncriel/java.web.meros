<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
	</head>
	<body onload="cmbBind(); bindHorseType()">
		말 식별 번호 : <xsl:value-of select="body/horses/horse/id"/><br/>
		이름 : <xsl:value-of select="body/horses/horse/name"/><br/>
		나이 : <xsl:value-of select="body/horses/horse/age"/><br/>
		성별 : <xsl:value-of select="body/horses/horse/gender"/><br/>
		신장(m) : <xsl:value-of select="body/horses/horse/height"/><br/>
		몸무게(kg) : <xsl:value-of select="body/horses/horse/weight"/><br/>
		별명 : <xsl:value-of select="body/horses/horse/nick"/><br/>
		말 종류 : <xsl:value-of select="body/horses/horse/type"/> <br/>
		<img id="imgPic" width="150" height="200">
			<xsl:attribute name="src"><xsl:value-of select="body/horses/horse/picURL"/></xsl:attribute>
		</img><br/>
		<a href="#" onclick="history.go(-1)">목록으로</a>
			<input type="hidden" id="hdnDb" name="db" value="2"/>
			<input type="hidden" id="hdnMd" name="md" value="upd"/>
	</body>
	</html>
	</xsl:template>
</xsl:stylesheet>