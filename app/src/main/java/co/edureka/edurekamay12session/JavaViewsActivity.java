package co.edureka.edurekamay12session;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class JavaViewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button btnSubmit = new Button(this);
        btnSubmit.setText("SUBMIT");
        btnSubmit.setBackgroundColor(Color.GREEN);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //.....
            }
        });



        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(Color.CYAN);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT,
                 RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        relativeLayout.addView(btnSubmit,params);

        setContentView(relativeLayout);

    }
}
