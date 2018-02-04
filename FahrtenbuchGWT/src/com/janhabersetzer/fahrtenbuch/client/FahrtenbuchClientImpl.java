package com.janhabersetzer.fahrtenbuch.client;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministrationAsync;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;


public class FahrtenbuchClientImpl implements FahrtenbuchClient {
	
	/**
	 * Hier wird das Objekt instanziert, das uns erlaubt, asynchrone Aufrufe durchzuführen. 
	 * Dieses wurde in <code>ClientSideSettings</code> bereits deklariert und beim Aufruf von getFahrtenbuchVerwaltung() als 
	 * <code>fahrtenbuchVerwaltung</code> instanziert. 
	 * Wahrscheinlich waere es für den Speicherbedarf des Client besser, nur eine Instanz zu erzeugen, darauf wurde aber verzichtet,
	 * um spärker am Rahmen des zu Grunde liegenden Bank-Projekts zu orientieren.
	 *  Um eine Unterscheidung zwischen beiden Instanzen zu vereinfachen, 
	 * sowie den Zweck dieses Objekts deutlicher zu machen wird er hier schlichtweg als <code>service</code> benannt.
	 */
	
	private FahrtenbuchAdministrationAsync fbService;
	
	
	
	
	public FahrtenbuchClientImpl(String url){
		this.fbService = ClientSideSettings.getFahrtenbuchVerwaltung();
		/*
		 * Hier wird der Ziel-URL für den remote Service gesetzt, 
		 * um das System unabhängig von der Serverumgebung laufen lassen zu können.
		 */
		ServiceDefTarget endpoint = (ServiceDefTarget) this.fbService;
		endpoint.setServiceEntryPoint(url);	
		
	
	}

//***********************Methoden-Teil*********************************************************************


	@Override
	public void init() {
		this.fbService.init(new InitCallback());
		
	}



	@Override
	public void createFahrer(String first, String last, String eMail) {
		this.fbService.createFahrer(first, last, eMail, callback);
		
	}



	@Override
	public void createFahrzeug(String regNo, int milage, String description, String color) {
		this.fbService.createFahrzeug(regNo, milage, description, color, callback);
		
	}



	@Override
	public void createFahrt(Date tripDate, String destDescr, int firstMilage, int secondMilage, int privateDist,
			int workingDist, int companyDist, String comment, int vehicleId, int driverId) {
		
		this.fbService.createFahrt(tripDate, destDescr, firstMilage, secondMilage, privateDist, workingDist,
			companyDist, comment, vehicleId, driverId, callback);
		
	}



	@Override
	public void getFahrer(Fahrt t) {
		this.fbService.getFahrer(t, callback);
		
	}



	@Override
	public void getFahrerByEmail(String email) {
		this.fbService.getFahrerByEmail(email, callback);
		
	}



	@Override
	public void pruefeObFahrerNeu(String email) {
		this.fbService.pruefeObFahrerNeu(email, callback);
		
	}



	@Override
	public void getAlleFahrer() {
		this.fbService.getAlleFahrer(callback);
		
	}



	@Override
	public void saveFahrer(Fahrer d) {
		this.fbService.saveFahrer(d, callback);
		
	}



	@Override
	public void deleteFahrer(Fahrer d) {
		if (Window.confirm("Möchten Sie Ihr Profil und sich als Fahrer wirklich löschen?")) {
		this.fbService.deleteFahrer(d, new DeleteCallback());
		}
		
	}



	@Override
	public void getFahrzeug(Fahrt t) {
		this.fbService.getFahrzeug(t, callback);
		
	}



	@Override
	public void getAlleFahrzeug() {
		this.fbService.getAlleFahrzeug(callback);
		
	}



	@Override
	public void saveFahrzeug(Fahrzeug v) {
		this.fbService.saveFahrzeug(v, callback);
		
	}



	@Override
	public void deleteFahrzeug(Fahrzeug v) {
		this.fbService.deleteFahrzeug(v, callback);
		
	}



	@Override
	public void getAlleFahrtenVonFahrer(Fahrer d) {
		this.fbService.getAlleFahrtenVonFahrer(d, callback);
		
	}



	@Override
	public void getAlleFahrtenVonFahrzeug(Fahrzeug v) {
		this.fbService.getAlleFahrtenVonFahrzeug(v, callback);
		
	}



	@Override
	public void saveFahrt(Fahrt t) {
		this.fbService.saveFahrt(t, callback);
		
	}



	@Override
	public void deleteFahrt(Fahrt t) {
		this.fbService.deleteFahrt(t, callback);
		
	}



	@Override
	public void deleteAlleFahrtenVonFahrzeug(Fahrzeug v) {
		this.fbService.deleteAlleFahrtenVonFahrzeug(v, callback);
		
	}



	@Override
	public void createAlleFahrtenVonFahrerReport(Fahrer d) {
		this.fbService.createAlleFahrtenVonFahrerReport(d, callback);
		
	}



	@Override
	public void createAlleFahrtenVonFahrzeugReport(Fahrzeug v) {
		this.fbService.createAlleFahrtenVonFahrzeugReport(v, callback);
		
	}
	
	
//***************************GETTER-Teil*****************************************************************
	


//*******************Private-Async-Callback-Klassen-Teil**********************************************
	
	
	private class InitCallback implements AsyncCallback<Void>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Applikationsschicht nicht errreichbar");
		
	}

	@Override
	public void onSuccess(Void result) {
		Window.alert("Applikationsschicht steht zur Verfügung");
		
	}

		
	}
	
	
	private class DeleteCallback implements AsyncCallback<Void>{
			
				public void onSuccess(Void result) {
				
					
				}@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
	}

}


