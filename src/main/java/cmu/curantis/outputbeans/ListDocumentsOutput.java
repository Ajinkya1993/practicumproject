package cmu.curantis.outputbeans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


import cmu.curantis.dao.DocumentMgmtDAO;

@XmlRootElement
public class ListDocumentsOutput {
	private boolean success;
	
	private boolean accessLevel;
	/*
	 * this will contain jsonobjects specifying (docname, docurl, accessLevel)
	 * useful when you want to send back list of docs for the caregiver for a service
	 */
	private List<DocumentMgmtDAO.Docattr> listofdocs;
	private String message;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<DocumentMgmtDAO.Docattr> getListofdocs() {
		return listofdocs;
	}
	public void setListofdocs(List<DocumentMgmtDAO.Docattr> listofdocs) {
		this.listofdocs = listofdocs;
	}
	
	public void setMessage(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	/*public boolean getAccessLevel () {
		return this.accessLevel;
	}
	
	public void setAccessLevel (Boolean alevel) {
		this.accessLevel = alevel;
	}*/
	
}
