package cmu.curantis.outputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DocumentPeople {
	private String email;
	private boolean accessLevel;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(boolean accessLevel) {
		this.accessLevel = accessLevel;
	}
}
