package com.janhabersetzer.fahrtenbuch.shared;


import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.janhabersetzer.fahrtenbuch.shared.report.AlleFahrtenVonFahrerReport;
import com.janhabersetzer.fahrtenbuch.shared.report.AlleFahrtenVonFahrzeugReport;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public interface FahrtenbuchAdministrationAsync {
	
	void init(AsyncCallback<Void> callback);
	
	void test(AsyncCallback<Vector<Fahrzeug>> callback);
	
	void createFahrer(String first, String last, String eMail, AsyncCallback<Fahrer> callback);
	
	void createFahrzeug(String regNo, int milage, String description, String color, AsyncCallback<Fahrzeug> callback);
	
	void createFahrt(Date tripDate, String destDescr, int firstMilage, int secondMilage, int privateDist,
			int workingDist,int companyDist,String comment,int vehicleId, int driverId, AsyncCallback<Fahrt> callback);

	void getFahrer(int id, AsyncCallback<Fahrer> callback);
	
	void getFahrer(Fahrt t, AsyncCallback<Fahrer> callback);
	
	void getFahrerByEmail(String email, AsyncCallback<Fahrer> callback);
	
	void pruefeObFahrerNeu(String email, AsyncCallback<Boolean> callback);
	
	void getAlleFahrer(AsyncCallback<Vector<Fahrer>> callback);
	
	void saveFahrer(Fahrer d, AsyncCallback<Void> callback);
	
	void updateFahrer(Fahrer d, AsyncCallback<Void> callback);
	
	void deleteFahrer(int id, AsyncCallback<Void> callback);
	
	void getFahrzeug(Fahrt t, AsyncCallback<Fahrzeug> callback);
	
	void getFahrzeug(int id, AsyncCallback<Fahrzeug> callback);
	
	void getAlleFahrzeug(AsyncCallback<Vector<Fahrzeug>> callback);
	
	void getFahrt(int id, AsyncCallback<Fahrt> callback);
	
	void saveFahrzeug(Fahrzeug v, AsyncCallback<Void> callback);
	
	void updateFahrzeug(Fahrzeug v, AsyncCallback<Void> callback);
	
	void deleteFahrzeug(int id, AsyncCallback<Void> callback);
	
	void getAlleFahrtenVonFahrer(int id, AsyncCallback<Vector<Fahrt>> callback);
	
	void getAlleFahrtenVonFahrzeug(int id, AsyncCallback<Vector<Fahrt>> callback);
	
	void saveFahrt(Fahrt t, AsyncCallback<Void> callback);
	
	void updateFahrt(Fahrt t, AsyncCallback<Void> callback);
	
	void deleteFahrt(int id, AsyncCallback<Void> callback);
	
	void deleteAlleFahrtenVonFahrzeug(int id, AsyncCallback<Void> callback);
	
	void schreibeFahrerReportHTML(Fahrer d, AsyncCallback<String> callback);
	
	void schreibeFahrzeugReportHTML(Fahrzeug v, AsyncCallback<String> callback);
	
	void createAlleFahrtenVonFahrerReport(Fahrer d, AsyncCallback<AlleFahrtenVonFahrerReport> callback);
	
	void createAlleFahrtenVonFahrzeugReport(Fahrzeug v, AsyncCallback<AlleFahrtenVonFahrzeugReport> callback);
}
