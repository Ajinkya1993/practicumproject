package cmu.curantis.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="senior_housing_circle")
public class SeniorHousingCircleBean {

	@Id
	@Column(name = "circle_id")
	private long circleId;
	
	@Column(name = "preference", columnDefinition="TEXT")
	private String preference;
	
	public long getCircleId() {
		return circleId;
	}

	public void setCircleId(long circleId) {
		this.circleId = circleId;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}
}
