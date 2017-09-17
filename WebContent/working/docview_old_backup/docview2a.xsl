<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
	<script type="text/javascript" src="common.js" />
	</head>
	<body onload="cmbBind(); bindHorseType()">
		<form id="frm" method="post" action="search.jsp" enctype="multipart/form-data">
			말 식별 번호 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/id"/></xsl:attribute></input><br/>
			이름 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/name"/></xsl:attribute></input><br/>
			나이 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/age"/></xsl:attribute></input><br/>
			<input type="hidden" id="hdnGender">
				<xsl:attribute name="value"><xsl:value-of select="body/horses/horse/gender"/></xsl:attribute>
			</input>
			성별 : <select id="cmbGender" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/gender"/></xsl:attribute>
				<option value="true">수컷</option>
				<option value="false">암컷</option>
			</select><br/>
			신장(m) : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/height"/></xsl:attribute></input><br/>
			몸무게(kg) : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/weight"/></xsl:attribute></input><br/>
			<input type="hidden" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/picURL"/></xsl:attribute></input>
			별명 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/nick"/></xsl:attribute></input><br/>
			<input type="hidden" id="hdnType"><xsl:attribute name="value"><xsl:value-of select="body/horses/horse/type"/></xsl:attribute></input>
			말 종류 : <select id="cmbType" name="val">
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
			</select><br/>
			<img id="imgPic" width="150" height="200">
				<xsl:attribute name="src"><xsl:value-of select="body/horses/horse/picURL"/></xsl:attribute>
			</img><br/>
			<input type="file" name="picURL"/><br/>
			<input type="button" value="입력 완료" onclick="btnIns_Click()"/>
			<input type="button" value="수정 완료" onclick="btnUpd_Click()"/>
			<input type="button" value="삭제" onclick="btnDel_Click()"/><br/>
			<a href="#" onclick="history.go(-1)">목록으로</a>
			<input type="hidden" id="hdnDb" name="db" value="2"/>
			<input type="hidden" id="hdnMd" name="md" value="upd"/>
		</form>
	</body>
	</html>
	</xsl:template>
</xsl:stylesheet>