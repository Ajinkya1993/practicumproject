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

import cmu.curantis.entities.CaregiverCircleBean.Identity;


@Entity
@Table(name="document_mgmt")
public class DocumentMgmtBean {
	
	@Embeddable
    public static class Identity implements Serializable {

		private static final long serialVersionUID = 1L;
		
		/*
		 * Column where the circleid, email and serviceId will be appended
		 * separator used will be '>'
		 */
        @Column(name = "mainkey")
        private String mainkey;
        
        /*
         * Column where the document name will be stored (not unique)
         */
        @Column(name = "documentName")
        private String documentName;
                
        public String getDocumentName() {
            return documentName;
        }
        
        public void setDocumentName(String docname) {
            documentName = docname;
        }
        
        public String getMainkey() {
            return mainkey;
        }
        
        public void setMainkey(String mkey) {
            mainkey = mkey;
        }
    }
	
	@EmbeddedId
    private Identity identity;
    
	/*
	 * Column to store the S3 Url to the document
	 */
    @Column(name = "documentUrl")
    private String documentUrl;
    
    /*
     * Column to store the permission level of this document for this caregiver
     * False -> viewonly - you can see the doc and download it
     * True - all permissions - apart from the viewonly perms you can delete or share the doc with others 
     */
    @Column(name = "accessLevel")
    private Boolean accessLevel;
    
    public Identity getIdentity() {
    	return identity;
    }
    
    public void setIdentity() {
    	identity = new Identity();
    }
    
    public Boolean getAccessLevel() {
        return accessLevel;
    }
    
    public void setAccessLevel(Boolean alevel) {
        accessLevel = alevel;
    }
    
    public String getDocumentUrl() {
        return documentUrl;
    }
    
    public void setDocumentUrl(String url) {
        documentUrl = url;
    }

}
