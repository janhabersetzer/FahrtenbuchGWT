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
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;

public class ContEditFahrer extends Composite{
	
	// Attribute fuer Aufrufe
	private MainView mainView;
	
	private FahrtenbuchClientImpl serviceImpl;
	
	private Fahrer fahrerProfil;
	
	//Panels
	private VerticalPanel vPanel= new VerticalPanel();
	private FlexTable showFahrerFlexTable = new FlexTable();
	
	//Widgets oeffneBearbeiten(Fahrer fahrer) hinzugefügt werden
	TextBox vorNameTBox = new TextBox();
	TextBox nachNameTBox = new TextBox();
	TextBox eMailTBox = new TextBox();
	Button speichernBtn;
	Button abbrechenBtn;
	
	private Label ueberschriftLabel = new Label("Fahrer bearbeiten: ");
	private Label warnungLabel = new Label();
	
	
	ContEditFahrer(Fahrer fahrer, MainView mainView, FahrtenbuchClientImpl serviceImpl){
		initWidget(this.vPanel);
		
		this.fahrerProfil = fahrer;
		
		this.mainView = mainView;
		
		this.serviceImpl =  serviceImpl;
		
		
		/*
		 * CSS anwenden und beide Tabellen zu formatieren.
		 */
		ueberschriftLabel.addStyleName("fahrtenbuch-label");
		warnungLabel.setStyleName("red_label");
		showFahrerFlexTable.addStyleName("FlexTable");
		showFahrerFlexTable.setCellPadding(6);
		showFahrerFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");
		
		/**
		 * Erste Spalte der Fahrten-Tabelle festlegen.
		 */
		showFahrerFlexTable.setText(0, 0, "Vorname");
		showFahrerFlexTable.setText(0, 1, "Nachname");
		showFahrerFlexTable.setText(0, 2, "E-Mail");
		showFahrerFlexTable.setText(0, 3, "Speichern");
		showFahrerFlexTable.setText(0, 4, "Abbrechen");
		
		//Da hier das fahrerobjekt bereits bekannt ist, kann man die Textfelder direkt(ohne AsyncCall) befüllen und anzeigen
		vorNameTBox.setText(fahrerProfil.getVorname());
		showFahrerFlexTable.setWidget(1, 0, vorNameTBox);
		
		nachNameTBox.setText(fahrerProfil.getNachname());
		showFahrerFlexTable.setWidget(1, 1, nachNameTBox);
		
		eMailTBox.setText(fahrerProfil.getEMail());
		showFahrerFlexTable.setWidget(1, 2, eMailTBox);
		
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
					
						fahrerProfil.setVorname(vorNameTBox.getText());
						
						fahrerProfil.setNachname(nachNameTBox.getText());
						
						fahrerProfil.setEMail(eMailTBox.getText());
				
						}catch(RuntimeException e){
						
						}
					//Fahrzeug an server zum speichern uebergeben
					serviceImpl.updateFahrer(fahrerProfil);
				}
			}
		});
		showFahrerFlexTable.setWidget(1, 3, speichernBtn);
		
		
		abbrechenBtn = new Button("Abbrechen");
		abbrechenBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(Window.confirm("Wollen Sie das Bearbeiten abbrechen?")){
				ContEditFahrer.this.mainView.openAlleFahrerCont();
				}	
			}
		});
		showFahrerFlexTable.setWidget(1, 4, abbrechenBtn);
		
		
		vPanel.add(ueberschriftLabel);
		vPanel.add(showFahrerFlexTable);
		
	}
	
	
	
	public Boolean pruefeEingabe(){
		//Prüfen, ob die Felder leer sind
		if (vorNameTBox.getText().length() == 0) {
			warnungLabel.setText("Dieses Feld darf nicht leer sein");
			showFahrerFlexTable.setWidget(2, 0, warnungLabel);
		} 
		else if (nachNameTBox.getText().length() == 0) {
			warnungLabel.setText("Dieses Feld darf nicht leer sein");
			showFahrerFlexTable.setWidget(2, 1, warnungLabel);
		}
		else if (eMailTBox.getText().length() == 0){
			warnungLabel.setText("Dieses Feld darf nicht leer sein");
			showFahrerFlexTable.setWidget(2, 2, warnungLabel);
		}
		else{
			return true;
		}
		return false;
		
	}

}
