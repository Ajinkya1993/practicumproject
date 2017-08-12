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
	private List<Double> mlist;
	private List<String> listv;
	private Set<String> set;
	private VendorMgmtBean vb;
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
    
    public List<String> getListv() {
        return listv;
    }
    public void setListv(List<String> listv) {
        this.listv = listv;
    }
    
    public List<Double> getMonthlylist() {
        return mlist;
    }
    public void setMonthlylist(List<Double> mlist) {
        this.mlist = mlist;
    }

	public void setBean(VendorMgmtBean vb) {
		vb = this.vb;
	}
	public VendorMgmtBean getBean() {
		return vb;
	}
	public void setSet(Set<String> setip) {
		set = setip;
	}
	public Set<String> getSet() {
		return set;
	}

	
}
