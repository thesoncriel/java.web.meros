package hr.manager;

public class AdminOnlyException extends Exception{
	private String message = null;
	public AdminOnlyException(){
		this.message = "관리자 전용 입니다.";
	}
	public String getMessage(){
		return message;
	}
}
