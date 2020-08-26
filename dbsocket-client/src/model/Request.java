package model;
//Class for requested data name by client.
public class Request {
        String name,byType;

        public Request(String name,String byType) {
            super();
            this.name = name;
            this.byType = byType;
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

