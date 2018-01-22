package com.janhabersetzer.fahrtenbuch.server.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;


public class FahrerMapper {
	
	/**
	 * Attribut des Sigleton-Patterns
	 */
	private static FahrerMapper fahrerMapper = null;
	
	/**
	 * privater Konstruktor des Sigleton-Patterns
	 */
	
	private FahrerMapper() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * methode des Sigleton Patterns
	 * @return DAS <code>FahrerMapper</code>-Objekt.
	 */
	public static FahrerMapper fahrerMapper(){
		if(fahrerMapper == null){
			fahrerMapper = new FahrerMapper();
		}
		return fahrerMapper;
	}
	
	/**
	 * methode um einen Fahrer anhand seiner id in der Datenbank zu finden
	 * @param id
	 * @return zur id passendes Fahrer Objekt, bei nicht vorhandenem Datensatz wird null zurückgegeben
	 */
	
	public Fahrer findByKey(int id){
		Connection con = null;
		PreparedStatement stmt = null;
		
		String selectByKey = "SELECT * FROM Fahrer WHERE idFahrer= ? ORDER BY idFahrer";
		
		try {
		     con= DBConnection.connection();
		     stmt = con.prepareStatement(selectByKey);
		     stmt.setInt(1, id);
		     
		     // execute SQL Statement
		     ResultSet rs = stmt.executeQuery();	 
		    		 
		     if (rs.next()) {
		       // Ergebnis-Tupel in Objekt umwandeln
		       Fahrer d = new Fahrer();
		       // Setzen der Attribute entspechenden des DB-Datensatzes
		       d.setId(rs.getInt("idFahrer"));
		       d.setVorname(rs.getString("Vorname"));
		       d.setNachname(rs.getString("Nachname"));
		       d.setEMail(rs.getString("EMail"));
		       return d;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
	}
	
	public Vector<Fahrer> findAll(){
		Connection con = null;
		PreparedStatement stmt = null;
		
		String selectAll = "SELECT * FROM Fahrer ORDER by idFahrer";
		
		//Vector erzeugen, der alle Fahrer-Datensätze aufnehmen kann
		Vector<Fahrer> result = new Vector<Fahrer>();
		
		try{
			// Verbindung und Abfrage
			con = DBConnection.connection(); 
			stmt = con.prepareStatement(selectAll);
			
			// SELECT Statement ausführen und in ResultSet einfügen
			ResultSet rs = stmt.executeQuery();
			
			// while Schleife, weil hier Viele Zeilen durchaufen werden müssen
			// schreiben der Objekt-Atrribute aus ResultSet
			while(rs.next()){
				Fahrer d = new Fahrer();
				d.setId(rs.getInt("idFahrer"));
				d.setVorname(rs.getString("Vorname"));
				d.setNachname(rs.getString("Nachname"));
				d.setEMail(rs.getString("EMail"));
				
				//Anstatt ein Ergebnis über return auszugeben muss hier zuerst der Vektor um d erweitert werden
				result.addElement(d);
			}		
		}catch (SQLException e2){
			e2.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * Einfuegen eines Fahrer-Objekts als neuer Tupel in die Datenbank
	 * @param d - einzufügendes Fahrer-Objekt
	 * @return d - eingefügtes Fahrer-Objekt 
	 */
	public Fahrer insert(Fahrer d){
	
		Connection con = null;
		PreparedStatement stmt = null;
		
		//Query für Abfrage der hoechsten ID (Primärschlüssel) in der Datenbank
		String maxIdSQL = "SELECT MAX(idFahrer) AS maxId FROM Fahrer";
		
		//Query fuer eigentlichen Insert
		String insertSQL = "INSERT INTO Fahrer(idFahrer,Vorname,Nachname,EMail) VALUES (?,?,?,?)";
		
		try{
			con = DBConnection.connection();
			stmt = con.prepareStatement(maxIdSQL);
			
			//maxid-Query ausführen
			ResultSet rs = stmt.executeQuery();
			
			// ....um diese dann um 1 inkrementiert der id des BO zuzuweisen.
			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein --> if-Bedingung
			if(rs.next()){
				d.setId(rs.getInt("maxId")+1);
			}
			
			// Jetzt erfolgt der INSERT. 
			stmt = con.prepareStatement(insertSQL);
			
			//Setzten der ? Platzhalter als "VALUES"
			stmt.setInt(1, d.getId());
			stmt.setString(2, d.getVorname());
			stmt.setString(3, d.getNachname());
			stmt.setString(4, d.getEMail());
			
			
			//INSERT-Query ausführen
			stmt.executeUpdate();
			
		}catch(SQLException e2){
			e2.printStackTrace();
			}
		return d;
	}
	
	/**
	 * Verändern eines bereits in der DB vorhandenen Datensatzes
	 * @param Fahrer-Objekt, das geändert werden soll
	 * @return das übergebene und geänderte Fahrer-Objekt
	 */
	
	public Fahrer update(Fahrer d){
		Connection con = null;
		PreparedStatement stmt = null;
		
		String updateSQL = "UPDATE Fahrer SET Vorname= ?,Nachname=?,EMail=? WHERE idFahrer=?";
		
		try{
			con=DBConnection.connection();
			stmt = con.prepareStatement(updateSQL);
			stmt.setString(1, d.getVorname());
			stmt.setString(2, d.getNachname());
			stmt.setString(3, d.getEMail());
			stmt.setInt(4, d.getId());
			stmt.executeUpdate();
			
		}catch(SQLException e2){
			e2.printStackTrace();
		}
		
		return d;
	}
	
	/**
	 * Löschen des Fahrers aus der DB
	 * @param Das zu löschende FahrerObjekt
	 * ACHTUNG !!!!!!! Darf nur möglich sein, wenn der Fahrer nicht mehr von Fahrten oder Fahrzeugen referenziert wird
	 * Diese Umsetzung steht noch aus!!!!!!!!
	 */
	
	public void delete(Fahrer d){
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		String deleteSQL= "DELETE FROM Fahrer WHERE idFahrer=?";
		
		try{
			con = DBConnection.connection();
			
			stmt = con.prepareStatement(deleteSQL);
			stmt.setInt(1, d.getId());
			
			stmt.executeUpdate();
			
		}catch(SQLException e2){
			e2.printStackTrace();
		}
	}
	


}