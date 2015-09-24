package isocost;

public class MAIN_IsoCostBuilder {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        @SuppressWarnings("unused")
        final boolean SHOW_MARKS_WITHOUT_PRICE = true; // пока не использовано
        final boolean ONLY_COORDS = false;

        MapUseAPIYandex yandexAPI;
        //MapDraw mapDraw = new MapDraw();

        FileCheckAndGo file = new FileCheckAndGo();
        
        
        file.fileInput(args);
        if (!file.isFileHasException()) {

            yandexAPI = new MapUseAPIYandex(file.getAddress());
            if(ONLY_COORDS){yandexAPI.setCoords(file.getCoords());}else{yandexAPI.getCoords();}
            
            if (!yandexAPI.isYAPIHasException()){
                file.fileOutput(yandexAPI.exportCoords(), "coords.txt");
                file.fileOutput(yandexAPI.getMap(), "map.htm");
                }
        }       
        System.out.println("Программа завершена.");
    }
}
