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
		//bean.setCity(input.getCity());
		//bean.setState(input.getState());
		bean.setZip(input.getZip());
		
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
