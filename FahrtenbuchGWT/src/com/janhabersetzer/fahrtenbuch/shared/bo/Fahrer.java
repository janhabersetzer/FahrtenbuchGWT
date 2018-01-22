package com.janhabersetzer.fahrtenbuch.shared.bo;

public class Fahrer extends BusinessObject {
	
	//Attribute
	
	private static final long serialVersionUID = 1L;
	
	private String vorname;
	private String nachname;
	private String eMail;
	
	
	//Getter und Setter
	
	public String getVorname() {
		return this.vorname;
	}
	
	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	
	public String getNachname(){
		return this.nachname;
	}
	
	public void setNachname(String nachname){
		this.nachname = nachname;
	}

	public String getEMail(){
		return this.eMail;
	}
	
	public void setEMail(String eMail){
		this.eMail = eMail;
	}
	
	public String toString() {
	    return super.toString() + " " + this.vorname + " " + this.nachname+ " " + this.eMail;
	  }
}
