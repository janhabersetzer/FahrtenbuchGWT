package com.janhabersetzer.fahrtenbuch.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.gui2.ShowFahrzeug;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class ContAlleFahrzeuge extends Composite{
	
	private VerticalPanel vPanel= new VerticalPanel();
	
	/**
	 * Widgets erzeugen.
	 */
	private Label ueberschriftLabel = new Label("Alle Fahrzeuge:");
	private FlexTable showFhrzFlexTable = new FlexTable();
	private Label infoLabel = new Label();
	private Button loeschenButton;
	private Button anzeigenButton;
	
//	private Label lbl = new Label("Hier ist der ContAlleFahrzeuge Content");
	
	public ContAlleFahrzeuge(){
		initWidget(this.vPanel);
//		vPanel.add(lbl);
		
		/*
		 * CSS anwenden und die Tabelle formatieren.
		 * 
		 * *************************************************************
		 */

		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		showFhrzFlexTable.setText(0, 0, "Kennzeichen");
		showFhrzFlexTable.setText(1, 0, "Aktueller Kilometerstand");
		showFhrzFlexTable.setText(2, 0, "Modellbeschreibung");
		showFhrzFlexTable.setText(3, 0, "Farbe");
		showFhrzFlexTable.setText(4, 0, "Anzeigen");
		showFhrzFlexTable.setText(5, 0, "Löschen");
		
		
		
		
		
		
	}
	
	
	public void befuelleFhzTabelle(Vector<Fahrzeug> vec){
		
		for (int i = 0; i < vec.size(); i++){
			
			Fahrzeug fahrzeugProfil = vec.get(i);
			
			showFhrzFlexTable.setText(0, (i+1), vec.get(i).getKennzeichen());
			showFhrzFlexTable.setText(1, (i+1), Integer.toString(vec.get(i).getKm()));
			showFhrzFlexTable.setText(2, (i+1), vec.get(i).getModellBeschreibung());
			showFhrzFlexTable.setText(3, (i+1), vec.get(i).getFarbe());
			
			
			//anzeigenButton erzeugen, zur Tabelle hinzufügen und Clickhandler zuweisen
			anzeigenButton = new Button("Fahrzeug anzeigen");
			showFhrzFlexTable.setWidget(4, (i+1), anzeigenButton);
			anzeigenButton.addClickHandler(new AnzeigenClickhandler()); 
				

		
			
			//LoeschenButton erzeugen, zur Tabelle hinzufügen und Clickhandler zuweisen
			loeschenButton = new Button("Fahrzeug löschen");
			showFhrzFlexTable.setWidget(5, (i+1), loeschenButton);
			loeschenButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					loescheFahrzeug(fahrzeugProfil);
					
				}
			});
			
		}
	}
	
	
	
	private class AnzeigenClickhandler implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			
			ContFahrzeug contFahrzeug = new ContFahrzeug(fahrzeugId);
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(contFahrzeug);	
		}	
	}

}
