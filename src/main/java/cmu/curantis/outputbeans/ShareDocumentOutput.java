package cmu.curantis.outputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShareDocumentOutput {
	private boolean success;
	private String message;
	
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
