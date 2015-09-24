package isocost;

public class HTML_JS_Yandex {

    private final String block1, jsVersion, block2;
    private String[] placeMarkName;
    private String centerPlaceMark, jsBody;
    private int count;

    HTML_JS_Yandex(int addCount, String[][] coords) {
        MapDraw mapDraw = new MapDraw();
        count = 0;
        placeMarkName = new String[addCount];
        
        
        block1 = "<!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\"><head>"
                + "<title>Быстрый старт. Размещение интерактивной карты на странице</title>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
        jsVersion = "<script src=\"https://api-maps.yandex.ru/2.1/?lang=ru_RU\" "
                + "type=\"text/javascript\"></script><script type=\"text/javascript\">";
        
        block2 = "</script><style type=\"text/css\">html,body,"
                + "#map{width:100%;height:100%;margin:0;padding:0;}"
                + "</style></head><body><div id=\"map\"></div></body></html>";
        jsBody = "ymaps.ready(init);var myMap";

        for(int i=0; i<addCount; i++){
            placeMarkName[i] = "PlaceMark" +i;
        }
        centerPlaceMark = "myMap = new ymaps.Map(\"map\", {center: ["
                + mapDraw.getCenterCoords(coords)
                + "],zoom: 15});"; 
    }
        
    String getPlaceMark(String[][] address, String[][] coords){
        String value;
        value = placeMarkName[count] + " = new ymaps.Placemark([" + coords[count][1] + "," + coords[count][0] +"],";
        value += "{hintContent: '" + address[count][1] +"', ";
        value += "balloonContent: '" + address[count++][2]  +"'});";
        return value;
    }
    
    String getJSBody(String value){
        
        for(String a: placeMarkName){
            jsBody += ", " + a;
        }
        jsBody += ";";
        jsBody += "function init(){ ";
        jsBody += centerPlaceMark;
        jsBody += "myMap.container.fitToViewport();"; //Fullscreen
        jsBody += value;
        
        for(String a: placeMarkName){
            jsBody += "myMap.geoObjects.add(" + a + ");";
        }        
        jsBody +="}";        
        return block1 + jsVersion + jsBody + block2;
    }
}
