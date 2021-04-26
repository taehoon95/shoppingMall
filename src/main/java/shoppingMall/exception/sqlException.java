package shoppingMall.exception;

@SuppressWarnings("serial")
public class sqlException extends RuntimeException { //추가버튼 누른 경우 텍스트필드에 아무것도 없을때 예외 처리 해준다. 

	public sqlException() {
		super("배송중입니다.");
	}
	
	public sqlException(Throwable cause) {
		super("배송중입니다.", cause);
	}

	public sqlException(String string) {
	}
}
