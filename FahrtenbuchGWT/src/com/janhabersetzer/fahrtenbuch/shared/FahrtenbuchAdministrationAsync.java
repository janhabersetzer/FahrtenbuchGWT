package com.janhabersetzer.fahrtenbuch.shared;

import java.time.LocalDate;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.janhabersetzer.fahrtenbuch.server.report.AlleFahrtenVonFahrerReport;
import com.janhabersetzer.fahrtenbuch.server.report.AlleFahrtenVonFahrzeugReport;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public interface FahrtenbuchAdministrationAsync {
	
	void init(AsyncCallback<Void> callback);
	
	void createFahrer(String first, String last, String eMail, AsyncCallback<Fahrer> callback);
	
	void createFahrzeug(String regNo, int milage, String description, String color, AsyncCallback<Fahrzeug> callback);
	
	void createFahrt(LocalDate tripDate, String destDescr, int firstMilage, int secondMilage, int privateDist,
			int workingDist,int companyDist,String comment,int vehicleId, int driverId, AsyncCallback<Fahrt> callback);
	
	void getFahrer(Fahrt t, AsyncCallback<Fahrer> callback);
	
	void getAlleFahrer(AsyncCallback<Vector<Fahrer>> callback);
	
	void saveFahrer(Fahrer d, AsyncCallback<Void> callback);
	
	void deleteFahrer(Fahrer d, AsyncCallback<Void> callback);
	
	void getFahrzeug(Fahrt t, AsyncCallback<Fahrzeug> callback);
	
	void  getAlleFahrzeug(AsyncCallback<Vector<Fahrzeug>> callback);
	
	void saveFahrzeug(Fahrzeug v, AsyncCallback<Void> callback);
	
	void deleteFahrzeug(Fahrzeug v, AsyncCallback<Void> callback);
	
	void getAlleFahrtenVonFahrer(Fahrer d, AsyncCallback<Vector<Fahrt>> callback);
	
	void getAlleFahrtenVonFahrzeug(Fahrzeug v, AsyncCallback<Vector<Fahrt>> callback);
	
	void saveFahrt(Fahrt t, AsyncCallback<Void> callback);
	
	void deleteFahrt(Fahrt t, AsyncCallback<Void> callback);
	
	void deleteAlleFahrtenVonFahrzeug(Fahrzeug v, AsyncCallback<Void> callback);
	
	void createAlleFahrtenVonFahrerReport(Fahrer d, AsyncCallback<AlleFahrtenVonFahrerReport> callback);
	
	void createAlleFahrtenVonFahrzeugReport(Fahrzeug v, AsyncCallback<AlleFahrtenVonFahrzeugReport> callback);
}
