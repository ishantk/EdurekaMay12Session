package co.edureka.edurekamay12session;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener{

    Button btnGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        btnGoBack = findViewById(R.id.buttonGoBack);
        btnGoBack.setOnClickListener(this);

        //btnSomeOtherBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.buttonGoBack){
            finish();
        }
    }
}