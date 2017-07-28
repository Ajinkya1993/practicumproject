package cmu.curantis.inputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShareDocumentInput {
	private String mainkey;
	private String email;
	private Long circleId;
	private int service;
	private String targetEmail;
	private String documentName;
	private Boolean accessLevel;
	private String documentUrl;
	
	public String getMainkey() {
		return mainkey;
	}
	public void setMainkey(String mainkey) {
		this.mainkey = mainkey;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getCircleId() {
		return circleId;
	}
	public void setCircleId(Long circleId) {
		this.circleId = circleId;
	}
	public int getService() {
		return service;
	}
	public void setService(int service) {
		this.service = service;
	}
	public String getTargetEmail() {
		return targetEmail;
	}
	public void setTargetEmail(String targetEmail) {
		this.targetEmail = targetEmail;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public Boolean getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(Boolean accessLevel) {
		this.accessLevel = accessLevel;
	}
	public String getDocumentUrl() {
		return documentUrl;
	}
	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}
}
