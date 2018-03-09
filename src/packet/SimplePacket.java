package packet;

public class SimplePacket {
	
	private PacketHeader header;
	private byte[] body;
	
	public SimplePacket() {
	}
	
	public PacketHeader getHeader() {
		return header;
	}
	
	public void setHeader(PacketHeader header) {
		this.header = header;
	}
		
	public byte[] getBody() {
		return body;
	}
	
	public void setBody(byte[] body) {
		this.body = body;
	}
	
	public void putPacketNumber(Integer packetnumber) {
		
	}
}
