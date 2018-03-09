package packet;

public class PacketException extends Exception {
	
	private static final long serialVersionUID = 568700325085625838L;

	public PacketException() {
	}
	
	public PacketException(String message) {
		super(message);
	}
	
	public PacketException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
