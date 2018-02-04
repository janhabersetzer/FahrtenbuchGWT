package com.janhabersetzer.fahrtenbuch.server.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class FahrzeugMapper {

	
	/**
	 * Attribut des Sigleton-Patterns
	 */
	private static FahrzeugMapper fahrzeugMapper = null;
	
	
	/**
	 * privater Konstruktor des Sigleton-Patterns
	 */
	private FahrzeugMapper() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * methode des Sigleton Patterns
	 * @return DAS <code>FahrzeugMapper</code>-Objekt.
	 */
	public static FahrzeugMapper fahrzeugMapper(){
		if(fahrzeugMapper == null){
			fahrzeugMapper = new FahrzeugMapper();
		}
		return fahrzeugMapper;
	}
	
	/**
	 * Methode um ein Fahrzeug anhand seiner id in der Datenbank zu finden.
	 * @param id
	 * @return das <code>Fahrzeug</code>-Objekt
	 */
	public Fahrzeug findByKey(int id){
		
		Connection con = null;
		
		PreparedStatement stmt = null;
		
		String selectByKey = "SELECT * FROM Fahrzeug WHERE idFahrzeug= ?";
		
		try{
			con = DBConnection.connection();
			
			stmt= con.prepareStatement(selectByKey);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				Fahrzeug v = new Fahrzeug();
				v.setId(rs.getInt("idFahrzeug"));
				v.setKennzeichen(rs.getString("Kennzeichen"));
				v.setModellBeschreibung(rs.getString("Modellbeschreibung"));
				v.setKm(rs.getInt("Kilometerstand"));
				v.setFarbe(rs.getString("Farbe"));
				return v;	
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;	
	}
	
	/**
	 * Methode um alle Fahrzeuge aus der Datenbank abzufragen
	 * @return Vector mit allen in der DB enthaltenen Fahrzeugen
	 */
	
	public Vector<Fahrzeug> findAll(){
		
		//Verbindungsobject zur DB
		Connection con = null;
		
		// Object für die Aufnahme der SQL Query
		PreparedStatement stmt = null;
		
		//Ergebinsvector
		Vector<Fahrzeug> result = new Vector<Fahrzeug>();
		
		//String mit der SQL Query
		String selectAll = "SELECT * FROM Fahrzeug ORDER by idFahrzeug";
		
		
		try{
			con = DBConnection.connection();
			
			stmt = con.prepareStatement(selectAll);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Fahrzeug v = new Fahrzeug();
				v.setId(rs.getInt("idFahrzeug"));
				v.setKennzeichen(rs.getString("Kennzeichen"));
				v.setModellBeschreibung(rs.getString("Modellbeschreibung"));
				v.setKm(rs.getInt("Kilometerstand"));
				v.setFarbe(rs.getString("Farbe"));
				
				//Anstatt ein Ergebnis über return auszugeben muss hier zuerst der Vektor um d erweitert werden
				result.add(v);
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return result;
	}
	
	
	public Fahrzeug insert(Fahrzeug v){
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		//Query für Abfrage der hoechsten ID (Primärschlüssel) in der Datenbank
		String maxIdSQL = "SELECT MAX(idFahrzeug) AS maxId FROM Fahrzeug";
		
		//Query fuer eigentlichen Insert
		String insertSQL = "INSERT INTO Fahrzeug(idFahrzeug,Kennzeichen,Kilometerstand,Modellbeschreibung,Farbe) VALUES (?,?,?,?,?)";
		
		try{
			// MaxId aus der Datenbank abfragen und dem Fahrzeug-Objekt zuweisen 
			con = DBConnection.connection();
			stmt = con.prepareStatement(maxIdSQL);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				v.setId(rs.getInt("maxId") + 1);
			}
			
			// Insert durchführen 
			stmt = con.prepareStatement(insertSQL);
			
			
			stmt.setInt(1, v.getId());
			stmt.setString(2, v.getKennzeichen());
			stmt.setInt(3, v.getKm());
			stmt.setString(4, v.getModellBeschreibung());
			stmt.setString(5, v.getFarbe());
			
			// Query ausführen
			stmt.executeUpdate();
			
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return v;	
	}
	
	/**
	 * Verändern eines bereits in der DB vorhandenen Datensatzes
	 * @param Fahrzeug-Objekt, das geändert werden soll
	 * @return das übergebene Fahrzeug-Objekt
	 */
	
	
	public Fahrzeug update(Fahrzeug v){
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		String updateSQL = "UPDATE Fahrzeug SET Kennzeichen = ?, Modellbeschreibung= ?, Kilometerstand = ?, Farbe= ? WHERE idFahrzeug = ?";
		
		try{
			con = DBConnection.connection();
			
			stmt = con.prepareStatement(updateSQL);
			
			
			stmt.setString(1, v.getKennzeichen());
			stmt.setString(2, v.getModellBeschreibung());
			stmt.setInt(3, v.getKm());
			stmt.setString(4, v.getFarbe());
			stmt.setInt(5, v.getId());
			
			stmt.executeUpdate();
			
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return v;
	}
	
	
	public void delete(Fahrzeug v){
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		
		String deleteSQL = "DELETE FROM Fahrzeug WHERE idFahrzeug = ?";
		
		try{
			con = DBConnection.connection();
			
			stmt = con.prepareStatement(deleteSQL);
			stmt.setInt(1, v.getId());
			
			stmt.executeUpdate();	
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}	
	}
	



//Ende der Klasse

}
