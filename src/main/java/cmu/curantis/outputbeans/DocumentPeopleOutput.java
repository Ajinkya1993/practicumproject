package cmu.curantis.outputbeans;

import java.util.List;

public class DocumentPeopleOutput {
	private boolean success;
	private String message;
	private List<DocumentPeople> people;
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
	public List<DocumentPeople> getPeople() {
		return people;
	}
	public void setPeople(List<DocumentPeople> people) {
		this.people = people;
	}
}
