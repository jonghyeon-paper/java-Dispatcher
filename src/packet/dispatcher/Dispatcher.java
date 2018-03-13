package packet.dispatcher;

import packet.SimplePacket;

/**
 * 약식 패킷을 전당받아 패킷정보를 확인한다.
 * 패킷의 데이터를 오브젝트화 한다.
 * 데이터 오브젝트를 어떻게 사용할 지 결정한다.
 * @author jonghyeon
 *
 */
public class Dispatcher {
	
	private static Dispatcher instance;
	
	public static Dispatcher getInstance() {
		if (instance == null) {
			instance = new Dispatcher();
		}
		return instance;
	}
	
	/**
	 * 약식패킷을 패킷 객체로 반환한다.
	 * @param simplePacket
	 * @throws DispatcherException
	 */
	public ArchetypePacket convert(SimplePacket simplePacket) throws DispatcherException {
		String packetNumber = String.valueOf(simplePacket.getHeader().getPacketNumber());
		
		// 리플렉션을 사용해 객체를 생성하고 있지만, 그냥 패킷 번호를 비교하는 로직을 사용해도 된다.
		Class<?> target = null;
		try {
			target = Class.forName("packet.sample.Packet" + packetNumber);
		} catch (ClassNotFoundException e) {
			throw new DispatcherException("패킷객체가 없습니다.");
		}
		if (target == null) {
			throw new DispatcherException("패킷객체가 없습니다.");
		}
		
		ArchetypePacket newPacket = null;
		try {
			newPacket = (ArchetypePacket) target.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new DispatcherException("패킷을 생성할 수 없습니다.");
		}
		newPacket.deserialize(simplePacket.getBody());
		
		// TODO 변환된 패킷을 필요한 곳에 사용한다(내부사용, 인스턴스 호출, 쓰레드 실행, 기타등등)
		return newPacket;
	}
}
