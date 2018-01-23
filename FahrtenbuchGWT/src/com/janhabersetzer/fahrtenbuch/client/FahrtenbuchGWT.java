package com.janhabersetzer.fahrtenbuch.client;

import javax.swing.text.AbstractDocument.ElementEdit;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministration;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministrationAsync;

import de.hdm.thies.bankProjekt.client.gui.CustomerForm.SaveCallback;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FahrtenbuchGWT implements EntryPoint {
	
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel menuPanel = new HorizontalPanel();
	private Button fahrzeugNeuBtn = new Button("Neues Fahrzeug anlegen");
	
	
	public void onModuleLoad() {
		
		FahrtenbuchAdministrationAsync fahrtenbuchVerwaltung = null;
		
		if (fahrtenbuchVerwaltung == null) {
			fahrtenbuchVerwaltung = ClientSideSettings.getFahrtenbuchVerwaltung();
		}
		
		fahrzeugNeuBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				FahrtenbuchAdministrationAsync service = GWT.create(FahrtenbuchAdministration.class);
				
				// Erzeugen eines Asynchronous CallBack Objekts für das Ergebnis
				 AsyncCallback callback = new AsyncCallback() {
					    public void onSuccess(Void result) {
					      // do some UI stuff to show success
					    }

					    public void onFailure(Throwable caught) {
					      // do some UI stuff to show failure
					    }
					  };
					  service.createFahrer("Hans", "Servlet", "hans.servlet@gmx.de", callback);
				
			}
		});
	    menuPanel.add(fahrzeugNeuBtn);
	    
	    mainPanel.add(menuPanel);
	    
	    
	    RootPanel.get("Navigator").add(mainPanel);
		
		
	    
	

}


}

	    
	  

