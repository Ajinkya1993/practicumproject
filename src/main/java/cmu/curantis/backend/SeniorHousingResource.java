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


@Path("/seniorHousing")
public class SeniorHousingResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getFilteredResults")
	public SeniorHousingCuratedOutput getData(SeniorHousingCuratedInput input) {
		SeniorHousingCuratedOutput output = new SeniorHousingCuratedOutput();
		SeniorHousingCuratedBean bean = new SeniorHousingCuratedBean();
		bean.setCity(input.getCity());
		bean.setState(input.getState());
		bean.setZip(input.getZip());
		bean.setAddress(input.getAddress());
		bean.setOverallRating(input.getOverallRating());
		
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/applyPriceFilters")
	public SeniorHousingCuratedOutput getPriceFiltered(SeniorHousingCuratedInput input) {
		SeniorHousingCuratedOutput output = new SeniorHousingCuratedOutput();
		Integer minPrice = input.getMinPrice();
		Integer maxPrice = input.getMaxPrice();
		if (minPrice == null || maxPrice == null) {
			output.setMessage("Please provide min Price and max Price");
			output.setSuccess(false);
			output.setListofresults(null);
			return output;
		}
		if (minPrice > maxPrice) {
			output.setMessage("min Price cannot be greater than max Price");
			output.setSuccess(false);
			output.setListofresults(null);
			return output;
		}
		
		SeniorHousingCuratedDAO dao = new SeniorHousingCuratedDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		List<SeniorHousingCuratedDAO.CuratedDataNest> result = dao.getPriceResults(session, maxPrice, minPrice);
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
