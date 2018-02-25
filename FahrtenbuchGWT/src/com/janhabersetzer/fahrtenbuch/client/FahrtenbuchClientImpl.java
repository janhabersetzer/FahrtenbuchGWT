package com.janhabersetzer.fahrtenbuch.client;


import java.util.Enumeration;
import java.util.Vector;

import com.fasterxml.jackson.databind.type.HierarchicType;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.janhabersetzer.fahrtenbuch.client.gui.ContAlleFahrer;
import com.janhabersetzer.fahrtenbuch.client.gui.ContAlleFahrzeuge;
import com.janhabersetzer.fahrtenbuch.client.gui.ContCreateFahrer;
import com.janhabersetzer.fahrtenbuch.client.gui.ContCreateFahrt;
import com.janhabersetzer.fahrtenbuch.client.gui.ContCreateFahrzeug;
import com.janhabersetzer.fahrtenbuch.client.gui.ContEditFahrer;
import com.janhabersetzer.fahrtenbuch.client.gui.ContEditFahrt;
import com.janhabersetzer.fahrtenbuch.client.gui.MainView;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministrationAsync;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
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
	@Override
	public void getFahrer(Fahrt t) {
		this.fbService.getFahrer(t, new GetFahrerSimpleCallback());
		
	}

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
	@Override
	public void getAlleFahrer() {
		this.fbService.getAlleFahrer(new GetAlleFahrerCallback());
		
	}

	@Override
	public void saveFahrer(Fahrer d) {
		this.fbService.saveFahrer(d, new SaveFahrerCallback());
		
	}
	
	@Override
	public void updateFahrer(Fahrer d) {
		this.fbService.updateFahrer(d, new UpdateFahrerCallback());
		
	}
	
		
	@Override
	public void deleteFahrer(int id) {	
		this.fbService.deleteFahrer(id, new DeleteFahrerCallback());	
	}

	@Override
	public void getFahrzeug(Fahrt t) {
		this.fbService.getFahrzeug(t, new GetFahrzeugSimpleCallback());
		
	}

	@Override
	public void getFahrzeug(int id) {
		this.fbService.getFahrzeug(id, new GetFahrzeugCallback());
		
	}

	@Override
	public void getAlleFahrzeug() {
		this.fbService.getAlleFahrzeug(new GetAllFahrzeugCallback());
		
	}

	@Override
	public void saveFahrzeug(Fahrzeug v) {
		this.fbService.saveFahrzeug(v, new SaveFahrzeugCallback());	
	}

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

	@Override
	public void getAlleFahrtenVonFahrzeug(int id) {
		this.fbService.getAlleFahrtenVonFahrzeug(id, new GetAlleFahrtenFhrzCallback());
		
	}
	
	@Override
	public void getFahrt(int id) {
		this.fbService.getFahrt( id, new GetFahrtCallback());
		
	}

	@Override
	public void saveFahrt(Fahrt t) {
		this.fbService.saveFahrt(t, new SaveFahrtCallback());
		
	}
	
	@Override
	public void updateFahrt(Fahrt t) {
		this.fbService.updateFahrt(t, new UpdateFahrtCallback());
		
	}

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
	
	private class GetFahrerSimpleCallback implements AsyncCallback<Fahrer>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Fahrer result) {
			Fahrer fahrer = (Fahrer) result;
			mainView.getContEditFahrt().schreibeFahrer(fahrer);
			
		}
		
	}
	
	private class GetAlleFahrerCallback implements AsyncCallback<Vector<Fahrer>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Fahrer> result) {
			
			Vector<Fahrer>  vecFahrer = (Vector<Fahrer>)  result;
			
			if (mainView.getCurrentCont() instanceof ContCreateFahrt){	
				mainView.getContCreateFahrt().schreibeAlleFahrer(vecFahrer);
			}
			else if(mainView.getCurrentCont() instanceof ContAlleFahrer){
				mainView.getContAlleFahrer().befuelleFhrTabelle(vecFahrer);	
			}
		}
		
	}
	
	private class SaveFahrerCallback implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {	
			
			if (mainView.getCurrentCont() instanceof ContCreateFahrer){	
				mainView.openAlleFahrerCont();
			}		
		}	
	}
	
	private class UpdateFahrerCallback implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {	
			
			if (mainView.getCurrentCont() instanceof ContEditFahrer){	
				mainView.openAlleFahrerCont();
			}else{
				Window.alert("Hier gehts nicht weiter");
			}		
		}	
	}
	
	
	
	
	private class GetFahrzeugCallback implements AsyncCallback<Fahrzeug>{

		@Override
		public void onFailure(Throwable caught) {
			System.out.print("Der Fehler ist beim Übertragen des Fahrzeugs von Server (GetFahrzeugCallback)");
			
		}

		@Override
		public void onSuccess(Fahrzeug result) {
			Fahrzeug v = (Fahrzeug) result;
				mainView.getContFahrzeug().schreibeFahrzeug(v);
			}
			
	}
	
	private class GetFahrzeugSimpleCallback implements AsyncCallback<Fahrzeug>{

		@Override
		public void onFailure(Throwable caught) {
			System.out.print("Der Fehler ist beim Übertragen des Fahrzeugs von Server (GetFahrzeugCallback)");
			
		}

		@Override
		public void onSuccess(Fahrzeug result) {
			Fahrzeug fahrzeug = (Fahrzeug) result;
				mainView.getContEditFahrt().schreibeFahrzeug(fahrzeug);
			}
		
	}
	
	private class SaveFahrzeugCallback implements AsyncCallback<Void>{

			@Override
			public void onFailure(Throwable caught) {
				System.out.print("Der Fehler ist beim Übertragen des Fahrzeugs von Server (SaveFahrzeugCallback)");
				
			}

			@Override
			public void onSuccess(Void result) {
				if(mainView.getCurrentCont() instanceof ContCreateFahrzeug)
					mainView.openAlleFhrzCont();
				}		
	}
		
	private class GetAlleFahrtenFhrzCallback implements AsyncCallback<Vector<Fahrt>>{

		@Override
		public void onFailure(Throwable caught) {
			System.out.print("Der Fehler ist beim Übertragen des Fahrzeugs von Server (GetAlleFahrtenFhrzCallback)");
			
		}

		@Override
		public void onSuccess(Vector<Fahrt> result) {
			Vector<Fahrt> fahrten = (Vector<Fahrt>) result;
			mainView.getContFahrzeug().oeffneFahrten(fahrten);
			
		}
		
	}
		
	
	private class GetAllFahrzeugCallback implements AsyncCallback<Vector<Fahrzeug>>{

		@Override
		public void onFailure(Throwable caught) {
			System.out.print("Der Fehler ist beim Übertragen des GetAllFahrzeugCallback");
			
		}
		@Override
		public void onSuccess(Vector<Fahrzeug> result) {
			Vector<Fahrzeug> fahrzeuge= (Vector<Fahrzeug>)result;
			mainView.getContAlleFahrzeuge().befuelleFhzTabelle(fahrzeuge);
			
		}
		
	}
	
	
	private class GetFahrtCallback implements AsyncCallback<Fahrt>{

		@Override
		public void onFailure(Throwable caught) {
			System.out.print("Der Fehler ist beim Übertragen des GetFahrtCallback");
			
		}

		@Override
		public void onSuccess(Fahrt result) {
			if(mainView.getCurrentCont() instanceof ContEditFahrt){
			Fahrt fahrt = (Fahrt) result;
			mainView.getContEditFahrt().oeffneBearbeiten(fahrt);
			}
		}
		
	}
	
	private class UpdateFahrtCallback implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			System.out.print("Der Fehler ist beim Übertragen des SaveFahrtCallback");
			
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Fahrt wurde gespeichert");
			int fahrzeugId = mainView.getContEditFahrt().getFahrzeugId();
			mainView.openFahrzeugCont(fahrzeugId);	
		}
		
	}
	
	private class SaveFahrtCallback implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			System.out.print("Der Fehler ist beim Übertragen des SaveFahrtCallback");
			
		}

		@Override
		public void onSuccess(Void result) {
			if(mainView.getCurrentCont() instanceof ContEditFahrt){
			Window.alert("Fahrt wurde erstellt");
			int fahrzeugId = mainView.getContEditFahrt().getFahrzeugId();
			mainView.openFahrzeugCont(fahrzeugId);
			}	
			else if(mainView.getCurrentCont() instanceof ContCreateFahrt){
				int fahrzeugId = mainView.getContCreateFahrt().getFahrzeugId();
				mainView.openFahrzeugCont(fahrzeugId);
			}
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

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fahrzeug konnte nicht gelöscht werden");
			
		}

		@Override
		public void onSuccess(Void result) {
			mainView.openAlleFahrerCont();
		}

	}



}


