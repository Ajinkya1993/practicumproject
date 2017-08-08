package cmu.curantis.outputbeans;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import cmu.curantis.entities.VendorMgmtBean;

@XmlRootElement
public class VendorOutput {
	private boolean success;
	private String message;
	private List<VendorMgmtBean> list;
	private Set<String> set;
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
	
    public List<VendorMgmtBean> getList() {
        return list;
    }
    public void setList(List<VendorMgmtBean> list) {
        this.list = list;
    }
    public Set<String> getSet() {
        return set;
    }
    public void setSet(Set<String> set) {
        this.set = set;
    }
}
