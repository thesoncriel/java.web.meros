package hr.manager;
import hr.member.ClassType;
import hr.member.Horse;
import hr.member.HorseRace;
import hr.member.HorseType;
import hr.member.IMember;
import hr.member.ObjectFactory;
import hr.member.Player;
import hr.member.Result;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.xml.datatype.XMLGregorianCalendar;


public class MemberManager extends AbstractDBManager{
	public static final String DEFAULT_SAVE_FILE = "horserace.hrm";
	public static final ClassType DEFAULT_USER_SELECTION = ClassType.PLAYER;
	public final String ROW_KEY = "선수";
	
	private static HorseRace hr = null;
	private static ClassType userSelection = null;


	public MemberManager() {
		if(hr == null) clear();
	}

	public MemberManager(String vectorPath) {
		if (this.load(vectorPath) == false) {
			clear();
		}
	}

	public void add(IMember imbr){
		if(imbr.getClass() == Player.class)
			hr.getPlayers().add((Player)imbr);
		else if(imbr.getClass() == Horse.class)
			hr.getHorses().add((Horse)imbr);
		else if(imbr.getClass() == Result.class)
			hr.getResults().add((Result)imbr);
	}

	public void add(HorseRace hr){
		for(Iterator<Player> iter = hr.getPlayers().iterator(); iter.hasNext();){
			add(iter.next());
		}
		for(Iterator<Horse> iter = hr.getHorses().iterator(); iter.hasNext();){
			add(iter.next());
		}
		for(Iterator<Result> iter = hr.getResults().iterator(); iter.hasNext();){
			add(iter.next());
		}
	}
	public void add(List<IMember> list){
		for(IMember imbr: list){
			add(imbr);
		}
	}
	

	public void add(String[] str) {
		switch (str.length) {
		case 4:
			this.add(new Result(str[0], str[1], Double.parseDouble(str[2]),
					Integer.parseInt(str[3])));
			break;
		case 8:
			this.add(new Player(str[0], str[1], Integer.parseInt(str[2]),
					Boolean.parseBoolean(str[3]), Double.parseDouble(str[4]),
					Double.parseDouble(str[5]), str[6], str[7]));
			break;
		case 9:
			this.add(new Horse(str[0], str[1], Integer.parseInt(str[2]),
					Boolean.parseBoolean(str[3]), Double.parseDouble(str[4]),
					Double.parseDouble(str[5]), str[6], str[7], HorseType
							.fromValue(str[8])));
			break;
		}
	}
	public void remove(Player mbr){
		hr.getPlayers().remove(mbr);
	}
	public void remove(Horse mbr){
		hr.getHorses().remove(mbr);
	}
	public void remove(Result mbr){
		hr.getResults().remove(mbr);
	}

	public boolean load(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			hr = (HorseRace) ois.readObject();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return false;
	}
	public boolean load(){
		return this.load(DEFAULT_SAVE_FILE);
	}
	public boolean load(String filePath){
		return this.load(new File(filePath));
	}

	public boolean save(File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(hr);
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("����; ã; �� ��� -��-");
		} catch (IOException e) {
			System.out.println("��� �Ұ�;;");
		}
		return false;
	}
	public boolean save(){
		return this.save(DEFAULT_SAVE_FILE);
	}
	public boolean save(String filePath){
		return this.save(new File(filePath));
	}
	public IMember newMember(){
		return this.newMember(userSelection);
	}
	public IMember newMember(ClassType type){
		return HorseRace.createInstance(type);
	}

	public List<? extends IMember> getMembers(){
		return hr.getMembers(userSelection);
	}
	
	public List<IMember> getMembers(int attIndex, int operator, String stdValue){
		return this.getMembers(attIndex, operator, stdValue, userSelection);
	}
	public List<IMember> getMembers(int attIndex, int operator, String stdValue, ClassType classType){
		List<? extends IMember> list = null;
		switch(classType){
		case PLAYER:
			list = getPlayers();
			break;
		case HORSE:
			list = getHorses();
			break;
		case RESULT:
			list = getResults();
			break;
		}
		
		Class<?> cls = list.get(0).toArray()[attIndex].getClass();
		if(cls == XMLGregorianCalendar.class){
			stdValue = stdValue.replace("-", "");
			cls = Integer.class;
		}
		return this.getMembers(attIndex, operator, stdValue, list, cls);
	}
	
	public List<IMember> getMembers(String stdAtt, int operator, String stdValue){
		return getMembers(stdAtt, operator, stdValue, userSelection);
	}
	public List<IMember> getMembers(String stdAtt, int operator, String stdValue, ClassType classType){
		List<? extends IMember> list = null;
		switch(classType){
		case PLAYER:
			list = getPlayers();
			break;
		case HORSE:
			list = getHorses();
			break;
		case RESULT:
			list = getResults();
			break;
		}
		
		String[] attSample = list.get(0).getColumnName();
		int attIndex = -1;
		for(int i = 0; i < attSample.length; i++){
			if(stdAtt.equals(attSample[i])){
				attIndex = i;
				break;
			}
		}
		
		

		Class cls = list.get(0).toArray()[attIndex].getClass();
		if(cls == XMLGregorianCalendar.class){
			stdValue = stdValue.replace("-", "");
			cls = Integer.class;
		}
		
		
		return this.getMembers(attIndex, operator, stdValue, list, cls);
	}
	protected List<IMember> getMembers(int attIndex, int operator, String stdValue, List<? extends IMember> list, Class cls){
		List<IMember> result = new ArrayList<IMember>();

		System.out.println(attIndex);
		System.out.println(operator);
		System.out.println(stdValue);
		System.out.println(cls);
		
		if(cls == Integer.class || cls == Double.class){
			for(IMember imbr : list){
				double leftVal = Double.parseDouble(imbr.toStrings()[attIndex]);
				double rightVal = Double.parseDouble(stdValue);
				
				switch(operator){
				case 0:
					if(leftVal > rightVal) result.add(imbr);
					break;
				case 1:
					if(leftVal >= rightVal) result.add(imbr);
					break;
				case 2:
					if(leftVal == rightVal) result.add(imbr);
					break;
				case 3:
					if(leftVal <= rightVal) result.add(imbr);
					break;
				case 4:
					if(leftVal < rightVal) result.add(imbr);
					break;
				case 5:
					if(leftVal != rightVal) result.add(imbr);
					break;
				}
			}
		}
		else{
			for(IMember imbr : list){
				String leftVal = imbr.toStrings()[attIndex];
				String rightVal = stdValue;
				if(operator != 5){
					if(leftVal.contains(rightVal) == true) result.add(imbr);
				}
				else{
					if(leftVal.contains(rightVal) == false) result.add(imbr);
				}
			}
		}
		
		return result;
	}
	public HorseRace getHorseRace(){
		return hr.clone();
	}

	public List<Player> getPlayers() {
		return hr.getPlayers();
	}

	public List<Horse> getHorses() {
		return hr.getHorses();
	}

	public List<Result> getResults(){
		return hr.getResults();
	}
	public List<? extends IMember> getMembers(ClassType type){
		return hr.getMembers(type);
	}

	public ClassType getUserSelection() {
		if(userSelection == null){
			userSelection = DEFAULT_USER_SELECTION;
		}
		return userSelection;
	}

	public void setUserSelection(ClassType userSelection) {
		this.userSelection = userSelection;
	}

	public void clear() {
		hr = new HorseRace().createChilds();
	}
	
	
}