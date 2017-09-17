package hr.web;

public class TextBox implements IFieldControl{
	
	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_ID = 1;
	public static final int TYPE_PW = 2;
	public static final int TYPE_HIDDEN = 4;
	
	private String id = null;
	private String name = null;
	private String value = null;
	private int type;
	private String cssClass;
	
	public TextBox(int type, String id, String cssClass){
		this.type = type;
		this.id = ((type != TYPE_HIDDEN)? "txt" : "hdn") + id;
		this.cssClass = cssClass;
	}
	
	@Override
	public String getControl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValue() {
		// TODO Auto-generated method stub
		
	}
	
}
