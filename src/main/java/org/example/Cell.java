package org.example;

public class Cell {
    private String value;
    private String columnIndex;
    private int rowIndex;

    public Cell(String value, String columnIndex, int rowIndex){
        this.columnIndex = columnIndex;
        this.value = value;
        this.rowIndex = rowIndex;
    }
    public String getValue(){
        return this.value;
    }

    public String getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }
}
