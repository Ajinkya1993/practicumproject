package cmu.curantis.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="circle_subscription")
public class CircleSubsBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "circle_id")
	private long circleId;

	@Column(name = "circle_name")
	private String circleName;
	
	
	/**
	 * just going to be a string
	 * Each service will be separated from the other by the separator '->'
	 */
	@Column(name = "services_subscribed")
	private String servicesSubscribed;
	
	/*
	 * Column to store the S3 Url to the lovedOne's profile picture
	 */
	@Column(name = "lovedone_firstName")
    private String lovedone_firstName;
	
	@Column(name = "lovedone_LastName")
    private String lovedone_LastName;
	
    @Column(name = "lovedone_pictureUrl")
    private String pictureUrl;

	@Column(name = "lovedone_address")
	private String lovedoneAddress;
	
	@Column(name = "lovedone_fname")
    private String lovedoneFname;
	
	@Column(name = "lovedone_lname")
    private String lovedoneLname;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "expiration_date")
	private String expirationDate;
	
	@Column(name = "cvv_no")
	private String cvvNo;
	
	@Column(name = "card_member_first_name")
	private String cardMemberFirstName;
	
	@Column(name = "card_member_middle_name")
	private String cardMemberMiddleName;
	
	@Column(name = "card_member_last_name")
	private String cardMemberLastName;
	
	@Column(name = "card_type")
	private String cardType;

	public long getCircleId() {
		return circleId;
	}

	public void setCircleId(long circleId) {
		this.circleId = circleId;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	@Column(name = "services_subscribed", columnDefinition="TEXT")
	public String getServicesSubscribed() {
		return servicesSubscribed;
	}

	public void setServicesSubscribed(String servicesSubscribed) {
		this.servicesSubscribed = servicesSubscribed;
	}

	public String getLovedoneFname() {
		return lovedoneFname;
	}

	public void setLovedoneFname(String lovedoneFname) {
		this.lovedoneFname = lovedoneFname;
	}
	
	public String getLovedoneLname() {
		return lovedoneLname;
	}

	public void setLovedoneLname(String lovedoneLname) {
		this.lovedoneLname = lovedoneLname;
	}
	
	public String getLovedoneAddress() {
		return lovedoneAddress;
	}

	public void setLovedoneAddress(String lovedoneAddress) {
		this.lovedoneAddress = lovedoneAddress;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCvvNo() {
		return cvvNo;
	}

	public void setCvvNo(String cvvNo) {
		this.cvvNo = cvvNo;
	}

	public String getCardMemberFirstName() {
		return cardMemberFirstName;
	}

	public void setCardMemberFirstName(String cardMemberFirstName) {
		this.cardMemberFirstName = cardMemberFirstName;
	}

	public String getCardMemberMiddleName() {
		return cardMemberMiddleName;
	}

	public void setCardMemberMiddleName(String cardMemberMiddleName) {
		this.cardMemberMiddleName = cardMemberMiddleName;
	}

	public String getCardMemberLastName() {
		return cardMemberLastName;
	}

	public void setCardMemberLastName(String cardMemberLastName) {
		this.cardMemberLastName = cardMemberLastName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getLovedone_firstName() {
		return lovedone_firstName;
	}

	public void setLovedone_firstName(String lovedone_firstName) {
		this.lovedone_firstName = lovedone_firstName;
	}

	public String getLovedone_LastName() {
		return lovedone_LastName;
	}

	public void setLovedone_LastName(String lovedone_LastName) {
		this.lovedone_LastName = lovedone_LastName;
	}
}