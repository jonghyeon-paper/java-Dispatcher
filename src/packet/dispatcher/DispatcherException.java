package packet.dispatcher;

/**
 * 디스패처 프로세스중 사용할 예외 클래스
 * @author jonghyeon
 *
 */
public class DispatcherException extends Exception {
	
	private static final long serialVersionUID = 568700325085625838L;

	public DispatcherException() {
	}
	
	public DispatcherException(String message) {
		super(message);
	}
	
	public DispatcherException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
