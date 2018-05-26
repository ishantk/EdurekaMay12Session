package co.edureka.edurekamay12session;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileIOActivity extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener{

    EditText editText;
    Button button;

    // Try to Implement One Time Login !!
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public static final String KEY_EMAIL = "keyEmail";
    public static final String KEY_AGE = "keyAge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);

        editText = findViewById(R.id.editTextData);
        button = findViewById(R.id.buttonSubmit);

        button.setOnClickListener(this);

        preferences = getSharedPreferences("myPrefs",MODE_PRIVATE); // by default myPrefs.xml
        editor = preferences.edit();

        preferences.registerOnSharedPreferenceChangeListener(this);

        // Read Operation
        if(preferences.contains(KEY_EMAIL)){
            String email = preferences.getString(KEY_EMAIL,"NA");
            int age = preferences.getInt(KEY_AGE,0);
            getSupportActionBar().setTitle("Welcome, "+email+" | "+age);
            editText.setText(email+" | "+age);
        }

    }


    void writeDataInInternalFile(){

        try {

            String data = editText.getText().toString();

            FileOutputStream fos = openFileOutput("data.txt",MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();

            Toast.makeText(this,"Contents Written..",Toast.LENGTH_LONG).show();
            editText.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void readFromInternalFile(){

        try {

            FileInputStream fis = openFileInput("data.txt");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(fis)); // java.io

            String data = buffer.readLine();


            Toast.makeText(this,"Contents Read..",Toast.LENGTH_LONG).show();
            editText.setText(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void writeDataInExternalFile(){

        try {

            String data = editText.getText().toString();

            // Obtain Path of SD-Card
            String path = Environment.getExternalStorageDirectory().getPath();

            File file = new File(path,"edureka_courses.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();

            Toast.makeText(this,"Contents Written..",Toast.LENGTH_LONG).show();
            editText.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void readFromExternalFile(){

        try {

            // Obtain Path of SD-Card
            String path = Environment.getExternalStorageDirectory().getPath();

            File file = new File(path,"edureka_courses.txt");

            FileInputStream fis = new FileInputStream(file);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(fis)); // java.io

            String data = buffer.readLine();


            Toast.makeText(this,"Contents Read..",Toast.LENGTH_LONG).show();
            editText.setText(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void checkSDCardState(){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){

            String path = Environment.getExternalStorageDirectory().getPath();

            Toast.makeText(this,"SD Card is Mounted: "+path,Toast.LENGTH_LONG).show();
            Log.i("FileIOActivity","path is: "+path);
        }else{
            Toast.makeText(this,"SD Card is not Mounted",Toast.LENGTH_LONG).show();
        }
    }

    void saveEmail(){

        // Write Operation
        String email = editText.getText().toString();
        editor.putString("keyEmail",email);
        editor.putInt("keyAge",30);

        editor.apply(); // apply is to save the data in XML File in background
        Toast.makeText(this,"Data Saved in SharedPreference",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

        //writeDataInInternalFile();
        //readFromInternalFile();
        //checkSDCardState();

        //writeDataInExternalFile();
        //readFromExternalFile();

        saveEmail();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Write your use case...
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // No More Listening on the SharedPreferences...
        preferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
