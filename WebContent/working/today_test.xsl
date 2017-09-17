<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/today">
<html xsl:version="1.0">
<head>
</head>
<body>
<h2>오늘의 명마</h2>
	<table border="1">
	<xsl:for-each select="data">
	<tr>
	<xsl:if test="position() = 1">
		
			<td>1st</td>
			<td>
				<img width="150" height="200">
					<xsl:attribute name="src">
						<xsl:value-of select="pic"/>
					</xsl:attribute>
				</img>
			</td>
			<td>
				이름 : <xsl:value-of select="name"/><br/>
				별명 : <xsl:value-of select="nick"/><br/>
				승률 : <xsl:value-of select="winRate"/><br/>
				베팅률 : <xsl:value-of select="betRate"/><br/>
			</td>
		
	</xsl:if>
	
	<xsl:if test="position() = 2">
			<td>2nd</td>
			<td colspan="2">이름 : <xsl:value-of select="name"/></td>
	</xsl:if>
			
	<xsl:if test="position() = 3">
			<td>3rd</td>
			<td colspan="2">이름 : <xsl:value-of select="name"/></td>
	</xsl:if>
	</tr>
	</xsl:for-each>
	</table>
</body>
</html>
	</xsl:template>
</xsl:stylesheet>