package cmu.curantis.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="caregiver_circle_info")
public class UserBean {

    @Embeddable
    public class Identity implements Serializable {

		private static final long serialVersionUID = 1L;

        @Column(name = "circle_id")
        private long circleid;

        @Column(name = "email")
        private String email;
        
        //getter, setter methods
        public long getCircleID() {
            return circleid;
        }
        
        public void setCircleID(long cicid) {
            circleid = cicid;
        }
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String emailadd) {
            email = emailadd;
        }
    }
    
    @EmbeddedId
    private Identity primid;
    
    @Column(name = "circle_name")
    private String circleName;
    
    @Column(name = "primary_caregiver")
    private boolean primaryCaregiver;
    
    @Column(name = "relationship_nature")
    private String relationshipNature;
    
    @Column(name = "georelationship")
    private String georelationship;
    
    @Column(name = "trigger_event")
    private String triggerEvent;
    
    @Column(name = "join_status")
    private Boolean joinStatus;
    
    public Identity getIdentity() {
    	return primid;
    }
    
    public String getCirclename() {
        return circleName;
    }
    
    public void setCirclename(String circlenm) {
        circleName = circlenm;
    }
    
    public boolean getPrimaryCaregiver() {
        return primaryCaregiver;
    }
    
    public void setPrimaryCaregiver(boolean primarycg) {
        primaryCaregiver = primarycg;
    }
    public String getRelationshipNature() {
        return relationshipNature;
    }
    
    public void setRelationshipNature(String relnat) {
        relationshipNature = relnat;
    }
    
    public String getGeorelationship() {
        return georelationship;
    }
    
    public void setGeorelationship(String georel) {
        georelationship = georel;
    }
    
    public String getTriggerEvent() {
        return triggerEvent;
    }
    
    public void setTriggerEvent(String triggereve) {
        triggerEvent = triggereve;
    }
    
    public Boolean getJoinStatus() {
        return joinStatus;
    }
    
    public void setJoinStatus(Boolean joinstat) {
        joinStatus = joinstat;
    }
}