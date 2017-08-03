package cmu.curantis.inputbeans;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;

@XmlRootElement
public class ServicesInput {
	private long circleId;
	private String services;
	public long getCircleId() {
		return circleId;
	}
	public void setCircleId(long circleId) {
		this.circleId = circleId;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
}
