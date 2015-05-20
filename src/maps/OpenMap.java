package maps;


import com.example.trabus.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.*;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


//@SuppressLint("NewApi")
@SuppressLint("NewApi")
public class OpenMap extends Activity {
	GPSTracker gps;
	double latitude;
	double longitude;

   // @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapdisp);
     // Get a handle to the Map Fragment
       // GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

      //  LatLng sydney = new LatLng(-33.867, 151.206);

      //  map.setMyLocationEnabled(true);
       // map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
/*
        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
  */
        gps = new GPSTracker(OpenMap.this);

		// check if GPS enabled		
        if(gps.canGetLocation()){
        	
        	latitude = gps.getLatitude();
        	longitude = gps.getLongitude();
        	
        	// \n is for new line
        	Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
        }else{
        	// can't get location
        	// GPS or Network is not enabled
        	// Ask user to enable GPS/network in settings
        	gps.showSettingsAlert();
        }
		
       DisplayMarkerOnMap( latitude,longitude);        
        
        
    }


	//@SuppressLint("NewApi")
	//@SuppressLint("NewApi")
	private void DisplayMarkerOnMap(double lat, double longi) {
		// TODO Auto-generated method stub
		  GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

	        LatLng CurrentLocation = new LatLng(lat, longi);

	        map.setMyLocationEnabled(true);
	        map.moveCamera(CameraUpdateFactory.newLatLngZoom(CurrentLocation, 13));
	
	        map.addMarker(new MarkerOptions()
	                .title("Current Location")
	                .snippet("The most populous .")
	                .position(CurrentLocation));
	        
	  
		
	}
}
