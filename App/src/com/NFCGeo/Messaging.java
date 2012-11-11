package com.NFCGeo;

import java.sql.ResultSet;

public class Messaging {
	
	public Messaging(){
		
	}
	
	public void Send(DatabaseHandler db, Message m){
		String temp = new String("");
		
		temp += ("INSERT INTO messaging(subject,sender,recipient,body) values(\'");
		temp += (m.getSubject() + "\',\'");
		temp += (m.getSender() + "\',\'");
		temp += (m.getRecipient() + "\',\'");
		temp += (m.getBody() + "\')");
		
		try{
		db.addDB(temp);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	public Message[] retrieveMessages(DatabaseHandler db, String user){
		String temp = new String("");
		Message[] m = null;
		
		temp += ("SELECT * FROM messaging WHERE recipient=\'");
		temp += (user + "\'");
		ResultSet r = null;
		
		try{
			r = db.queryTable(temp);
			int size = 0;
			while(r.next()){
				size++;
			}
			
			r.first();
			
			m = new Message[size];
			int i;
			
			for(i = 0; i < size; i++){
				m[i] = new Message();
				m[i].setSubject(r.getString("subject"));
				m[i].setSender(r.getString("sender"));
				m[i].setRecipient(r.getString("recipient"));
				m[i].setBody(r.getString("body"));
				r.next();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return m;
		
	}
}
