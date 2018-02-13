package com.janhabersetzer.fahrtenbuch.client.gui;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class ContFahrzeug extends Composite{
		
		
	// Attribute fuer Aufrufe
		
	private FahrtenbuchClientImpl serviceImpl;
		
	private MainView mainView;
		
	private int fahrzeugid;
		
	private Fahrzeug fahrzeugProfil;
		
		
	//Panels
	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();
		
	//Widgets
	FlexTable showFhrzFlexTable = new FlexTable();
	private Label ueberschriftLabel = new Label("Ein einzelnes Fahrzeug: ");
	Button loeschenBtn;
	
	//Widgets die durch oeffneFahrten() hinzukommen
	FlexTable showFahrtenFlexTable = new FlexTable();
	private Label ueberschriftLabel2 = new Label("Alle Fahrten dieses Fahrzeugs: ");
	Button fahrtHinzuBtn;
	Button fahrtBearbeitenBtn;
	Button fahrtLoeschenBtn;
	private ArrayList<Integer> fahrtenIDs;		
		
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
		 * CSS anwenden und beide Tabellen zu formatieren.
		 */
		ueberschriftLabel.addStyleName("fahrtenbuch-label");
		ueberschriftLabel2.addStyleName("fahrtenbuch-label");
		showFhrzFlexTable.addStyleName("FlexTable");
		showFahrtenFlexTable.addStyleName("FlexTable");
		showFhrzFlexTable.setCellPadding(6);
		showFahrtenFlexTable.setCellPadding(6);
		showFhrzFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");
		showFahrtenFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");

			/**
			 * Erste Spalte der Fhrz.-Tabelle festlegen.
			 */
			showFhrzFlexTable.setText(0, 0, "Fahrtdatum");
			showFhrzFlexTable.setText(0, 1, "Kilometerstand");
			showFhrzFlexTable.setText(0, 2, "Modellbeschreibung");
			showFhrzFlexTable.setText(0, 3, "Farbe");
			showFhrzFlexTable.setText(0, 4, "Löschen");

			
			serviceImpl.getFahrzeug(id);
			
			
			//Füge das vPanel zusammen.
			vPanel.add(ueberschriftLabel);
			vPanel.add(showFhrzFlexTable);
			
			/**
			 * Erste Spalte der Fahrten-Tabelle festlegen.
			 */
			showFahrtenFlexTable.setText(0, 0, "Fahrt-Nr.");
			showFahrtenFlexTable.setText(0, 1, "Fahrtdatum");
			showFahrtenFlexTable.setText(0, 2, "Zielbeschreibung");
			showFahrtenFlexTable.setText(0, 3, "Km (Start)");
			showFahrtenFlexTable.setText(0, 4, "Km (Endstand)");
			showFahrtenFlexTable.setText(0, 5, "Km Privat");
			showFahrtenFlexTable.setText(0, 6, "Km Arbeitsweg");
			showFahrtenFlexTable.setText(0, 7, "Km Betriebsfahrt");
			showFahrtenFlexTable.setText(0, 8, "Kommentar");
			showFahrtenFlexTable.setText(0, 9, "Bearbeitungsdatum");
			showFahrtenFlexTable.setText(0, 10, "Bearbeiten");
			
			
			serviceImpl.getAlleFahrtenVonFahrzeug(id);
			
			//Füge das hPanel zusammen.
			
			hPanel.add(ueberschriftLabel2);
			
			//Füge das vPanel zusammen.
			vPanel.add(hPanel);
			vPanel.add(showFahrtenFlexTable);

		}
		
		
		public void schreibeFahrzeug(Fahrzeug v){
			
			//Füge Fahrzeug + Button in die Tabelle ein
			
			this.fahrzeugProfil = v;
			
			//Erstelle den Button für das Hinzufuegen der Fahrten und fuege ihn den hPanel hinzu
			fahrtHinzuBtn = new Button(" + Fahrt hinzufügen");
			fahrtHinzuBtn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					mainView.openCreateFahrt(fahrzeugProfil);
					
				}
			});
			hPanel.add(fahrtHinzuBtn);
			
			showFhrzFlexTable.setText(1, 0, v.getKennzeichen());
			showFhrzFlexTable.setText(1, 1, Integer.toString(v.getKm()));
			showFhrzFlexTable.setText(1 , 2, v.getModellBeschreibung());
			showFhrzFlexTable.setText(1 , 3, v.getFarbe());	
			
			//bearbeitenButton erzeugen, zur Tabelle hinzufügen und Clickhandler zuweisen
			loeschenBtn = new Button("Löschen");
			loeschenBtn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if (Window.confirm("Wollen Sie das Fahrzeug wirklich löschen? ")){
					int deleteId = fahrzeugProfil.getId();
					serviceImpl.deleteFahrzeug(deleteId);
					mainView.openAlleFhrzCont();
					}
					
				}
			});
			showFhrzFlexTable.setWidget(1, 4, loeschenBtn);
		}
		
		
	
	public void oeffneFahrten(Vector<Fahrt> vec){
		
		fahrtenIDs = new ArrayList<Integer>();
		
		//Gibt das gewünschte Datumsformat für die Transformation von Daten zu String vor
		
		DateTimeFormat formatterDTF = DateTimeFormat.getFormat("yyyy.MM.dd");
		
		for(int i = 0; i < vec.size(); i++){
			
			final int id = vec.get(i).getId();
			
			fahrtenIDs.add(id);
			
			
			//Füge Fahrten + Buttons für jede Fahrt in die Tabelle ein
			
			showFahrtenFlexTable.setText((i+1), 0, Integer.toString(vec.get(i).getId()));
			//Datum formatieren
			Date fahrtDatumUntil = vec.get(i).getFahrtDatum();
			String datumString = formatterDTF.format(fahrtDatumUntil);
			
			showFahrtenFlexTable.setText((i+1), 1, datumString);
			
			showFahrtenFlexTable.setText((i+1), 2, vec.get(i).getZielBeschreibung());
			showFahrtenFlexTable.setText((i+1), 3, Integer.toString(vec.get(i).getKmStart()));
			showFahrtenFlexTable.setText((i+1), 4, Integer.toString(vec.get(i).getKmEnd()));
			showFahrtenFlexTable.setText((i+1), 5, Integer.toString(vec.get(i).getPrivatKm()));
			showFahrtenFlexTable.setText((i+1), 6, Integer.toString(vec.get(i).getArbeitswegKm()));
			showFahrtenFlexTable.setText((i+1), 7, Integer.toString(vec.get(i).getBetriebsfahrtKm()));
			showFahrtenFlexTable.setText((i+1), 8, vec.get(i).getKommentar());
			//Datum formatieren
			Date bearbeitungsDatumUntil = vec.get(i).getBearbeitungsdatum();
			String BDatumString = formatterDTF.format(bearbeitungsDatumUntil);
			
			showFahrtenFlexTable.setText((i+1), 9, BDatumString);
			
			//Fahrzeug Kennzeichen
			
			//Fahrer E-Mail
			
			//Button für das bearbeiten einer Fahrt
			fahrtBearbeitenBtn = new Button("Bearbeiten");
			fahrtBearbeitenBtn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					mainView.openEditFahrt(id, fahrzeugProfil);	
				}
			});
			showFahrtenFlexTable.setWidget((i+1), 10, fahrtBearbeitenBtn);
			
			
			
		}
	}	
	
		
		
}
	


	
	
