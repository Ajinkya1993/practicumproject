package cmu.curantis.backend;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.SeniorHousingCuratedDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.SeniorHousingCuratedBean;
import cmu.curantis.inputbeans.SeniorHousingCuratedInput;
import cmu.curantis.outputbeans.SeniorHousingCuratedOutput;


@Path("/getMatchedResults")
public class GetCuratedData {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SeniorHousingCuratedOutput getData(SeniorHousingCuratedInput input) {
		SeniorHousingCuratedOutput output = new SeniorHousingCuratedOutput();
		SeniorHousingCuratedBean bean = new SeniorHousingCuratedBean();
		if(input != null) {
			if(input.getCity() != null) {
				bean.setCity(input.getCity());
			}
			if(input.getState() != null) {
				bean.setState(input.getState());
			}
			if(input.getZip() != null) {
				bean.setZip(input.getZip());
			}
			if(input.getAddress() != null) {
				bean.setAddress(input.getAddress());
			}
			if(input.getOverallRating() != null) {
				bean.setOverallRating(input.getOverallRating());
			}
			if(input.getMemoryCare() != null) {
				bean.setMemoryCare(input.getMemoryCare());
			}
			if(input.getIndependentCare() != null) {
				bean.setIndependentCare(input.getIndependentCare());
			}
			if(input.getSkilledCare() != null) {
				bean.setSkilledCare(input.getSkilledCare());
			}
			if(input.getUnskilledCare() != null) {
				bean.setUnskilledCare(input.getUnskilledCare());
			}
			if(input.getQualityTier() != null) {
				bean.setQualityTier(input.getQualityTier());
			}
			if(input.getType() != null) {
				bean.setType(input.getType());
			}
		}
		SeniorHousingCuratedDAO dao = new SeniorHousingCuratedDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		List<SeniorHousingCuratedDAO.CuratedDataNest> result = dao.getFilteredResults(session, bean);
		tx.commit();
		if (result == null) {
			output.setMessage("No results available!");
			output.setSuccess(false);
			output.setListofresults(null);
			return output;
		}
		output.setMessage("Received list of results!");
		output.setSuccess(true);
		output.setListofresults(result);
        session.close();
        return output;
	}
}
