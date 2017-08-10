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
}
