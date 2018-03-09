package packet;

import java.util.HashMap;
import java.util.Map;

public class PacketProtocol {
	
	private static PacketProtocol instance;
	
	private Map<Integer, PacketCombination> archive;
	
	private PacketProtocol() {
		archive = new HashMap<>();
	}
	
	public static PacketProtocol getInstance() {
		if (instance == null) {
			instance = new PacketProtocol();
		}
		return instance;
	}
	
	public SimplePacket dismantle(byte[] bytes) {
		// header
		PacketHeader header = extractPacketHeader(bytes);
		
		// body
		int length = 10; //헤더를 제외한 데이터 길이(가변이라면....?)
		byte[] body = new byte[length];
		System.arraycopy(bytes, 0, body, 0, length);
		
		SimplePacket newSimplePacket = new SimplePacket();
		newSimplePacket.setHeader(header);
		newSimplePacket.setBody(body);
		
		PacketCombination packetCombination = null;
		try {
			if (archive.containsKey(header.getPacketNumber())) {
				packetCombination = archive.get(header.getPacketNumber());
				packetCombination.add(newSimplePacket);
			} else {
				packetCombination = new PacketCombination(header.getTotalCount());
				packetCombination.add(newSimplePacket);
				archive.put(header.getPacketNumber(), packetCombination);
			}
		} catch (PacketException e) {
			// 패킷 처리중 에러가 발생하면 보관소에서 삭제한다.
			archive.remove(header.getPacketNumber());
			// log......
			return null;
		}
		
		if (packetCombination.isComplete()) {
			archive.remove(header.getPacketNumber());
			// 패킷이 완성되었다면 반환 완성된 패킷을 전달
			return packetCombination.getCompletetSimplePacket();
		} else {
			// 패킷이 완성되지 않았다면 무시
			return null;
		}
	}
	
	private PacketHeader extractPacketHeader(byte[] bytes) {
		PacketHeader newPacketHeader = new PacketHeader();
		// 패킷의 헤더를 분리하여 리턴
		// 헤더 검증이 필요하면 추가
		return newPacketHeader;
	}
}
