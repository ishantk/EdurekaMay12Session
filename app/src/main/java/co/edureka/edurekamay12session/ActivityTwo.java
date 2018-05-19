package co.edureka.edurekamay12session;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener{

    Button btnGoBack;
    EditText eTxtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        btnGoBack = findViewById(R.id.buttonGoBack);
        eTxtData = findViewById(R.id.editTextData);

        btnGoBack.setOnClickListener(this);

        //btnSomeOtherBtn.setOnClickListener(this);

        // get Intent Object passed by ActivityOne
        //Intent rcv = getIntent();
        //String name = rcv.getStringExtra("keyName");
        //String phone = rcv.getStringExtra("keyPhone");

        /*Bundle bundle = rcv.getBundleExtra("keyBundle");
        String name = bundle.getString("keyName");
        String phone = bundle.getString("keyPhone");
        int age = bundle.getInt("keyAge");*/

        //User user = (User)rcv.getSerializableExtra("keyUser");

        //eTxtData.setText(user.name+" - "+user.phone+" - "+user.age);
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.buttonGoBack){

            String str = eTxtData.getText().toString();

            // An Empty Intent containing only data
            Intent data = new Intent();
            data.putExtra("keyData",str);

            setResult(201,data);
            finish();
        }
    }
}
