package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class ContCreateFahrzeug extends Composite{
	// Attribute fuer Aufrufe
	
	private MainView mainView;
	
	private FahrtenbuchClientImpl serviceImpl;
	
	private Fahrzeug fahrzeugProfil;
	
	//Panels
	private VerticalPanel vPanel= new VerticalPanel();
	private FlexTable showFhrzFlexTable = new FlexTable();
	
	//Widgets oeffneBearbeiten(Fahrer fahrer) hinzugefügt werden
	TextBox kennzeichenTBox = new TextBox();
	TextBox modellTBox = new TextBox();
	TextBox kmTBox = new TextBox();
	TextBox farbeTBox = new TextBox();
	Button speichernBtn;
	Button abbrechenBtn;
	
	private Label ueberschriftLabel = new Label("Neues Fahrzeug anlegen: ");
	private Label warnungLabel = new Label();
	private Label reqLabel1= new Label("* Pflichtfeld");
	private Label reqLabel2= new Label("* Pflichtfeld");
	private Label reqLabel3= new Label("* Pflichtfeld");
	private Label reqLabel4= new Label("* Pflichtfeld");

	
	
	ContCreateFahrzeug(MainView mainView, FahrtenbuchClientImpl serviceImpl){
		initWidget(this.vPanel);
		
		this.fahrzeugProfil = new Fahrzeug();
		
		this.mainView = mainView;
		
		this.serviceImpl =  serviceImpl;
		
		
		/*
		 * CSS anwenden und beide Tabellen zu formatieren.
		 */
		ueberschriftLabel.addStyleName("fahrtenbuch-label");
		warnungLabel.setStyleName("red_label");
		showFhrzFlexTable.addStyleName("FlexTable");
		showFhrzFlexTable.setCellPadding(6);
		showFhrzFlexTable.getRowFormatter().addStyleName(0, "TableHeader");
		reqLabel1.setStyleName("grey_label");
		reqLabel2.setStyleName("grey_label");
		reqLabel3.setStyleName("grey_label");
		reqLabel4.setStyleName("grey_label");

		
		/**
		 * Erste Spalte der Fahrten-Tabelle festlegen.
		 */
		showFhrzFlexTable.setText(0, 0, "Kennzeichen");
		showFhrzFlexTable.setText(0, 1, "Kilometerstand");
		showFhrzFlexTable.setText(0, 2, "Modellbeschreibung");
		showFhrzFlexTable.setText(0, 3, "Farbe");
		showFhrzFlexTable.setText(0, 4, "Speichern");
		showFhrzFlexTable.setText(0, 5, "Abbrechen");
		
		//Zweite Zeile 
		
		showFhrzFlexTable.setWidget(1, 0, kennzeichenTBox);
		
		showFhrzFlexTable.setWidget(1, 1, kmTBox);
		
		showFhrzFlexTable.setWidget(1, 2, modellTBox);
		
		showFhrzFlexTable.setWidget(1, 3, farbeTBox);
		
		
		
		//SpeichernButton
		speichernBtn = new Button("Speichern");
		speichernBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				
				//Eingabe der Texfelder prüfen
				if(pruefeEingabe()==true){
				
					try{
						//fahrerProfil mit den Texfeldern überschreibe
					
						fahrzeugProfil.setKennzeichen(kennzeichenTBox.getText());
						
						fahrzeugProfil.setModellBeschreibung(modellTBox.getText());
						
						fahrzeugProfil.setKm(Integer.parseInt(kmTBox.getText()));
						
						fahrzeugProfil.setFarbe(farbeTBox.getText());
				
						}catch(RuntimeException e){
						
						}
					//Fahrzeug an server zum speichern uebergeben
					ContCreateFahrzeug.this.serviceImpl.saveFahrzeug(fahrzeugProfil);
				}
			}
		});
		showFhrzFlexTable.setWidget(1, 4, speichernBtn);
		
		
		abbrechenBtn = new Button("Abbrechen");
		abbrechenBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(Window.confirm("Wollen Sie das Bearbeiten abbrechen?")){
				ContCreateFahrzeug.this.mainView.openAlleFhrzCont();
				}	
			}
		});
		showFhrzFlexTable.setWidget(1, 5, abbrechenBtn);
		
		//Hinweislabel in dritter Zeile
		showFhrzFlexTable.setWidget(2, 0, reqLabel1);
		showFhrzFlexTable.setWidget(2, 1, reqLabel2);
		showFhrzFlexTable.setWidget(2, 2, reqLabel3);
		showFhrzFlexTable.setWidget(2, 3, reqLabel4);
		
		vPanel.add(ueberschriftLabel);
		vPanel.add(showFhrzFlexTable);
		
	}
		
	
	public Boolean pruefeEingabe(){
		//Prüfen, ob die Felder leer sind
		if (kennzeichenTBox.getText().length() == 0) {
			warnungLabel.setText("Dieses Feld darf nicht leer sein");
			showFhrzFlexTable.setWidget(2, 0, warnungLabel);
		} 
		else if (modellTBox.getText().length() == 0) {
			warnungLabel.setText("Dieses Feld darf nicht leer sein");
			showFhrzFlexTable.setWidget(2, 1, warnungLabel);
		}
		else if (kmTBox.getText().length() == 0){
			warnungLabel.setText("Dieses Feld darf nicht leer sein");
			showFhrzFlexTable.setWidget(2, 2, warnungLabel);
		}
		else if (farbeTBox.getText().length() == 0){
			warnungLabel.setText("Dieses Feld darf nicht leer sein");
			showFhrzFlexTable.setWidget(2, 3, warnungLabel);
		}
		else{
			return true;
		}
		return false;
		
	}



	
	
}
