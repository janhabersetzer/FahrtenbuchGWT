package com.janhabersetzer.fahrtenbuch.client;



import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.gui.MainView;
import com.janhabersetzer.fahrtenbuch.client.gui2.Navigator;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministrationAsync;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;




/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FahrtenbuchGWT implements EntryPoint {
	
	/**
	 * Deklaraion der Labels fuer die Startseite(n)
	 */

	private Label begruessenN = new Label(
			"Willkommen bei deinem Fahrtenbuch. ");
	private Label begruessenN2 = new Label(
			"Klicke dich nun durch die Webseite und trage deine Fahrten ein");
	
	
	
	
	public void onModuleLoad() {
		//FahrtenbuchClientImpl clientImpl = new FahrtenbuchClientImpl(GWT.getModuleBaseURL() + "fahrtenbuchservice");
		//RootPanel.get().add(clientImpl.getNavigator());
		
		MainView mainView = new MainView();
		RootPanel.get().add(mainView);

		
	}
	

}


	    
	  

