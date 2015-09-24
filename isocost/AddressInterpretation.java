package isocost;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddressInterpretation {
    
    private ExcelApiUse excelFile;
    private String[][] addresses;
    
    AddressInterpretation(File file)  throws FileNotFoundException, IOException{
        
        FileInputStream input = new FileInputStream(file);
        
        switch (file.getName().substring(file.getName().lastIndexOf("."))) {
        
        case ".xls":  excelFile = new ExcelApiUse(input); addresses = excelFile.getXLS(); break;
        case ".xlsx": excelFile = new ExcelApiUse(input); addresses = excelFile.getXLSX(); break;
        case ".csv":  excelFile = new ExcelApiUse(input); addresses = excelFile.getCSV(); break;
        case ".txt": break;
        case ".doc": break;
        case ".docx": break;
        default:
            System.out.println("Text.");
            System.out.println(file.getName().substring(file.getName().lastIndexOf(".")));
        }
    }
    
    String[][] getAddresses(){
        String[][] tempArr = new String[addresses.length][3];
        int count =0;
        for(int i=0; i<addresses.length; i++){
            if(addresses[i][0]!=null&&addresses[i][0].length()>1){
                tempArr[count][0] = addresses[i][0];
                tempArr[count][1] = addresses[i][1];
                tempArr[count++][2] = addresses[i][2];
            }
        }
        addresses = new String[count][3];
        
        for(int i=0; i<count; i++){
            addresses[i][0] = tempArr[i][0];
            addresses[i][1] = tempArr[i][1];
            addresses[i][2] = tempArr[i][2];
        }
        return addresses;
    }
    
    String[][] getCoords(){
        String[][] tempArr = new String[addresses.length][3];
        int count =0;
        for(int i=0; i<addresses.length; i++){
            if(!addresses[i][0].equals(null)&&addresses[i][0].length()>1){
                System.out.println(addresses[i][0]);
                tempArr[count][0] = addresses[i][0];
                tempArr[count++][1] = addresses[i][1];
            }
        }
        addresses = new String[count][2];
        
        for(int i=0; i<count; i++){
            addresses[i][1] = tempArr[i][0];
            addresses[i][0] = tempArr[i][1];
        }
        return addresses;
    }
}
