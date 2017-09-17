package hr.web;

public class FieldControl {
	private static final EditType[] EDIT_USER = {EditType.TEXT_ID, 
												EditType.TEXT_PW, 
												EditType.TEXT, 
												EditType.TEXT, 
												EditType.TEXT, 
												EditType.ENUM};
	private static final EditType[] EDIT_PLAYER = {EditType.TEXT_ID, 
												EditType.TEXT, 
												EditType.TEXT, 
												EditType.BOOLEAN, 
												EditType.TEXT,
												EditType.TEXT, 
												EditType.IMAGE,
												EditType.TEXT};
	private static final EditType[] EDIT_HORSE = {EditType.TEXT_ID, 
												EditType.TEXT, 
												EditType.TEXT, 
												EditType.BOOLEAN, 
												EditType.TEXT,
												EditType.TEXT, 
												EditType.IMAGE,
												EditType.TEXT,
												EditType.ENUM};
	private static final EditType[] EDIT_RESULT = {EditType.TEXT_ID, 
												EditType.DATE, 
												EditType.TEXT, 
												EditType.TEXT};

	public static String[] getControl(ClassType type){
		
	}
	protected String getTextBox(EditType type, String id){
		String temp = "<input type=\"?t\" id=\"?i\" name=\"?n\" class=\"?c\"/>";
	}
	protected String getInputType(EditType type){
		switch(type){
		case TEXT:
		case TEXT_ID:
			return "text";
		case 
		}
	}
}
