package co.edureka.edurekamay12session;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<UserModel>{

    Context context;
    int resource;
    ArrayList<UserModel> objects;

    public UserAdapter(Context context, int resource, ArrayList<UserModel> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }


    // getView Method will be execute n number of times from 0 to n-1 (position -> 0 to n-1) | n is size of objects(ArrayList)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(resource,parent,false);
        //view is pointing to object of list_item (R.layout.list_item)

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView txtName = view.findViewById(R.id.textViewName);
        TextView txtEmail = view.findViewById(R.id.textViewEmail);

        UserModel user = objects.get(position);

        // Extracting data from Object and setting it on the list_item
        imageView.setBackgroundResource(user.image);
        txtName.setText(user.name);
        txtEmail.setText(user.email);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
