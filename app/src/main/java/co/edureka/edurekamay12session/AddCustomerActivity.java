package co.edureka.edurekamay12session;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eTxtName,eTxtEmail,eTxtPhone;
    Button btnAdd;

    Customer customer;

    ContentResolver resolver;
    ArrayList<Customer> customers;

    void initViews(){

        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);
        eTxtPhone = findViewById(R.id.editTextPhone);

        btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(this);

        customer = new Customer();

        resolver = getContentResolver();
        customers = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        initViews();
    }

    void addCustomer(){

        // Data which we want to insert
        ContentValues values = new ContentValues(); // HashMap : Key is the Column of Table, Value is the data which you want to insert
        values.put(Util.COL_NAME,customer.name);
        values.put(Util.COL_EMAIL,customer.email);
        values.put(Util.COL_PHONE,customer.phone);

        Uri uri = resolver.insert(Util.CUSTOMER_URI,values);



        if(Integer.parseInt(uri.getLastPathSegment())>0) {
            Toast.makeText(this,customer.name+" Added at ID: "+uri.getLastPathSegment(),Toast.LENGTH_LONG).show();
            clearFields();
        }else{
            Toast.makeText(this,customer.name+" Not Added. Please Try Again !!",Toast.LENGTH_LONG).show();
        }

    }

    void updateCustomer(){

        // Data which we want to insert
        ContentValues values = new ContentValues(); // HashMap : Key is the Column of Table, Value is the data which you want to insert
        values.put(Util.COL_NAME,customer.name);
        values.put(Util.COL_EMAIL,customer.email);
        values.put(Util.COL_PHONE,customer.phone);

        int id = 3; // HardCoded Value
        String where = Util.COL_ID+" = "+id;
        int i = resolver.update(Util.CUSTOMER_URI,values,where,null);


        if(i>0) {
            Toast.makeText(this,customer.name+" Updated at ID: "+id,Toast.LENGTH_LONG).show();
            clearFields();
        }else{
            Toast.makeText(this,customer.name+" Not Updated. Please Try Again !!",Toast.LENGTH_LONG).show();
        }

    }

    void deleteCustomer(){

        int id = 3; // HardCoded Value
        String where = Util.COL_ID+" = "+id;
        int i = resolver.delete(Util.CUSTOMER_URI,where,null);


        if(i>0) {
            Toast.makeText(this,customer.name+" Deleted at ID: "+id,Toast.LENGTH_LONG).show();
            clearFields();
        }else{
            Toast.makeText(this,customer.name+" Not Deleted. Please Try Again !!",Toast.LENGTH_LONG).show();
        }

    }

    void retrieveCustomers(){
        String[] projection = {Util.COL_ID,Util.COL_NAME,Util.COL_EMAIL,Util.COL_PHONE};
        Cursor cursor = resolver.query(Util.CUSTOMER_URI,projection,null,null,null);

        if(cursor!=null){
            while (cursor.moveToNext()){
                Customer customer = new Customer();
                customer.id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
                customer.name = cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
                customer.email = cursor.getString(cursor.getColumnIndex(Util.COL_EMAIL));
                customer.phone = cursor.getString(cursor.getColumnIndex(Util.COL_PHONE));

                customers.add(customer); // Process to display customer data on ListView/RecyclerView

                Log.i("Customers",customer.toString());
            }

            cursor.close(); // release the resources
        }
    }


    void clearFields(){
        eTxtName.setText("");
        eTxtPhone.setText("");
        eTxtEmail.setText("");
    }

    @Override
    public void onClick(View v) {

        customer.name = eTxtName.getText().toString();
        customer.email = eTxtEmail.getText().toString();
        customer.phone = eTxtPhone.getText().toString();

        //addCustomer();
        //updateCustomer();
        //deleteCustomer();
        //retrieveCustomers(); // This should be done in an AsyncTask when data is high in volume

        Intent intent = new Intent(AddCustomerActivity.this,AllCustomers.class);
        startActivity(intent);
    }
}
