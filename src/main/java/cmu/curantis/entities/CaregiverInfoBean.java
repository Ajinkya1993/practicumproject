package cmu.curantis.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="caregiver_information")
public class CaregiverInfoBean {
    
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "middle_name")
    private String middleName;
    
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;
    
    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "registered")
    private Boolean registerStatus;

    //getter, setter methods

    public String getEmail() {
            return email;
    }
        
    public void setEmail(String emailadd) {
            email = emailadd;
    }
     
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstnm) {
        firstName = firstnm;
    }

    public String getMiddleName() {
        return middleName;
    }
    
    public void setMiddleName(String middlenm) {
        middleName = middlenm;
    }

    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastnm) {
        lastName = lastnm;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String pswd) {
        password = pswd;
    }

    public String getAddress() {
        return address;
    }
    
    public void setAddress(String add) {
        address = add;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phonenm) {
        phoneNumber = phonenm;
    }

    
    public Boolean getRegisteredStatus() {
        return registerStatus;
    }
    
    public void setRegisteredStatus(Boolean registerstat) {
        registerStatus = registerstat;
    }
}
