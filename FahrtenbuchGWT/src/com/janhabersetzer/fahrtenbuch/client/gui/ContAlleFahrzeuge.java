package com.janhabersetzer.fahrtenbuch.client.gui;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class ContAlleFahrzeuge extends Composite{
	
	// Attribute fuer Aufrufe
	MainView mainView;
	FahrtenbuchClientImpl serviceImpl;
	
	//Panels
	
	private VerticalPanel vPanel= new VerticalPanel();
	
	/*
	 * Widgets erzeugen.
	 */
	private Label ueberschriftLabel = new Label("Alle Fahrzeuge:");
	String text ="Hier hat sich was geändert"; 
	private FlexTable showFhrzFlexTable = new FlexTable();
	private Button loeschenButton;
	private Button anzeigenButton;
	
	/*
	 * Um den Index Fahrzeugen in der FlexTable ermitteln zu können brauchen wir diese ArrayList
	 *  --> siehe StockWatcher Bsp.
	 */
	private ArrayList<Integer> fahrzeugIDs =  new ArrayList<Integer>();
	
	private int arrayListIndex;
	

	
	public ContAlleFahrzeuge(MainView mainView, FahrtenbuchClientImpl serviceImpl){
		initWidget(this.vPanel);
		
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
		
		
	
		/**
		 *  Die Klasse <code>FahrtenbuchClientImpl</code> ruft alle Fahrzeuge ab und ruft bei <code>onSuccess</code> 
		 *  des <code>GetAllFahrzeugCallback</code> die Mehtode <code>befuelleFhzTabelle(Vector<Fahrzeug> vec)</code> auf.
		 *  So wird die <code>showFhrzFlexTable</code> aufgebaut, jedoch noch nicht dem vPanel hinzugefügt.
		 */
		serviceImpl.getAlleFahrzeug();
		
		
		//Füge das vPanel zusammen.
		vPanel.add(ueberschriftLabel);
		//Fuege die vollstaendige FlexTable dem vPanel hinzu
		vPanel.add(showFhrzFlexTable);
	}
	
	
	public void befuelleFhzTabelle(Vector<Fahrzeug> vec){
		
		for (int i = 0; i < vec.size(); i++){
			
			final int id = vec.get(i).getId();
			
			fahrzeugIDs.add(id);
			
			arrayListIndex = fahrzeugIDs.indexOf(id);
			
			//Füge Fahrzeug + Buttons für jedes Fhrz in die Tabelle ein
			
			showFhrzFlexTable.setText((i+1), 0, vec.get(i).getKennzeichen());
			showFhrzFlexTable.setText((i+1), 1,  Integer.toString(vec.get(i).getKm()));
			showFhrzFlexTable.setText((i+1), 2,  vec.get(i).getModellBeschreibung());
			showFhrzFlexTable.setText((i+1), 3, vec.get(i).getFarbe());
			
			
			//anzeigenButton erzeugen, zur Tabelle hinzufügen und Clickhandler zuweisen
			anzeigenButton = new Button("Fahrzeug anzeigen");
			anzeigenButton.addClickHandler(new AnzeigenClickHandler()); 
			showFhrzFlexTable.setWidget( (i+1), 4, anzeigenButton);
			
				
			
			//LoeschenButton erzeugen, zur Tabelle hinzufügen und Clickhandler zuweisen
			loeschenButton = new Button("Fahrzeug löschen");
			loeschenButton.addClickHandler(new LoeschenClickHandler());
			showFhrzFlexTable.setWidget((i+1), 5, loeschenButton);		
		}
		
	}
	
	
	private class AnzeigenClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			int fahrzeugId = fahrzeugIDs.get(arrayListIndex);
			ContFahrzeug contFahrzeug = new ContFahrzeug(fahrzeugId);
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(contFahrzeug);		
		}
		
	}
	
	private class LoeschenClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			if (Window.confirm("Möchten Sie dieses Fahrzeug wirklich löschen?")){
				int fahrzeugId = fahrzeugIDs.get(arrayListIndex);
				fahrzeugIDs.remove(arrayListIndex);
				serviceImpl.deleteFahrzeug(fahrzeugId);
				
			}
		}
	}

}
