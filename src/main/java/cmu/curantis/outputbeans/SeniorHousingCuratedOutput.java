package cmu.curantis.outputbeans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cmu.curantis.dao.SeniorHousingCuratedDAO;

@XmlRootElement
public class SeniorHousingCuratedOutput {
	private boolean success;
	private String message;
	private List<SeniorHousingCuratedDAO.CuratedDataNest> listofresults;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<SeniorHousingCuratedDAO.CuratedDataNest> getListofresults() {
		return listofresults;
	}
	public void setListofresults(List<SeniorHousingCuratedDAO.CuratedDataNest> listofresults) {
		this.listofresults = listofresults;
	}
	

}
