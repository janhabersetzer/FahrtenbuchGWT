package com.janhabersetzer.fahrtenbuch.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;



import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;


public class FahrtMapper {
	
	//Singleton-Pattern
	private static FahrtMapper fahrtMapper = null;
	

	private FahrtMapper(){
		
	}
	

	public static FahrtMapper fahrtMapper(){
		if(fahrtMapper==null){
			fahrtMapper=new FahrtMapper();
		}
		return fahrtMapper;
	}
	
	
	//Methoden-Teil
	
	
	
	/**
	 * Methode zum suchen einer Fahrt in der DB
	 * @param id
	 * @return Fahrt-Objekt mit allen Attributen AUSSER Fremdschlüsseln
	 */
	
	public Fahrt findByKey(int id){
		
		Connection con = null;
		
		PreparedStatement stmt = null;
		
		String selectByKey ="SELECT * FROM Fahrt WHERE idFahrt =?"; 
		
		try{
			//Verbindung aufbauen
			con = DBConnection.connection();
			
			//Query aufbaunen und an stmt-Objekt übergeben
			stmt = con.prepareStatement(selectByKey);
			stmt.setInt(1, id);
			
			
			// Query ausführen und Ergebnis in rs-Objekt übergeben
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				return result2Object(rs);
			}	
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Methode um alle Fahrten aus der Datenbank abzufragen
	 * @return Vector mit allen Fahrt-Objekten der DB
	 */
	
	public Vector<Fahrt>findAll(){
		
		//Verbindungsobject zur DB
		Connection con = null;
				
		// Object für die Aufnahme der SQL Query
		PreparedStatement stmt = null;
		
		//Ergebinsvector
		Vector<Fahrt> result = new Vector<Fahrt>();
				
		//String mit der SQL Query
		String selectAll = "SELECT * FROM Fahrt ORDER by idFahrt";
		
		try{
			con = DBConnection.connection();
			
			stmt = con.prepareStatement(selectAll);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				result.add(result2Object(rs));
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return result;
	}
	/**
	 * Methode um alle Fahrten eines Fahrers aus der Datenbank abzufragen
	 * @param id eines Fahrers
	 * @return Vector  mit Fahrt-Objekten, die als Fremdschlüssen die Fahrer-id haben
	 */
	
	public Vector<Fahrt> findByFahrerId(int id){
		
		Connection con = null;
		
		PreparedStatement stmt = null;
		
		Vector<Fahrt> result = new Vector<Fahrt>();
		
		String selectByFahrerId = "SELECT * FROM Fahrt where Fahrer_idFahrer = ?"; 
		
		try{
			con = DBConnection.connection();
			
			stmt = con.prepareStatement(selectByFahrerId);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				result.add(result2Object(rs));
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Methode um alle Fahrten eines Fahrers aus der Datenbank abzufragen
	 * @param Fahrer Object
	 * @return Vector  mit Fahrt-Objekten, die als Fremdschlüssen die Fahrer-id haben
	 */
	
	public Vector<Fahrt> findByFahrer(Fahrer d){
		
		int id = d.getId();
		Vector<Fahrt> result = findByFahrerId(id);
		return result;
	}
	
	/**
	 * Methode um alle Fahrten eines Fahrzeugs aus der Datenbank abzufragen
	 * @param id des Fahrzeugs
	 * @return Vector mit Fahrten dieses Fahrzeugs
	 */
	
	public Vector<Fahrt> findByFahrzeugId(int id){
		
		Connection con = null;
		
		PreparedStatement stmt = null;
		
		Vector<Fahrt> result = new Vector<Fahrt>();
		
		String selectByFahrzeugId = "SELECT * FROM Fahrt where Fahrzeug_idFahrzeug = ?"; 
		
		try{
			con = DBConnection.connection();
			
			stmt = con.prepareStatement(selectByFahrzeugId);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				result.add(result2Object(rs));
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Methode um alle Fahrten eines Fahrzeugs aus der Datenbank abzufragen
	 * @param v betreffendens Fahrzeug-Object
	 * @return Vector mit Fahrten dieses Fahrzeugs
	 */

	public Vector<Fahrt> findByFahrzeug(Fahrzeug v){
		
		int id = v.getId();
		Vector<Fahrt> result = findByFahrzeugId(id);
		return result;
	}
	
	/**
	 * Methode um ein <code>Fahrt</code>-Object in die Datenbank einzufügen 
	 * @param t in die Datenbank einzufügendes <code>Fahrt</code>-Object
	 * @return
	 */
	public Fahrt insert(Fahrt t){
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		//Query für Abfrage der hoechsten ID (Primärschlüssel) in der Datenbank
		String maxIdSQL = "SELECT MAX(idFahrt) AS maxId FROM Fahrt";
		
		//Query fuer eigentlichen Insert
		String insertSQL = "INSERT INTO" 
							+" Fahrt(idFahrt,Fahrtdatum,KmStart,KmEnd,PrivateKm,ArbeitswegKm,BetriebsfahrtKm,Zielbeschreibung,Kommentar, Bearbeitungsdatum,Fahrzeug_idFahrzeug,Fahrer_idFahrer)" 
							+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			// MaxId aus der Datenbank abfragen und dem Fahrzeug-Objekt zuweisen 
			con = DBConnection.connection();
			stmt = con.prepareStatement(maxIdSQL);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				t.setId(rs.getInt("maxId") + 1);
			}
			
			// Insert durchführen 
			//1.Fahrtdatum konvertieren
			Date fahrtDatumUntil = t.getFahrtDatum();
			
			SimpleDateFormat formatterSDF = new SimpleDateFormat("yyyy-MM-dd");
			
			String mysqlFDatumString = formatterSDF.format(fahrtDatumUntil);
			
			//2.Bearbeitungsdatum von konvertieren
			
			Date bearbeitungsDatumUntil = t.getBearbeitungsdatum();
			
			SimpleDateFormat formatterSDF2 = new SimpleDateFormat("yyyy-MM-dd");
			
			String mysqlBDatumString = formatterSDF2.format(bearbeitungsDatumUntil);
			
			
			//Query vorbereiten
			stmt = con.prepareStatement(insertSQL);
			stmt.setInt(1, t.getId());
			stmt.setString(2, mysqlFDatumString);
			stmt.setInt(3, t.getKmStart());
			stmt.setInt(4, t.getKmEnd());
			stmt.setInt(5, t.getPrivatKm());
			stmt.setInt(6, t.getArbeitswegKm());
			stmt.setInt(7, t.getBetriebsfahrtKm());
			stmt.setString(8, t.getZielBeschreibung());
			stmt.setString(9, t.getKommentar());
			stmt.setString(10, mysqlBDatumString);
			stmt.setInt(11, t.getSourceFahrzeugId());
			stmt.setInt(12, t.getSourceFahrerId());
			
			// Query ausführen
			stmt.executeUpdate();
			
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return t;	
	}
	
	/**
	 * Verändern (Update) eines bereits in der DB vorhandenen Datensatzes
	 * @param t Fahrt-Objekt, das geändert werden soll
	 * @return das übergebene Fahrzeug-Objekt
	 */
	
	public Fahrt update(Fahrt t){
		Connection con = null;
		PreparedStatement stmt = null;
		
		String updateSQL = "UPDATE Fahrt SET Fahrtdatum = ?, KmStart= ?, KmEnd = ?, PrivateKm= ?,  ArbeitswegKm= ?,BetriebsfahrtKm= ?,Zielbeschreibung= ?,Kommentar= ?,"
							+" Bearbeitungsdatum= ?,Fahrzeug_idFahrzeug= ?,Fahrer_idFahrer= ? WHERE idFahrt = ?";
		
		try{
			con = DBConnection.connection();
			
			stmt = con.prepareStatement(updateSQL);
			
			//1.Fahrtdatum konvertieren
			Date fahrtDatumUntil = t.getFahrtDatum();
			
			SimpleDateFormat formatterSDF = new SimpleDateFormat("yyyy-MM-dd");
			
			String mysqlFDatumString = formatterSDF.format(fahrtDatumUntil);
			
			//2.Bearbeitungsdatum von konvertieren
			
			Date bearbeitungsDatumUntil = t.getFahrtDatum();
			
			SimpleDateFormat formatterSDF2 = new SimpleDateFormat("yyyy-MM-dd");
			
			String mysqlBDatumString = formatterSDF2.format(bearbeitungsDatumUntil);
			

			//Query vorbereiten
			stmt = con.prepareStatement(updateSQL);

			stmt.setString(1, mysqlFDatumString);
			stmt.setInt(2, t.getKmStart());
			stmt.setInt(3, t.getKmEnd());
			stmt.setInt(4, t.getPrivatKm());
			stmt.setInt(5, t.getArbeitswegKm());
			stmt.setInt(6, t.getBetriebsfahrtKm());
			stmt.setString(7, t.getZielBeschreibung());
			stmt.setString(8, t.getKommentar());
			stmt.setString(9, mysqlBDatumString);
			stmt.setInt(10, t.getSourceFahrzeugId());
			stmt.setInt(11, t.getSourceFahrerId());
			stmt.setInt(12, t.getId());
			
			stmt.executeUpdate();
			
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
		return t;
	}
	
	/**
	 * Methode zum löschen einer Fahrt aus der Datenbank
	 * @param t zu löschendes Fahrt-Objekt
	 */
	
	public void delete(int id){
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		
		String deleteSQL = "DELETE FROM Fahrt WHERE idFahrt = ?";
		
		try{
			con = DBConnection.connection();
			
			stmt = con.prepareStatement(deleteSQL);
			stmt.setInt(1, id);
			
			stmt.executeUpdate();	
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}	
	}
	
	/**
	 * Methode zum Löschen aller Fahrten eines Fahrzeugs
	 * @param t das Fahrzeugobjekt, dessen Fahrten gelöscht werden
	 */
	
	public void deleteAlleFahrtenOfFahrzeug(int id){
		Connection con = null;
		PreparedStatement stmt = null;
		
		
		String deleteSQL = "DELETE FROM Fahrt WHERE Fahrzeug_idFahrzeug=?";
		
		try{
			con = DBConnection.connection();
			
			stmt = con.prepareStatement(deleteSQL);
			stmt.setInt(1, id);
			
			stmt.executeUpdate();	
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
	}
	
	
	/**
	 * Methode um ein Element des ResultSet's in ein Fahrt-Objekt zu verwandeln.
	 * Diese Hilfsmethode dient vor allem dazu, redundanten Code zu vermeinden.
	 * Diese Hilfsmethode kann die Stelle des Fahrt Objects eingesetzt werden.
	 * Besonderheit: Da der verwendete MySQL Driver JDBC 4.2 nicht unterstützt 
	 * und LocalDate verwendet wird, muss hier explizit der Datentyp angepasst werden
	 * @param rs
	 * @return Ein geschreibenenes Fahrt-Object aus dem Result-Set
	 */
	
	private Fahrt result2Object(ResultSet rs){
		try{
			Fahrt t = new Fahrt();
			t.setId(rs.getInt("idFahrt"));
			//Hier die Umwandlung des Datentyps
			Date fahrtDatum = new Date(rs.getDate("Fahrtdatum").getTime());
			t.setFahrtDatum(fahrtDatum);
			t.setKmStart(rs.getInt("KmStart"));
			t.setKmEnd(rs.getInt("KmEnd"));
			t.setPrivatKm(rs.getInt("PrivateKm"));
			t.setArbeitswegKm(rs.getInt("ArbeitswegKm"));
			t.setBetriebsfahrtKm(rs.getInt("BetriebsfahrtKm"));
			t.setZielBeschreibung(rs.getString("Zielbeschreibung"));
			t.setKommentar(rs.getString("Kommentar"));
			//Hier die Umwandlung des Datentyps 
			Date bearbeitungsDatum = new Date(rs.getDate("Bearbeitungsdatum").getTime());
			t.setBearbeitungsdatum(bearbeitungsDatum);
			t.setSourceFahrzeugId(rs.getInt("Fahrzeug_idFahrzeug"));
			t.setSourceFahrerId(rs.getInt("Fahrer_idFahrer"));
			return t;
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
	}
}
