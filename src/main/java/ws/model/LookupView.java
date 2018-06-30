package ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LookupView {
    private String name;

    public LookupView() {
    }

    public LookupView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
