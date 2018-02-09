package com.janhabersetzer.fahrtenbuch.client;


import java.util.Enumeration;
import java.util.Vector;

import com.fasterxml.jackson.databind.type.HierarchicType;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.janhabersetzer.fahrtenbuch.client.gui.ContAlleFahrzeuge;
import com.janhabersetzer.fahrtenbuch.client.gui.MainView;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministrationAsync;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;
import com.janhabersetzer.fahrtenbuch.shared.report.Test;


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
	
	// Attribute fuer Aufrufe
	
	private FahrtenbuchAdministrationAsync fbService;
	
	private MainView mainView;
	
	//Konstruktor
	
	public FahrtenbuchClientImpl(String url){
		this.fbService = ClientSideSettings.getFahrtenbuchVerwaltung();
		/*
		 * Hier wird der Ziel-URL für den remote Service gesetzt, 
		 * um das System unabhängig von der Serverumgebung laufen lassen zu können.
		 */
		ServiceDefTarget endpoint = (ServiceDefTarget) this.fbService;
		endpoint.setServiceEntryPoint(url);	
		
		
		this.mainView = new MainView(this);
		
		this.init();
		
	
	}

//***********************Methoden-Teil*********************************************************************


	@Override
	public void init() {
		this.fbService.init(new InitCallback());
		
	}
	
	public void test(){
		this.fbService.test(new TestCallback());
	}


//	@Override
//	public void createFahrer(String first, String last, String eMail) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void createFahrzeug(String regNo, int milage, String description, String color) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void createFahrt(Date tripDate, String destDescr, int firstMilage, int secondMilage, int privateDist,
//			int workingDist, int companyDist, String comment, int vehicleId, int driverId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getFahrer(int id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getFahrer(Fahrt t) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getFahrerByEmail(String email) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void pruefeObFahrerNeu(String email) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getAlleFahrer() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void saveFahrer(Fahrer d) {
//		// TODO Auto-generated method stub
//		
//	}
	
//	@Override
//	public void deleteFahrer(int id) {
//		if (Window.confirm("Möchten Sie Ihr Profil und sich als Fahrer wirklich löschen?")) {
//		this.fbService.deleteFahrer(id, new DeleteFahrerCallback());
//		}
//		
//	}
//
//	@Override
//	public void getFahrzeug(Fahrt t) {
//		// TODO Auto-generated method stub
//		
//	}
//
	@Override
	public void getFahrzeug(int id) {
		this.fbService.getFahrzeug(id, new GetFahrzeugCallback());
		
	}

	@Override
	public void getAlleFahrzeug() {
		this.fbService.getAlleFahrzeug(new GetAllFahrzeugCallback());
		
	}
//
//	@Override
//	public void saveFahrzeug(Fahrzeug v) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void deleteFahrzeug(int id) {
		this.fbService.deleteFahrzeug(id, new DeleteFahrzeugCallback());
		
	}
//
//	@Override
//	public void getAlleFahrtenVonFahrer(int id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getAlleFahrtenVonFahrzeug(int id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void saveFahrt(Fahrt t) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteFahrt(int id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAlleFahrtenVonFahrzeug(int id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void createAlleFahrtenVonFahrerReport(Fahrer d) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void createAlleFahrtenVonFahrzeugReport(Fahrzeug v) {
//		// TODO Auto-generated method stub
//		
//	}
	
//***************************GETTER-Teil*****************************************************************
	
	/**
	 * Methode, die das, von FahrtenbuchClientImpl erzeugte MainView-Objekt zurueck gibt.
	 * @return von FahrtenbuchClientImpl erzeugtes MainView Objekt
	 */
	
	public MainView getMainView(){
		return this.mainView;
	}

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
	
	
	private class TestCallback implements AsyncCallback<Vector<Fahrzeug>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Der Fehler ist beim übertragen des TestCallback");
			
		}
		@Override
		public void onSuccess(Vector<Fahrzeug> result) {
			Vector<Fahrzeug> fahrzeuge= (Vector<Fahrzeug>)result;
			mainView.getContAlleFahrzeuge().befuelleFhzTabelle(fahrzeuge);
			}
			
		}
	
	private class GetFahrzeugCallback implements AsyncCallback<Fahrzeug>{

		@Override
		public void onFailure(Throwable caught) {
			System.out.print("Der Fehler ist beim übertragen des Fahrzeugs von Server (GetFahrzeugCallback)");
			
		}

		@Override
		public void onSuccess(Fahrzeug result) {
			Fahrzeug v = (Fahrzeug) result;
			mainView.getContFahrzeug().schreibeFahrzeug(v);
			
		}
		
	}
		
	
	private class GetAllFahrzeugCallback implements AsyncCallback<Vector<Fahrzeug>>{

		@Override
		public void onFailure(Throwable caught) {
			System.out.print("Der Fehler ist beim übertragen des GetAllFahrzeugCallback");
			
		}
		@Override
		public void onSuccess(Vector<Fahrzeug> result) {
			Vector<Fahrzeug> fahrzeuge= (Vector<Fahrzeug>)result;
			mainView.getContAlleFahrzeuge().befuelleFhzTabelle(fahrzeuge);
			
		}
		
	}
	
	
	private class DeleteFahrzeugCallback implements AsyncCallback<Void>{
		
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fahrzeug konnte nicht gelöscht werden");		
		}
		@Override
		public void onSuccess(Void result) {
			mainView.openAlleFhrzCont();
			
			
		}
	}
	
	
	
	private class DeleteFahrerCallback implements AsyncCallback<Void>{
			
				public void onSuccess(Void result) {
					
					
				}@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
	}



}


