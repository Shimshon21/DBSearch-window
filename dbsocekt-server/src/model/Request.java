package model;
//Class for requested data name by client.
public class Request {
    String name,byType;

    public Request(String name,String byField) {
        super();
        this.name = name;
        this.byType = byField;
    }

    public String getByType() {
        return byType;
    }

    public void setByType(String byType) {
        this.byType = byType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
