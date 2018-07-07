package ws.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class LookupView {
    private String name;
    private String description;

    public LookupView() {
    }

    public LookupView(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
