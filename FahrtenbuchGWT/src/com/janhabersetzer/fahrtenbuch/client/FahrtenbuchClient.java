package com.janhabersetzer.fahrtenbuch.client;

import java.util.Date;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;


/**
 * @author janhabersetzer
 * Dieses Interface definiert die Methoden der Klasse <code>FahrtenbuchClientImpl</code>.
 * Es dient dazu, alle Mehtoden zu spezifieren, die der Client vom Server aufrufen soll.
 * WICHTIG: Diese Methoden sind nicht mit den Methoden in <code>FahrtenbuchAdministration</code> gleichzusetzten, sondern dienen
 * lediglich dazu diese aufzurufen.
 */
public interface FahrtenbuchClient {
	
	void init();
	
	void createFahrer(String first, String last, String eMail);
	
	void createFahrzeug(String regNo, int milage, String description, String color);
	
	void createFahrt(Date tripDate, String destDescr, int firstMilage, int secondMilage, int privateDist,
			int workingDist,int companyDist,String comment,int vehicleId, int driverId);
	
	void getFahrer(Fahrt t);
	
	void getFahrerByEmail(String email);
	
	void pruefeObFahrerNeu(String email);
	
	void getAlleFahrer();
	
	void saveFahrer(Fahrer d);
	
	void deleteFahrer(Fahrer d);
	
	void getFahrzeug(Fahrt t);
	
	void  getAlleFahrzeug();
	
	void saveFahrzeug(Fahrzeug v);
	
	void deleteFahrzeug(Fahrzeug v);
	
	void getAlleFahrtenVonFahrer(Fahrer d);
	
	void getAlleFahrtenVonFahrzeug(Fahrzeug v);
	
	void saveFahrt(Fahrt t);
	
	void deleteFahrt(Fahrt t);
	
	void deleteAlleFahrtenVonFahrzeug(Fahrzeug v);
	
	void createAlleFahrtenVonFahrerReport(Fahrer d);
	
	void createAlleFahrtenVonFahrzeugReport(Fahrzeug v);

}
