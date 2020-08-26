package model;

import java.util.List;
//Server response for requested data.
public class Response {

    public static final int SUCCESS = 200;
    public static final int FAILED = 201;

    int status;
    String description;
    List<Emp> result;

    public Response(int status, List<Emp> result) {
        super();
        this.status = status;
        this.result = result;
    }

    public Response(int status, String description, List<Emp> result) {
        super();
        this.status = status;
        this.description = description;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Emp> getResult() {
        return result;
    }

    public void setResult(List<Emp> result) {
        this.result = result;
    }
}
