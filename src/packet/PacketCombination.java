package packet;

import java.util.Map;

/**
 * 전달받은 패킷을 완성시키는 클래스
 * @author jonghyeon
 *
 */
public class PacketCombination {
	
	private int totalCount;
	private Map<Integer, SimplePacket> simplePacketMap;
	
	public PacketCombination(int totalCount) {
		this.totalCount = totalCount;
	}
	
	/**
	 * 패킷을 추가한다.
	 * @param simplePacket
	 * @throws PacketException
	 */
	public void add(SimplePacket simplePacket) throws PacketException {
		if (simplePacketMap.containsKey(simplePacket.getHeader().getSequencyNumber())) {
			// 같은 패킷순서가 이미 존재하면
			throw new PacketException("패킷정보 에러");
		}
		// 같은 패킷순서가 존재하지 않으면 추가
		simplePacketMap.put(simplePacket.getHeader().getSequencyNumber(), simplePacket);
	}
	
	/**
	 * 완성된 패킷을 반환한다.
	 * @return
	 */
	public SimplePacket getCompletedSimplePacket() {
		if (simplePacketMap.size() == 1) {
			return simplePacketMap.get("1");
		} else {
			SimplePacket newSimplePacket = new SimplePacket();
			for (int i = 1; i <= simplePacketMap.size(); i++) {
				SimplePacket item = simplePacketMap.get(i);
				newSimplePacket.setHeader(item.getHeader());
				
				// body 값을 결합
				if (newSimplePacket.getBody() == null) {
					newSimplePacket.setBody(item.getBody());
				} else {
					byte[] front = newSimplePacket.getBody();
					byte[] rear = item.getBody();
					byte[] combined = new byte[front.length + rear.length];
					System.arraycopy(front, 0, combined, 0, front.length);
					System.arraycopy(rear, 0, combined, front.length, rear.length);
					newSimplePacket.setBody(combined);
				}
			}
			return newSimplePacket;
		}
	}
	
	/**
	 * 패킷이 완성되었는지 확인한다.
	 * @return
	 */
	public boolean isComplete() {
		if (totalCount == simplePacketMap.size()) {
			return true;
		} else {
			return false;
		}
	}
}
