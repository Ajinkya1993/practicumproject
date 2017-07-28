package cmu.curantis.outputbeans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserListOutput {
    private List<UserOfCircle> list;
    public List<UserOfCircle> getList() {
        return list;
    }
    public void setList(List<UserOfCircle> list) {
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
    private boolean success;
    private String message;
}
