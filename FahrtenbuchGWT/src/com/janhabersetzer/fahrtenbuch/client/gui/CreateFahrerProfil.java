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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.ClientSideSettings;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchGWT;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;


public class CreateFahrerProfil extends VerticalPanel {
	
	private String email = FahrtenbuchGWT.getLoginInfo().getEmailAddress();
	
	/**
	 * Panels erzeugen.
	 */
	private VerticalPanel verPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	/**
	 * Widgets erzeugen.
	 */
	private Label ueberschriftLabel = new Label("Fahrer-Profil anlegen:");
	private FlexTable createFahrerProfilFlexTable = new FlexTable();
	private TextBox vornameTextBox = new TextBox();
	private TextBox nachnameTextBox = new TextBox();
	
	
	
	private Button createFahrerProfilButton = new Button("Profil anlegen");
	private Button abbrechenButton = new Button("Abbrechen");
	
	
	private Label reqLabel1 = new Label("* Pflichtfeld");
	private Label reqLabel2 = new Label("* Automatisch ausgefüllt");
	private Label warnungLabel = new Label();
	
	
	public CreateFahrerProfil(){
		build();
	}
	
	/**
	 * Diese Methode baut die gesamte Seite auf
	 */

	public void build(){
		
		//zuweisen der CSS Id's
		ueberschriftLabel.addStyleName("fahrtenbuch-label");
		reqLabel1.setStyleName("red_label");
		reqLabel2.setStyleName("red_label");
		warnungLabel.setStyleName("red_label");
		createFahrerProfilFlexTable.addStyleName("FlexTable");
		createFahrerProfilFlexTable.setCellPadding(6);
		createFahrerProfilFlexTable.getColumnFormatter().addStyleName(0,"TableHeader");
		
		//Attributspalte erzeugen
		createFahrerProfilFlexTable.setText(0, 0, "Vorname");
		createFahrerProfilFlexTable.setText(1, 0, "Nachname");
		createFahrerProfilFlexTable.setText(2, 0, "Email-Adresse");
		
		//Tabelle befuellen
		createFahrerProfilFlexTable.setWidget(0, 1, vornameTextBox);
		createFahrerProfilFlexTable.setWidget(0, 2, reqLabel1);
		
		createFahrerProfilFlexTable.setWidget(1, 1, nachnameTextBox);
		createFahrerProfilFlexTable.setWidget(1, 2, reqLabel1);
		
		createFahrerProfilFlexTable.setText(2, 1, email);
		createFahrerProfilFlexTable.setWidget(2, 2, reqLabel2);
		
		/**
		 * 
		 */
		createFahrerProfilButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				pruefeEingabe();	
			}
		});
		
		/**
		 * Clickhandler für den Abbrechen-Button leitet zur LogoutURL weiter
		 */
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {			
				Window.Location.replace(FahrtenbuchGWT.getLoginInfo().getLogoutUrl());
			}
		});
		
		/**
		 * Widgets dem Panel hinzufuegen.
		 */
		buttonPanel.add(createFahrerProfilButton);
		buttonPanel.add(abbrechenButton);
		
		verPanel.add(ueberschriftLabel);
		verPanel.add(createFahrerProfilFlexTable);
		verPanel.add(buttonPanel);
	}
	
	public void pruefeEingabe() {
		boolean vornameWert = vornameTextBox.getText().matches("^[a-zA-ZäöüÄÖÜß ]+$");
		boolean nachnameWert = nachnameTextBox.getText().matches("^[a-zA-ZäöüÄÖÜß ]+$");
		
		//Überprüfung ob Textfeld leer ist
		if (vornameTextBox.getText().length() == 0) {
			warnungLabel.setText("Bitte geben Sie Ihren Vornamen an.");
			createFahrerProfilFlexTable.setWidget(0, 3, warnungLabel);
		} else if (nachnameTextBox.getText().length() == 0) {
			warnungLabel.setText("Bitte geben Sie Ihren Nachnamen an.");
			createFahrerProfilFlexTable.setWidget(1, 3, warnungLabel);
		//Überprüfung ob der oben gesetzte Boolen-Wert für ungültige Zeichen true oder false ist
		} else if (vornameWert == false) {
			warnungLabel.setText("Ihr Vorname enthält ungültige Zeichen.");
			createFahrerProfilFlexTable.setWidget(0, 3, warnungLabel);
		} else if (nachnameWert == false) {
			warnungLabel.setText("Ihr Nachname enthält ungültige Zeichen.");
			createFahrerProfilFlexTable.setWidget(1, 3, warnungLabel);
		}else {
			fahrerProfilAnlegen();
		}
	}
	
	
	public void fahrerProfilAnlegen(){
		ClientSideSettings.getFahrtenbuchVerwaltung().
		createFahrer(
					vornameTextBox.getText(), 
					nachnameTextBox.getText(), 
					email, 
					new AsyncCallback<Fahrer>() {
					
					@Override
					public void onSuccess(Fahrer result) {
					Navigator navigator = new Navigator();
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(navigator);
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
					}
				});
	}
	
	
}
