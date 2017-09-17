<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
	<script type="text/javascript" src="common.js" />
	</head>

	<body onload="bindDate()">
		<form id="frm" method="post" enctype="multipart/form-data">
			아이디 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/results/result/id"/></xsl:attribute></input><br/>
			날짜 : <select id="cmbYear">
			</select>년
			<select id="cmbMonth">
			</select>월
			<select id="cmbDay">
			</select>일
			<input type="hidden" name="val" id="hdnDate"><xsl:attribute name="value"><xsl:value-of select="body/results/result/date"/></xsl:attribute></input><br/>
			베팅률 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/results/result/betRate"/></xsl:attribute></input><br/>
			승률 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/results/result/winRate"/></xsl:attribute></input><br/>
			

			<input type="button" value="입력 완료" onclick="btnIns_Click()"/>
			<input type="button" value="수정 완료" onclick="btnUpd_Click()"/>
			<input type="button" value="삭제" onclick="btnDel_Click()"/><br/>
			<a href="#" onclick="history.go(-1)">목록으로</a>
			<input type="hidden" id="hdnDb" name="db" value="3"/>
			<input type="hidden" id="hdnMd" name="md" value="upd"/>
		</form>
	</body>

	</html>
	</xsl:template>
</xsl:stylesheet>