package cmu.curantis.outputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LovedOneOutput {
	private boolean success;
	private long circleId;
	private String message;
	private String subscribedServices;
    private String circleName;
    private boolean primaryCaregiver;
    private String relationshipNature;
    private String georelationship;
    private String triggerEvent;
    private String lovedoneAddress;
    private String pictureUrl;
    private String lovedone_firstName;
    private String lovedone_LastName;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public long getCircleId() {
		return circleId;
	}
	public void setCircleId(long circleId) {
		this.circleId = circleId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public String getSubscribedServices() {
		return this.subscribedServices;
	}
	
	public void setSubscribedServices(String subscribedServices) {
		this.subscribedServices = subscribedServices;
	}
	public String getCirclename() {
		return circleName;
    }
    
    public void setCirclename(String circlenm) {
        circleName = circlenm;
    }
    
    public boolean getPrimaryCaregiver() {
        return primaryCaregiver;
    }
    
    public void setPrimaryCaregiver(boolean primarycg) {
        primaryCaregiver = primarycg;
    }
    public String getRelationshipNature() {
        return relationshipNature;
    }
    
    public void setRelationshipNature(String relnat) {
        relationshipNature = relnat;
    }
    
    public String getGeorelationship() {
        return georelationship;
    }
    
    public void setGeorelationship(String georel) {
        georelationship = georel;
    }
    
    public String getTriggerEvent() {
        return triggerEvent;
    }
    
    public void setTriggerEvent(String triggereve) {
        triggerEvent = triggereve;
    }
    
	public String getLovedoneAddress() {
		return lovedoneAddress;
	}

	public void setLovedoneAddress(String lovedoneAddress) {
		this.lovedoneAddress = lovedoneAddress;
	}
    
    //Document URL
    public String getPictureUrl() {
        return pictureUrl;
    }
    
    public void setPictureUrl(String lvdurl) {
    	pictureUrl = lvdurl;
    }
}
