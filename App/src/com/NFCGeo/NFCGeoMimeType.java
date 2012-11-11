package com.NFCGeo;

/**
 * This is simply a place to store our application's custom MIME type to write to the 
 * NFC tags to identify them as specific to our application, rather than any other.
 * @author Alexander Maxwell
 *
 */
public class NFCGeoMimeType {
	public static final String NFCGEO_MIMETYPE = "application/com.nfcgeo.cache";
	public static final String NFCGEO_INTENT_URI_STRING = "#Intent;component=com.NFCGeo"
			+ "/.ScanCacheActivity;end";

}
