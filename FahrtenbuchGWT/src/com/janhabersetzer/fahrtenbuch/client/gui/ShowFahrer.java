package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.ClientSideSettings;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchGWT;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;




public class ShowFahrer extends VerticalPanel {
	
	/**
	 * Variable für angemeldeten Fahrer
	 */
	Fahrer fahrerProfil;

	/**
	 * Panels erzeugen.
	 */
	private VerticalPanel fahrerPanel = new VerticalPanel();
	private VerticalPanel infoPanel = new VerticalPanel();
	private HorizontalPanel horPanel = new HorizontalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();

	/**
	 * Widgets erzeugen.
	 */
	private Label ueberschriftLabel = new Label("Mein Profil:");
	private FlexTable showEigenerFahrerFlexTable = new FlexTable();
	private Label infoLabel = new Label();
	private Button loeschenButton = new Button("Profil löschen");
	private Button bearbeitenButton = new Button("Profil bearbeiten");
	
	
	/**
	 * Konstruktor der auch die onload()-Methode aufruft und so die Seite aufbaut.
	 * @param d angemeldetes Fahrerobjekt
	 */
	
	public ShowFahrer(Fahrer fahrerProfil){
		this.fahrerProfil= fahrerProfil;
		this.build();
	}

	
	public void build(){
		
		this.add(horPanel);
		horPanel.add(fahrerPanel);
		horPanel.add(infoPanel);

		/**
		 * CSS anwenden und die Tabelle formatieren.
		 */
		ueberschriftLabel.addStyleName("fahrtenbuch-label");
		showEigenerFahrerFlexTable.addStyleName("FlexTable");
		showEigenerFahrerFlexTable.setCellPadding(6);
		showEigenerFahrerFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");
		
		
		//Tabellenbeschriftung erzeugen
		
		showEigenerFahrerFlexTable.setText(0, 0, "Vorname");
		showEigenerFahrerFlexTable.setText(1, 0, "Nachname");
		showEigenerFahrerFlexTable.setText(2, 0, "Email-Adresse");
		
		//Tabelle befuellen
		
		showEigenerFahrerFlexTable.setText(0, 1, fahrerProfil.getVorname());
		showEigenerFahrerFlexTable.setText(1, 1, fahrerProfil.getNachname());
		showEigenerFahrerFlexTable.setText(2, 1, fahrerProfil.getNachname());
		
		
		/**
		 * ClickHandler fuer den Button zum Bearbeiten des Fahrers.
		 *  Sobald der Button betaetigt wird, wird die Seite zum
		 * Bearbeiten des Fahrer aufgerufen.
		 */
		
		bearbeitenButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				EditFahrer editFahrer = new EditFahrer(fahrerProfil);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(editFahrer);
			}
		});
		
		/**
		 * Clickhandler für das löschen dieses Fahrers.
		 * Die eigentliche Funktionalitaet diese wird in der Methode loescheDiesenFahrer() implementiert.
		 * Sie wird von diesem Clickhandler lediglich aufgerufen.
		 * Zur Dokumnetation des Loeschens siehe daher <code>loescheDiesenFaher()</code>
		 */
		
		loeschenButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				loescheDiesenFahrer();
			}
		});
		
		/**
		 * Zusaetzlich zu den Profildaten werden die Infos des Nuterprofils
		 * angezeigt.
		 */
		//String listtyp = "Np";
		//ShowInfo showInfo = new ShowInfo(profilId, profiltyp, listtyp);
		
		
		/**
		 * Widgets den Panels hinzufuegen.
		 */
		fahrerPanel.add(ueberschriftLabel);
		fahrerPanel.add(showEigenerFahrerFlexTable);
		buttonPanel.add(bearbeitenButton);
		buttonPanel.add(loeschenButton);
		fahrerPanel.add(buttonPanel);
		fahrerPanel.add(infoLabel);
		//infoPanel.add(showInfo);
		
	}
	
	/**
	 * Methode um den Fahrer aus der Datenbank zu löschen und sich gleichzeitig auszuloggen
	 */
	
	
	//***************Frage wie kann man das Feedback zurückgeben, wenn deleteFahrer() das Löschen verweigert?********
	
	public void loescheDiesenFahrer(){
		if (Window.confirm("Möchten Sie Ihr Profil und sich als Fahrer wirklich löschen?")) {
			ClientSideSettings.getFahrtenbuchVerwaltung().deleteFahrer(fahrerProfil, new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					Window.alert("Fahrer gelöscht");
					RootPanel.get("Details").clear();
					RootPanel.get("Navigator").add(new Navigator());
					
				}@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}

}
