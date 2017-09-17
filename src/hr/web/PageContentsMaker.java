package hr.web;

import hr.member.ClassType;
import hr.member.HorseRace;

public class PageContentsMaker {
	public static final int VIEW_QUANTITY = 10;
	public static final String PREV_STR = "이전";
	public static final String NEXT_STR = "다음";
	
	public static String getNumbers(String max, String current, String params){
		return getNumbers(Integer.parseInt(max), Integer.parseInt(current), params);
	}
	public static String getNumbers(int max, int current, String params){
		int pageMin = current / VIEW_QUANTITY;
		int pageMax = pageMin + VIEW_QUANTITY;
		pageMax = (pageMax < max)? pageMax : max;
		StringBuffer sb = new StringBuffer();
		if(pageMin >= VIEW_QUANTITY) sb.append("<a href=\"#\" onclick=\"javascript: btnPage_Click(" + pageMin + ")\">" + PREV_STR + "</a>");
		for(int i = pageMin + 1; i <= pageMax; i++){
			if(i == current)
				sb.append(" <strong>" + i + "</strong>\n");
			else
				sb.append(" <a href=\"#\" onclick=\"btnPage_Click(" + i + ")\">" + i + "</a>\n");
		}
		if(pageMax < max) sb.append(" <a href=\"#\" onclick=\"btnPage_Click(" + (pageMax+1) + ")\">" + NEXT_STR + "</a>");
		
		return sb.toString();
	}
	
	public static String getColumns(ClassType type){
		String[] str = HorseRace.createInstance(type).getColumnName();
		StringBuffer sb = new StringBuffer();
		for(String s : str){
			sb.append("<th>" + s + "</th>\n");
		}
		return sb.toString();
	}
	
	public static String getXSLValueOf(ClassType type){
		String[] str = HorseRace.createInstance(type).getFieldName();
		StringBuffer sb = new StringBuffer();
		sb.append("<td><a href=\"#\" onclick=\"a_Click(" + type.value() + ", this)\">");
		sb.append("<xsl:attribute name=\"name\">");
		sb.append("<xsl:value-of select=\"" + "date" + "\" />");
		sb.append("</xsl:attribute>");
		sb.append("<xsl:value-of select=\"" + str[0] + "\" /></a></td>\n");
		for(int i = 1; i < str.length; i++){
			String s = str[i];
			if(s.equals("fdate")){
				s = "date";
			}
			sb.append("<td><xsl:value-of select=\"" + s + "\" /></td>\n");
		}
		return sb.toString();
	}
	
	public static String getOptions(ClassType type){
		String[] str = HorseRace.createInstance(type).getColumnName();
		StringBuffer sb = new StringBuffer();
		for(String s : str){
			sb.append("<option>" + s + "</option>\n");
		}
		return sb.toString();
	}
	
	public static String getXSLForEachElement(ClassType type){
		String str = type.toString();
		return (str + "s/" + str).toLowerCase();
	}
	
	public static String getXSLSrc(ClassType type){
		switch(type){
		case USER:
			return "documentview.xsl";
		case PLAYER:
		case HORSE:
		case RESULT:
		}
		return null;
	}
	
	public static String getFooterCheckBox(ClassType type){
		String[] col = HorseRace.createInstance(type).getColumnName();
		StringBuffer sb = new StringBuffer();
		String hidden = " style=\"display: none\"";
		
		for(String s : col){
			if(s.equals("사진") == false){
				sb.append("<input name=\"chkList\" type=\"checkbox\" onclick=\"chkList_Click(this)\">" + s + "</input>\r\n");
			}
			else{
				hidden = "";
			}
		}
		sb.insert(0, "<label" + hidden + "><input name=\"chkList\" type=\"checkbox\" onclick=\"chkList_Click(this)\"/>사진</label>\r\n");
		return sb.toString();
	}
	
	public static String getXSLValueOf_ListView(ClassType type){
		String[] col = HorseRace.createInstance(type).getColumnName();
		String[] fld = HorseRace.createInstance(type).getFieldName();
		StringBuffer sb = new StringBuffer();
		sb.append("<tr><td class=\"record\">" + col[0] + ":</td>");
		sb.append("<td class=\"recorddata\"><a href=\"#\" onclick=\"a_Click(" + type.value() + ", this)\">");
		sb.append("<xsl:attribute name=\"name\">");
		sb.append("<xsl:value-of select=\"" + "date" + "\" />");
		sb.append("</xsl:attribute>");
		sb.append("<xsl:value-of select=\"" + fld[0] + "\"/></a></td></tr>");
		for(int i = 1; i < col.length; i ++){
			if(fld[i].equals("fdate")) fld[i] = "date";
			if(col[i].equals("사진")) continue;
			sb.append("<tr><td class=\"record\">" + col[i] + ":</td>");
			sb.append("<td class=\"recorddata\"><xsl:value-of select=\"" + fld[i] + "\"/></td></tr>");
		}
		return sb.toString();
	}
	
	public static int getCheckBox_InitValue(ClassType type){
		switch(type){
		case USER:
			return 2+8+32+64;
		case PLAYER:
			return 1+2+4+8+32+64;
		case HORSE:
			return 1+2+4+8+32+64+256;
		case RESULT:
			return 2+4+8+16;
		}
		return 1+2+4+8+16+32+64+128+256+512;
	}
}
/*
 * sb.append("<td><a href=\"#\" onclick=\"a_Click(" + type.value() + ", this)\">");
		sb.append("<xsl:attribute name=\"name\">");
		sb.append("<xsl:value-of select=\"" + "date" + "\" />");
		sb.append("</xsl:attribute>");
		sb.append("<xsl:value-of select=\"" + str[0] + "\" /></a></td>\n");
 */
/*
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
*/
/*
<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>그림--</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>아이디</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>패스워드</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>이름</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>주민번호</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>이메일</label>
		<label><input name="chkList" type="checkbox" onclick="chkList_Click(this)"/>권한</label>
 */
/*
<a href="manager.jsp">이전</a>
&nbsp;&nbsp;<a href="manager.jsp?">1</a>
&nbsp;&nbsp;2
&nbsp;&nbsp;3
&nbsp;&nbsp;4
&nbsp;&nbsp;5
&nbsp;&nbsp;6
&nbsp;&nbsp;7
&nbsp;&nbsp;8
&nbsp;&nbsp;9
&nbsp;&nbsp;10
&nbsp;&nbsp;<a href="다음">다음</a>
*/