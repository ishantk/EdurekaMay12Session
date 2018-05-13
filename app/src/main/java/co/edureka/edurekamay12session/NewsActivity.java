package co.edureka.edurekamay12session;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NewsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapter.add("Zee News"); // 0
        adapter.add("Aaj Tak");  // 1
        adapter.add("CNN");      // 2
        adapter.add("BBC");      // 3
        adapter.add("NDTV");     // 4

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String news = adapter.getItem(position);
        Toast.makeText(this,"News: "+news,Toast.LENGTH_LONG).show();

        if(position == 0){
            Intent intent = new Intent(NewsActivity.this,ShowNewsActivity.class);
            startActivity(intent);
        }

    }
}
