package app.example.www.neartome;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.spec.MGF1ParameterSpec;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Bundle extras;
    double presentlat;
    double presentlon;
    double frndlat;
    double frndlon;
    String personName;
    GoogleSignInAccount acct;

    LatLng position;
    double lon;
    double lat;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        /*extras = getIntent().getExtras();
        if (extras != null) {
            presentlat = extras.getDouble("presentlatitude");
            presentlon = extras.getDouble("presentlongitude");
            frndlat = extras.getDouble("frndlatitude");
            frndlon = extras.getDouble("frndlongitude");
            System.out.println("In maps_activity: ");
            System.out.println(presentlat+" "+presentlon+" "+frndlon+" "+frndlat);
            // and get whatever type user account id is
        }
        else
        {
            System.out.println("extras is null");
            return;
        }*/

        //Add a marker in Sydney and move the camera
        LatLng loc1 = new LatLng(presentlat,presentlon);
        mMap.addMarker(new MarkerOptions().position(loc1).title("saranya"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc1));

        LatLng loc2 = new LatLng(frndlat,frndlon);
        mMap.addMarker(new MarkerOptions().position(loc2).title("srikhar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc2));

        LatLng loc3 = new LatLng(17.4380202,78.4256295);
        mMap.addMarker(new MarkerOptions().position(loc3).title("rosita"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc3));

    }

}
