<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/horseRace">
<!-- <xsl:value-of select="header/author"/> -->
<html xsl:version="1.0">
<head>
<meta http-equiv="Content-Type" content="text/xml; charset=UTF-8" />
<title>Meroz1.0 ListeView</title>
<style type="text/css">

body{
	font-family: 맑은 고딕;
}
 #left1
    {
    	margin-left:70px;
    }
    .listview {
	width:598px;
	font-size:1.0em;
	text-align=center;
	align=center;
	font-weight: bold;
	text-align: left;
	}
.chkbox{
	background-color: #B3D9FF;
}
.listdata{

	padding-top:2px;
	padding-right:0px;
	padding-bottom:2px;
	padding-left:0px;
	text-align: left;
	vertical-align: top;
}
.record{
	padding:4px;
	font-size:1.0em;
	color:#000;
	text-align=right;
	font-weight: bold;
	text-align: right;
	background-color:#D2E8FF;
	text-valign:middle;
	width: 150px;
}
.recorddata{
	padding:4px;
	font-size:1.0em;
	color:#000;
	text-align=left;
	align=center;
	font-weight: bold;
	text-align: left;
	background-color:#E8F3FF;
	width: 250px;
}
    .image
    {
        padding: 2px 7px 2px 7px;
        text-align: center;
		vertical-align: middle;
    }
   
</style>
<script type="text/javascript" src="listview.js">
</script>
</head>

<body background="img/BG.jpg" onload="chkList_init(1 + 2 + 4 + 8)">
<form id="frm" method="post" action="">
<p>
  <input type="button" id="btnRowAll" value="전체선택" onclick="chkRowAll(true)"/>
  <input type="button" id="btnRowDe" value="전체선택해제" onclick="chkRowAll(false)"/>
</p>

<xsl:for-each select="body/users/user">
<table class="listview" border="0" cellpadding="2" cellspacing="3">
<tr>
    <td align="center" valign="middle" class="chkbox">
		<input type="checkbox" name="chkRow" />
	</td>
    
	<td class="image">
		<img src="" width="120" height="150"/>
	</td>

    <td class="listdata">
		
         <table border="0">
            <tr>
                <td class="record">아이디:</td>
                <td class="recorddata"><xsl:value-of select="id"/></td>
            </tr>
            <tr>
                <td class="record">패스워드:</td>
                <td class="recorddata"><xsl:value-of select="pw"/></td>
            </tr>
            <tr>
                 <td class="record">이름:</td>
                 <td class="recorddata"><xsl:value-of select="name"/></td>
            </tr>      
            <tr>
               <td class="record">주민번호:</td>
               <td class="recorddata"><xsl:value-of select="jumin"/></td>
            </tr>
			<tr>
               <td class="record">이메일:</td>
               <td class="recorddata"><xsl:value-of select="email"/></td>
            </tr>
			<tr>
               <td class="record">권한:</td>
               <td class="recorddata"><xsl:value-of select="admin"/></td>
            </tr>
        </table>
   </td>
</tr>
</table>
<hr width="650" align="left" style="color:#6699CC"/>
</xsl:for-each>


		<div id="left">
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>그림--</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>아이디</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>패스워드</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>이름</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>주민번호</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>이메일</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>권한</label>
	    </div>
 <div id="left1">
 <p>
    <label>
     <select name="select" size="1" id="select">
        <option>아이디</option>
        <option>패스워드</option>
        <option>이름</option>
        <option>이메일</option>
        <option>권한</option>
       
      </select>
    </label>
    <label>
      <input type="text" name="textfield" id="textfield" /> </label>
    
	<label>
    <input type="submit" name="button8" id="button8" value="검색" /> </label>
    
	<label>
      <input type="submit" name="button2" id="button2" value="새로입력" />
      <input type="submit" name="button" id="button" value="삭제" />
    </label>
</p>
</div>

</form>

</body>

</html>

	</xsl:template>
</xsl:stylesheet>