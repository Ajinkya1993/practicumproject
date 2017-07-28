package cmu.curantis.outputbeans;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonArray;

@XmlRootElement
public class CircleListOutput {
    private JsonArray list;
    private boolean success;
    private String message;
    public JsonArray getList() {
        return list;
    }
    public void setList(JsonArray list) {
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
