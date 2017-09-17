package hr.manager;

public class InvalidPasswordException extends Exception{
	private String message = null;
	public InvalidPasswordException(){
		this.message = "비밀 번호가 틀렸습니다.";
	}
	public String getMessage(){
		return message;
	}
}
