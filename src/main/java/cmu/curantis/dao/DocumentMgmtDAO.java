package cmu.curantis.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;

import cmu.curantis.entities.DocumentMgmtBean;

public class DocumentMgmtDAO {
	
	/*
	 * Method to add a document to the table
	 * returns true on successful addition. Returns false on error.
	 * by default the access level is set to false (viewonly) if null
	 */
	public Boolean create(Session session, DocumentMgmtBean dmb) {
		
		String mainkey = dmb.getIdentity().getMainkey();
		String docname = dmb.getIdentity().getDocumentName();
		String docurl = dmb.getDocumentUrl();
		Boolean accessLevel = dmb.getAccessLevel();
		
		if (mainkey == null || mainkey.length() == 0 ||
				docname == null || docname.length() == 0 ||
				docurl == null || docurl.length() == 0) {
			return false;
		}
		
		if (accessLevel == null) {
			dmb.setAccessLevel(false);
		}
		
		Query query = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey AND documentName = :docname");
        query.setString("mainkey", mainkey);
        query.setString("docname",docname);
        
        List<DocumentMgmtBean> list =  query.list();
		
		if(list != null && list.size() > 0) {
			return false;
		}
		
		DocumentMgmtBean mybean = (DocumentMgmtBean)session.merge(dmb);
		session.save(mybean);
		return true;
	}
	
	/*
	 * Returns the particular documentbean for the given composite primary key-> (mainkey, docname)
	 * returns nulll when not existing
	 * returns the bean when found
	 */
	
	public DocumentMgmtBean getByPrimarykey(Session session, String mainkey, String docname) {
		Query query = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey AND documentName = :docname");
        query.setString("mainkey", mainkey);
        query.setString("docname",docname);
        List<DocumentMgmtBean> list =  query.list();
		
		if(list == null && list.size() == 0) {
			return null;
		}
		return list.get(0);
	}	       
        
	/*
	 * Returns the list of documents for the particular mainkey
	 * the list is consists of docnest objects -> docnest -> (docname, docurl)
	 * please see the nested class docnest below for more info on the return type
	 * returns Null when there are no entries
	 */
	public List<Docnest> getByMainkey(Session session, String mainkey) {
		Query query = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey");
        query.setString("mainkey", mainkey);
        List<DocumentMgmtBean> list = query.list();
        if(list == null || list.size() == 0) {
            return null;
        }
        List<Docnest> result = new ArrayList<Docnest>();
        for (DocumentMgmtBean bean: list) {
        	System.out.println("in DAO: " + bean.getIdentity().getDocumentName());
        	result.add(new Docnest(bean.getIdentity().getDocumentName(), bean.getDocumentUrl()));
        }
        return result;
    }
	/*
	 * Returns the list of documents for the particular mainkey
	 * the list is consists of docnattr objects -> docattr -> (docname, docurl, accessLevel)
	 * please see the nested class docattr below for more info on the return type
	 * returns Null when there are no entries
	 */
	public List<Docattr> getListOfDocs(Session session, String mainkey) {
		Query query = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey");
        query.setString("mainkey", mainkey);
        List<DocumentMgmtBean> list = query.list();
        if(list == null || list.size() == 0) {
            return null;
        }
        List<Docattr> result = new ArrayList<Docattr>();
        for (DocumentMgmtBean bean: list) {
        	System.out.println("in DAO: " + bean.getIdentity().getDocumentName());
        	Query query2 = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey AND documentName = :docname");
            String docname = bean.getIdentity().getDocumentName();
        	query2.setString("mainkey", mainkey);
            query2.setString("docname",docname);
            List<DocumentMgmtBean> list2 =  query2.list();
    		Boolean accessLevel = list2.get(0).getAccessLevel();
        	result.add(new Docattr(bean.getIdentity().getDocumentName(), bean.getDocumentUrl(), accessLevel));
      
        }
        return result;
    }
	
	/*
	 * checks the access level for a particular caregiver for the specific docname
	 * returns jsonobject -> (accessLevel (boolean), Success (boolean))
	 */
	public JSONObject checkAccessLevel (Session session, DocumentMgmtBean dmb) throws JSONException {
		
		JSONObject obj = new JSONObject();
		obj.append("accessLevel", false);
		obj.append("success", false);
		
		String mainkey = dmb.getIdentity().getMainkey();
		String docname = dmb.getIdentity().getDocumentName();
		
		if (mainkey == null || mainkey.length() == 0 ||
				docname == null || docname.length() == 0) {
			return obj;
		}
		
		Query query = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey AND documentName = :docname");
        query.setString("mainkey", mainkey);
        query.setString("docname",docname);
        
        List<DocumentMgmtBean> list =  query.list();
		
		if((list == null || list.size() == 0) || (list != null && list.size() > 1)) {
			return obj;
		}
		
		obj.put("accessLevel",  list.get(0).getAccessLevel());
		obj.put("success",  true);
		return obj;
	}
	
	public boolean checkDocument(Session session, DocumentMgmtBean dmb){
		String mainkey = dmb.getIdentity().getMainkey();
		String docname = dmb.getIdentity().getDocumentName();
		if (mainkey == null || mainkey.length() == 0 ||
				docname == null || docname.length() == 0) {
			return false;
		}
		System.out.println(mainkey + docname);
		Query query_init = session.createQuery("FROM DocumentMgmtBean");
		List<DocumentMgmtBean> list_init =  query_init.list();
		if(list_init == null) {
			System.out.println("Initital list is empty so returning true");
			return true;
		}
		
		Query query = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey AND documentName = :docname");
        query.setString("mainkey", mainkey);
        query.setString("docname",docname);
 
        List<DocumentMgmtBean> list =  query.list();
        System.out.println("Size of list is "+list.size());
        if(list != null && list.size() > 0) {
        	System.out.println("Returning false");
		return false;
        }
        
        return true;
	}
	/*
	 * lets you update the document URL or accesslevel for
	 * a document identified by mainkey and docname
	 * returns true on successful update. false on error (eg. doc not found).
	 */
	public boolean updateDocument(Session session, DocumentMgmtBean dmb){
		String mainkey = dmb.getIdentity().getMainkey();
		String docname = dmb.getIdentity().getDocumentName();
		
		if (mainkey == null || mainkey.length() == 0 ||
				docname == null || docname.length() == 0) {
			return false;
		}
		
		Query query = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey AND documentName = :docname");
        query.setString("mainkey", mainkey);
        query.setString("docname",docname);
        
        List<DocumentMgmtBean> list =  query.list();
		
        if((list == null || list.size() == 0) || (list != null && list.size() > 1)) {
			return false;
		}
		
        DocumentMgmtBean oribean = list.get(0);
        if (dmb.getAccessLevel() != null) {
        	oribean.setAccessLevel(dmb.getAccessLevel());
        }
        
        if (dmb.getDocumentUrl() != null && dmb.getDocumentUrl().length() != 0) {
        	oribean.setDocumentUrl(dmb.getDocumentUrl());
        }
		
        DocumentMgmtBean mybean = (DocumentMgmtBean)session.merge(oribean);
		session.saveOrUpdate(mybean);		    
		return true;
	}
	
	/*
	 * lets you delete a document identified by mainkey and docname
	 * returns true on successful deletion. false on error (eg. doc not found).
	 */
	public boolean deleteDocument(Session session, DocumentMgmtBean dmb){
		String mainkey = dmb.getIdentity().getMainkey();
		String docname = dmb.getIdentity().getDocumentName();
		
		if (mainkey == null || mainkey.length() == 0 ||
				docname == null || docname.length() == 0) {
			return false;
		}
		
		//if mainkeydoes not exist in db
		Query query_init = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey");
		query_init.setString("mainkey", mainkey);
		List<DocumentMgmtBean> list_init =  query_init.list();
		if(list_init == null) {
			return true;
		}
		
		Query query = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey AND documentName = :docname");
        query.setString("mainkey", mainkey);
        query.setString("docname",docname);
        
        List<DocumentMgmtBean> list =  query.list();
        System.out.println(mainkey + " " + docname);
		System.out.println("The list size in delete is "+list.size());
        if((list == null || list.size() == 0) || (list != null && list.size() > 1)) {
			return false;
		}
		
        DocumentMgmtBean oribean = list.get(0);
        DocumentMgmtBean mybean = (DocumentMgmtBean)session.merge(oribean);
        session.delete(mybean);
        return true;
	}
	
	public boolean getRow(Session session, DocumentMgmtBean dmb){
		String mainkey = dmb.getIdentity().getMainkey();
		
		if (mainkey == null || mainkey.length() == 0) {
			return false;
		}
		
		//if mainkeydoes not exist in db
		Query query_init = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey");
		query_init.setString("mainkey", mainkey);
		List<DocumentMgmtBean> list_init =  query_init.list();
		if(list_init == null) {
			return false;
		}
		return true;
	}
	
	@XmlRootElement
	public class Docnest {
		public String docname;
		public String docurl;
		
		public Docnest (String dname, String durl) {
			docname = dname;
			docurl = durl;
		}
		
		public String getDocname () {
			return this.docname;
		}
		
		public String getDocurl () {
			return this.docurl;
		}
	}
	
	@XmlRootElement
	public class Docattr {
		public String docname;
		public String docurl;
		public Boolean accessLevel;
		
		public Docattr (String dname, String durl, Boolean aLevel) {
			docname = dname;
			docurl = durl;
			accessLevel = aLevel;
		}
		
		public String getDocname () {
			return this.docname;
		}
		
		public String getDocurl () {
			return this.docurl;
		}
		
		public Boolean getDocAccessLevel () {
			return this.accessLevel;
		}
	}
}
