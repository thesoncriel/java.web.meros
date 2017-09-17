package hr.member;


public enum ClassType{
	USER(0),
	PLAYER(1),
	HORSE(2),
	RESULT(3),
	TODAY_HORSE(4);
	
	private final int value;
    ClassType(int v) {
        value = v;
    }
    public int value() {
        return value;
    }
    public static ClassType parse(int value){
    	switch(value){
    	case 0:
    		return USER;
    	case 1:
    		return PLAYER;
    	case 2:
    		return HORSE;
    	case 3:
    		return RESULT;
    	case 4:
    		return TODAY_HORSE;
    	default:
    		return null;
    	}
    }
}