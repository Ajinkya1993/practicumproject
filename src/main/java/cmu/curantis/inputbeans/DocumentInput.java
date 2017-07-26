package cmu.curantis.inputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DocumentInput {
	private String mainkey;
	private String documentName;
	private String documentUrl;
	private Boolean accessLevel;
	private String email;
	private Long circleId;
	private int service;
	
	public String getMainkey() {
		return mainkey;
	}
	
	public void setMainkey(String mainkey) {
		this.mainkey = mainkey;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	public Boolean getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(Boolean accessLevel) {
		this.accessLevel = accessLevel;
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
	
}
