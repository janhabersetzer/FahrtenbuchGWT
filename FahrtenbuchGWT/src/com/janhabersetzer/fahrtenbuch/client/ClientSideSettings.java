package com.janhabersetzer.fahrtenbuch.client;

import com.google.gwt.core.client.GWT;
import com.janhabersetzer.fahrtenbuch.shared.CommonSettings;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministration;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministrationAsync;





public class ClientSideSettings extends CommonSettings {
	
	//Ohne Logger
	
	 private static FahrtenbuchAdministrationAsync fahrtenbuchVerwaltung = null;
	 
	 
	 public static FahrtenbuchAdministrationAsync getFahrtenbuchVerwaltung() {
		    // Wenn es noch keine Instanz gibt
		    if (fahrtenbuchVerwaltung == null) {
		      
		    	fahrtenbuchVerwaltung = GWT.create(FahrtenbuchAdministration.class);
		    }

		    // So, nun brauchen wir die BankAdministration nur noch zurückzugeben.
		    return fahrtenbuchVerwaltung;
		  }

}
