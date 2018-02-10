package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;
import com.janhabersetzer.fahrtenbuch.server.FahrtenbuchAdministrationImpl;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class ContFahrzeug extends Composite{
		
		
	// Attribute fuer Aufrufe
		
	FahrtenbuchClientImpl serviceImpl;
		
	MainView mainView;
		
	int fahrzeugid;
		
	Fahrzeug fahrzeugProfil;
		
		
	//Panels
	VerticalPanel vPanel = new VerticalPanel();
		
	//Widgets
	FlexTable showFhrzFlexTable = new FlexTable();
	private Label ueberschriftLabel = new Label("Ein einzelnes Fahrzeug: ");
	Button bearbeitenBtn;
		
		
	//Widgets die durch "bearbeiten" dazukommen
	TextBox kennzeichenTBox;
	TextBox kmTBox;
	TextBox beschrTBox;
	TextBox farbeTBox;
	Button speichernBtn;
		Button abbrechenBtn;
		
		
	/**
	 * Constructor
	 * @param id des Fahrzeugs, das dargestellt werden soll
	 * @param mainView Referenz zum aufrufenden MainView-Objekt
	 * @param serviceImpl Referenz zum FahrtenbuchClientImpl-Objekt, das alle Serveraufrufe buendelt
	 */
	ContFahrzeug(int id, MainView mainView, FahrtenbuchClientImpl serviceImpl){
		initWidget(vPanel);
		this.fahrzeugid= id;
		this.mainView = mainView;
		this.serviceImpl = serviceImpl;
			
			
			
		/*
		 * CSS anwenden und die Tabelle formatieren.
		 */
		ueberschriftLabel.addStyleName("fahrtenbuch-label");
		showFhrzFlexTable.addStyleName("FlexTable");
		showFhrzFlexTable.setCellPadding(6);
		showFhrzFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");

			/**
			 * Erste Spalte der Tabelle festlegen.
			 */
			
			showFhrzFlexTable.setText(0, 0, "Kennzeichen");
			showFhrzFlexTable.setText(0, 1, "Aktueller Kilometerstand");
			showFhrzFlexTable.setText(0, 2, "Modellbeschreibung");
			showFhrzFlexTable.setText(0, 3, "Farbe");
			showFhrzFlexTable.setText(0, 4, "Anzeigen");
			showFhrzFlexTable.setText(0, 5, "Löschen");
			
			serviceImpl.getFahrzeug(id);
			
			
			//Füge das vPanel zusammen.
			vPanel.add(ueberschriftLabel);
			vPanel.add(showFhrzFlexTable);
		}
		
		
		public void schreibeFahrzeug(Fahrzeug v){
			//Füge Fahrzeug + Button in die Tabelle ein
			
			showFhrzFlexTable.setText(1, 0, v.getKennzeichen());
			showFhrzFlexTable.setText(1, 1, Integer.toString(v.getKm()));
			showFhrzFlexTable.setText(1 , 2, v.getModellBeschreibung());
			showFhrzFlexTable.setText(1 , 3, v.getFarbe());	
			
			//bearbeitenButton erzeugen, zur Tabelle hinzufügen und Clickhandler zuweisen
			bearbeitenBtn = new Button("Bearbeiten");
			bearbeitenBtn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					
				}
			});
			showFhrzFlexTable.setWidget(1, 4, bearbeitenBtn);
		}
		
		
		
	
	
	private void openBearbeiten(){
		
		//Setzte bearbeitenBtn auf disabled
		bearbeitenBtn.setEnabled(true);
		
		
		//Fuege eine Spalte mit den Textboxen und Buttons hinzu
		kennzeichenTBox.setText(fahrzeugProfil.getKennzeichen());
		showFhrzFlexTable.setWidget(2, 0, kennzeichenTBox);
		
		kmTBox.setText(Integer.toString(fahrzeugProfil.getKm()));
		showFhrzFlexTable.setWidget(2, 1, kmTBox);
	
		beschrTBox.setText(fahrzeugProfil.getModellBeschreibung());
		showFhrzFlexTable.setWidget(2, 2, beschrTBox);
		
		farbeTBox.setText(fahrzeugProfil.getFarbe());
		showFhrzFlexTable.setWidget(2, 3, farbeTBox);
		
		speichernBtn = new Button("Speichern");
		speichernBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				
				//Eingabe der Texfelder prüfen
				
				
				//Fahrzeug an server zum speichern uebergeben
				
				//
				
				
				
			}
		});
		showFhrzFlexTable.setWidget(2, 4, speichernBtn);
		
		
		abbrechenBtn = new Button("Abbrechen");
		abbrechenBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ContFahrzeug.this.schreibeFahrzeug(ContFahrzeug.this.fahrzeugProfil);
				
			}
		});
		showFhrzFlexTable.setWidget(2, 5, abbrechenBtn);
	}
		
		
}
	


	
	
