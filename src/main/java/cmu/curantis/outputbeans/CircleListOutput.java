package cmu.curantis.outputbeans;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;



@XmlRootElement
public class CircleListOutput {
    private JSONArray list;
    private boolean success;
    private String message;
    public JSONArray getList() {
        return list;
    }
    public void setList(JSONArray list) {
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
