package cmu.curantis.outputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CircleOutput {
	private boolean success;
	private long circleId;
	private String message;
	private String subscribedServices;
	
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
	
	public String getSubscribedServices() {
		return this.subscribedServices;
	}
	
	public void setSubscribedServices(String subscribedServices) {
		this.subscribedServices = subscribedServices;
	}
}
