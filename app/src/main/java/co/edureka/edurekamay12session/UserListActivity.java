package co.edureka.edurekamay12session;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //ListView listView;

    //GridView listView;
    RecyclerView recyclerView;
    ArrayList<UserModel> userList;
    UserAdapter userAdapter;

    UserRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        //listView = findViewById(R.id.listView);
        recyclerView = findViewById(R.id.recyclerView);

        userList = new ArrayList<>();

        UserModel u1 = new UserModel(R.drawable.category,"John Watson","john.watson@example.com");
        UserModel u2 = new UserModel(R.drawable.contact,"Jennie Watson","jennie.watson@example.com");
        UserModel u3 = new UserModel(R.drawable.pb,"Jim Watson","jim.watson@example.com");
        UserModel u4 = new UserModel(R.drawable.music,"Jack Watson","jack.watson@example.com");
        UserModel u5 = new UserModel(R.drawable.folder,"Joe Watson","joe.watson@example.com");
        UserModel u6 = new UserModel(R.drawable.pb,"Jim Watson","jim.watson@example.com");
        UserModel u7 = new UserModel(R.drawable.music,"Jack Watson","jack.watson@example.com");
        UserModel u8 = new UserModel(R.drawable.folder,"Joe Watson","joe.watson@example.com");


        // Size : n=5
        userList.add(u1); // 0
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);
        userList.add(u5); // 4
        userList.add(u6);
        userList.add(u7);
        userList.add(u8);



        //userAdapter = new UserAdapter(this,R.layout.list_item,userList);

        //listView.setAdapter(userAdapter);
        //listView.setOnItemClickListener(this);

        recyclerAdapter = new UserRecyclerAdapter(this,R.layout.list_item,userList);

        // ListView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        // GridView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,GridLayoutManager.DEFAULT_SPAN_COUNT);

        recyclerView.setLayoutManager(linearLayoutManager);

        //recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserModel user = userList.get(position);
        Toast.makeText(this,"You Selected: "+user.name,Toast.LENGTH_LONG).show();
    }
}
