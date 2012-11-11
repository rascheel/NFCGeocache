package com.NFCGeo;

public class Message {
	
	private String sender;
	private String recipient;
	private String body;
    private String subject;
	
    public Message()
    {
    }

	public Message(String s, String r, String b, String sub){
		sender = s;
		recipient = r;
		body = b;
        subject = sub;
	}
	
	public String getSender(){
		return sender;
	}
	
	public String getRecipient(){
		return recipient;
	}
	
	public String getBody(){
		return body;
	}

    public String getSubject(){
        return subject;
    }
	
	public void setSender(String s){
		sender = s;
	}
	
	public void setRecipient(String r){
		recipient = r;
	}
	
	public void setBody(String b){
		body = b;
	}

    public void setSubject(String sub){
        subject = sub;
    }

    @Override
    public String toString()
    {
        String retString = "";
        retString += "From: " + sender + "\n";
        retString += "Subject: " + subject;
        return retString;
    }
}
