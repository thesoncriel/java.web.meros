package hr.manager;

public class NonexsistentIDException {
	private String message = null;
	public NonexsistentIDException(){
		message = "존재하지 않는 아이디 입니다.";
	}
	public String getMessage(){
		return message;
	}
}
