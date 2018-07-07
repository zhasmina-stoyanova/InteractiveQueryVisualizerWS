package ws.model;

public class TableDataCellItem {
    private String attributeName;
    private String attributeValue;

    public TableDataCellItem() {
    }

    public TableDataCellItem(String attributeName, String attributeValue) {
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
