package co.edureka.edurekamay12session;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>{


    Context context;
    int resource;
    ArrayList<UserModel> objects;

    public UserRecyclerAdapter(Context context, int resource, ArrayList<UserModel> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        UserModel user = objects.get(position);

        // Extracting data from Object and setting it on the list_item
        holder.imageView.setBackgroundResource(user.image);
        holder.txtName.setText(user.name);
        holder.txtEmail.setText(user.email);

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    // Hold the Views of list_item
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtName;
        TextView txtEmail;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtName = itemView.findViewById(R.id.textViewName);
            txtEmail = itemView.findViewById(R.id.textViewEmail);

        }
    }

}
