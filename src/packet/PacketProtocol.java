package packet;

import java.util.HashMap;
import java.util.Map;

/**
 * 패킷 규약
 * @author jonghyeon
 *
 */
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
	
	/**
	 * 바이트정보를 해체하여 약식패킷을 만든다.
	 * @param bytes
	 * @return
	 */
	public SimplePacket dismantle(byte[] bytes) {
		/*
		 * 전체 패킷 크기를 100바이트로 가정한다.
		 * 패킷헤더를 20바이트로 가정한다.
		 * 패킷바디를 80바이트로 가정한다.
		 */
		
		// header
		PacketHeader header = extractPacketHeader(bytes);
		
		// body
		int length = 80; //헤더를 제외한 데이터 길이(가변이라면....?)
		byte[] body = new byte[length];
		System.arraycopy(bytes, 20, body, 0, length);
		
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
			return packetCombination.getCompletedSimplePacket();
		} else {
			// 패킷이 완성되지 않았다면 무시
			return null;
		}
	}
	
	/**
	 * 패킷의 헤더정보를 추출한다.
	 * @param bytes
	 * @return
	 */
	private PacketHeader extractPacketHeader(byte[] bytes) {
		// 패킷의 헤더를 분리하여 리턴
		PacketHeader newPacketHeader = new PacketHeader();
		// TODO 바이트정보를 디코드하여 헤더정보를 분리한다.
//		newPacketHeader.setPacketNumber(x);
//		newPacketHeader.setSequencyNumber(x);
//		newPacketHeader.setTotalCount(x);
		
		// if TODO 헤더 검증이 필요하면 추가한다
		return newPacketHeader;
	}
}
