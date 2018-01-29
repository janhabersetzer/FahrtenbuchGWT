package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.ClientSideSettings;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;



public class EditFahrer extends VerticalPanel {
	
	//Zugehöriger Fahrer
	Fahrer fahrerProfil;

	/**
	 * Panels erzeugen.
	 */
	private VerticalPanel verPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	/**
	 * Widgets erzeugen.
	 */
	private Label ueberschriftLabel = new Label("Profil bearbeiten:");
	private FlexTable editFahrerFlexTable = new FlexTable();
	private TextBox vornameTextBox = new TextBox();
	private TextBox nachnameTextBox = new TextBox();


	private Label emailLabel = new Label();
	private Button editFahrerButton = new Button("Profil speichern");
	private Button abbrechenButton = new Button("Abbrechen");
	private Label reqLabel1 = new Label("* Pflichtfeld");

	private Label infoLabel = new Label();
	private Label warnungLabel = new Label();
	private Label pfadLabelShowFhr = new Label("Zurück zu: Profil anzeigen");
	
	
	/**
	 * Konstruktor der auch die onload()-Methode aufruft und so die Seite aufbaut.
	 * @param d angemeldetes Fahrerobjekt
	 */
	public EditFahrer(Fahrer fahrerProfil){
		this.fahrerProfil= fahrerProfil;
		this.build();
	}
	
	
	private void build(){
		
		this.add(verPanel);
		
		/**
		 * CSS anwenden und die Tabelle formatieren.
		 */
		ueberschriftLabel.addStyleName("partnerboerse-label");
		reqLabel1.setStyleName("red_label");
		warnungLabel.setStyleName("red_label");
		editFahrerFlexTable.addStyleName("FlexTable");
		editFahrerFlexTable.setCellPadding(6);
		editFahrerFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");
		pfadLabelShowFhr.addStyleName("fahrtenbuch-zurueckbutton");
		
		// Erste Zeile der Tabelle setzen
		 editFahrerFlexTable.setText(0, 0, "Vorname");
		 editFahrerFlexTable.setText(1, 0, "Nachname");
		 editFahrerFlexTable.setText(2, 0, "E-Mail Adresse");
		 
		 //Textfeld für Vorname und Hinweis auf Pflichtfeld
		 editFahrerFlexTable.setWidget(0, 1, vornameTextBox);
		 editFahrerFlexTable.setWidget(0, 2, reqLabel1);
		 
		 //Textfedl für Nachname und Hinweis auf Pflichtfeld
		 editFahrerFlexTable.setWidget(1, 1, nachnameTextBox);
		 editFahrerFlexTable.setWidget(1, 2, reqLabel1);
		 
		 
		 editFahrerFlexTable.setWidget(2, 1, emailLabel);
		 
		 befuelleTabelle();
		 
		 /**
		  * Clickhandler für das Speichern der Eingabe. 
		  * Bevor diese gespeichert wird, wird die Validitaet der Eingabe ueberprueft
		  */
		 
		editFahrerButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				pruefeEingabe(); 
				}
		});
		
		/**
		 * ClickHandler fuer den Button zum Abbrechen dieser Ansicht
		 * und somit dem Bearbeiten eines Nutzerprofils. 
		 * Sobald dieser Clickhandler feuert, wird die Seite zum Anzeigen des eigenen Nutzerprofils
		 * aufgerufen.
		 */
		
		abbrechenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ShowFahrer showFahrer = new ShowFahrer(fahrerProfil);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showFahrer);
			}
		});
		
		/**
		 * ClickHandler fuer den Button zum Verlassen dieser Ansicht
		 * und somit dem Bearbeiten eines Nutzerprofils. 
		 * Sobald dieser Clickhandler feuert, wird die Seite zum Anzeigen des eigenen Nutzerprofils
		 * aufgerufen.
		 */
		
		pfadLabelShowFhr.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ShowFahrer showFahrer = new ShowFahrer(fahrerProfil);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showFahrer);	
			}
		});
		
		/**
		 * Widgets den Panels hinzufuegen.
		 */
		verPanel.add(ueberschriftLabel);
		verPanel.add(editFahrerFlexTable);
		buttonPanel.add(editFahrerButton);
		buttonPanel.add(abbrechenButton);
		verPanel.add(buttonPanel);
		verPanel.add(infoLabel);
	}
	
	
	/**
	 * Methode zum Befüllen der Tabelle via Verwendung eines AsyncCallback
	 */
	
	public void befuelleTabelle(){
		ClientSideSettings.getFahrtenbuchVerwaltung().getFahrerByEmail(fahrerProfil.getEMail(), new AsyncCallback<Fahrer>() {
			
			@Override
			public void onSuccess(Fahrer result) {
				vornameTextBox.setText(result.getVorname());
				
				nachnameTextBox.setText(result.getNachname());
				
				emailLabel.setText(result.getEMail());
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		/**
		 * Widgets dem Panel hinzufuegen.
		 */
		verPanel.add(pfadLabelShowFhr);
		verPanel.add(ueberschriftLabel);
		verPanel.add(editFahrerFlexTable);
		verPanel.add(editFahrerButton);
		verPanel.add(infoLabel);
	}
	
	/**
	 * Methode um Eingabe der Textfelder danach zu prüfen, ob diese leer sind, oder ungültige Zeichen enthalten
	 */
	
	public void pruefeEingabe(){
		
		//Setzen eines Boolen-Wertes, je nach dem ob das Textfeld ein ungültiges Zeichen enthält
		boolean vornameWert = vornameTextBox.getText().matches("^[a-zA-ZäöüÄÖÜß ]+$");
		boolean nachnameWert = nachnameTextBox.getText().matches("^[a-zA-ZäöüÄÖÜß ]+$");
		
		//Überprüfung ob Textfeld leer ist
		if (vornameTextBox.getText().length() == 0) {
			warnungLabel.setText("Bitte geben Sie Ihren Vornamen an.");
			editFahrerFlexTable.setWidget(0, 3, warnungLabel);
		} else if (nachnameTextBox.getText().length() == 0) {
			warnungLabel.setText("Bitte geben Sie Ihren Nachnamen an.");
			editFahrerFlexTable.setWidget(1, 3, warnungLabel);
		//Überprüfung ob der oben gesetzte Boolen-Wert für ungültige Zeichen true oder false ist
		} else if (vornameWert == false) {
			warnungLabel.setText("Ihr Vorname enthält ungültige Zeichen.");
			editFahrerFlexTable.setWidget(0, 3, warnungLabel);
		} else if (nachnameWert == false) {
			warnungLabel.setText("Ihr Nachname enthält ungültige Zeichen.");
			editFahrerFlexTable.setWidget(1, 3, warnungLabel);
		} else {
			aktualisiereFahrer(); 
		}
	}
	

	public void aktualisiereFahrer(){
		
		//Zuerst wird das fahrerProfil um die gemachten Eingaben erweitert.
		fahrerProfil.setVorname(vornameTextBox.getText());
		fahrerProfil.setNachname(nachnameTextBox.getText());
		
		/*Nun kann dieses geänderte Objekt an die Applikationslogik gegeben werden, die den mit den Daten dieses Fahrerobjekts
		 * den Entsprechenden Tupel in der Datenbank updated 
		 */
	
		ClientSideSettings.getFahrtenbuchVerwaltung().saveFahrer(fahrerProfil, new AsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				ShowFahrer showFahrer = new ShowFahrer(fahrerProfil);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showFahrer);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
