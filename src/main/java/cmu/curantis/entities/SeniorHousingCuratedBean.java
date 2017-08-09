package cmu.curantis.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="curated_senior_housing")
public class SeniorHousingCuratedBean {
	
	@Embeddable
	public static class Identity implements Serializable {
	
		private static final long serialVersionUID = 1L;
		
		@Column(name = "provider_name")
	    private String name;
	
	    @Column(name = "provider_address")
	    private String address;
	    
	    @Column(name = "quality_tier")
	    private String qualityTier;
	    
	    @Column(name = "city")
	    private String city;
	
	    @Column(name = "state")
	    private String state;
	    
	    @Column(name = "zip")
	    private String zip;
	
	    @Column(name = "phone_number")
	    private String phoneNumber;
	    
	    @Column(name = "number_of_certified_beds")
	    private Integer certifiedBeds;
	    
	    @Column(name = "number_of_residents")
	    private Integer residentsNumber;
	
	    @Column(name = "provider_type")
	    private String type;
	    
	    @Column(name = "legal_business_name")
	    private String legalName;
	    
	    @Column(name = "first_approved_date")
	    private String firstApprovedDate;
	
	    @Column(name = "independent_care")
	    private Boolean independentCare;
	    
	    @Column(name = "unskilled_care")
	    private Boolean unskilledCare;
	    
	    @Column(name = "skilled_nursing_care")
	    private Boolean skilledCare;
	    
	    @Column(name = "memory_care")
	    private Boolean memoryCare;
	    
	    @Column(name = "overall_rating")
	    private Integer overallRating;
	
	    @Column(name = "health_inspection_rating")
	    private Integer healthInspectionRating;
	    
	    @Column(name = "rn_staffing_rating")
	    private Integer rnStaffingRating;
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public String getAddress() {
			return address;
		}
	
		public void setAddress(String address) {
			this.address = address;
		}
	
		public String getQualityTier() {
			return qualityTier;
		}
	
		public void setQualityTier(String qualityTier) {
			this.qualityTier = qualityTier;
		}
	
		public String getCity() {
			return city;
		}
	
		public void setCity(String city) {
			this.city = city;
		}
	
		public String getState() {
			return state;
		}
	
		public void setState(String state) {
			this.state = state;
		}
	
		public String getZip() {
			return zip;
		}
	
		public void setZip(String zip) {
			this.zip = zip;
		}
	
		public String getPhoneNumber() {
			return phoneNumber;
		}
	
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
	
		public Integer getCertifiedBeds() {
			return certifiedBeds;
		}
	
		public void setCertifiedBeds(Integer certifiedBeds) {
			this.certifiedBeds = certifiedBeds;
		}
	
		public Integer getResidentsNumber() {
			return residentsNumber;
		}
	
		public void setResidentsNumber(Integer residentsNumber) {
			this.residentsNumber = residentsNumber;
		}
	
		public String getType() {
			return type;
		}
	
		public void setType(String type) {
			this.type = type;
		}
	
		public String getLegalName() {
			return legalName;
		}
	
		public void setLegalName(String legalName) {
			this.legalName = legalName;
		}
	
		public String getFirstApprovedDate() {
			return firstApprovedDate;
		}
	
		public void setFirstApprovedDate(String firstApprovedDate) {
			this.firstApprovedDate = firstApprovedDate;
		}
	
		public Boolean getIndependentCare() {
			return independentCare;
		}
	
		public void setIndependentCare(Boolean independentCare) {
			this.independentCare = independentCare;
		}
	
		public Boolean getUnskilledCare() {
			return unskilledCare;
		}
	
		public void setUnskilledCare(Boolean unskilledCare) {
			this.unskilledCare = unskilledCare;
		}
	
		public Boolean getSkilledCare() {
			return skilledCare;
		}
	
		public void setSkilledCare(Boolean skilledCare) {
			this.skilledCare = skilledCare;
		}
	
		public Boolean getMemoryCare() {
			return memoryCare;
		}
	
		public void setMemoryCare(Boolean memoryCare) {
			this.memoryCare = memoryCare;
		}
	
		public Integer getOverallRating() {
			return overallRating;
		}
	
		public void setOverallRating(Integer overallRating) {
			this.overallRating = overallRating;
		}
	
		public Integer getHealthInspectionRating() {
			return healthInspectionRating;
		}
	
		public void setHealthInspectionRating(Integer healthInspectionRating) {
			this.healthInspectionRating = healthInspectionRating;
		}
	
		public Integer getRnStaffingRating() {
			return rnStaffingRating;
		}
	
		public void setRnStaffingRating(Integer rnStaffingRating) {
			this.rnStaffingRating = rnStaffingRating;
		} 
		
	}
	
	@EmbeddedId
    private Identity primid;
	
	public Identity getIdentity() {
    	return primid;
    }
    
    public void setIdentity() {
    	primid = new Identity();
    }
}