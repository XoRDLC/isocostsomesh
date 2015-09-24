package isocost;

public class StaticXmlParser {
    public static String getTextFromXml(String xmlText){
        String tag, value = xmlText;
        int i=0;
        
        while(value.contains(">")){
            value = value.substring(value.indexOf(">")+1,value.length());
            i++;   
            if(i>2) break;
        }
        value = xmlText;
        if(i==2){
            tag = value.substring(value.indexOf("<")+1,value.indexOf(">"));
            value = value.substring(value.indexOf("<" + tag + ">")+tag.length()+2,value.length());
            value = value.substring(0,value.indexOf("</"+tag+">")-1);
        }
        return value;
    }

}
