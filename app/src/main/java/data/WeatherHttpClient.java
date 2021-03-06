package data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import util.Utils;

public class WeatherHttpClient {


    private Bitmap bitmap;

    public String getWeatherData(String place) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        InputStream input=null;
        OutputStream output=null;


        try {
            connection = (HttpURLConnection) (new URL(Utils.BASE_URL + place)).openConnection();
            connection.connect();
            if (connection.getResponseCode()!= HttpURLConnection.HTTP_OK){
                return "serveur reurned HTTP"+connection.getResponseCode()+"   "+connection.getResponseMessage();

            }

            //Read the response
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while ((line = bufferedReader.readLine()) != null)
                stringBuffer.append(line + "\r\n");

            inputStream.close();
            connection.disconnect();
            return stringBuffer.toString();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

}