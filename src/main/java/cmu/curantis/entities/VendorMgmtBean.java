/**
 * This Bean creates the table for vendor management (using hibernate)
 */
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
@Table(name="vendor_mgmt")
public class VendorMgmtBean {
	
	@Embeddable
    public static class Identity implements Serializable {

		private static final long serialVersionUID = 1L;
		
		/*
		 * Column where the circle id will be stored 
		 */
        @Column(name = "circleId")
        private long circleId;
        
        /*
         * Column where the document name will be stored (not unique)
         */
        @Column(name = "vendorName")
        private String vendorName;
        
        /*
         * Column where the month will be stored (integer)
         */
        @Column(name = "month")
        private int month;
        
        /*
         * Column where the day will be stored (integer)
         * This is made primary key as there may be multiple payments in a month to a vendor
         */
        @Column(name = "day")
        private int day;
        
        public long getCircleId() {
            return circleId;
        }
        
        public void setCircleId(long cicid) {
        	circleId = cicid;
        }
        
        public String getVendorName() {
            return vendorName;
        }
        
        public void setVendorName(String vendornm) {
        	vendorName = vendornm;
        }
        
        public int getMonth() {
            return month;
        }
        
        public void setMonth(int mth) {
            month = mth;
        }
        
        public int getDay() {
            return day;
        }
        
        public void setDay(int dy) {
            day = dy;
        }
    }
	
	@EmbeddedId
    private Identity identity;
    
	/*
	 * Column to store the vendor website
	 */
    @Column(name = "vendorWebsite")
    private String vendorWebsite;
    
    /*
     * Column to store the vendors account number 
     */
    @Column(name = "vendorAccount")
    private String vendorAccount;
    
    /*
     * Column to store the vendors address
     */
    @Column(name = "vendorAddr")
    private String vendorAddr;
    
    /*
     * Column to store the expense (this would be a monthly payment)
     */
    @Column(name = "expenses")
    private double expenses;
    
    /*
     * Column to store the vendor type
     */
    @Column(name = "vendortype")
    private String vendortype;
    
    /*
     * Column to store the contact person
     */
    @Column(name = "contactperson")
    private String contactperson;
    
    /*
     * Column to store the phone number of vendor
     */
    @Column(name = "phoneno")
    private long phoneno;  
    
    /*
     * Column to store the payment source
     */
    @Column(name = "paymentsource")
    private String paymentsource;
    
    //If notification is to be pushed or not
    private Boolean notification;
    
    //If notification to be pushed then the number of remaining days for the payment due date
    private long remdays;
    
    public Identity getIdentity() {
    	return identity;
    }
    
    public void setIdentity() {
    	identity = new Identity();
    }
    
    public String getVendorWebsite() {
        return vendorWebsite;
    }
    
    public void setVendorWebsite(String vendorweb) {
    	vendorWebsite = vendorweb;
    }
    
    public String getVendorAddr() {
        return vendorAddr;
    }
    
    public void setVendorAddr(String vendorAddr) {
    	this.vendorAddr = vendorAddr;
    }
    
    public String getVendorAccount() {
        return vendorAccount;
    }
    
    public void setVendorAccount(String vendoracc) {
    	vendorAccount = vendoracc;
    }
    
    public double getExpenses() {
        return expenses;
    }
    
    public void setExpenses(double exp) {
    	expenses = exp;
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
	public void setPhoneno(long l) {
		this.phoneno = l;
	}
	public String getPaymentsource() {
		return paymentsource;
	}
	public void setPaymentsource(String paymentsource) {
		this.paymentsource = paymentsource;
	}

	public Boolean getNotification() {
		return notification;
	}

	public void setNotification(Boolean notification) {
		this.notification = notification;
	}

	public long getRemdays() {
		return remdays;
	}

	public void setRemdays(long remdays) {
		this.remdays = remdays;
	}
}
