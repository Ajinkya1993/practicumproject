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
    private String lovedoneaddr;
    private String lovedoneURL;
    private String lovedoneFname;
    private String lovedoneLname;
	
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
	public String getLovedoneFname() {
		return lovedoneFname;
	}

	public void setLovedoneFname(String lovedoneFname) {
		this.lovedoneFname = lovedoneFname;
	}
	
	public String getLovedoneLname() {
		return lovedoneLname;
	}

	public void setLovedoneLname(String lovedoneLname) {
		this.lovedoneLname = lovedoneLname;
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
    
    public String getLovedAddress() {
        return lovedoneaddr;
    }
    
    public void setLovedAddress(String addr) {
    	lovedoneaddr = addr;
    }
    
    //Document URL
    public String getLovedURL() {
        return lovedoneURL;
    }
    
    public void setLovedURL(String lvdurl) {
    	lovedoneURL = lvdurl;
    }
}
