package hr.manager;

import hr.member.ClassType;
import hr.member.HorseRace;
import hr.member.IMember;
import hr.member.ObjectFactory;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class AbstractDBManager {
	protected final String DB_URL = "jdbc:odbc:meroz";
	protected final String DB_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	protected final static String DB_ENCODING = "8859_1";
	
	protected Connection conn = null;
	protected Statement stmt = null;
	
	protected final String[] table = {"tuser", "tplayer", "thorse", "tresult"};
	
	protected boolean connection(){
		try {
			
			Class.forName(DB_DRIVER);
			Properties props = new Properties();
			props.put("charSet", DB_ENCODING);
			conn = DriverManager.getConnection(DB_URL, props); 
			
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
	protected String getFieldName(String attName, ClassType type){
		IMember imbr = HorseRace.createInstance(type);
		String[] fieldName = imbr.getFieldName();
		String result = null;
		int i = 0;
		for(String str : imbr.getColumnName()){
			if(str.equals(attName)) result = fieldName[i];
			i++;
		}
		return result;
	}
	protected String getFieldName(int attNum, ClassType type){
		IMember imbr = HorseRace.createInstance(type);
		String[] fieldName = imbr.getFieldName();
		try{
			return fieldName[attNum];
		}
		catch(IndexOutOfBoundsException ex){
			return null;
		}
	}
	protected String getFieldName(String korName, String table){
		IMember imbr = HorseRace.createInstance(getClassType(table));
		String[] fieldName = imbr.getFieldName();
		String[] colName = imbr.getColumnName();
		for(int i = 0; i < colName.length; i++){
			if(colName[i].equals(korName)) return fieldName[i];
		}
		return null;
	}
	protected ClassType getClassType(String table){
		for(int i = 0; i < table.length(); i ++){
			if(table.equals(this.table[i])) return ClassType.parse(i);
		}
		return null;
	}
	protected String getOperator(int operator){
		String[] oper = {">=", ">", "=", "<", "<=", "<>", "like"};
		for(int i = 0; i < oper.length; i++){
			if(operator == i) return oper[i];
		}
		return null;
	}
	protected boolean isNumeric(String value){
		try{
			double d = Double.parseDouble(value);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
	protected boolean isDate(String value){
		try{
			Date d = Date.valueOf(value);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
	protected String[] encodingFromDB(String[] arg){
		String[] tmp = new String[arg.length];
		for(int i = 0; i < arg.length; i++){
			tmp[i] = encodingFromDB(arg[i])	;
		}
		return tmp;
	}
	public static String encodingFromDB(String arg){
		try{
			String tmp = new String(arg.getBytes(DB_ENCODING), "ksc5601");
			return tmp;
		}
		catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	protected String[] encodingToDB(String[] arg){
		String[] tmp = new String[arg.length];
		for(int i = 0; i < arg.length; i++){
			tmp[i] = encodingToDB(arg[i]);
		}
		return tmp;
	}
	public static String encodingToDB(String arg){
		try{
			String tmp = new String(arg.getBytes("ksc5601"), DB_ENCODING);
			return tmp;
		}
		catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	protected String setTable(String sql, ClassType type){
		for(int i = 0; i < table.length; i++){
			if(type.value() == i){
				sql = sql.replace("!t", table[i]);
				break;
			}
		}
		return sql;
	}
}
