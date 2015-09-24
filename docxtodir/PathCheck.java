package docxtodir;

public class PathCheck {
    
    public  String check(String path){
        
        if (path.endsWith("\\")) {
            return path;
        } else {
            return path + "\\";
        }
    }
}
