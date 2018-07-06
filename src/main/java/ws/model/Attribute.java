package ws.model;

public class Attribute {
    private String name;
    private String lookupView;

    public Attribute() {
    }

    public Attribute(String name, String lookupView) {
        this.name = name;
        this.lookupView = lookupView;
    }

    public String getLookupView() {
        return lookupView;
    }

    public void setLookupView(String lookupView) {
        this.lookupView = lookupView;
    }
}
