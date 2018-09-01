package ws.model;

/**
 * A class that represents the min and max value of an attribute.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
public class ExtremeAttributeValues {
    private String min;
    private String max;

    //empty constructor
    public ExtremeAttributeValues() {
    }

    /**
     * Initializes a new instance of the class by passing the attribute's min and max values
     *
     * @param min the min value of the attribute
     * @param max the max value of the attribute
     */
    public ExtremeAttributeValues(String min, String max) {
        this.min = min;
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}
