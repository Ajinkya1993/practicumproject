package cmu.curantis.inputbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VendorInput {
	private long circleId;
	private String vendorname;
	private String vendorwebsite;
	private String vendoraddress;
	private String vendoraccountnumber;
	private int month;
	private int day;
	private int replicationfactor;
	private double expenses; 
	private String vendortype;
	private String contactperson;
	private long phoneno;
	private String paymentsource;
	
	public long getCircleId() {
		return circleId;
	}
	public void setCircleId(long circleId) {
		this.circleId = circleId;
	}
	public String getVendorname() {
		return vendorname;
	}
	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}
	public String getVendorwebsite() {
		return vendorwebsite;
	}
	public void setVendorwebsite(String vendorwebsite) {
		this.vendorwebsite = vendorwebsite;
	}
	public String getVendoraddress() {
		return vendoraddress;
	}
	public void setVendoraddress(String vendoraddress) {
		this.vendoraddress = vendoraddress;
	}
	public String getVendoraccountnumber() {
		return vendoraccountnumber;
	}
	public void setVendoraccountnumber(String vendoraccountnumber) {
		this.vendoraccountnumber = vendoraccountnumber;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getReplicationfactor() {
		return replicationfactor;
	}
	public void setReplicationfactor(int replicationFactor) {
		this.replicationfactor = replicationFactor;
	}
	public double getExpenses() {
		return expenses;
	}
	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getVendortype() {
		return vendortype;
	}
	public void setVendortype(String vendortype) {
		this.vendortype = vendortype;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public long getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}
	public String getPaymentsource() {
		return paymentsource;
	}
	public void setPaymentsource(String paymentsource) {
		this.paymentsource = paymentsource;
	}

}
