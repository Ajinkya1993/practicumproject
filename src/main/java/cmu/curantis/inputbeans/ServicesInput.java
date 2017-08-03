package cmu.curantis.inputbeans;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;

@XmlRootElement
public class ServicesInput {
	private long circleId;
	private JSONArray services;
	public long getCircleId() {
		return circleId;
	}
	public void setCircleId(long circleId) {
		this.circleId = circleId;
	}
	public JSONArray getServices() {
		return services;
	}
	public void setServices(JSONArray services) {
		this.services = services;
	}
}
