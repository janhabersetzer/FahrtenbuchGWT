package com.janhabersetzer.fahrtenbuch.shared;


import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.janhabersetzer.fahrtenbuch.shared.report.AlleFahrtenVonFahrerReport;
import com.janhabersetzer.fahrtenbuch.shared.report.AlleFahrtenVonFahrzeugReport;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

@RemoteServiceRelativePath("fahrtenbuchservice")
public interface FahrtenbuchAdministration extends RemoteService {
	
	
	public void init() throws IllegalArgumentException;
	
	
	public Vector<Fahrzeug> test() throws IllegalArgumentException;
	
	/**
	   * Einen Fahrer anlegen.
	   * 
	   * @param first Vorname
	   * @param last Nachname
	   * @param eMail 
	   * @return Ein fertiges Fahrer-Objekt.
	   * @throws IllegalArgumentException
	   * 
	   */
	public Fahrer createFahrer(String first, String last, String eMail) throws IllegalArgumentException;
	
	/**
	 * Ein Fahrzeug anlegen
	 * @param regNo Nummernschild
	 * @param milage aktueller Kilometerstand
	 * @param descrition Modellbezeichung des Fahrzeugs
	 * @param color	Farbe
	 * @return Ein fertiges Fahrzeug-Objekt.
	 * @throws IllegalArgumentException
	 */
	public Fahrzeug createFahrzeug(String regNo, int milage, String description, String color) throws IllegalArgumentException;
	
	/**
	 * Eine Fahrt anlegen
	 * @param tripDate Fahrtdatum
	 * @param destDescr Zeilbeschreibung
	 * @param firstMilage Km(Start) zu Beginn der Fahrt
	 * @param secondMilage Km(End) zum Ende der Fahrt
	 * @param privateDist Anteil der PrivateKm
	 * @param workingDist Anteil der ArbeitswegKm
	 * @param companyDist Anteil der BetriebsfahrtKm
	 * @param Comment Kommentar
	 * @return Ein fertiges Fahrt-Objekt.
	 * @throws IllegalArgumentException
	 */
	public Fahrt createFahrt(Date tripDate, String destDescr, int firstMilage, int secondMilage, int privateDist,
			int workingDist, int companyDist,String comment,int vehicleId, int driverId) 
			throws IllegalArgumentException;	

	/**
	 * Einen Fahrer zu einer bestimmten Fahrt finden.
	 * @param t Fahrt-Objekt, zu dem ein Fahrer gefunden werden soll
	 * @return zugehöriges Fahrer-Object
	 * @throws IllegalArgumentException
	 */
	public Fahrer getFahrer(int id) throws IllegalArgumentException;
	
	
	/**
	 * Einen Fahrer zu einer bestimmten Fahrt finden.
	 * @param t Fahrt-Objekt, zu dem ein Fahrer gefunden werden soll
	 * @return zugehöriges Fahrer-Object
	 * @throws IllegalArgumentException
	 */
	public Fahrer getFahrer(Fahrt t) throws IllegalArgumentException;
	
	/**
	 * Einen Fahrer zu einer bestimmten Fahrt finden.
	 * @param email Sting, zu dem ein Fahrer gefunden werden soll
	 * @return zugehöriges Fahrer-Object
	 * @throws IllegalArgumentException
	 */
	public Fahrer getFahrerByEmail(String email) throws IllegalArgumentException;
	
	
	/**
	 * Prüft anhand der email ob ein Nutzer in der Datenbank vorhanden ist.
	 * @param email
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public boolean pruefeObFahrerNeu(String email) throws IllegalArgumentException;
	
	/**
	 * Alle Fahrer, die im System hinterlegt sind finden
	 * @return Vector<Fahrer> mit allen Fahrern
	 * @throws IllegalArgumentException
	 */
	
	public Vector<Fahrer> getAlleFahrer() throws IllegalArgumentException;
	
	
	/**
	 * Speichern eines Fahrers in der Datenbank
	 * @param d zu speicherndes Fahrer-Objekt
	 * @throws IllegalArgumentException
	 */
	
	public void saveFahrer(Fahrer d) throws IllegalArgumentException;
	
	/**
	 * Löschen eines Fahrers aus der Datenbank
	 * @param d zu löschendes Fahrer-Object
	 * @throws IllegalArgumentException
	 */
	
	public void deleteFahrer(int id) throws IllegalArgumentException;
	

	/**
	 * Ein Fahrzeug zu einer bestimmten id finden.
	 * @param id  zu dem ein Fahrzeug gefunden werden soll
	 * @return zugehöriges Fahrzeug-Object
	 * @throws IllegalArgumentException
	 */
	public Fahrzeug getFahrzeug(int id) throws IllegalArgumentException;
	
	
	/**
	 * Ein Fahrzeug zu einer bestimmten Fahrt finden.
	 * @param t Fahrt-Objekt, zu dem ein Fahrzeug gefunden werden soll
	 * @return zugehöriges Fahrzeug-Object
	 * @throws IllegalArgumentException
	 */
	public Fahrzeug getFahrzeug(Fahrt t) throws IllegalArgumentException;
	
	/**
	 * Alle Fahrzeuge, die im System hinterlegt sind finden
	 * @return Vector mit allen Fahrzeugen
	 * @throws IllegalArgumentException
	 */
	
	public Vector<Fahrzeug> getAlleFahrzeug() throws IllegalArgumentException;
	
	/**
	 * Speichern eines Fahrzeugs in der Datenbank
	 * @param v zu speicherndes Fahrzeug-Objekt
	 * @throws IllegalArgumentException
	 */
	
	public void saveFahrzeug(Fahrzeug v) throws IllegalArgumentException;
	
	/**
	 * Löschen eines Fahrzeugs aus der Datenbank
	 * @param v zu löschendes Fahrzeug-Object
	 * @throws IllegalArgumentException
	 */
	
	public void deleteFahrzeug(int id) throws IllegalArgumentException;
	
	/**
	 * Alle Fahrten eines Fahrers finden
	 * @param d Fahrer-Objekt 
	 * @return Vector mit allen Fahrten dieses Fahrers
	 * @throws IllegalArgumentException
	 */
	public Vector<Fahrt> getAlleFahrtenVonFahrer(int id) throws IllegalArgumentException;
	
	/**
	 * Alle Fahrten eines Fahrzeugs finden
	 * @param v Fahrzeug-Objekt 
	 * @return Vector mit allen Fahrten dieses Fahrzeugs
	 * @throws IllegalArgumentException
	 */
	public Vector<Fahrt> getAlleFahrtenVonFahrzeug(int id) throws IllegalArgumentException;
	
	/**
	 * Speichern einer Fahrt in der Datenbank
	 * @param t zu speicherndes Fahrzeug-Objekt
	 * @throws IllegalArgumentException
	 */
	
	public void saveFahrt(Fahrt t) throws IllegalArgumentException;
	
	/**
	 * Löschen einer Fahrt aus der Datenbank
	 * @param t zu löschendes Fahrt-Object
	 * @throws IllegalArgumentException
	 */
	
	public void deleteFahrt(int id) throws IllegalArgumentException;
	
	/**
	 * Alle Fahrten eines Fahrzeugs aus der Datenbank löschen
	 * @param v Fahrzeug dessen Fahrten gelöscht werden sollen
	 * @throws IllegalArgumentException
	 */
	public void deleteAlleFahrtenVonFahrzeug(int id) throws IllegalArgumentException;
	
	/**
	 * Einen AlleFahrtenVonFahrerReport anlegen
	 * @param d Fahrer-Objekt zu dem der Report angelegt wird
	 * @return AlleFahrtenVonFahrerReport-Objekt (String), dass mittels HTMLWriter in HTML übertragbar ist.
	 */
	public AlleFahrtenVonFahrerReport createAlleFahrtenVonFahrerReport(Fahrer d);
	
	/**
	 * Einen AlleFahrtenVonFahrzeugReport anlegen
	 * @param v Fahrzeug-Objekt zu dem der Report angelegt wird
	 * @return AlleFahrtenVonFahrzeugReport-Objekt (String), dass mittels HTMLWriter in HTML übertragbar ist.
	 */
	
	public AlleFahrtenVonFahrzeugReport createAlleFahrtenVonFahrzeugReport(Fahrzeug v);
	
}

