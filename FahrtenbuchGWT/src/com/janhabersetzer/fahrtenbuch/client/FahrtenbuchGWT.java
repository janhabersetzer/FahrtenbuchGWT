package com.janhabersetzer.fahrtenbuch.client;



import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


import com.janhabersetzer.fahrtenbuch.client.gui.Navigator;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministrationAsync;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;




/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FahrtenbuchGWT implements EntryPoint {
	
	Fahrer fahrerProfil = new Fahrer();
	/**
	 * Deklaraion der Labels fuer die Startseite(n)
	 */
	private Label begruessenN = new Label(
			"Willkommen bei deinem Fahrtenbuch. ");
	private Label begruessenN2 = new Label(
			"Klicke dich nun durch die Webseite und trage deine Fahrten ein");
	
	/**
	 * Deklaration fuer den Login und den Logout
	 */
	private static Fahrer fhr=  new Fahrer();
	
	//private static String editorHtmlName = "FahrtenbuchGWT.html";

	private FahrtenbuchAdministrationAsync fahrtenbuchVerwaltung;
	
	
	
	public void onModuleLoad() {
		setStyles();
		
		// Zunächst wird admin eine Fahrtenbuch-Instanz zugewiesen

		if (fahrtenbuchVerwaltung == null) {
			fahrtenbuchVerwaltung = ClientSideSettings.getFahrtenbuchVerwaltung();
			}
		
		/*
		 * 
		 * Vorerst: Da der Login wegfällt und ich die Wahl eines Fahrer einbauen muss,
		 *  wird hier zum Testen ein Fahrer mit  gesetzter Email intanziert. Später als kein Fahrer angemeldet
		 */
		String emailAdress = "jh200@hdm.stuttgar.de";
		getFahrerByEmailExecute(emailAdress);
		getMenu();
	
	}
	

	
	/**
	 * AsyncCallback für die Methode pruefeObFahrerNeu(). Falls der Wert false ist wird die Methode
	 * getNutzerByEmail() aufgerufen, sonst wird der Nutzer auf CreateNutzerprofil() weitergeleitet.
	 * 
	 * @return
	 */



	private void getFahrerByEmailExecute(String emailAddress) {
			fahrtenbuchVerwaltung.getFahrerByEmail(emailAddress, new AsyncCallback<Fahrer>() {
			   @Override
			public void onFailure(Throwable caught) {
				Window.alert("Fahrer nicht aus der Datenbank abgerufen");
				
			}@Override
			public void onSuccess(Fahrer result) {
				// nichts
				
			}
		});    
	}


	/**
	 * Methode legt die CSS-Styles für verschiedene Labels fest.
	 */
	private void setStyles() {
		begruessenN.setStyleName("welcome-label");
		begruessenN2.setStyleName("welcome-label2");

	}

	/**
	 * Methode erzeugt ruft das Panel auf, durch welches die Menubar sichtbar wird.
	 */
	public static void getMenu() {
		RootPanel.get("Navigator").add(new Navigator());
	}
	
	
	//***************Getter-Teil*******************
	
	public static Fahrer getfhr() {
		return fhr;
	}



}


	    
	  

