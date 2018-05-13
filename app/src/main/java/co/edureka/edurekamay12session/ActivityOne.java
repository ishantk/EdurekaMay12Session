package co.edureka.edurekamay12session;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
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

    MyReceiver myReceiver;
    YourReceiver yourReceiver;

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

        //initMyReceiver();
        initYourReceiver();
        Toast.makeText(this,"Receiver Initialized and Registered",Toast.LENGTH_LONG).show();
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

        if(myReceiver!=null) {
            // No More listening to the System Events
            unregisterReceiver(myReceiver);
        }

        if(yourReceiver != null){
            // No More listening to the Custom Events
            LocalBroadcastManager.getInstance(this).unregisterReceiver(yourReceiver);
        }
    }

    public void clickHandler(View view){

        /*String dateTimeStamp = new Date().toString();

        //Toast.makeText(this,"You Clicked Button\nIts: "+dateTimeStamp,Toast.LENGTH_LONG).show();
        String name = eTxtName.getText().toString();
        String phone = eTxtPhone.getText().toString();
        Toast.makeText(this,"You Entered: "+name+" - "+phone,Toast.LENGTH_LONG).show();
        */

        // Launch a New Activity

        // Implicit Intent -> We need to have an action name
        //Intent intent = new Intent("co.edureka.edurekamay12session.activitytwo");
        //startActivity(intent);

        // Explicit Intent -> No action is required
        //Intent intent = new Intent(ActivityOne.this,NewsActivity.class);
        //Intent intent = new Intent(getApplicationContext(),ActivityThree.class);
        //Intent intent = new Intent(ActivityOne.this,ViewsActivity.class);
        //startActivity(intent);

        Intent intent = new Intent("custom.action");
        // We are going to broadcast custom action
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }

    void initYourReceiver(){

        // Construct BroadcastReceiver
        yourReceiver = new YourReceiver();

        // Subscribe to the Custom Events which are Intent Action
        IntentFilter filter = new IntentFilter();
        filter.addAction("a.b.c.d");
        filter.addAction("p.q.r.s");
        filter.addAction("custom.action");

        // Register the Receiver to listen to the Local or Custom Events
        LocalBroadcastManager.getInstance(this).registerReceiver(yourReceiver,filter);
    }

    void initMyReceiver(){

        // Construct BroadcastReceiver
        myReceiver = new MyReceiver();

        // Subscribe to the System Level Events which are Intents
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);

        filter.addDataScheme("package");

        // Register the Receiver to listen to the Events
        registerReceiver(myReceiver,filter);

    }

    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            String packageName = intent.getData().getSchemeSpecificPart();


            if(action.equals(Intent.ACTION_PACKAGE_ADDED)){
                Toast.makeText(context,"Package Installed: "+packageName,Toast.LENGTH_LONG).show();
                Log.i("MyReceiver","Package Installed: "+packageName);

            }
            if(action.equals(Intent.ACTION_PACKAGE_REMOVED)){
                Toast.makeText(context,"Package UnInstalled: "+packageName,Toast.LENGTH_LONG).show();
                Log.i("MyReceiver","Package UnInstalled: "+packageName);
            }
            //...
            //..
            //.
        }
    }

    class YourReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("custom.action")){
                Toast.makeText(context,"Custom Action Tracked",Toast.LENGTH_LONG).show();
                Log.i("YourReceiver","Custom Action Tracked");

            }
        }
    }
}
