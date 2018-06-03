package co.edureka.edurekamay12session;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.List;

public class MyLocationActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    TextView txtLocation;
    Button btnFetch;

    // To Access Google's Services
    GoogleApiClient apiClient;
    GoogleApiClient.Builder builder; // builds the GoogleApiClient

    void initViews() {

        txtLocation = findViewById(R.id.textViewLocation);
        btnFetch = findViewById(R.id.buttonFetch);
        btnFetch.setOnClickListener(this);

        builder = new GoogleApiClient.Builder(this);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        builder.addApi(LocationServices.API);

        apiClient = builder.build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        initViews();
    }

    @Override
    public void onClick(View v) {
        apiClient.connect(); // Request to create connection with Google's Location Service
    }

    // ConnectionFailed
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // ConnectionCallBacks
    // onConnected gets executed when Connection with Google's Location Service is established as hit on the click of button
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        //fetchLastKnownLocation();
        fetchLocation();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    void fetchLocation() {

        @SuppressLint("RestrictedApi")
        LocationRequest request = new LocationRequest(); // I want location after every 10 secs. min is 5.
        request.setInterval(10000);
        request.setFastestInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Please grant permissions to access location in settings",Toast.LENGTH_LONG).show();
        }else {
            LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, request, this);
        }

    }

    void fetchLastKnownLocation(){
        // Code here to get User's Last Known Location
        // If Location exists you get it else it will give a null. Not a preferable choice.

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Please grant permissions to access location in settings",Toast.LENGTH_LONG).show();
        }else {
            Location location = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            if(location != null){
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                txtLocation.setText("Location: "+latitude+" : "+longitude);
            }else{
                txtLocation.setText("Location Not found..");
            }
        }
    }

    // Will be executed again and again. To be precise after every 10 secs.
    @Override
    public void onLocationChanged(Location location) {

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        txtLocation.setText("Location: "+latitude+" : "+longitude);

        // No More Location Update Required. Its like get only one location change.
        LocationServices.FusedLocationApi.removeLocationUpdates(apiClient,this);

        StringBuilder builder = new StringBuilder();

        try {

            // Reverse Geocoding -> Fetch Address from Mathematical Numbers i.e. latitude and longitude
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude,5); // We are requesting 5 addresses near to the latitude and longitude which we passed

            // Can Iterate over the Address List one by one
            /*for(Address address : addresses){

            }*/

            if(addresses !=null && addresses.size()>0){

                Address address = addresses.get(0); // this is the closest address

                for(int i=0;i<=address.getMaxAddressLineIndex();i++){
                    builder.append(address.getAddressLine(i)+"\n");
                }

                txtLocation.setText("Location: "+latitude+" : "+longitude+"\n\n"+builder.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        // No More Location Changes Required
        LocationServices.FusedLocationApi.removeLocationUpdates(apiClient,this);
    }*/
}
