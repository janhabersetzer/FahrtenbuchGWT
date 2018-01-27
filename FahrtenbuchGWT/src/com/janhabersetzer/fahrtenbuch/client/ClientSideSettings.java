package com.janhabersetzer.fahrtenbuch.client;

import com.google.gwt.core.client.GWT;
import com.janhabersetzer.fahrtenbuch.shared.CommonSettings;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministration;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministrationAsync;
import com.janhabersetzer.fahrtenbuch.shared.LoginService;
import com.janhabersetzer.fahrtenbuch.shared.LoginServiceAsync;


public class ClientSideSettings extends CommonSettings {
	
	//Ohne Logger
	
	 private static FahrtenbuchAdministrationAsync fahrtenbuchVerwaltung = null;
	 
	 
	 private static LoginServiceAsync loginService = null;
	 
	 
	 public static FahrtenbuchAdministrationAsync getFahrtenbuchVerwaltung() {
		// Wenn es noch keine Instanz gibt
		if (fahrtenbuchVerwaltung == null) {
		      
		   fahrtenbuchVerwaltung = GWT.create(FahrtenbuchAdministration.class);
		}

		// So, nun brauchen wir die BankAdministration nur noch zurückzugeben.
		return fahrtenbuchVerwaltung;
	}
	 
	 
	 /**
	 * Anlegen und Auslesen eines eindeutigen LoginService.
	 * Diese Methode erzeug den LoginService, sofern dieser noch nicht
	 * existiert. Bei wiederholtem Aufruf dieser Methode wird stets das bereits
	 * angelegte Objekt zurueckgegeben.
	 * 
	 * @return Instanz des Typs LoginServiceAsync
	 */
	 
	 public static LoginServiceAsync getLoginService() {

		if (loginService == null) {
			loginService = GWT.create(LoginService.class);
		}
		return loginService;

	}

}
