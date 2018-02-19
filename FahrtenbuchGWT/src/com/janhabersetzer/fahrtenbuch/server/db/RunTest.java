package com.janhabersetzer.fahrtenbuch.server.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;


public class RunTest {

	public static void main(String[] args) throws Exception {
		
		// TODO Auto-generated method stub
		
		
		testInsertFahrt();
		
	}
	
	
	public static  void testFindByKey() throws Exception{
		

		//FahrerMapper intanzieren
		
		FahrtMapper tm = FahrtMapper.fahrtMapper();
		
		//Objekt abfragen
		Fahrt t = tm.findByKey(1);
		
		System.out.println(t);

	}
	
	public static void testFindFahrerByEmail(){
		FahrerMapper dMapper = FahrerMapper.fahrerMapper();
		String emailAdress = "jh200@hdm-stuttgart.de";
		
		Fahrer dFahrer = dMapper.findByEmail(emailAdress);
		System.out.println(dFahrer);
	}
	
public static  void testFindByFahrer() throws Exception{
		

		//FahrerMapper intanzieren
		
		FahrtMapper tm = FahrtMapper.fahrtMapper();
		
		Fahrer d = new Fahrer();
		d.setId(1);
		
		//Objekt abfragen
		Vector <Fahrt> tv  = tm.findByFahrer(d);
		
		for (Enumeration<Fahrt> e = tv.elements(); e.hasMoreElements();){
			 System.out.println(e.nextElement() + " ");
		}

	}

public static  void testFindByFahrzeugId() throws Exception{
	

	//FahrerMapper intanzieren
	
	FahrtMapper tm = FahrtMapper.fahrtMapper();
	
	Fahrzeug v = new Fahrzeug();
	v.setId(1);
	
	//Objekt abfragen
	Vector <Fahrt> tv  = tm.findByFahrzeug(v);
	
	if(tv.isEmpty()){
		System.out.println("Kein Ergebnis");
	}else{
		for (Enumeration<Fahrt> e = tv.elements(); e.hasMoreElements();){
			 System.out.println(e.nextElement() + " ");
		}
	}
}
	
public static  void testFindAll() throws Exception{
		

		//FahrerMapper intanzieren
		
		FahrtMapper tm = FahrtMapper.fahrtMapper();
		
		//Objekt abfragen
		Vector <Fahrt> tv = tm.findAll();
		
		for (Enumeration<Fahrt> e = tv.elements(); e.hasMoreElements();){
			 System.out.println(e.nextElement() + " ");
		}
		
		
	}
	public static  void testInsertFahrzeug() throws Exception{
		
		//Objekt anlegen
				Fahrzeug v= new Fahrzeug();
				v.setKennzeichen("S-JJ 0811");
				v.setKm(11);
				v.setModellBeschreibung("A-Klasse");
				v.setFarbe("schwarz");

		try{
		//FahrerMapper intanzieren
		FahrzeugMapper vm = FahrzeugMapper.fahrzeugMapper();
		
		//in DB schreiben
		vm.insert(v);
		System.out.println(v.getId()+"Erfolgreicher Insert");
		
		}catch(Exception e){
			System.out.println("So ein Sch...");
		}
	
	}
	
	public static  void testInsertFahrt() throws Exception{
		
		//Objekt anlegen
				
				Date fdate =new Date();
				
				Date bdate = new Date();
				
				
				Fahrt t= new Fahrt();
				t.setFahrtDatum(fdate);
				t.setKmStart(30000);
				t.setKmEnd(30100);
				t.setPrivatKm(100);
				t.setArbeitswegKm(0);
				t.setBetriebsfahrtKm(0);
				t.setZielBeschreibung("Oma");
				t.setKommentar("Datum Test");
				t.setSourceFahrzeugId(1);
				t.setSourceFahrerId(1);
				t.setBearbeitungsdatum(bdate);
		try{
		//FahrerMapper intanzieren
		FahrtMapper tm = FahrtMapper.fahrtMapper();
		
		//in DB schreiben
		tm.insert(t);
		System.out.println(t.getId()+"Erfolgreicher Insert");
		
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
public static  void testUpdateFahrzeug() throws Exception{
		
		//Objekt anlegen
				Fahrzeug v= new Fahrzeug();
				v.setId(3);
				v.setKennzeichen("S-JJ 0811");
				v.setModellBeschreibung("C-Klasse");
				v.setKm(0000);
				v.setFarbe("schwarz-grau");
		try{
		//FahrerMapper intanzieren
		FahrzeugMapper vm = FahrzeugMapper.fahrzeugMapper();
		
		//in DB schreiben
		vm.update(v);
		System.out.println(v.getId()+"Erfolgreiches Update");
		
		}catch(Exception e){
			System.out.println("So ein Sch...");
		}
	
	}
	

	public static  void testUpdateFahrt() throws Exception{
	
		//Objekt anlegen
		Date fdate = new Date();
		
		Date bdate = new Date();
		
		
		Fahrt t= new Fahrt();
		t.setId(6);
		t.setFahrtDatum(fdate);
		t.setKmStart(30000);
		t.setKmEnd(30100);
		t.setPrivatKm(100);
		t.setArbeitswegKm(0);
		t.setBetriebsfahrtKm(0);
		t.setZielBeschreibung("RusslandFahrt");
		t.setSourceFahrzeugId(1);
		t.setSourceFahrerId(1);
		t.setBearbeitungsdatum(bdate);
		try{
		//FahrerMapper intanzieren
		FahrtMapper tm = FahrtMapper.fahrtMapper();
		
		//in DB schreiben
		tm.update(t);
		System.out.println(t.getId()+"Erfolgreiches Update");
		
		}catch(Exception e){
			System.out.println("So ein Sch...");
		}

	}
	
	public static  void testDeleteFahrzeug() throws Exception{
		
		FahrzeugMapper vm = FahrzeugMapper.fahrzeugMapper();
		
		
		Fahrzeug v = new Fahrzeug();
		v.setId(3);
		
		
		vm.delete(v.getId());
		System.out.println("Erfolgreich gelöscht");
	}
	
public static  void testDeleteAlleFahrtenOfFahrzeug() throws Exception{
		
		FahrtMapper tm = FahrtMapper.fahrtMapper();
		
		Fahrzeug v = new Fahrzeug();
		v.setId(2);
		
		
		tm.deleteAlleFahrtenOfFahrzeug(v.getId());
		System.out.println("Erfolgreich gelöscht");
	}
	
	public static ArrayList<String> get() throws Exception{
		
		ArrayList<String> array = new ArrayList<String>();
		
		try{
			//1. Get a connection to the database
			Connection testCon = DBConnection.connection();
			PreparedStatement statement = testCon.prepareStatement("SELECT * FROM Fahrer");
			ResultSet result = statement.executeQuery();
			
			
			
			while(result.next()){
				 System.out.print(result.getString("Vorname"));
				 System.out.print(" ");
				 System.out.println(result.getString("Nachname"));
				 array.add(result.getString("Vorname"));
				 array.add(result.getString("Nachname"));
				 
			 }
			System.out.println("All records have been seleced");
			return array;

			}
		catch(Exception e){
			System.out.print("Fuck...went wrong");
			return null;
		}
	}

}
