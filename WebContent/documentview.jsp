<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="
hr.manager.*,
hr.member.*,
hr.web.*,
hr.win.MemberModel,
java.util.*,
java.io.*
"%>
<% request.setCharacterEncoding("UTF-8");
//response.setCharacterEncoding("UTF-8");

try{
	LoginManager logm = LoginManager.hasLogin(request, response, false);
	if(logm == null) throw new Exception();
	
	int db = Integer.parseInt(request.getParameter("db"));
	String tmp = request.getParameter("row");
	String md = request.getParameter("md");
	
	XMLManager xmlm = new XMLManager();
	List<IMember> list = new ArrayList<IMember>();
	IMember imbr = null;
	
	if(tmp != null){//데이터 하나 클릭했을 시
		int row = Integer.parseInt(tmp);
		imbr = logm.getUserResult().get(row);
		logm.setSelectedData(imbr);
		list.add(imbr);
	}
	else if(md != null && md.equals("mod")){
		list.add(logm.getUser());
		logm.setSelectedData(logm.getUser());
	}
	//그렇지 않으면 수정 모드
	if(db == 1 && imbr != null){
		try{
			DBManager dbm = new DBManager();
			int tt = dbm.getViewMax();
			dbm.setViewMax(100);
			List<IMember> gData = dbm.search(0, 2, imbr.getId(), ClassType.parse(3), 1);
			MemberModel mbrModel = new MemberModel(gData);
			GraphMaker gm1 = new GraphMaker(mbrModel.getDataset(2, "선수"),"베팅률 변화","시간","베팅률");
			GraphMaker gm2 = new GraphMaker(mbrModel.getDataset(3, "선수"),"승률 변화","시간","승률");
			gm1.createLineChart();
			gm2.createBarChart();
			String path = AbstractFileManager.DEFAULT_PATH + "/" + AbstractFileManager.SUB_FOLDER_NAME + "/";
			gm1.saveChart(path + imbr.getId() + "_g1.png");
			gm2.saveChart(path + imbr.getId() + "_g2.png");
			dbm.setViewMax(tt);
		}
		catch(NullPointerException ex){}
	}
	xmlm.setData(list);
	String adm = (logm.isAdmin())? "a" : "";
	xmlm.setXslPath("docview/docview" + db + adm + ".xsl");
	
	out.println(xmlm.getXML());
}
catch(Exception ex){
	out.println("<error>");
	out.println("<subject>그냥은 못쓴데이 -_-);;</subject>");
	out.println("<comment>아마 매개 변수가 잘못 되었을끼야. 아니면 로긴 안했던가..</comment>");
	out.println("<detail class=\"" + ex.toString() + "\">");
	out.println("<db>" + request.getParameter("db") + "</db>");
	out.println("<row>" + request.getParameter("row") + "</row>");
	out.println("</detail>");
	out.println("</error>");
	ex.printStackTrace();
}
%>