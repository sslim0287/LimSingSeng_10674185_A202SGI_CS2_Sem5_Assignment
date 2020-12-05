package com.example.a202sgi_cs2_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class UserGoogleMapRouteInti extends AppCompatActivity {

    private EditText etCurrentLocation, etDestinationLocation;
    private Button btnDisplay;

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_google_map_route_inti);

        supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.google_map);
        client = LocationServices.getFusedLocationProviderClient(this);

        //Check permission
        if(ActivityCompat.checkSelfPermission(UserGoogleMapRouteInti.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            //When permission granted
            //Call method
            getCurrentLocation();

        }else{
            //When permission denied
            //Request permission
            ActivityCompat.requestPermissions(UserGoogleMapRouteInti.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);

        }

        etCurrentLocation = (EditText)findViewById(R.id.etSourceLocation);
        etDestinationLocation = (EditText)findViewById(R.id.etDestinationLocation);
        btnDisplay = (Button)findViewById(R.id.btnSearch);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentLocation = etCurrentLocation.getText().toString().trim();
                String destinationLocation = etDestinationLocation.getText().toString().trim();

                if(currentLocation.equals("")&& destinationLocation.equals("")){
                    // When both blank
                    Toast.makeText(UserGoogleMapRouteInti.this, "Enter both Location", Toast.LENGTH_SHORT).show();
                }else{
                    DisplayTrackLocation(currentLocation,destinationLocation);
                }

            }

            private void DisplayTrackLocation(String currentLocation, String destinationLocation) {
                try{
                    // When google map installed
                    Uri uri = Uri.parse("https://www.google.com/maps/dir/" + currentLocation + "/"
                     + destinationLocation);

                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);

                    intent.setPackage("com.google.android.apps.maps");

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);

                }catch (ActivityNotFoundException e){
                    //When google map not installed
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");

                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);
                }

            }
        });

    }

    private void getCurrentLocation() {
        //Initialize task Location
        @SuppressLint("MissingPermission")
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                //When success
                if(location!= null){
                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            // initialize lat and lng
                            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                            //Create marker options
                            MarkerOptions options = new MarkerOptions().position(latLng).title("I am there");
                            //Zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                            //Add marker on map
                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 44){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //When permission granted
                //Call method
                getCurrentLocation();

            }
        }
    }
}