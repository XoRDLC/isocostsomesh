package isocost;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileCheckAndGo {
    private AddressInterpretation addresses;
    private boolean bNoFileError = false, bInputError = false;
    private File file;
    
    public void fileInput(String[] args) {
        try {
            if (args.length == 0) {
                throw new FileNotFoundException();
            }
            file = new File(args[0]);
            addresses = new AddressInterpretation(file);

        } catch (FileNotFoundException e) {
            try {
                System.out.printf("���� %1$S �� ������. ������� ���� � �����.\n", args[0]);
            } catch (ArrayIndexOutOfBoundsException e1) {
                System.out.println("�� ������ ���� � �����, ��������� ��������� � ����������: ��������� ����_�_�����");
            } finally {
                bNoFileError = true;
            }
        } catch (IOException e) {
            System.out.println("������ ��������� �����. " + e);
            bInputError = true;
        } finally {
        }
    }
    
    public void fileOutput(String htmlPage, String fileName){
        String path = (file.getPath()).substring(0, (file.getPath()).lastIndexOf("\\")+1);
        
        
        try{
            File file2 = new File(path + fileName);
            file2.createNewFile();
           
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file2.getPath()), "UTF-8"));
                try {
                    out.write(htmlPage);
                } finally {
                    out.close();
                }
            
            File htmlFile = new File(file2.getPath());
            Desktop.getDesktop().browse(htmlFile.toURI());
            
        }
        catch(FileNotFoundException e){System.out.println("��� ����� ��� ������. ");}
        catch(IOException e){System.out.println("�� ���������� ������� ����. ");}
        finally{}
    }

    boolean isFileHasException() {
        return bInputError | bNoFileError;
    }

    String[][] getAddress() {
        return addresses.getAddresses();
    }
    
    String[][] getCoords() {
        return addresses.getCoords();
    }
}
