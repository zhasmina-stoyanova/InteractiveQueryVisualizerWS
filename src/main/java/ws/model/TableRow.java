package ws.model;

import java.util.List;

/**
 * A class that represents a row of cells from a table in the database.
 * Contains information about the name and the value of each cell.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
public class TableRow {
    //row that consists of cells
    private List<TableCell> row;

    //empty constructor
    public TableRow() {
    }

    public TableRow(List<TableCell> row) {
        this.row = row;
    }

    public List<TableCell> getRow() {
        return row;
    }

    public void setRow(List<TableCell> row) {
        this.row = row;
    }
}
