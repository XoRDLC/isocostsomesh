package isocost;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelApiUse {

    private FileInputStream fisExcelFile;
    private String addresses[][];

    ExcelApiUse(FileInputStream file) {
        fisExcelFile = file;
    }

    String[][] getXLSX() throws IOException {
        System.out.println("Office Excel 2007 workbook.");
        XSSFWorkbook workBook = new XSSFWorkbook(fisExcelFile);
        XSSFSheet sheet = workBook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        addresses = new String[rows][3];
        
        for (int r = 0; r< rows; r++){
            XSSFRow row = sheet.getRow(r);
            if(row == null) continue;
            
            int cells = row.getPhysicalNumberOfCells();
            for (int c = 0; c < cells; c++) {
                XSSFCell cell = row.getCell(c);
                String value = null;
                try{
                value = cell.toString();}
                catch(NullPointerException  e){
                }
                if(c<3) {addresses[r][c] = value;}
            }
        }
        workBook.close();
        return addresses;
    }

    String[][] getXLS() throws IOException {
        System.out.println("Office Excel 97-2003 workbook.");
        HSSFWorkbook workBook = new HSSFWorkbook(fisExcelFile);

        HSSFSheet sheet = workBook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        addresses = new String[rows][3];

        for (int r = 0; r < rows; r++) {
            HSSFRow row = sheet.getRow(r);
            if (row == null) {
                continue;
            }

            int cells = row.getPhysicalNumberOfCells();
            for (int c = 0; c < cells; c++) {
                HSSFCell cell = row.getCell(c);
                String value = null;
                value = cell.toString();

                if(c<3) {addresses[r][c] = value;}
            }
        }
        workBook.close();
        return addresses;
    }

    String[][] getCSV() throws IOException {
        System.out.println("Office CSV file. Not implemented yet");
        return null;
    }
}
