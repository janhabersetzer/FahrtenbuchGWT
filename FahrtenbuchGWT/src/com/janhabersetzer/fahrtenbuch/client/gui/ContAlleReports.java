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

public class ContAlleReports extends Composite{
		 
		// Attribute fuer Aufrufe
		MainView mainView;
		FahrtenbuchClientImpl serviceImpl;
		
		//Panels
		
		private VerticalPanel vPanel= new VerticalPanel();
		
		/*
		 * Widgets erzeugen.
		 */
		private Label ueberschriftLabel = new Label("Wähle einen Bericht:");
		
		//Radio-Buttons Bereich
		private HorizontalPanel radioButtonPanel = new HorizontalPanel();
		private RadioButton rbFhr;
		private RadioButton rbFhz;
		
		// Fahrzeug oder Fahrer Wählen Bereich
		private FlexTable selectTable = new FlexTable();
		private Label waehleFahrerLbl = new Label("Wähle ein Fahrzeug aus, zu dem ein Bericht erstellt werden soll");
		private Label waehleFahrzeugLbl = new Label("Wähle ein Fahrzeug aus, zu dem ein Bericht erstellt werden soll");
		private ListBox waehleFahrerBox;
		private ListBox waehleFahrzeugBox = new ListBox();
		private Button processFhrRpBtn = new Button("Erstelle Bericht für diesen Fahrer");
		private Button processFhzRpBtn = new Button("Erstelle Bericht für diesen Fahrer");
		
		
		
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
			
			//Listbox zusammenfuegen, um den Fahrer waehlen zu koennen
			waehleFahrerBox = new ListBox();
			for(int i=0; i < vec.size(); i ++){
				waehleFahrerBox.addItem(vec.get(i).getVorname() + " " + vec.get(i).getNachname());
				
			}
			
			
			
			selectTable.setWidget(0, 0, waehleFahrerLbl);
			selectTable.setWidget(1, 0, waehleFahrerBox);
			selectTable.setWidget(1, 1, processFhrRpBtn);
			
			
			
		}
		
		
		
		private class RadioFahrerClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
						
			}
			
		}
		
		private class RadioFahrzeugClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
						
			}
			
		}
		


}
