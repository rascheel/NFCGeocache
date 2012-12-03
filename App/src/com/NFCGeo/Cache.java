package com.NFCGeo;

public class Cache {
	/** < Unique hardware ID of NFC tag*/
	private String id;			
	/** < Name of Cache (determined by creator) */
	private String name;		
	/** < Lattitude location */
	private int loc_lat;		
	/** < Longitude location */
	private int loc_long;		
	/** < Username of creator */
	private String creator;	
	/** < Number of times Cache has been found */
	private int timesFound;	
	/** < Average user rating */
	private int rating;		
	
	public Cache()
	{
		this.id = "";
		this.name = "";
		this.loc_lat = 0;
		this.loc_long = 0;
		this.creator = "";
		this.timesFound = 0;
		this.rating = 0;
	}
	
	public Cache(String id, String name, int loc_lat, int loc_long, String creator) {
		this.id = id;
		this.name = name;
		this.loc_lat = loc_lat;
		this.loc_long = loc_long;
		this.creator = creator;
		this.timesFound = 0;
		this.rating = 0;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLoc_lat() {
		return loc_lat;
	}
	public void setLoc_lat(int loc_lat) {
		this.loc_lat = loc_lat;
	}
	public int getLoc_long() {
		return loc_long;
	}
	public void setLoc_long(int loc_long) {
		this.loc_long = loc_long;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public int getTimesFound() {
		return timesFound;
	}
	public void setTimesFound(int timesFound) {
		this.timesFound = timesFound;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
