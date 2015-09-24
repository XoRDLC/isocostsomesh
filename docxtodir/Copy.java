package docxtodir;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copy {
    
    private String outPath;
    private FileInputStream fileStream;
    private FileOutputStream outPut;
    private byte[] buffer = new byte[2048];
    
    public void copy(File file, String outPath) throws IOException{
        this.fileStream = new FileInputStream(file);
        new File(outPath).mkdirs();
        this.outPath = outPath + "\\" + file.getName();
        
        try {
            outPut = new FileOutputStream(this.outPath);
            int len = 0;
            while ((len = fileStream.read(buffer)) > 0) {
                outPut.write(buffer, 0, len);
            }
        } finally {
            // we must always close the output file
            if (outPut != null)
                outPut.close();
        }
        
        
    }
    
}
