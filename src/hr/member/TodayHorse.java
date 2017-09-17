package hr.member;

public class TodayHorse implements IMember{
	String id = null;
	String name = null;
	String nick = null;
	double winRate;
	double betRate;
	String pic = null;
	@Override
	public ClassType getClassType() {
		return ClassType.TODAY_HORSE;
	}

	@Override
	public String[] getColumnName() {
		String[] str = {"아이디", "이름", "별명", "승률", "베팅률", "사진"};
		return str;
	}

	@Override
	public String[] getFieldName() {
		String[] str = {"id", "name", "nick", "winRate", "betRate", "pic"};
		return str;
	}
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public double getBetRate() {
		return betRate;
	}

	public void setBetRate(double betRate) {
		this.betRate = betRate;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Override
	public void setData(String... str) {
		id = str[0];
		name = str[1];
		nick = str[2];
		winRate = Double.parseDouble(str[3]);
		betRate = Double.parseDouble(str[4]);
		pic = str[5];
	}

	@Override
	public Object[] toArray() {
		return toStrings();
	}

	@Override
	public String[] toStrings() {
		String[] str = {id, name, nick, winRate+"", betRate+"", pic};
		return str;
	}
	
	public IMember clone(){
		try {
			return (IMember)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
			//e.printStackTrace();
		}
	}
}
