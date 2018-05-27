package co.edureka.edurekamay12session;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BooksActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;

    ArrayList<String> bookList;
    ArrayList<Book> bookModelList;

    StringBuffer serverResponse;

    ProgressDialog progressDialog;


    RequestQueue requestQueue;
    StringRequest stringRequest;
    //JsonRequest

    ResponseReceiver responseReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        listView = findViewById(R.id.listView);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");


        responseReceiver = new ResponseReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("co.edureka.response.rcvd");

        registerReceiver(responseReceiver,filter);

        bookList = new ArrayList<>();
        bookModelList = new ArrayList<>();

        serverResponse = new StringBuffer();

        if(isInternetConnected()){
            //new BookFetcherThread().start(); // start the thread in the background
            //new BookFetcherTask().execute();
            //executeRequest();

            Intent intent = new Intent(BooksActivity.this,BookIntentService.class);
            //intent.putExtra();
            startService(intent);

        }else{
            Toast.makeText(this,"Please Connect to the Internet First!!",Toast.LENGTH_LONG).show();
        }

    }

    boolean isInternetConnected(){

        ConnectivityManager manager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return (networkInfo!=null && networkInfo.isConnected());
    }


    void executeRequest(){

        requestQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(Request.Method.GET,
                "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2",
                 new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        serverResponse.append(response);
                        processResponse();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
                );

        /*stringRequest = new StringRequest(Request.Method.POST,
                "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        serverResponse.append(response);
                        processResponse();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }

        )

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("name","John");
                //...
                return params;
            }
        }
        ;*/

        requestQueue.add(stringRequest);
    }


    // Background Thread which cannot update any UI over UI Thread(Activity)
    class BookFetcherThread extends Thread{
        @Override
        public void run() {

            try{

                URL url = new URL("http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // Send a Request to the server

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader buffer = new BufferedReader(reader);

                String data = null;

                while((data = buffer.readLine())!=null){
                    serverResponse.append(data);
                }

                Log.i("RESPONSE","From Server: "+serverResponse.toString());

                handler.sendEmptyMessage(101);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    // Handler is attached to the UI thread. This guy can update the UI.
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 101){
                processResponse();
            }
        }
    };


    class BookFetcherTask extends AsyncTask{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try{

                URL url = new URL("http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // Send a Request to the server

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader buffer = new BufferedReader(reader);

                String data = null;

                while((data = buffer.readLine())!=null){
                    serverResponse.append(data);
                }

                Log.i("RESPONSE","From Server: "+serverResponse.toString());


            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            processResponse();

            progressDialog.dismiss();


        }
    }

    void processResponse(){
        Toast.makeText(this,"Response: "+serverResponse.toString(),Toast.LENGTH_LONG).show();

        // JSON Parsing
        try{

            JSONObject jsonObject = new JSONObject(serverResponse.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("bookstore"); // Read the JSON Array from JSONObject

            String price="",name="",author="";

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jObj = jsonArray.getJSONObject(i);
                price = jObj.getString("price");
                name = jObj.getString("name");
                author = jObj.getString("author");

                bookList.add(name+"\n"+price+"\n"+author);

                // This is for Custom Adapter
                Book book = new Book(price,name,author);
                bookModelList.add(book);
            }

            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,bookList);
            listView.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    class ResponseReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("co.edureka.response.rcvd")) {
                String response = intent.getStringExtra("keyResponse");
                serverResponse.append(response);
                processResponse();
            }
        }

    }
}
