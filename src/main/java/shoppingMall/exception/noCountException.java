package shoppingMall.exception;

@SuppressWarnings("serial")
public class noCountException extends RuntimeException { //추가버튼 누른 경우 텍스트필드에 아무것도 없을때 예외 처리 해준다. 

	public noCountException() {
		super("1이상 입력해주세요.");
	}
	
	public noCountException(Throwable cause) {
		super("1이상 입력해주세요.", cause);
	}

	public noCountException(String string) {
	}
}
