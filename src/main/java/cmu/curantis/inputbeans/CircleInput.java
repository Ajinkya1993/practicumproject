package cmu.curantis.inputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CircleInput {
    private String email;
	private String emailToAdd;
	private long circleId;
	private String circleName;
	private String triggerEvent;
	private String natureOfRel;
	private String geoRel;
	private String services;
	
	public String getEmail() {
	    return email;
	}
	public void setEmail(String email) {
	    this.email = email;
	}
	public String getEmailToAdd() {
		return emailToAdd;
	}
	public void setEmailToAdd(String emailToAdd) {
		this.emailToAdd = emailToAdd;
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
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
}
