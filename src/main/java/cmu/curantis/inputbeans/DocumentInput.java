package cmu.curantis.inputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DocumentInput {
	private String mainkey;
	private String documentName;
	private String documentUrl;
	private Boolean accessLevel;
	
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
	
}
