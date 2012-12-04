package com.NFCGeo;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationService extends MapActivity implements LocationListener{

	private Location loc;
	private LocationManager lm;
	
     public LocationService()
     {
    	 lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    	 loc = null;
     }
     
     public boolean gpsEnabled()
     {
    	 LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	 return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
     }
     
     
     public GeoPoint getGeoPoint()
     {
    	 loc = getLocation();
    	 if (loc != null)
    		 return new GeoPoint((int)(1000000*loc.getLatitude()), 
    			 (int)(1000000*loc.getLongitude()));
    	 else
    		 return new GeoPoint(0,0);
     }
     
    public Location getLocation()
    {
    	lm.requestSingleUpdate(new Criteria(), this, null);
    	return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);    	
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

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
