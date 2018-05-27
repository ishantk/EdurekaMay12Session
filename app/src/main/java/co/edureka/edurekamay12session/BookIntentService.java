package co.edureka.edurekamay12session;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


// Service Executes in the Background
public class BookIntentService extends IntentService {

    StringBuffer serverResponse;

    public BookIntentService(){
        super("BookIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("BookIntentService","BookIntentService Started...");

        try{

            URL url = new URL("http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // Send a Request to the server

            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(reader);

            String data = null;
            serverResponse = new StringBuffer();

            while((data = buffer.readLine())!=null){
                serverResponse.append(data);
            }

            Log.i("BookIntentService","From Server: "+serverResponse.toString());


            Intent intent1 = new Intent("co.edureka.response.rcvd");
            intent1.putExtra("keyResponse",serverResponse.toString());
            sendBroadcast(intent1);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
