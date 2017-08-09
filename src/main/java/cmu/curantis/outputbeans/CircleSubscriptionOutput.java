package cmu.curantis.outputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CircleSubscriptionOutput {
	private boolean success;
	private long circleId;
	private String message;
	private String cardmemberFirstName;
	private String cardmemberMiddleName;
	private String cardMemberLastName;
	private String cardNo;
	private String expirationDate;
	private String cvvNo;
	private String cardType;

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public long getCircleId() {
		return circleId;
	}
	public void setCircleId(long circleId) {
		this.circleId = circleId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCardmemberFirstName() {
		return cardmemberFirstName;
	}
	public void setCardmemberFirstName(String cardmemberFirstName) {
		this.cardmemberFirstName = cardmemberFirstName;
	}
	public String getCardmemberMiddleName() {
		return cardmemberMiddleName;
	}
	public void setCardmemberMiddleName(String cardmemberMiddleName) {
		this.cardmemberMiddleName = cardmemberMiddleName;
	}
	public String getCardMemberLastName() {
		return cardMemberLastName;
	}
	public void setCardMemberLastName(String cardMemberLastName) {
		this.cardMemberLastName = cardMemberLastName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}
