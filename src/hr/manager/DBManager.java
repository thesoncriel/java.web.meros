package hr.manager;

import hr.member.ClassType;
import hr.member.HorseRace;
import hr.member.IMember;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DBManager extends AbstractDBManager{

	private static final String SQL_SELECT = "select * from !t where !f !o ?";
	private static final String SQL_UPDATE = "update !t set !fvs where !ids ";
	private static final String SQL_INSERT = "insert into !t values(!fs)";
	private static final String SQL_DELETE = "delete from !t where !fvs";
	private static final String SQL_COUNT = "select count(*) as count from !t";
	
	private ClassType dbNum;
	private int attribute;
	private int operator;
	private String value = null;
	private int page;
	private int viewMax;
	private static int dbRowTotal = 0;
	
	//private static Vector<LoginManager> vecId = new Vector<LoginManager>(4,4);
	//private static Vector<List> vecResult = new Vector<List>(4,4);
	//private int userNum;
	
	private List<IMember> list = null;
	
	public DBManager(){
		list = new ArrayList<IMember>();
		dbNum = ClassType.PLAYER;
		page = 1;
		attribute = 0;
		operator = 6;
		value = "";
		viewMax = 100;
	}
	@SuppressWarnings("unchecked")
	public void setData(List<? extends IMember> list){
		this.list = (List<IMember>)list;
	}
	
	public int delete(ClassType type, String id){
		String[] str = new String[1];
		str[0] = id;
		return delete(type, str);
	}
	public int delete(ClassType type, String... id){
		connection();
		int result = deletePure(type, id);
		setDBRowTotal(type);
		disconnection();
		return result;
	}
	public int delete(){
		if(list.size() > 0){
			connection();
			int result = 0;
			for(IMember imbr : list){
				result += deletePure(imbr);
			}
			setDBRowTotal(list.get(0).getClassType());
			disconnection();
			return result;
		}
		return 0;
	}
	public int delete(List<IMember> list){
		this.list = list;
		return delete();
	}
	protected int deletePure(IMember imbr){
		ClassType type = imbr.getClassType();
		if(type != ClassType.RESULT){
			return deletePure(type, imbr.getId());
		}
		else{
			String[] str = imbr.toStrings();
			return deletePure(type, str[0], str[1]);
		}
	}
	protected int deletePure(ClassType type, String... id){
		String sql = setTable(SQL_DELETE, type);
		
		StringBuffer fvs = null;
		
		if(type != ClassType.RESULT){
			fvs = new StringBuffer("id = ");
			fvs.append("'" + id[0] + "'");
			for(int i = 1; i < id.length; i++){
				fvs.append(" or id = '" + id[i] + "'");
			}
		}
		else{
			fvs = new StringBuffer("(id = ");
			fvs.append("'" + id[0] + "' and fdate = #" + id[1] + "#)");
			for(int i = 2; i < id.length; i++){
				fvs.append(" or (id = '" + id[i] + "' and fdate = #" + id[++i] + "#)");
			}
		}
		sql = sql.replace("!fvs", fvs);
		
		try{
			stmt = conn.createStatement();
			System.out.println(sql);
			//System.out.println(this.encodingToDB(sql));
			int result = stmt.executeUpdate(this.encodingToDB(sql));
			
			return result;
		}
		catch(SQLException ex){}
		return -1;
	}
	
	public int insert(List<IMember> list){
		this.list = list;
		return insert();
	}
	public int insert(IMember imbr){
		ClassType type = imbr.getClassType();
		connection();
		int result = insertPure(imbr);
		setDBRowTotal(type);
		disconnection();
		return result;
	}
	public int insert(){
		if(list.size() > 0){
			connection();
			int result = 0;
			for(IMember imbr : list){
				result += insertPure(imbr);
			}
			setDBRowTotal(list.get(0).getClassType());
			disconnection();
			return result;
		}
		return 0;
	}
	protected int insertPure(IMember imbr){
		String sql = setTable(SQL_INSERT, imbr.getClassType());
		
		int len = HorseRace.createInstance(imbr.getClassType()).getFieldName().length;
		
		StringBuffer fs = new StringBuffer();
		fs.append("?0");
		for(int i = 1; i < len; i++){
			fs.append(", ?" + i);
		}
		sql = sql.replace("!fs", fs);
		try{
			System.out.println(sql);
			stmt = conn.createStatement();
			//System.out.println(imbr.getId() + "==");
			int i = 0;
			for(String str : imbr.toStrings()){
				if(isNumeric(str)){
					sql = sql.replace("?" + i, str);
				}
				else{
					sql = sql.replace("?" + i, "'" + str + "'");
				}
				i++;
			}
			int result = stmt.executeUpdate(encodingToDB(sql));
			return result;
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return -1;
	}
	
	public int update(IMember oldmbr, IMember newmbr){
		connection();
		deletePure(oldmbr);
		int result = insertPure(newmbr);
		disconnection();
		return result;
	}
	public List<IMember> search(){
		return search(attribute, operator, value, dbNum, page);
	}
	public List<IMember> search(int attName, int operator, String str, ClassType type, int page){
		String att = getFieldName(attName, type);
		String sql = setTable(SQL_SELECT, type);
		sql = sql.replace("!f", att);
		str = DBManager.encodingFromDB(str);
		if(att.equals("age") || att.equals("height") || att.equals("weight")){
			sql = sql.replace("?", str);
			operator = (operator == 6)? 2 : operator;
		}
		else if(att.contains("date")){
			sql = sql.replace("?", "#" + str + "#");
			operator = 2;
		}
		else{
			if(operator == 2){
				sql = sql.replace("?", "'" + str + "'");
			}
			else{
				sql = sql.replace("?", "'%" + str + "%'");
				operator = 6;
			}
		}
		
		sql = sql.replace("!o", getOperator(operator));
		//System.out.println(sql);
		return search(sql, type);
	}

	public List<IMember> search(String sql, ClassType type){
		sql = encodingToDB(sql);
		List<IMember> list = new ArrayList<IMember>();
		connection();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int len = HorseRace.createInstance(type).getFieldName().length;
			int pnum = 0;
			int max = viewMax * page;
			int min = max - viewMax + 1;
			while(rs.next()){
				pnum++;
				if(pnum < min || pnum > max) continue;

				IMember imbr = HorseRace.createInstance(type);
				String[] value = new String[len];
				for(int i = 0; i < len; i++){
					value[i] = rs.getString(i+1);
				}
				imbr.setData(encodingFromDB(value));
				list.add(imbr);
			}
			dbRowTotal = pnum;
			dbNum = type;
			this.list = list;
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		}
		disconnection();
		this.list = list;
		return list;
	}
	

	public void setViewMax(int viewMax) {
		this.viewMax = viewMax;
	}

	public int getViewMax() {
		return viewMax;
	}

	public static int getDBRowTotal() {
		return dbRowTotal;
	}
	public void setDBRowTotal(ClassType type){
		String sql = setTable(SQL_COUNT, type);
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int count = 0;
			if(rs.next()) count = rs.getInt(1);
			dbRowTotal = count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ClassType getDBNum(){
		return dbNum;
	}
	public int getDb(){
		return dbNum.value();
	}
	public void setDb(int dbNum) {
		this.dbNum = ClassType.parse(dbNum);
	}
	public int getAtt() {
		return attribute;
	}
	public void setAtt(int attribute) {
		this.attribute = attribute;
	}
	public int getOpr() {
		return operator;
	}
	public void setOpr(int operator) {
		this.operator = operator;
	}
	public void setVal(String value) {
		this.value = (value != null)? DBManager.encodingToDB(value) : "";
	}
	public String getVal() {
		return value;
	}
	public int getPg(){
		return page;
	}
	public void setPg(int page) {
		this.page = page;
	}
	public void setRecentResult(HttpServletRequest request){
		String id = request.getLocalAddr() + "_res"; 
		request.getSession().setAttribute(id, list);
	}
	@SuppressWarnings("unchecked")
	public List<IMember> getRecentResult(HttpServletRequest request){
		String id = request.getLocalAddr() + "_res";
		Object obj = request.getSession().getAttribute(id);
		if(obj != null){
			list = (List<IMember>)obj;
			return list;
		}
		else{
			return null;
		}
	}
	public void setRecentRow(HttpServletRequest request, int row){
		String id = request.getLocalAddr() + "_row"; 
		request.getSession().setAttribute(id, new Integer(row));
	}
	public int getRecentRow(HttpServletRequest request){
		String id = request.getLocalAddr() + "_row";
		Object obj = request.getSession().getAttribute(id);
		if(obj != null){
			return ((Integer)obj).intValue();
		}
		else{
			return -1;
		}
	}
}
