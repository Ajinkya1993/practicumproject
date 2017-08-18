package cmu.curantis.outputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserOfCircle {
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String natureOfRel;
    private boolean primaryCaregiver;
    public String getNatureOfRel() {
        return natureOfRel;
    }
    public void setNatureOfRel(String natureOfRel) {
        this.natureOfRel = natureOfRel;
    }
    public boolean isPrimaryCaregiver() {
        return primaryCaregiver;
    }
    public void setPrimaryCaregiver(boolean primaryCaregiver) {
        this.primaryCaregiver = primaryCaregiver;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
		return phoneNumber;	
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;	
	}
}
