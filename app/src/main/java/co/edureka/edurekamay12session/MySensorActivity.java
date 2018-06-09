package co.edureka.edurekamay12session;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MySensorActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener{

    TextView txtSensorData;
    Button btnShake;

    SensorManager sensorManager;
    Sensor sensor;

    void initViews(){
        txtSensorData = findViewById(R.id.textViewSensorData);
        btnShake = findViewById(R.id.buttonShake);
        btnShake.setOnClickListener(this);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sensor);
        initViews();
    }

    @Override
    public void onClick(View v) {

        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        /*float[] values = event.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        float cal = ((x*x)+(y*y)+(z*z))/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);

        if(cal>2){
            txtSensorData.setText("Shake Detected at "+x+" : "+y+" : "+z);
            sensorManager.unregisterListener(this);
        }else{
            txtSensorData.setText("Coordinates: "+x+" : "+y+" : "+z);
        }*/

        float direction = event.values[0];

        if(direction == 0){
            txtSensorData.setText("NORTH");
        }else if(direction == 90){
            txtSensorData.setText("EAST");
        }else if(direction == 180){
            txtSensorData.setText("SOUTH");
        }else if(direction == 270){
            txtSensorData.setText("WEST");
        }else{
            txtSensorData.setText("Detection in Progress....");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //sensorManager.unregisterListener(this);
    }
}
