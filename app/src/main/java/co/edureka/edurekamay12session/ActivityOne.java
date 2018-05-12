package co.edureka.edurekamay12session;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class ActivityOne extends AppCompatActivity {


    // Declare References to the Views
    EditText eTxtName, eTxtPhone;

    // When Object of Activity is Constructed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding the Layout(Views) on Activity
        setContentView(R.layout.activity_one);

        // After the setContentView initialize the references
        eTxtName = findViewById(R.id.editText);
        eTxtPhone = findViewById(R.id.editText2);

        Log.i("ActivityOne","==onCreate==");
    }

    // When Activity id pushed into the stack
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ActivityOne","==onStart==");
    }

    // When Activity is in stopped state and we are going to re launch it
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ActivityOne","==onRestart==");
    }

    // When Activity is visible to the User where user can interact with it
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ActivityOne","==onResume==");
    }

    // When Activity is covered partially with some other activity, but user cannot interact
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityOne","==onPause==");
    }

    // When Activity is covered completely with some other activity, and hence user cannot interact at all
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityOne","==onStop==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityOne","==onDestroy==");
    }

    public void clickHandler(View view){

        String dateTimeStamp = new Date().toString();

        //Toast.makeText(this,"You Clicked Button\nIts: "+dateTimeStamp,Toast.LENGTH_LONG).show();
        String name = eTxtName.getText().toString();
        String phone = eTxtPhone.getText().toString();
        Toast.makeText(this,"You Entered: "+name+" - "+phone,Toast.LENGTH_LONG).show();


    }
}
