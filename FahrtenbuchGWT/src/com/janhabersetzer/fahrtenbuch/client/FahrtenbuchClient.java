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
	
//	void createFahrer(String first, String last, String eMail);
//	
//	void createFahrzeug(String regNo, int milage, String description, String color);
//	
//	void createFahrt(Date tripDate, String destDescr, int firstMilage, int secondMilage, int privateDist,
//			int workingDist,int companyDist,String comment,int vehicleId, int driverId);
//
//	void getFahrer(int id);
//	
	void getFahrer(Fahrt t);
//	
//	void getFahrerByEmail(String email);
//	
//	void pruefeObFahrerNeu(String email);
//	
//	void getAlleFahrer();
//	
//	void saveFahrer(Fahrer d);
	
//	void updateFahrer(Fahrer d);
	
//	void deleteFahrer(int id);
	
	void getFahrzeug(Fahrt t);
	
	void getFahrzeug(int id);
	
	void getAlleFahrzeug();
	
//	void saveFahrzeug(Fahrzeug v);
	
//	void updateFahrzeug(Fahrzeug v);
	
	void deleteFahrzeug(int id);
//	
//	void getAlleFahrtenVonFahrer(int id);
	
	void getAlleFahrtenVonFahrzeug(int id);
	
	void getFahrt(int id);
	
//	void saveFahrt(Fahrt t);
	
	void updateFahrt(Fahrt t);
//	
//	void deleteFahrt(int id);
//	
//	void deleteAlleFahrtenVonFahrzeug(int id);
//	
//	void createAlleFahrtenVonFahrerReport(Fahrer d);
//	
//	void createAlleFahrtenVonFahrzeugReport(Fahrzeug v);
}
