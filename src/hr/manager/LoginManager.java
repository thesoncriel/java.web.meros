package hr.manager;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.member.ClassType;
import hr.member.IMember;
import hr.member.User;

public class LoginManager extends AbstractDBManager{
	private final String SQL_SELECT = "select * from tuser where id = ?";
	private final String SQL_INSERT = "insert into tuser values(?, ?, ?, ?, ?, ?,)";
	private final String SQL_UPDATE = "update tuser set pw = ?, name = ?, jumin = ?, email = ?, admin = ? where id = ?";
	private final String SQL_DELETE = "delete * from tuser where id = ?";
	private static final String MAIN_PAGE = "main.jsp";
	private static final String JOIN_PAGE = "join.htm";
	private static final String INDX_PAGE = "index.htm";
	
	private PreparedStatement stmt = null;

	public static final int STATE_UNREGISTERED_ID = 2;
	public static final int STATE_INVALID_PW = 4;
	public static final int STATE_SUCCESS = 8;
	public static final int STATE_ADMIN = 128;
	
	private static final int MSG_LOGIN_ERROR = 64;
	private static final int MSG_NO_LOGIN = 65;
	private static final int MSG_LOGIN_SUCCESS = 66;
	private static final int MSG_LOGOUT = 67;
	
	private int state;
	// TODO 쓰고 삭제
	//public StringBuffer aa = new StringBuffer("차자:::");
	
	private String visitorId = null;
	private String visitorPw = null;
	private ClassType userSelection = null;
	private String viewSelection;
	private User user = null;;
	private List<IMember> userResult = null;
	private IMember selectedData = null;
	
	public LoginManager(){
		userSelection = ClassType.PLAYER;
		viewSelection = "tableview.jsp";
		state = STATE_UNREGISTERED_ID;
		//user = new User();
	}
	/*
	public LoginManager(String dburl, String driver){
		this();
		this.dburl = dburl;
		this.driver = driver;
	}*/
	
	public void setId(String id){
		visitorId = id;
	}
	public void setPw(String pw){
		connection();
		this.user = findUser(pw);
		if(user != null){
			if(user.isAdmin()) state = STATE_ADMIN;
			else if(user.getPw().equals(pw) == false) state = STATE_INVALID_PW;
			else state = STATE_SUCCESS;
		}
		disconnection();
	}
	
	protected User findUser(String pw){
		try {
			stmt = conn.prepareStatement(SQL_SELECT);
			stmt.setString(1, visitorId);
			ResultSet rs = stmt.executeQuery();
			
			User user = null;
			if(rs.next()){
				String[] str = new String[6];
				for(int i = 0; i < 6; i++){
					str[i] = rs.getString(i+1);
					//aa.append(str[i] + ";;");
				}
				str = encodingFromDB(str);
				user = new User();
				user.setData(str);
			}
			if(user.getPw().equals(pw)){
				//aa.append(user.getPw() + " = " + pw + " : " + user.getPw().equals(pw));
				this.user = user;
				return user;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			//aa.append("쿼리");
			return null;
		} catch (NullPointerException e){
			//aa.append("널포인터");
			return null;
		}
	}

	public ClassType getUserSelection() {
		return userSelection;
	}

	public void setUserSelection(ClassType userSelection) {
		this.userSelection = userSelection;
	}
	
	public int update(User user){
		try {
			connection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			String[] str = user.toStrings();
			for(int i = 0; i < 6; i++){
				stmt.setString(i+1, this.encodingToDB(str[i]));
			}
			
			int result = stmt.executeUpdate();
			disconnection();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnection();
		return -1;
	}
	public int insert(User user){
		try {
			connection();
			stmt = conn.prepareStatement(SQL_INSERT);
			String[] str = user.toStrings();
			for(int i = 1; i < 6; i++){
				System.out.println(str[i]);
				stmt.setString(i, this.encodingToDB(str[i]));
			}
			stmt.setString(6, str[0]);
			int result = stmt.executeUpdate();
			disconnection();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnection();
		this.user = user;
		state = STATE_SUCCESS;
		return -1;
	}
	public int delete(User user){
		try{
			connection();
			stmt = conn.prepareStatement(SQL_DELETE);
			String id = user.getId();
			stmt.setString(1, id);
			int result = stmt.executeUpdate();
			disconnection();
			return result;
		}
		catch(SQLException ex){}
		disconnection();
		return 0;
	}
	public int delete(List<User> list){
		try{
			connection();
			int result = 0;
			stmt = conn.prepareStatement(SQL_DELETE);
			for(User user : list){
				stmt.setString(1, user.getId());
				result += stmt.executeUpdate();
			}
			disconnection();
			return result;
		}
		catch(SQLException ex){}
		disconnection();
		return -1;
	}
	protected void disconnection(){
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getState(){
		return state;
	}
	
	public User getUser(){
		return (User)user.clone();
	}
	public static LoginManager hasLogin(HttpServletRequest request, HttpServletResponse response){
		return hasLogin(request, response, true);
	}
	public static LoginManager hasLogin(HttpServletRequest request, HttpServletResponse response, boolean msg){
		try{
			String ip = request.getRemoteAddr();
			LoginManager logm = (LoginManager)request.getSession().getAttribute(ip);
			logm.getUser().getName();
			return logm;
		}
		catch(Exception ex){
			if(msg){
				try {
					response.getWriter().println(message(MSG_NO_LOGIN));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public boolean login(HttpServletRequest request, HttpServletResponse response, boolean msg){
		String ip = request.getRemoteAddr();
		String value = request.getParameter("md");
		
		try{
			if(state >= LoginManager.STATE_SUCCESS){
				request.getSession().setAttribute(ip, this);
				if(msg) response.getWriter().println(message(MSG_LOGIN_SUCCESS));
				response.sendRedirect(MAIN_PAGE);
				return true;
			}
			else if(value.equals("join")){
				response.sendRedirect(JOIN_PAGE);
			}
			else{
				if(msg){
					response.getWriter().println(message(MSG_LOGIN_ERROR));
				}	
			}
			return false;
		}
		catch (IOException e) {
			try {
				response.getWriter().println(MAIN_PAGE + "를 찾을 수 없습니다 ㅠㅠ");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		catch (Exception ex){
			ex.printStackTrace();
			try {
				response.getWriter().println(INDX_PAGE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	protected static String message(int state){
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		switch(state){
		case MSG_LOGIN_ERROR:
			sb.append("alert(\"아이디 혹은 비밀번호가 잘못 되었습니다능. \\n제대로 써 주라능~ -ㅅ-)/;;\");");
			sb.append("history.go(-1);");
			break;
		case MSG_LOGIN_SUCCESS:
			sb.append("alert(\"성공적으로 로그인 하셨습니다.\\n환영해요오 ^o^)b\");");
			sb.append("location.href = \"" + MAIN_PAGE + "\";");
			break;
		case MSG_LOGOUT:
			sb.append("alert(\"성공적으로 로그아웃 하였습니다. \\n이용해 주셔서 감사합니다 ^^~\");");
			sb.append("location.href = \"" + INDX_PAGE + "\";");
			break;
		case MSG_NO_LOGIN:
			sb.append("alert(\"로그인을 하시고 이용해 주십시요. ^^;; \\n자동으로 로그인 페이지로 갑니다.\");");
			sb.append("location.href = \"" + INDX_PAGE + "\";");
			break;
		}
		sb.append("</script>");
		return sb.toString();
	}

	public void setViewSelection(String viewSelection) {
		this.viewSelection = viewSelection;
	}

	public String getViewSelection() {
		return viewSelection;
	}

	public boolean isAdmin(){
		return user.isAdmin();
	}
	
	public void setUserResult(List<IMember> list){
		userResult = list;
	}
	public List<IMember> getUserResult(){
		return userResult;
	}
	public void setSelectedData(IMember imbr){
		selectedData = imbr;
	}
	public IMember getSelectedData(){
		return selectedData;
	}
	public String getName(){
		return user.getName();
	}
	public static boolean logout(HttpServletRequest request, HttpServletResponse response, boolean msg){
		String ip = request.getRemoteAddr();
		request.getSession().setAttribute(ip, null);
		try {
			if(msg) response.getWriter().println(message(MSG_LOGOUT));
			return true;
		} catch (IOException e) {
			try {
				response.getWriter().println(INDX_PAGE + " 을 찾을 수 없슴돠 ㅠㅠ");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}
}
