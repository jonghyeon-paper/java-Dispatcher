package packet.dispatcher;

/**
 * 패킷 원형
 * @author jonghyeon
 *
 */
public interface ArchetypePacket {
	
	/**
	 * 객체를 바이트배열로 변환
	 */
	byte[] serialize();
	
	/**
	 * 바이트배열을 객체로 변환
	 * @param byteArray
	 */
	void deserialize(byte[] byteArray);
}
