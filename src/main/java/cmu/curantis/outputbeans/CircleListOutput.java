package cmu.curantis.outputbeans;

import com.google.gson.JsonArray;

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
