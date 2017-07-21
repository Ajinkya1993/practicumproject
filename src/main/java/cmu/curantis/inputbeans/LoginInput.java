package cmu.curantis.inputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginInput {
	private String emial;
	private String password;
	public String getEmial() {
		return emial;
	}
	public void setEmial(String emial) {
		this.emial = emial;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
