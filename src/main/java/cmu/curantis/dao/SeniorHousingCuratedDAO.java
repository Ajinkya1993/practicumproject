package cmu.curantis.dao;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.Query;
import org.hibernate.Session;
import cmu.curantis.entities.SeniorHousingCuratedBean;

public class SeniorHousingCuratedDAO {
	
	public List<CuratedDataNest> getFilteredResults(Session session, SeniorHousingCuratedBean bean){
		StringBuffer sb = new StringBuffer();
		Boolean flag = false;
		
		if(bean.getState() != null) {
			sb.append("state = "+ String.valueOf(bean.getState()));
			flag = true;
		}
		if(bean.getCity() != null) {
			if(flag) {
				sb.append(" and city = "+String.valueOf(bean.getCity()));
			} else {
				sb.append("city = "+ String.valueOf(bean.getCity()));
				flag = true;
			}
			
		}
		if(bean.getZip() != null) {
			if(flag) {
				sb.append(" and zip = "+String.valueOf(bean.getZip()));
			} else {
				sb.append("zip = "+ String.valueOf(bean.getZip()));
				flag = true;
			}
			
		}
		if(bean.getAddress() != null) {
			if(flag) {
				sb.append("and address = "+String.valueOf(bean.getAddress()));
			} else {
				sb.append("address = "+String.valueOf(bean.getAddress()));
				flag = false;
			}
			
		}
		System.out.println(sb.toString());
		Query query = session.createQuery("from SeniorHousingCuratedBean where " + sb.toString());
		List<SeniorHousingCuratedBean> data =  query.list();
		if (data == null || data.size() == 0) {
	    	return null;
	    }
		List<CuratedDataNest> result = new ArrayList<CuratedDataNest>();
        for (SeniorHousingCuratedBean b: data) {
        	result.add(new CuratedDataNest(b.getName(), b.getAddress(), b.getQualityTier(), b.getCity(), 
        			b.getState(), b.getZip(), b.getPhoneNumber(), b.getCertifiedBeds(), b.getResidentsNumber(),
        			b.getType(), b.getLegalName(), b.getFirstApprovedDate(), b.getIndependentCare(),
        			b.getUnskilledCare(), b.getSkilledCare(), b.getMemoryCare(), b.getOverallRating(),
        			b.getHealthInspectionRating(), b.getRnStaffingRating(), b.getPrice()));
        }
        return result;
	}

	@XmlRootElement
	public class CuratedDataNest {
		private String name;
		private String address;
		private String qualityTier;
		private String city;
		private String state;
		private String zip;
		private String phoneNumber;
		private Integer certifiedBeds;
		private Integer residentsNumber;
		private String type;
		private String legalName;
		private String firstApprovedDate;
		private Boolean independentCare;
		private Boolean unskilledCare;
		private Boolean skilledCare;
		private Boolean memoryCare;
		private Integer overallRating;
		private Integer healthInspectionRating;
		private Integer rnStaffingRating;
		private Integer price;
		
		public CuratedDataNest(String nm, String addr, String qTier, String city, String state, 
				String zip, String pNo, Integer beds, Integer residents, String type, 
				String legalName, String date, Boolean indCare, Boolean unskCare, Boolean skCare, 
				Boolean memCare, Integer oRating, Integer hiRating, Integer rnStaffRating, Integer price
				) {
			this.name = nm;
			this.address = addr;
			this.qualityTier = qTier;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.phoneNumber = pNo;
			this.certifiedBeds = beds;
			this.residentsNumber = residents;
			this.type = type;
			this.legalName = legalName;
			this.firstApprovedDate = date;
			this.independentCare = indCare;
			this.unskilledCare = unskCare;
			this.skilledCare = skCare;
			this.memoryCare = memCare;
			this.overallRating = oRating;
			this.healthInspectionRating = hiRating;
			this.rnStaffingRating = rnStaffRating;
			this.price = price;	
		}
		public String getName() {
			return name;
		}
		
		public String getAddress() {
			return address;
		}
		
		public String getQualityTier() {
			return qualityTier;
		}
		
		public String getCity() {
			return city;
		}
		
		public String getState() {
			return state;
		}
		
		public String getZip() {
			return zip;
		}
		
		public String getPhoneNumber() {
			return phoneNumber;
		}
		
		public Integer getCertifiedBeds() {
			return certifiedBeds;
		}
		
		public Integer getResidentsNumber() {
			return residentsNumber;
		}
		
		public String getType() {
			return type;
		}
		
		public String getLegalName() {
			return legalName;
		}
		
		public String getFirstApprovedDate() {
			return firstApprovedDate;
		}
		
		public Boolean getIndependentCare() {
			return independentCare;
		}
		
		public Boolean getUnskilledCare() {
			return unskilledCare;
		}
		
		public Boolean getSkilledCare() {
			return skilledCare;
		}
		
		public Boolean getMemoryCare() {
			return memoryCare;
		}
		
		public Integer getOverallRating() {
			return overallRating;
		}
		
		public Integer getHealthInspectionRating() {
			return healthInspectionRating;
		}
		
		public Integer getRnStaffingRating() {
			return rnStaffingRating;
		}
		
		public Integer getPrice() {
			return price;
		}
	}
}