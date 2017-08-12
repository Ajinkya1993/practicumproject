package cmu.curantis.inputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeniorHousingCuratedInput {
	private String  name;
	private String  address;
	private String  qualityTier;
	private String  city;
	private String  state;
	private String  zip;
	private String  phoneNumber;
	private Integer certifiedBeds;
	private Integer residentsNumber;
	private String  type;
	private String  legalName;
	private String  firstApprovedDate;
	private Boolean independentCare;
	private Boolean unskilledCare;
	private Boolean skilledCare;
	private Boolean memoryCare;
	private Integer overallRating;
	private Integer healthInspectionRating;
	private Integer rnStaffingRating;
	private Integer price;
	private Integer maxPrice;
	private Integer minPrice;
	
	public Integer getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Integer getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

}
