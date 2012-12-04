package com.NFCGeo;

/*NOTE: To be able to import the com.google.android libraries
 * you need to add this build path to the project:
 * C:\Users\Kaitlin\AppData\Local\Android\android-sdk\add-ons\addon-google_apis-google-8\libs\maps.jar
 * */
import java.util.ArrayList;

import com.google.android.maps.OverlayItem;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;

public class MapOverlay extends ItemizedOverlay<OverlayItem> 
{
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	
	public MapOverlay(Drawable defaultMarker, Context context) {
	  super(boundCenter(defaultMarker));
	  mContext = context;
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
	  return mOverlays.get(i);
	}

	@Override
	public int size() {
	  return mOverlays.size();
	}
	
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = mOverlays.get(index);
	  
	  Intent intent = new Intent(mContext, ViewPointActivity.class);
	  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	  intent.putExtra("cacheID", item.getSnippet());
	  intent.putExtra(ScanCacheActivity.CACHE_FOUND, false);
	  
	  
	  mContext.startActivity(intent);
	  
	  return true;
	}
}
