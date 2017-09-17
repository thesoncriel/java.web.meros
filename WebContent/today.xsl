<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/today">
<html xsl:version="1.0">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<title></title>
</head>

<body text="black" link="blue" vlink="purple" alink="red" background="img/BG.jpg">
<p align="center"><img src="img/maintoday.jpg" width="317" height="50"/></p>
<table width="550" height="380" border="0"  background="img/bg3.jpg" align="center" style="background-repeat: no-repeat">
  <tr>
    <td width="549" height="380">
    
    <table width="449" height="302" cellspacing="0"  align="center" bordercolordark="white" 
bordercolorlight="black" style="border-right-width:1; border-right-color:white; border-left-color:white; border-right-
style:none; border-collapse:collapse; font-family: '휴먼모음T';">

<xsl:for-each select="data">


	  <xsl:if test="position() = 1">
      <tr bordercolor="#66CCFF" bordercolordark="#66CCFF" bordercolorlight="#66CCFF" style="border-right-width:1; 
		border-right-style:none;">
        <td width="41" height="41" style="text-align: center"><div align="center">
        <span style="font-weight:bodl; font-family:휴먼모음T; font-size:13pt;">1st</span></div></td>
        <td width="207" height="41" align="bottom" style="text-align: center">
        <div align="center">
	        <img src="img/horsepace.jpg" width="200" height="201">
		        <xsl:attribute name="src">
					<xsl:value-of select="pic"/>
				</xsl:attribute>
			</img>
		</div></td>
        <td width="190" height="41" style="text-align: center">
          <p align="left"><font face="휴먼모음T"><span style="font-size:13pt;">이름　 : <xsl:value-of select="name"/></span></font></p>
          <p align="left"><font face="휴먼모음T"><span style="font-size:13pt;">별명　 : <xsl:value-of select="nick"/></span></font></p>
          <p align="left"><font face="휴먼모음T"><span style="font-size:13pt;">승률　 : <xsl:value-of select="winRate"/>%</span></font></p>
          <p align="left"><font face="휴먼모음T"><span style="font-size:13pt;">베팅률 : <xsl:value-of select="betRate"/>%</span></font></p>
        </td>
      </tr>
      </xsl:if>
      
      
      <xsl:if test="position() = 2">
      <tr bordercolor="#66CCFF" bordercolordark="#66CCFF" bordercolorlight="#66CCFF" font="휴먼모음T">
        <td width="41" height="49" style="text-align: center"><div align="center"><strong><span style="font-
size:13pt;">2st</span></strong></div></td>
        <td width="400" height="49" colspan="2" align="center" style="text-align: center"><p align="center"><font 
face="휴먼모음T"><span style="font-size:13pt;">이름 : <xsl:value-of select="name"/></span></font></p></td>
      </tr>
      </xsl:if>
      
      <xsl:if test="position() = 3">
      <tr bordercolor="#66CCFF" bordercolordark="#66CCFF" bordercolorlight="#66CCFF" font="휴먼모음T">
        <td width="41" height="48" style="text-align: center"><div align="center"><strong><span style="font-
size:13pt;">3st</span></strong></div></td>
        <td width="400" height="48" colspan="2" align="center" style="text-align: center"><p align="center"><font 
face="휴먼모음T"><span style="font-size:13pt;">이름 : <xsl:value-of select="name"/></span></font></p></td>
      </tr>
      </xsl:if>
      
</xsl:for-each>      
      
    </table></td>
  </tr>
</table>
</body>
</html>
	</xsl:template>
</xsl:stylesheet>