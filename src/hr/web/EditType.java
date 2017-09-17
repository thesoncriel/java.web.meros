package hr.web;

public enum EditType {
	TEXT_ID(1),
	TEXT_PW(2),
	TEXT(4),
	INTEGER(8),
	FLOAT(16),
	BOOLEAN(32),
	ENUM(64),
	MULTI_SELECTION(128),
	DATE(256),
	IMAGE(512);
	
	private final int value;
    EditType(int v) {
        value = v;
    }
    public int value() {
        return value;
    }
}
