package co.edureka.edurekamay12session;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class ActivityThree extends AppCompatActivity {


    LinearLayout linearLayout;
    Button button;

    void initViews(){
        linearLayout = new LinearLayout(this);
        button = new Button(this);
        button.setText("Submit");

        linearLayout.addView(button);

        setContentView(linearLayout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_three);
        //initViews();
        //setContentView(R.layout.activity_three_relative);
        //setContentView(R.layout.activity_three_frame);
        setContentView(R.layout.activity_three_table);
    }
}
