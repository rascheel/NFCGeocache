package com.NFCGeo;

public class Cache {
	private int id;
	private String name;
	private int loc_lat;
	private int loc_long;
	private String creator;
	private int timesFound;
	private int rating;
	
	public Cache()
	{
		this.id = 0;
		this.name = "";
		this.loc_lat = 0;
		this.loc_long = 0;
		this.creator = "";
		this.timesFound = 0;
		this.rating = 0;
	}
	
	public Cache(int id, String name, int loc_lat, int loc_long, String creator, int timesFound, int rating) {
		this.id = id;
		this.name = name;
		this.loc_lat = loc_lat;
		this.loc_long = loc_long;
		this.creator = creator;
		this.timesFound = timesFound;
		this.rating = rating;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
