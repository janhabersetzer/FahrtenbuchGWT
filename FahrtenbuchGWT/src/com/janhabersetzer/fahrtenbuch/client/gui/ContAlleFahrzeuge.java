package com.janhabersetzer.fahrtenbuch.client.gui;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class ContAlleFahrzeuge extends Composite{
	
	// Attribute fuer Aufrufe
	private MainView mainView;
	private FahrtenbuchClientImpl serviceImpl;
	
	//Panels
	
	private VerticalPanel vPanel= new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	// Widgets
	 
	private Label ueberschriftLabel = new Label("Alle Fahrzeuge:");
	String text ="Hier hat sich was geändert"; 
	private FlexTable showFhrzFlexTable = new FlexTable();
	private Button loeschenBtn;
	private Button anzeigenBtn;
	private Button fahrzeugHinzuBtn;
	
	/*
	 * Um den Index Fahrzeugen in der FlexTable ermitteln zu können brauchen wir diese ArrayList
	 *  --> siehe StockWatcher Bsp.
	 */
	private ArrayList<Integer> fahrzeugIDs;
	
	
	/**
	 * Constructor
	 * @param mainView Referenz zum aufrufenden MainView-Objekt
	 * @param serviceImplReferenz zum FahrtenbuchClientImpl-Objekt, das alle Serveraufrufe buendelt
	 */
	
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
		showFhrzFlexTable.getRowFormatter().addStyleName(0, "TableHeader");
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
		
		

		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		showFhrzFlexTable.setText(0, 0, "Kennzeichen");
		showFhrzFlexTable.setText(0, 1, "Kilometerstand");
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
		
		//horizontalPanel zusammenfuegen
		hPanel.add(ueberschriftLabel);
		
		//Erstelle den Button für das Hinzufuegen der Fahrten und fuege ihn den hPanel hinzu
		fahrzeugHinzuBtn = new Button(" + Fahrzeug hinzufügen");
		fahrzeugHinzuBtn.getElement().getStyle().setProperty("margin", "0.5em");
		fahrzeugHinzuBtn.addStyleName("hinzufuegen-button");
		fahrzeugHinzuBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				mainView.openCreateFahrzeug();	
			}
		});
		hPanel.add(fahrzeugHinzuBtn);
		
		//Füge das vPanel zusammen.
		vPanel.add(hPanel);
		vPanel.add(showFhrzFlexTable);
	}
	
	
	public void befuelleFhzTabelle(Vector<Fahrzeug> vec){
		
		fahrzeugIDs = new ArrayList<Integer>();

		for (int i = 0; i < vec.size(); i++){
			
			final int id = vec.get(i).getId();
			
			fahrzeugIDs.add(id);
			
			//Füge Fahrzeug + Buttons für jedes Fhrz in die Tabelle ein
			
			showFhrzFlexTable.setText((i+1), 0, vec.get(i).getKennzeichen());
			showFhrzFlexTable.setText((i+1), 1,  Integer.toString(vec.get(i).getKm()));
			showFhrzFlexTable.setText((i+1), 2,  vec.get(i).getModellBeschreibung());
			showFhrzFlexTable.setText((i+1), 3, vec.get(i).getFarbe());	
			
			
			//anzeigenButton erzeugen, zur Tabelle hinzufügen und Clickhandler zuweisen
			anzeigenBtn = new Button("Fahrzeug anzeigen");
			anzeigenBtn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					mainView.openFahrzeugCont(id);
					
				}
			}); 
			showFhrzFlexTable.setWidget( (i+1), 4, anzeigenBtn);
				
			
			//LoeschenButton erzeugen, zur Tabelle hinzufügen und Clickhandler zuweisen
			loeschenBtn = new Button("Fahrzeug löschen");
			loeschenBtn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if (Window.confirm("Wollen Sie das Fahrzeug wirklich löschen? ")){
					int removeIndex = fahrzeugIDs.indexOf(id);
					fahrzeugIDs.remove(removeIndex);
					serviceImpl.deleteFahrzeug(id);
					}
					
				}
			});
			showFhrzFlexTable.setWidget((i+1), 5, loeschenBtn);		
		}	
	}
	
	


}
