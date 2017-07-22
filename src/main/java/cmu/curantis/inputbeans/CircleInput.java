package cmu.curantis.inputbeans;

public class CircleInput {
	private String email;
	private long circleId;
	private String circleName;
	private String triggerEvent;
	private String natureOfRel;
	private String geoRel;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getCircleId() {
		return circleId;
	}
	public void setCircleId(long circleId) {
		this.circleId = circleId;
	}
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	public String getTriggerEvent() {
		return triggerEvent;
	}
	public void setTriggerEvent(String triggerEvent) {
		this.triggerEvent = triggerEvent;
	}
	public String getNatureOfRel() {
		return natureOfRel;
	}
	public void setNatureOfRel(String natureOfRel) {
		this.natureOfRel = natureOfRel;
	}
	public String getGeoRel() {
		return geoRel;
	}
	public void setGeoRel(String geoRel) {
		this.geoRel = geoRel;
	}
}
