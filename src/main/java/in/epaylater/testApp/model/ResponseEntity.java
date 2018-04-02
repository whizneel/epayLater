package in.epaylater.testApp.model;

import org.json.simple.JSONObject;

public class ResponseEntity {

    private String status;
    private JSONObject result;

    public ResponseEntity(String status, JSONObject result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "status='" + status + '\'' +
                ", result=" + result +
                '}';
    }
}
