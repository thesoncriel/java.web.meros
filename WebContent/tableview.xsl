<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
	<!-- <xsl:value-of select="header/author"/> -->
	<html xsl:version="1.0">
	<head>
	<script type="text/javascript" src="common.js" />
	</head>
	<body>
	<form method="post" action="manager.jsp">
		<table border="1">
			<tr>
				<th>선택</th>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>주민번호</th>
				<th>이메일</th>
				<th>관리자 여부</th>
			</tr>
			<xsl:for-each select="body/users/user">
			<tr>
				<td><input type="checkbox" name="chkRow"/></td>
				<td><xsl:value-of select="id"/></td>
				<td><xsl:value-of select="pw"/></td>
				<td><xsl:value-of select="name"/></td>
				<td><xsl:value-of select="jumin"/></td>
				<td><xsl:value-of select="email"/></td>
				<td><xsl:value-of select="admin"/></td>
			</tr>
			</xsl:for-each>
		</table>
		<select id="cmbAtt" onclick="javascript: cmbAtt_Change('Att');">
			<option>아이디</option>
			<option>비밀번호</option>
			<option>이름</option>
			<option>주민번호</option>
			<option>이메일</option>
			<option>관리자 여부</option>
		</select>
		<select id="cmbOpr" onclick="javascript: cmbAtt_Change('Opr');">
			<option>&gt;</option>
			<option>&gt;=</option>
			<option>=</option>
			<option>&lt;=</option>
			<option>&lt;</option>
			<option>not</option>
			<option>like</option>
		</select>
		<input type="text" name="val" />
		<input type="submit" value="검색" />
		<input type="hidden" id="hdnDb" name="db" value="0" />
		<input type="hidden" id="hdnAtt" name="att" />
		<input type="hidden" id="hdnOpr" name="opr" />
		<input type="hidden" id="hdnPg" name="pg" value="1"/>
	</form>
	</body>
	</html>
	</xsl:template>
</xsl:stylesheet>