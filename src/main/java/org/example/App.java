package org.example;

import org.example.Exceptions.InvalidCellException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class App {
    public void readCSV(){
        try {
            Scanner sc = new Scanner(new File("./src/main/java/org/example/assignment-database.csv"));
            sc.useDelimiter(",");
            validateDocument(sc);
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    public void validateDocument(Scanner sc){
        int rowIndex = 1;
        int lastCorrect = rowIndex;
        ArrayList<String> result = new ArrayList<>();
        while (sc.hasNext())
        {
            String[] columnValues = sc.nextLine().split(",");
            for(int n=0;n<columnValues.length;n++){
                String columnIndex = convertToChar(n);
                try{
                    Cell cell = new Cell(columnValues[n], columnIndex, rowIndex);
                    validateCell(cell);
                    lastCorrect = validateID(cell);
                }catch(InvalidCellException e){
                    result.add(columnIndex+rowIndex);
                }
            }
            rowIndex++;
        }
        result.add("A"+String.valueOf(lastCorrect));
        System.out.println(result);
    }
    public int validateID(Cell cell) throws InvalidCellException {
        if(Objects.equals(cell.getColumnIndex(), "A") && cell.getRowIndex() != 1){
            boolean rowIsCorrect = (cell.getRowIndex()-1) == Integer.parseInt(cell.getValue())%1000;
            if(!rowIsCorrect){
                throw new InvalidCellException();
            }
            else{
                return cell.getRowIndex();
            }
        }
        return 0;
    }
    public String convertToChar(int columnIndex) throws IllegalArgumentException{
        switch (columnIndex){
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            default:
                throw new IllegalArgumentException();
        }
    }
    public void validateCell(Cell cell) throws InvalidCellException {
        boolean emptyCell = Objects.equals(cell.getValue(), "");
        if(Objects.equals(cell.getColumnIndex(), "D") && cell.getRowIndex() != 1){
            try {
                Double.parseDouble(cell.getValue());
            }catch(NumberFormatException e){
                throw new InvalidCellException();
            }
        }
        if(emptyCell){
            throw new InvalidCellException();
        }else{
            boolean startWithBlank = cell.getValue().charAt(0) == ' ';
            if(startWithBlank && Objects.equals(cell.getColumnIndex(), "B")){
                throw new InvalidCellException();
            }
        }

    }
}
