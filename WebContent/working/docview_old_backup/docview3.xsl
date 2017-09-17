<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
	</head>
	<body onload="bindDate()">
		<form id="frm" method="post" enctype="multipart/form-data">
			아이디 : <xsl:value-of select="body/results/result/id"/><br/>
			날짜 : <xsl:value-of select="body/results/result/date"/><br/>
			베팅률 : <xsl:value-of select="body/results/result/betRate"/><br/>
			승률 : <xsl:value-of select="body/results/result/winRate"/><br/>
			<br/>
			<a href="#" onclick="history.go(-1)">목록으로</a>
			<input type="hidden" id="hdnDb" name="db" value="3"/>
			<input type="hidden" id="hdnMd" name="md" value="upd"/>
		</form>
	</body>
	</html>
	</xsl:template>
</xsl:stylesheet>