package com.NFCGeo;

/*NOTE: To be able to import the com.google.android libraries
 * you need to add this build path to the project:
 * C:\Users\Kaitlin\AppData\Local\Android\android-sdk\add-ons\addon-google_apis-google-8\libs\maps.jar
 * */
import java.sql.SQLException;
import java.util.List;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class ViewMapActivity extends MapActivity implements LocationListener
{    
    /* Called when the activity is first created. */
	@TargetApi(9)
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_map);
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
        MapController mapController = mapView.getController();
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setPositiveButton("OK", null);
    	
    	LocationService locServ = new LocationService();
    	GeoPoint p = null;
    	
        if (!locServ.gpsEnabled())
        {
        	builder.setMessage("GPS is not enabled.");
        	builder.show();
        	
        }
        else
        {
            p = locServ.getGeoPoint();        	
        }
        
        if (p== null)
        {
        	p = new GeoPoint(42023350,-93625622);
        }
        
        mapController.setCenter(p);
        
        mapController.setZoom(15);
        mapView.setBuiltInZoomControls(true);
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        
        //Sets marker image
        Drawable drawable = this.getResources().getDrawable(R.drawable.x);
        MapOverlay itemizedoverlay = new MapOverlay(drawable, this);
        
        // http://itouchmap.com/latlong.html
        
        Cache[] caches;
        
        try {
        	caches = Caching.GetLocation(42026167, -93648040, 1000000);
		} catch (Exception e) {
			builder.setMessage(e.toString() + "\n" + e.getMessage());
			builder.show();
			caches = new Cache[0];
		}
		
        for (int i = 0; i < caches.length; i++)
        {
        	OverlayItem overlayitem = new OverlayItem(new GeoPoint(caches[i].getLoc_lat(), caches[i].getLoc_long()), caches[i].getName(), "" + caches[i].getId());
            itemizedoverlay.addOverlay(overlayitem);
        }
        
        //Adds the overlay to the map
        mapOverlays.add(itemizedoverlay);
    }
 
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}
