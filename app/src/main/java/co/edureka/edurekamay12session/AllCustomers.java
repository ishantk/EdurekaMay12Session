package co.edureka.edurekamay12session;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class AllCustomers extends AppCompatActivity {

    ListView listView;
    ContentResolver resolver;
    SimpleCursorAdapter cursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customers);

        resolver = getContentResolver();

        listView = findViewById(R.id.listView);

        String[] projection = {Util.COL_ID,Util.COL_NAME,Util.COL_EMAIL,Util.COL_PHONE};
        Cursor cursor = resolver.query(Util.CUSTOMER_URI,projection,null,null,null);

        String[] from = {Util.COL_NAME,Util.COL_EMAIL};
        int[] to = new int[]{android.R.id.text1,android.R.id.text2};
        cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,from,to,0);
        listView.setAdapter(cursorAdapter);
    }
}
