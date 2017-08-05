package cmu.curantis.inputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LovedOneInput {
    private String email;
	private long circleId;
	private String circleName;
	private String triggerEvent;
	private String natureOfRel;
	private String geoRel;
	private String services;
	private String lovedoneAddress;
	private String pictureUrl;
	private String lovedone_firstName;
	private String lovedone_LastName;
	
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
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getLovedoneAddress() {
		return lovedoneAddress;
	}

	public void setLovedoneAddress(String lovedoneAddress) {
		this.lovedoneAddress = lovedoneAddress;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getLovedone_firstName() {
		return lovedone_firstName;
	}

	public void setLovedone_firstName(String lovedone_firstName) {
		this.lovedone_firstName = lovedone_firstName;
	}

	public String getLovedone_LastName() {
		return lovedone_LastName;
	}

	public void setLovedone_LastName(String lovedone_LastName) {
		this.lovedone_LastName = lovedone_LastName;
	}
	
}
