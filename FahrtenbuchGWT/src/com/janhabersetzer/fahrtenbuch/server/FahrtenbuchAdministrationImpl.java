package com.janhabersetzer.fahrtenbuch.server;


import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.janhabersetzer.fahrtenbuch.server.db.FahrerMapper;
import com.janhabersetzer.fahrtenbuch.server.db.FahrtMapper;
import com.janhabersetzer.fahrtenbuch.server.db.FahrzeugMapper;
import com.janhabersetzer.fahrtenbuch.shared.report.AlleFahrtenVonFahrerReport;
import com.janhabersetzer.fahrtenbuch.shared.report.AlleFahrtenVonFahrzeugReport;
import com.janhabersetzer.fahrtenbuch.shared.report.Column;
import com.janhabersetzer.fahrtenbuch.shared.report.CompositeParagraph;
import com.janhabersetzer.fahrtenbuch.shared.report.HTMLReportWriter;
import com.janhabersetzer.fahrtenbuch.shared.report.Row;
import com.janhabersetzer.fahrtenbuch.shared.report.SimpleParagraph;
import com.janhabersetzer.fahrtenbuch.shared.report.Test;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministration;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class FahrtenbuchAdministrationImpl extends RemoteServiceServlet implements FahrtenbuchAdministration {
	
	private static final long serialVersionUID = 1L;

	private FahrerMapper dMapper = null;
	
	private FahrzeugMapper vMapper = null;
	
	private FahrtMapper tMapper = null;
	
	private HTMLReportWriter writer = null;
	
	/**
	 * No-Argument-Konstruktor muss vorhanden sein
	 * @throws IllegalArgumentException
	 */
	public FahrtenbuchAdministrationImpl() throws IllegalArgumentException {
	  }

	@Override
	public void init() throws IllegalArgumentException {
		
		this.dMapper = FahrerMapper.fahrerMapper();
		this.vMapper = FahrzeugMapper.fahrzeugMapper();
		this.tMapper = FahrtMapper.fahrtMapper();

	}
	
	/*
	 * ****************ENDE INITIALISIERUNG********************************
	 */
	
	@Override
	public Vector<Fahrzeug> test(){
		
		Vector<Fahrzeug> result = new Vector<>();
		
		Fahrzeug aFahrzeug = new Fahrzeug();
		aFahrzeug.setId(1);
		aFahrzeug.setFarbe("schwarz");
		aFahrzeug.setKennzeichen("S-JH 200");
		aFahrzeug.setKm(100);
		aFahrzeug.setModellBeschreibung("Polo");
		result.add(aFahrzeug);
		
		Fahrzeug bFahrzeug = new Fahrzeug();
		aFahrzeug.setId(2);
		aFahrzeug.setFarbe("weiss");
		aFahrzeug.setKennzeichen("S-JG 900");
		aFahrzeug.setKm(200);
		aFahrzeug.setModellBeschreibung("Golf");
		result.add(bFahrzeug);
		
		Fahrzeug cFahrzeug = new Fahrzeug();
		aFahrzeug.setId(3);
		aFahrzeug.setFarbe("gelb");
		aFahrzeug.setKennzeichen("S-XY 1900");
		aFahrzeug.setKm(300);
		aFahrzeug.setModellBeschreibung("Passat");
		result.add(cFahrzeug);
		
		return result;
	}

	@Override
	public Fahrer createFahrer(String first, String last, String eMail) throws IllegalArgumentException {
		
		//FahrerObjekt anlegen
		Fahrer d = new Fahrer();
		d.setId(1);
		d.setVorname(first);
		d.setNachname(last);
		d.setEMail(eMail);
		
		return this.dMapper.insert(d);
	}

	@Override
	public Fahrzeug createFahrzeug(String regNo, int milage, String description, String color)
			throws IllegalArgumentException {
		Fahrzeug v = new Fahrzeug();
		v.setKennzeichen(regNo);
		v.setKm(milage);
		v.setModellBeschreibung(description);
		v.setFarbe(color);
		
		return this.vMapper.insert(v);
	}

	@Override
	public Fahrt createFahrt(Date tripDate, String destDescr, int firstMilage, int secondMilage, int privateDist,
			int workingDist, int companyDist, String comment, int vehicleId, int driverId) throws IllegalArgumentException {
		
		
		//Bearbeitungsdatum mit aktuellem Datum setzen
		Date bdate = new Date();
		
		
		Fahrt t= new Fahrt();
		t.setFahrtDatum(tripDate);
		t.setZielBeschreibung(destDescr);
		t.setKmStart(firstMilage);
		t.setKmEnd(secondMilage);
		t.setPrivatKm(privateDist);
		t.setArbeitswegKm(workingDist);
		t.setBetriebsfahrtKm(companyDist);
		t.setKommentar(comment);
		t.setSourceFahrzeugId(vehicleId);
		t.setSourceFahrerId(driverId);
		t.setBearbeitungsdatum(bdate);
		
		
		/*
		 * Wichtig ist, das beim Speichern der Fahrt, ebenfalls der Kilometerstand des Fahrzeugs erhöht wird.
		 * Dehalb holen wir updaten wir den Kilometerstand des Fahrzeugs siehe <code>updateMilage</code>
		 */
		
		this.updateMilage(t);
		
		// Dann 
	
		return this.tMapper.insert(t);
		
	}
	
	/**
	 * Methode um den Kilometerstand eines Fahrzeugs auf den Endkilometerstand einer Fahrt upzudaten
	 *  Sie dient als eine Art Hilfsmethode 
	 * @return
	 */
	
	private Fahrzeug updateMilage(Fahrt t){
		
		Fahrzeug v = this.vMapper.findByKey(t.getSourceFahrzeugId());
		
		 //....updaten den Kilometerstand des Objekts...
		v.setKm(t.getKmEnd());
		 
		 //....und updaten die Datenbank, indem wir das Objekt mit dem neuen Kilometerstand in die DB schreiben
		return this.vMapper.update(v);
	}
	
	@Override
	public Fahrer getFahrer(int id) throws IllegalArgumentException {
		
		//Den Fahrer anhand der FahrerId aus der Datenbank auslesen
		
		return dMapper.findByKey(id);
	}

	@Override
	public Fahrer getFahrer(Fahrt t) throws IllegalArgumentException {
		
		//Den Fahrer anhand der FahrerId aus der Datenbank auslesen
		
		return dMapper.findByKey(t.getSourceFahrerId());
	}
	
	@Override
	public Fahrer getFahrerByEmail(String email) throws IllegalArgumentException{
		return dMapper.findByEmail(email);
	}
	
	@Override
	public boolean pruefeObFahrerNeu(String email) throws IllegalArgumentException {

		if (dMapper.findByEmail(email) == null) {
			return true;
		}
		return false;
		
	}

	
	@Override
	public Vector<Fahrer> getAlleFahrer() throws IllegalArgumentException {
		return this.dMapper.findAll();
	}

	@Override
	public void saveFahrer(Fahrer d) throws IllegalArgumentException {
		dMapper.insert(d);
	}
	
	@Override
	public void updateFahrer(Fahrer d) throws IllegalArgumentException {
		dMapper.update(d);
	}

	@Override
	public void deleteFahrer(int id) throws IllegalArgumentException {
		
		//Alle Fahrten dieses Fahrers löschen
		tMapper.deleteAlleFahrtenOfFahrer(id);
		//Fahrer löschen
		dMapper.delete(id);
	}
	
	@Override
	public Fahrzeug getFahrzeug(int id) throws IllegalArgumentException {
				
		//Den Fahrer anhand der FahrerId aus der Datenbank auslesen
				
		return vMapper.findByKey(id);
	}

	@Override
	public Fahrzeug getFahrzeug(Fahrt t) throws IllegalArgumentException {
				
		//Den Fahrer anhand der FahrerId aus der Datenbank auslesen
				
		return vMapper.findByKey(t.getSourceFahrzeugId());
	}

	@Override
	public Vector<Fahrzeug> getAlleFahrzeug() throws IllegalArgumentException {
		return vMapper.findAll();
	}


	@Override
	public void saveFahrzeug(Fahrzeug v) throws IllegalArgumentException {
		vMapper.insert(v);
	}
	
	@Override
	public void updateFahrzeug(Fahrzeug v) throws IllegalArgumentException {
		vMapper.update(v);
	}

	@Override
	public void deleteFahrzeug(int id) throws IllegalArgumentException {
		//Alle Fahrten dieses Fahrzeugs löschen
		tMapper.deleteAlleFahrtenOfFahrzeug(id);
		//Fahrzeug löschen
		vMapper.delete(id);

	}

	@Override
	public Vector<Fahrt> getAlleFahrtenVonFahrer(int id) throws IllegalArgumentException {
		//Abfrage über Datenbank realisiert
		return tMapper.findByFahrerId(id);
	}

	@Override
	public Vector<Fahrt> getAlleFahrtenVonFahrzeug(int id) throws IllegalArgumentException {
		//Abfrage über Datenbank realisiert
		return tMapper.findByFahrzeugId(id);
	}
	
	@Override
	public Fahrt getFahrt(int id) throws IllegalArgumentException {
		return tMapper.findByKey(id);

	}

	@Override
	public void saveFahrt(Fahrt t) throws IllegalArgumentException {
		/*
		 * Es muss sichergestellt werden, dass der Kilometerendstand der Fahrt den aktuellen Kilometerstand des Fahrzeugs updatet.
		 * Dazu muss zunächst das entsprechende Fahrzeug anhand des Fremdschlüsses der bekannten Fahrt abgerufen werden
		 */
		Fahrzeug fahrzeug = vMapper.findByKey(t.getSourceFahrzeugId());
		
		// dann wird der Kilometerstand um den Endstand der Fahrt aktualisert.
		fahrzeug.setKm(t.getKmEnd());
		
		//dann werden die Fahrt und das Fahrzeug in die Db gescheiben
		tMapper.insert(t);
		vMapper.update(fahrzeug);

	}
	
	@Override
	public void updateFahrt(Fahrt t) throws IllegalArgumentException {
		/*
		 * Es muss sichergestellt werden, dass der Kilometerendstand der Fahrt den aktuellen Kilometerstand des Fahrzeugs updatet.
		 * Dazu muss zunächst das entsprechende Fahrzeug anhand des Fremdschlüsses der bekannten Fahrt abgerufen werden
		 */
		Fahrzeug fahrzeug = vMapper.findByKey(t.getSourceFahrzeugId());
		
		// dann wird der Kilometerstand um den Endstand der Fahrt aktualisert.
		fahrzeug.setKm(t.getKmEnd());
		
		//dann werden die Fahrt und das Fahrzeug in die Db gescheiben
		tMapper.update(t);
		vMapper.update(fahrzeug);
		

	}

	@Override
	public void deleteFahrt(int id) throws IllegalArgumentException {
		tMapper.delete(id);

	}

	@Override
	public void deleteAlleFahrtenVonFahrzeug(int id) throws IllegalArgumentException {
		tMapper.deleteAlleFahrtenOfFahrzeug(id);
	}
	
	//*********************REPORT-TEIL*************************************************
	
	public String schreibeFahrerReportHTML(Fahrer d){
		
		
		AlleFahrtenVonFahrerReport report = this.createAlleFahrtenVonFahrerReport(d);
		
		this.writer = new HTMLReportWriter();
		writer.process(report);
		return writer.getReportText();
	}
	
	
	public String schreibeFahrzeugReportHTML(Fahrzeug v){
				
		AlleFahrtenVonFahrzeugReport report = this.createAlleFahrtenVonFahrzeugReport(v);
		
		this.writer = new HTMLReportWriter();
		writer.process(report);
		return writer.getReportText();
	}
	
	public AlleFahrtenVonFahrerReport createAlleFahrtenVonFahrerReport(Fahrer d) 
			throws IllegalArgumentException {

		    /*
		     * Zunächst legen wir uns einen leeren Report an.
		     */
			AlleFahrtenVonFahrerReport result = new AlleFahrtenVonFahrerReport();

		    // Jeder Report hat einen Titel (Bezeichnung / Überschrift).
		    result.setTitle("Alle Fahrten den Fahrers:");

		    // Imressum hinzufügen
		    //this.addImprint(result);

		    /*
		     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		     */
		    result.setCreated(new Date());

		    /*
		     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
		     * auf dem Report stehen) des Reports. Die Kopfdaten sind mehrzeilig, daher
		     * die Verwendung von CompositeParagraph.
		     */
		    CompositeParagraph header = new CompositeParagraph();

		    // Name und Vorname des Fahrers aufnehmen
		    header.addSubParagraph(new SimpleParagraph(d.getNachname() + ", "
		        + d.getVorname()));

		    // EMail aufnehmen
		    header.addSubParagraph(new SimpleParagraph("EMail: " + d.getEMail()));

		    // Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		    result.setHeaderData(header);

		    /*
		     * Ab hier erfolgt ein zeilenweises Hinzufügen von Konto-Informationen.
		     */
		    
		    /*
		     * Zunächst legen wir eine Kopfzeile für die Konto-Tabelle an.
		     */
		    Row headline = new Row();

		    /*
		     * Wir wollen Zeilen mit 11 Spalten in der Tabelle erzeugen. In die erste
		     * In der Kopfzeile legen wir also entsprechende
		     * Überschriften ab.
		     */
		    headline.addColumn(new Column("Fahrt-Nr."));
		    headline.addColumn(new Column("Fahrtdatum"));
		    headline.addColumn(new Column("Zielbeschreibung"));
		    headline.addColumn(new Column("Km (Start)"));
		    headline.addColumn(new Column("Km (Endstand)"));
		    headline.addColumn(new Column("Km Privat"));
		    headline.addColumn(new Column("Km Arbeitsweg"));
		    headline.addColumn(new Column("Km Betriebsfahrt"));
		    headline.addColumn(new Column("Kommentar"));
		    headline.addColumn(new Column("Bearbeitungsdatum"));
		    headline.addColumn(new Column("Fahrzeug (Kennzeichen)"));
		    headline.addColumn(new Column("EMail(Fahrer)"));
		    

		    // Hinzufügen der Kopfzeile
		    result.addRow(headline);

		    /*
		     * Nun werden sämtliche Fahrten des Fahrers ausgelesen und deren Daten
		     * sukzessive in die Tabelle eingetragen.
		     */
		    Vector<Fahrt> fahrten = tMapper.findByFahrer(d);
		    
		    Fahrzeug vFahrzeug = new Fahrzeug();
		    Fahrer dFahrer = new Fahrer();

		    for (Fahrt t : fahrten) {
		    	// Fahrzeug- und Fahrer-Objekt zu dieser Fahrt finden um Später das Kennzeichen bzw die Steuernummer auszulesen
		    	vFahrzeug = this.getFahrzeug(t);
		    	dFahrer = this.getFahrer(t);
		    	
		      // Eine leere Zeile anlegen.
		      Row fahrtRow = new Row();
		      

		      // Erste Spalte: Fahrtdatum hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getFahrtDatum())));

		      // Zweite Spalte: Fahrt-Nr. hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getId())));
		      
		      // Dritte Spalte: Zielbeschreibung hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getZielBeschreibung())));
		      
		      // Vierte Spalte: Start Kilometerstand hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getKmStart())));
		      
		      // Fünfte Spalte: End Kilometerstand hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getKmEnd())));
		      
		      // Sechste Spalte: Anteil der Privatfahrt an dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getPrivatKm())));
		      
		      // Siebte Spalte: Anteil des Arbeitsweges an dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getArbeitswegKm())));
		      
		      // Achte Spalte: Anteil der Betriebsfahrt an dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getBetriebsfahrtKm())));
		      
		      // Neunte Spalte: Kommentar hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getKommentar())));
		      
		      // Zehnte Spalte: Bearbeitungsdatum dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getBearbeitungsdatum())));
		      
		      // Elfte Spalte: Kennzeichen des Autos dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(vFahrzeug.getKennzeichen())));
		      
		      // Zwölfte Spalte: EMail des Fahrers dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(dFahrer.getEMail())));

		      // und schließlich die Zeile dem Report hinzufügen.
		      result.addRow(fahrtRow);
		    }

		    /*
		     * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
		     */
		    return result;
		  }
	
	
	
	public AlleFahrtenVonFahrzeugReport createAlleFahrtenVonFahrzeugReport(Fahrzeug v) 
			throws IllegalArgumentException {

		    /*
		     * Zunächst legen wir uns einen leeren Report an.
		     */
			AlleFahrtenVonFahrzeugReport result = new AlleFahrtenVonFahrzeugReport();

		    // Jeder Report hat einen Titel (Bezeichnung / Überschrift).
		    result.setTitle("Alle Fahrten den Fahrzeugs:");

		    // Imressum hinzufügen
		    //this.addImprint(result);

		    /*
		     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		     */
		    result.setCreated(new Date());

		    /*
		     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
		     * auf dem Report stehen) des Reports. Die Kopfdaten sind mehrzeilig, daher
		     * die Verwendung von CompositeParagraph.
		     */
		    CompositeParagraph header = new CompositeParagraph();

		    // Name und Vorname des Fahrers aufnehmen
		    header.addSubParagraph(new SimpleParagraph("Mit dem Kennzeichen: "+ v.getKennzeichen()));

		    // Modellbeschreibung aufnehmen
		    header.addSubParagraph(new SimpleParagraph("Modell: " + v.getModellBeschreibung()));

		    // Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		    result.setHeaderData(header);

		    /*
		     * Ab hier erfolgt ein zeilenweises Hinzufügen von Konto-Informationen.
		     */
		    
		    /*
		     * Zunächst legen wir eine Kopfzeile für die Konto-Tabelle an.
		     */
		    Row headline = new Row();

		    /*
		     * Wir wollen Zeilen mit 11 Spalten in der Tabelle erzeugen. In die erste
		     * In der Kopfzeile legen wir also entsprechende
		     * Überschriften ab.
		     */
		    headline.addColumn(new Column("Fahrtdatum"));
		    headline.addColumn(new Column("Fahrt-Nr."));
		    headline.addColumn(new Column("Zielbeschreibung"));
		    headline.addColumn(new Column("Km (Start)"));
		    headline.addColumn(new Column("Km (Endstand)"));
		    headline.addColumn(new Column("Km Privat"));
		    headline.addColumn(new Column("Km Arbeitsweg"));
		    headline.addColumn(new Column("Km Betriebsfahrt"));
		    headline.addColumn(new Column("Kommentar"));
		    headline.addColumn(new Column("Bearbeitungsdatum"));
		    headline.addColumn(new Column("Fahrzeug (Kennzeichen)"));
		    headline.addColumn(new Column("EMail(Fahrer)"));
		    

		    // Hinzufügen der Kopfzeile
		    result.addRow(headline);

		    /*
		     * Nun werden sämtliche Fahrten des Fahrers ausgelesen und deren Daten
		     * sukzessive in die Tabelle eingetragen.
		     */
		    Vector<Fahrt> fahrten = tMapper.findByFahrzeug(v);
		    
		    Fahrzeug vFahrzeug = new Fahrzeug();
		    Fahrer dFahrer = new Fahrer();

		    for (Fahrt t : fahrten) {
		    	// Fahrzeug- und Fahrer-Objekt zu dieser Fahrt finden um Später das Kennzeichen bzw die eMail auszulesen
		    	vFahrzeug = this.getFahrzeug(t);
		    	dFahrer = this.getFahrer(t);
		    	
		      // Eine leere Zeile anlegen.
		      Row fahrtRow = new Row();
		      

		      // Erste Spalte: Fahrtdatum hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getFahrtDatum())));

		      // Zweite Spalte: Fahrt-Nr. hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getId())));
		      
		      // Dritte Spalte: Zielbeschreibung hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getZielBeschreibung())));
		      
		      // Vierte Spalte: Start Kilometerstand hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getKmStart())));
		      
		      // Fünfte Spalte: End Kilometerstand hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getKmEnd())));
		      
		      // Sechste Spalte: Anteil der Privatfahrt an dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getPrivatKm())));
		      
		      // Siebte Spalte: Anteil des Arbeitsweges an dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getArbeitswegKm())));
		      
		      // Achte Spalte: Anteil der Betriebsfahrt an dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getBetriebsfahrtKm())));
		      
		      // Neunte Spalte: Kommentar hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getKommentar())));
		      
		      // Zehnte Spalte: Bearbeitungsdatum dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(t.getBearbeitungsdatum())));
		      
		      // Elfte Spalte: Kennzeichen des Autos dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(vFahrzeug.getKennzeichen())));
		      
		      // Zwölfte Spalte: EMail des Fahrers dieser Fahrt hinzufügen
		      fahrtRow.addColumn(new Column(String.valueOf(dFahrer.getEMail())));

		      // und schließlich die Zeile dem Report hinzufügen.
		      result.addRow(fahrtRow);
		    }

		    /*
		     * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
		     */
		    return result;
		  }
	
	
}
