package ws.model;

/**
 * A class that represents a cell from a table in the database.
 * Contains information about the name and the value of each cell.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
public class TableCell {
    private String attributeName;
    private String attributeValue;

    //empty constructor
    public TableCell() {
    }

    /**
     * Initializes a new instance of the class by passing the cell name and value
     *
     * @param attributeName  the name of the cell in a table
     * @param attributeValue the value of the cell in a table
     */
    public TableCell(String attributeName, String attributeValue) {
        this.setAttributeName(attributeName);
        this.setAttributeValue(attributeValue);
    }


    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
