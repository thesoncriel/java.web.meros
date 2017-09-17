<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<html xsl:version="1.0">
	<head>
	<!-- <xsl:value-of select="/horseRace/header/author"/> -->
	<script type="text/javascript" src="common.js" />
	</head>

	<body onload="cmbBind()">
		<form id="frm" method="post" enctype="multipart/form-data">
			아이디 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/users/user/id"/></xsl:attribute></input><br/>
			비밀번호 : <input type="password" name="val"><xsl:attribute name="value"><xsl:value-of select="body/users/user/pw"/></xsl:attribute></input><br/>
			이름 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/users/user/name"/></xsl:attribute></input><br/>
			주민번호 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/users/user/jumin"/></xsl:attribute></input><br/>
			이메일 : <input type="text" name="val"><xsl:attribute name="value"><xsl:value-of select="body/users/user/email"/></xsl:attribute></input><br/>
			<input type="hidden" id="hdnAdmin" name="val" value="false"/>
			<input type="button" value="수정 완료" onclick="btnUpdn_Click()"/>
			<input type="button" value="회원 탈퇴" onclick="btnDeln_Click()"/><br/>
			<a href="#" onclick="history.go(-1)">목록으로</a>
			<input type="hidden" name="db" value="0"/>
			<input type="hidden" id="hdnMd" name="md" value="mod"/>
		</form>
	</body>
	</html>
	</xsl:template>
</xsl:stylesheet>