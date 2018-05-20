package co.edureka.edurekamay12session;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class ConfigDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_demo);
        Log.i("ConfigDemoActivity","==onCreate==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ConfigDemoActivity","==onDestroy==");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.i("ConfigDemoActivity","==onConfigurationChanged LANDSCAPE=="+newConfig.orientation);
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i("ConfigDemoActivity","==onConfigurationChanged PORTRAIT=="+newConfig.orientation);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Read the XML File and Construct the menu
        getMenuInflater().inflate(R.menu.mymenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.fav:

                break;

            case R.id.add:
                Intent intent = new Intent(ConfigDemoActivity.this,ActivityOne.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
