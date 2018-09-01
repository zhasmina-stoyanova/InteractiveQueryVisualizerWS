package ws.model;

/**
 * A class that represents a lookup view in the database.
 * Contains information about its name.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
public class LookupView {
    private String name;

    //empty constructor
    public LookupView() {
    }

    /**
     * Initializes a new instance of the class by passing the lookup view name.
     *
     * @param name the name of the lookup view
     */
    public LookupView(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
