package packet;

public class PacketHeader {
	
	private Integer packetNumber;
	private Integer totalCount;
	private Integer sequencyNumber;
	// etc property
	
	public Integer getPacketNumber() {
		return packetNumber;
	}
	public void setPacketNumber(Integer packetNumber) {
		this.packetNumber = packetNumber;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getSequencyNumber() {
		return sequencyNumber;
	}
	public void setSequencyNumber(Integer sequencyNumber) {
		this.sequencyNumber = sequencyNumber;
	}
}
