package ws.model;

/**
 * A class that represents an attribute in the database.
 * Contains information about the name and type of each attribute.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
public class Attribute {
    private String name;
    private String type;

    //empty constructor
    public Attribute() {
    }

    /**
     * Initializes a new instance of the class by passing the attribute name and type
     *
     * @param name the name of the attribute in a table
     * @param type the data type of the attribute in a table
     */
    public Attribute(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
