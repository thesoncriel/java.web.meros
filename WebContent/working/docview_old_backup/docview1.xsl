<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
	</head>
	<body onload="cmbBind()">
		선수 식별 번호 : <xsl:value-of select="body/players/player/id"/><br/>
		이름 : <xsl:value-of select="body/players/player/name"/><br/>
		나이 : <xsl:value-of select="body/players/player/age"/><br/>
		성별 : <xsl:value-of select="body/players/player/gender"/><br/>
		신장(m) : <xsl:value-of select="body/players/player/height"/><br/>
		몸무게(kg) : <xsl:value-of select="body/players/player/weight"/><br/>
		
		경주 파트너 : <xsl:value-of select="body/players/player/hId"/><br/>
		
		<img id="imgPic" width="150" height="200">
			<xsl:attribute name="src"><xsl:value-of select="body/players/player/picURL"/></xsl:attribute>
		</img><br/>
		<img><xsl:attribute name="src">C:/temp_folder/pic/<xsl:value-of select="body/players/player/id"/>_g1.png</xsl:attribute></img>
		<img><xsl:attribute name="src">C:/temp_folder/pic/<xsl:value-of select="body/players/player/id"/>_g2.png</xsl:attribute></img><br/>
		<a href="#" onclick="history.go(-1)">목록으로</a>
			<input type="hidden" id="hdnDb" name="db" value="1"/>
			<input type="hidden" id="hdnMd" name="md" value="upd"/>
	</body>
	</html>
	</xsl:template>
</xsl:stylesheet>