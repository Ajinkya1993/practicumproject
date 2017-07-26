package cmu.curantis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;

import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.entities.CircleSubsBean;
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
        query.setString("documentName",docname);
        
        List<DocumentMgmtBean> list =  query.list();
		
		if(list != null && list.size() > 0) {
			return false;
		}
		
		CircleSubsBean mybean = (CircleSubsBean)session.merge(dmb);
		session.save(mybean);
		return true;
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
        	result.add(new Docnest(bean.getIdentity().getDocumentName(), bean.getDocumentUrl()));
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
        query.setString("documentName",docname);
        
        List<DocumentMgmtBean> list =  query.list();
		
		if((list == null || list.size() == 0) || (list != null && list.size() > 1)) {
			return obj;
		}
		
		obj.append("accessLevel",  list.get(0).getAccessLevel());
		obj.append("success",  true);
		return obj;
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
        query.setString("documentName",docname);
        
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
	 * lets you update the document URL or accesslevel for
	 * a document identified by mainkey and docname
	 * returns true on successful update. false on error (eg. doc not found).
	 */
	public boolean deleteDocument(Session session, DocumentMgmtBean dmb){
		String mainkey = dmb.getIdentity().getMainkey();
		String docname = dmb.getIdentity().getDocumentName();
		
		if (mainkey == null || mainkey.length() == 0 ||
				docname == null || docname.length() == 0) {
			return false;
		}
		
		Query query = session.createQuery("FROM DocumentMgmtBean WHERE mainkey = :mainkey AND documentName = :docname");
        query.setString("mainkey", mainkey);
        query.setString("documentName",docname);
        
        List<DocumentMgmtBean> list =  query.list();
		
        if((list == null || list.size() == 0) || (list != null && list.size() > 1)) {
			return false;
		}
		
        DocumentMgmtBean oribean = list.get(0);
        DocumentMgmtBean mybean = (DocumentMgmtBean)session.merge(oribean);
        session.delete(mybean);
        return true;
	}
	
	public class Docnest {
		public String docname;
		public String docurl;
		
		public Docnest (String dname, String durl) {
			docname = docname;
			docurl = durl;
		}
		
		public String getDocname () {
			return this.docname;
		}
		
		public String getDocurl () {
			return this.docurl;
		}
	}
}