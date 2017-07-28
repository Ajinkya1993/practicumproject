package cmu.curantis.outputbeans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CircleListOutput {
    private List<CircleInfo> list;
    private boolean success;
    private String message;
    public List<CircleInfo> getList() {
        return list;
    }
    public void setList(List<CircleInfo> list) {
        this.list = list;
    }
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
    
}
