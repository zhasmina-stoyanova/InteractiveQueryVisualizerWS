package ws.model;

import java.util.List;

public class TableDataRowItem {
    private List<TableDataCellItem> row;

    public TableDataRowItem() {
    }

    public TableDataRowItem(List<TableDataCellItem> row) {
        this.row = row;
    }

    public List<TableDataCellItem> getRow() {
        return row;
    }

    public void setRow(List<TableDataCellItem> row) {
        this.row = row;
    }
}
