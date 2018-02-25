package com.janhabersetzer.fahrtenbuch.client.gui;

import java.util.ArrayList;
import java.util.Vector;

import org.apache.bcel.generic.NEW;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;
import com.janhabersetzer.fahrtenbuch.shared.report.AlleFahrtenVonFahrerReport;
import com.janhabersetzer.fahrtenbuch.shared.report.AlleFahrtenVonFahrzeugReport;

public class ContAlleReports extends Composite{
		 
		// Attribute fuer Aufrufe
		private MainView mainView;
		private FahrtenbuchClientImpl serviceImpl;
		
		private Vector<Fahrer> alleFahrer;
		
		private Vector<Fahrzeug> alleFahrzeuge;
		
		private Fahrer fahrerProfil;
		private Fahrzeug fahrzeugProfil;
		
		//Panels
		
		private VerticalPanel vPanel= new VerticalPanel();
		
		/*
		 * Widgets erzeugen.
		 */
		private Label ueberschriftLabel = new Label("Wähle einen Bericht:");
		
		//Radio-Buttons Bereich
		private HorizontalPanel radioButtonPanel = new HorizontalPanel();
		
		
		// Fahrzeug oder Fahrer Wählen Bereich
		private FlexTable selectTable = new FlexTable();
		private Label waehleFahrerLbl = new Label("Wähle die eMailadresse eines Fahrers aus, zu dem ein Bericht erstellt werden soll");
		private Label waehleFahrzeugLbl = new Label("Wähle das Kennzeichen ein Fahrzeug aus, zu dem ein Bericht erstellt werden soll");
		private ListBox waehleFahrerLBox;
		private ListBox waehleFahrzeugLBox;
		private Button processFhrRpBtn = new Button("Erstelle Bericht für diesen Fahrer");
		private Button processFhrzRpBtn = new Button("Erstelle Bericht für dieses Fahrzeug");
		
		
		
		 public ContAlleReports(MainView mainView, FahrtenbuchClientImpl serviceImpl){
			 initWidget(this.vPanel);
		
			this.mainView = mainView;
			this.serviceImpl = serviceImpl;
			
			/*
			 * CSS anwenden und die Tabelle formatieren.
		
			 */
			ueberschriftLabel.addStyleName("fahrtenbuch-label");
			
	
			
			//RadioButtons und Clickhandler erzeugen
			RadioButton rbFhr = new RadioButton("myRadioGroup", "Report aller Fahrten eines bestimmten Fahrers");
			rbFhr.addClickHandler(new RadioFahrerClickHandler());
			RadioButton rbFhz = new RadioButton("myRadioGroup", "Report aller Fahrten eines bestimmten Fahrzeugs");
			rbFhz.addClickHandler(new RadioFahrzeugClickHandler());
			
			
			
			radioButtonPanel.add(rbFhr);
			radioButtonPanel.add(rbFhz);
			
			vPanel.add(radioButtonPanel);

		}
		
		 
		
		public void zeigeWahlTabelleFahrer(Vector<Fahrer> vec){
			
			this.alleFahrer = vec;
			
			//Listbox zusammenfuegen, um den Fahrer waehlen zu koennen
			waehleFahrerLBox = new ListBox();
			for(int i=0; i < alleFahrer.size(); i ++){
				Fahrer fahrer = alleFahrer.get(i);
				waehleFahrerLBox.addItem(fahrer.getVorname()+" "+fahrer.getNachname()+" / "+fahrer.getEMail());
				
			}
			
			//Auswahltabelle zusammefuegen
			selectTable.setWidget(0, 0, waehleFahrerLbl);
			selectTable.setWidget(1, 0, waehleFahrerLBox);
			
			processFhrRpBtn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					//Ausgeählten Fahrer auslesen
					int index = waehleFahrerLBox.getSelectedIndex();
					ContAlleReports.this.fahrerProfil = alleFahrer.elementAt(index);
					
					//createAlleFahrtenVonFahrerReport erstellen und in neues HTML Panel einfügen.
					 serviceImpl.schreibeFahrerReportHTML(fahrerProfil);
					
				}
			});
			selectTable.setWidget(1, 1, processFhrRpBtn);
			
			vPanel.add(selectTable);
			
		}
		
		
		public void zeigeFahrerReport(String htmlString){
			HTMLPanel htmlPanel = new HTMLPanel(htmlString);
			vPanel.clear();
			vPanel.add(htmlPanel);
		}
		
		
		public void zeigeWahlTabelleFahrzeug(Vector<Fahrzeug> vec){
			
			this.alleFahrzeuge = vec;
			
			//Listbox zusammenfuegen, um den Fahrer waehlen zu koennen
			waehleFahrzeugLBox = new ListBox();
			for(int i=0; i < alleFahrzeuge.size(); i ++){
				Fahrzeug fahrzeug = alleFahrzeuge.get(i);
				waehleFahrzeugLBox.addItem(fahrzeug.getKennzeichen()+" / "+fahrzeug.getModellBeschreibung()+" / "+fahrzeug.getFarbe());
				
			}
			
			//Auswahltabelle zusammefuegen
			selectTable.setWidget(0, 0, waehleFahrzeugLbl);
			selectTable.setWidget(1, 0, waehleFahrzeugLBox);
			
			processFhrzRpBtn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					//Ausgeählten Fahrer auslesen
					int index = waehleFahrzeugLBox.getSelectedIndex();
					ContAlleReports.this.fahrzeugProfil = alleFahrzeuge.elementAt(index);
					
					//createAlleFahrtenVonFahrzeugReport erstellen und in neues HTML Panel einfügen.
					 serviceImpl.schreibeFahrzeugReportHTML(fahrzeugProfil);
					
				}
			});
			selectTable.setWidget(1, 1, processFhrzRpBtn);
			
			vPanel.add(selectTable);
			
		}
		
		public void zeigeFahrzeugReport(String htmlString){
			HTMLPanel htmlPanel = new HTMLPanel(htmlString);
			vPanel.clear();
			vPanel.add(htmlPanel);
		}
		
		
		private class RadioFahrerClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				serviceImpl.getAlleFahrer();		
			}
			
		}
		
		private class RadioFahrzeugClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				serviceImpl.getAlleFahrzeug();	
			}
			
		}
		
		
		


}
