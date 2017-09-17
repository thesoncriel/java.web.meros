package hr.web;

import hr.manager.DBManager;
import hr.manager.LoginManager;
import hr.manager.MemberManager;
import hr.manager.XMLManager;
import hr.member.ClassType;
import hr.member.IMember;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TableView
 */
public class TableView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableView() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		MemberManager mm = new MemberManager();
		XMLManager xmlm = new XMLManager();
		DBManager dbm = new DBManager();
		//xmlm.setXslPath("http://localhost:8080/WebMeroz/tableview.xsl");
		dbm.setViewMax(2);
		List<IMember> list = dbm.search(0, 6, "", ClassType.USER, 1);
		xmlm.setData(list);
		out.println(xmlm.getXML());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
	}

}
