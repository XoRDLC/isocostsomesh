package docxtodir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ArrayOfFolders {

    private Scanner scn;
    private String[][] arr, tempArr;
    private int i = 0;
    private Copy copyFile;
    private String outPath;

    ArrayOfFolders(File list, String outPath) throws FileNotFoundException {
        this.outPath = outPath;
        arr = new String[1][3];
        scn = new Scanner(list);
        while (scn.hasNextLine()) {
            arr[i][0] = scn.nextLine();
            arr[i] = arr[i++][0].split("[\t]");
            if (i == arr.length) {
                tempArr = arr;
                arr = new String[i * 2][3];
                int aAdd = 0;
                while (aAdd < i) {
                    arr[aAdd][0] = tempArr[aAdd][0];
                    arr[aAdd][1] = tempArr[aAdd++][1];
                }
            }
        }
        tempArr = arr;
        arr = new String[i][3];
        int t = 0;
        while (t < i) {
            arr[t][0] = tempArr[t][0];
            arr[t][1] = tempArr[t][1];
            arr[t++][2] = "-";
        }
        tempArr = null;
    }

    public void copy(String bscName, File a) throws IOException, FileNotFoundException {
        copyFile = new Copy();
        int i = 0;
        while (i < arr.length && !arr[i][0].equals(bscName.trim())) {
            i++;
        }
        if (i == arr.length) {
            System.out.println("Совпадение не найдено. " + bscName);
            copyFile.copy(a, outPath + "Нет");
        } else {
            System.out.println("Совпадение найдено. " + bscName);
            copyFile.copy(a, outPath + arr[i][1]);
            arr[i][2] = "Скопировано";
        }
    }

    public void notFound() {

        System.out.println("\n\nНе найдено совпадения для:");
        for (int z = 0; z < arr.length; z++) {
            if (!arr[z][2].equals("Скопировано")) {
                System.out.println(arr[z][0]);
            }
        }
    }
}
