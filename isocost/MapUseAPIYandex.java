package isocost;

//import java.io.BufferedReader; /// использовать
//import java.io.DataInputStream; /// использовать
//import java.io.DataOutputStream; /// использовать
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner; /// не использовать.

import javax.net.ssl.HttpsURLConnection;


public class MapUseAPIYandex {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String query = "https://geocode-maps.yandex.ru/1.x/?geocode=";
    private String htmlJSPage = "";
    private String[][] coords, addresses;
    private boolean isException = false;
    
    private HTML_JS_Yandex mapGen;

    public MapUseAPIYandex(String[][] addresses) {
        coords = new String[addresses.length][2];
        this.addresses = addresses;
    }

    String[][] getCoords() {

        try {
            for (int i = 0; i < addresses.length; i++) {
                
                if(i%50==00) {System.out.println("Выполнено: " + (double)i/addresses.length*100 + "%\t i: " + i);}
                if(i%500==0) {
                    try{
                        Thread.sleep(1000);}
                    catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

                URL myurl = new URL(query + addresses[i][0]);
                HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();

                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);
                try{
                if (con.getResponseCode() == 200) {

                    Scanner nsc = new Scanner(con.getInputStream());
                    String value = "";
                    boolean noCoords = true;
                    while (nsc.hasNext()) {
                        value = nsc.nextLine();
                        if (value.contains("<pos>")) {
                            noCoords = false;
                            break;
                        }
                    }
                    nsc.close();

                    if (!noCoords) {
                        value = StaticXmlParser.getTextFromXml(value.trim());
                        coords[i] = value.split(" ");
                    } else {System.out.println("no coords. i: " + i + ". address: " + addresses[i][0] + "\t(" + this.getClass() + ")");}

                } else {
                    System.out.println("Ошибка в запросе к геокодеру. /n" + con.getResponseCode() + "\t"
                            + con.getResponseMessage() + addresses[i][0]);
                }} catch (SocketTimeoutException e) {
                    i--;
                    System.out.println("Timeout on i:" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    continue;
                    
                }
            }
        }  catch (ConnectException e) {
            isException = true;
            System.out.println("Нет соединения с сервером геокодирования Yandex. " + e);
        } catch (MalformedURLException e) {
            isException = true;
            System.out.println("Ошибка в ссылке. " + e);
        } catch (IOException e) {
            isException = true;
            System.out.println("Проблема с соединением. " + e);
        } finally {
        }

        return coords;
    }

    boolean isYAPIHasException() {
        return isException;
    }

    String getMap() {
        mapGen = new HTML_JS_Yandex(addresses.length, coords);
        for (int i = 0; i < addresses.length; i++) {
            htmlJSPage += mapGen.getPlaceMark(addresses, coords);
        }
        
        htmlJSPage = mapGen.getJSBody(htmlJSPage);
        return htmlJSPage;
    }
    
    void setCoords(String[][] sC){coords = sC;}
    
    String exportCoords() {
        StringBuilder txtCoords = new StringBuilder();
       
        for(int i = 0; i< coords.length; i++){
            txtCoords.insert(0, i + "\t");
            txtCoords.insert(0, coords[i][1] + " " + coords[i][0] + "\t" + addresses[i][0]);
            txtCoords.insert(0, System.getProperty("line.separator"));
                }
        return txtCoords.toString();
    }
    
    
}
