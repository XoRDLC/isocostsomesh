package docxtodir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipFile;

public class UnZip {

    public static void main(String args[]) throws Exception {

        Search search = new Search();
        String bscName;
        ArrayOfFolders folderList;
        File[] dirList;
        PathCheck path = new PathCheck();

        String OUTPATH = "D:\\1\\ListFinal\\", LISTPATH = "D:\\1\\list\\";

        OUTPATH = path.check(OUTPATH);
        LISTPATH = path.check(LISTPATH);

        try {
            dirList = new File(LISTPATH).listFiles();
            File list = new File(args[1]); // Список с подрядчиками
            folderList = new ArrayOfFolders(list, OUTPATH);
        } catch (FileNotFoundException e) {
            System.out.println("No such file: " + args[1] + ".\n Please, correct input.");
            System.out.println("Files were not copied.");
            // zipFile.close();
            return;
        } catch (NullPointerException e) {
            System.out.println("No such folder: " + LISTPATH + ".\n Please, correct input.");
            System.out.println("Files were not copied.");
            // zipFile.close();
            return;
        } finally {
            
        }

        try {
            for (File a : dirList) {
                bscName = search.bscName(new ZipFile(a));
                folderList.copy(bscName, a);
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        } finally {
            System.out.println("Программа завершена.");
            folderList.notFound();
        }
    }

}
