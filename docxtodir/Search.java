package docxtodir;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Search {

    private ZipEntry entry, entryDoc;
    private String bscName = "";
    private InputStream stream;
    private boolean noImage = true;

    public String bscName(ZipFile zFile) throws IOException {

        Enumeration<? extends ZipEntry> entries = zFile.entries();

        try {

            while (entries.hasMoreElements()) {
                if (((entry = entries.nextElement()).getName()).contains("image1.wmf")) {
                    stream = zFile.getInputStream(entry);
                    noImage = false;
                } else if (entry.getName().contains("document.xml")) {
                    entryDoc = entry;
                }
            }

            if (noImage) {
                stream = zFile.getInputStream(entryDoc);
            }

            Scanner a = new Scanner(stream);

            try {
                if (noImage) {
                    while (a.hasNext()) {
                        bscName = a.nextLine();
                    }
                    bscName = bscName.substring(bscName.indexOf("Name") + 12, bscName.length());

                    while (bscName.substring(bscName.indexOf("</w:t>") - 2, bscName.indexOf("</w:t>") - 1)
                            .equals(">")) {
                        bscName = bscName.substring(bscName.indexOf("</w:t>") + 6, bscName.length());
                    }
                    bscName = bscName.substring(bscName.indexOf("<w:t") + 4, bscName.length());
                    bscName = bscName.substring(bscName.indexOf(">") + 1, bscName.indexOf("</w"));
                    bscName = bscName.trim();

                    // System.out.println("------------------------------------"
                    // + bscName);
                } else {
                    while (a.hasNext()) {
                        bscName = a.nextLine();
                        if (bscName.contains(" - ")) {
                            break;
                        }
                    }
                    int index;
                    if (bscName.indexOf("}") < 0) {
                        index = 16;
                    } else {
                        index = bscName.indexOf("}") + 4;
                    }
                    bscName = bscName.substring(index, bscName.length());
                    bscName = bscName.substring(0, bscName.indexOf(" "));
                }

            }

            finally {
                noImage = true;
                a.close();
                stream.close();
            }

        } catch (NullPointerException e) {
            System.out.println("В Файле " + zFile.getName() + " нет краткого имени площадки.");
            bscName = "нет";
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("В Файле " + zFile.getName() + " ошибка в поиске площадки");
            System.out.println(entry.getName() + "|\t Length:\t" + bscName.length() + "|\tString:" + bscName);
        }
        zFile.close();
        return bscName;
    }
}
