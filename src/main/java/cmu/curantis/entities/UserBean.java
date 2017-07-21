package cmu.curantis.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="caregiver_information_table")
public class UserBean {

	@Embeddable
	public class Identity implements Serializable {
		
	    @Column(name = "circle_id")
	    private long circle_id;

	    @Column(name = "email")
	    private String email;

	    //getter, setter methods
	    public long getCircleID() {
	    	return circle_id;
	    }
	    
	    public String getEmail() {
	    	return email;
	    }
	    
	    public void setEmail(String emailadd) {
	    	email = emailadd;
	    }
	}
	
	@EmbeddedId
    private Identity prim_id;
	
	@Column(name = "circle_name")
    private String circlename;
	
	@Column(name = "primary_caregiver")
    private boolean primarycaregiver;
    
	@Column(name = "relationship_nature")
    private String relationship_nature;
    
	@Column(name = "georelationship")
    private String georelationship;
    
	@Column(name = "trigger_event")
    private String triggerevent;
    
	@Column(name = "join_status")
    private Boolean join_status;
     
    public String getCirclename() {
    	return circlename;
    }
    
    public void setCirclename(String circlenm) {
    	circlename = circlenm;
    }
    
    public boolean getPrimarycaregiver() {
    	return primarycaregiver;
    }
    
    public void setPrimarycaregiver(boolean primarycg) {
    	primarycaregiver = primarycg;
    }
    public String getRelationshipNature() {
    	return relationship_nature;
    }
    
    public void setRelationshipNature(String relnat) {
    	relationship_nature = relnat;
    }
    
    public String getGeorelationship() {
    	return georelationship;
    }
    
    public void setGeorelationship(String georel) {
    	georelationship = georel;
    }
    
    public String getTriggerevent() {
    	return triggerevent;
    }
    
    public void setTriggerevent(String triggereve) {
    	triggerevent = triggereve;
    }
    
    public Boolean getJoinstatus() {
    	return join_status;
    }
    
    public void setJoinstatus(Boolean joinstat) {
    	join_status = joinstat;
    }
}