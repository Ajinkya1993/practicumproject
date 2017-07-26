package cmu.curantis.outputbeans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;

import cmu.curantis.dao.DocumentMgmtDAO;

@XmlRootElement
public class DocumentOutput {
	private boolean success;
	
	private boolean accessLevel;
	/*
	 * this will contain jsonobjects specifying (docname, docurl)
	 * useful when you want to send back list of docs for the caregiver for a service
	 */
	private List<DocumentMgmtDAO.Docnest> listofdocs;
	private String message;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<DocumentMgmtDAO.Docnest> getListofdocs() {
		return listofdocs;
	}
	public void setListofdocs(List<DocumentMgmtDAO.Docnest> listofdocs) {
		this.listofdocs = listofdocs;
	}
	
	public void setMessage(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public boolean getAccessLevel () {
		return this.accessLevel;
	}
	
	public void setAccessLevel (Boolean alevel) {
		this.accessLevel = alevel;
	}
	
}
