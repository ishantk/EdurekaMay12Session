package co.edureka.edurekamay12session;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ViewsActivity extends AppCompatActivity {

    AutoCompleteTextView autoTxt;
    ArrayAdapter<String> adapter;

    Spinner spinner;
    ArrayAdapter<String> cityAdapter;

    TextView txtDateTime;
    Button btnShow;

    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener dateSetListener;

    TimePickerDialog timePickerDialog;
    TimePickerDialog.OnTimeSetListener timeSetListener;

    void showTimePicker(){

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay+" : "+minute;
                txtDateTime.setText(time);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(this,timeSetListener,hh,mm,true);
        timePickerDialog.show();
    }

    void showDatePicker(){

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth+"/"+(month+1)+"/"+year;
                txtDateTime.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int mm = calendar.get(Calendar.MONTH);
        int yy = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(this,dateSetListener,yy,mm,dd);
        datePickerDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);

        autoTxt = findViewById(R.id.autoCompleteTextView);
        spinner = findViewById(R.id.spinner);

        txtDateTime = findViewById(R.id.textViewDateTime);
        btnShow = findViewById(R.id.button);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDatePicker();
                showTimePicker();
            }
        });

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Handbags");    //0
        adapter.add("Handkerchiefs");
        adapter.add("Wallet");
        adapter.add("Walnuts");
        adapter.add("Shirts");
        adapter.add("Trousers");
        adapter.add("Shoes");       //n-1

        cityAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        cityAdapter.add("--Select City--");
        cityAdapter.add("New Delhi");
        cityAdapter.add("Bengaluru");
        cityAdapter.add("Hyderabad");
        cityAdapter.add("Chennai");
        cityAdapter.add("Pune");
        cityAdapter.add("Mumbai");

        autoTxt.setAdapter(adapter);
        spinner.setAdapter(cityAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = cityAdapter.getItem(position);
                if(position !=0 ){
                    Toast.makeText(ViewsActivity.this,"You Selected: "+city,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
