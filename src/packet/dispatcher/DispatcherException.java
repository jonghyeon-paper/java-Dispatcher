package packet.dispatcher;

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
