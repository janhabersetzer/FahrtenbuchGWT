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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;

public class ContAlleFahrer extends Composite{
	
	
	// Attribute fuer Aufrufe
	private MainView mainView;
	
	private FahrtenbuchClientImpl serviceImpl;
	
	private Vector<Fahrer> alleFahrer;
	
	private Fahrer fahrerProfil;
	
	//Panels
	private VerticalPanel vPanel= new VerticalPanel();
	
	// Widgets
	 
	private Label ueberschriftLabel = new Label("Alle Fahrer:");
	String text ="Hier hat sich was geändert"; 
	private FlexTable showFhrFlexTable = new FlexTable();
	private Button loeschenBtn;
	private Button bearbeitenBtn;
	
	
	public ContAlleFahrer(MainView mainView, FahrtenbuchClientImpl serviceImpl){
		initWidget(this.vPanel);
		
		
		this.mainView = mainView;
		this.serviceImpl = serviceImpl;
		
		/*
		 * CSS anwenden und die Tabelle formatieren.
		 */
		ueberschriftLabel.addStyleName("fahrtenbuch-label");
		showFhrFlexTable.addStyleName("FlexTable");
		showFhrFlexTable.setCellPadding(6);
		showFhrFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");
		
		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		showFhrFlexTable.setText(0, 0, "Vorname");
		showFhrFlexTable.setText(0, 1, "Nachname");
		showFhrFlexTable.setText(0, 2, "Email-Adresse");
		showFhrFlexTable.setText(0, 3, "Anzeigen");
		showFhrFlexTable.setText(0, 4, "Löschen");
		
		
		serviceImpl.getAlleFahrer();
		
		//Füge das vPanel zusammen.
		vPanel.add(ueberschriftLabel);
		vPanel.add(showFhrFlexTable);
	}
	
	
	public void befuelleFhrTabelle(Vector<Fahrer> vec){
		this.alleFahrer = vec;
		
		for(int i = 0; i< alleFahrer.size(); i++){
			
			final int id = vec.get(i).getId();
				
			//Füge Fahrer + Buttons für jeden Fhr in die Tabelle ein
			
			showFhrFlexTable.setText((i+1), 0, alleFahrer.get(i).getVorname());
			showFhrFlexTable.setText((i+1), 1, alleFahrer.get(i).getNachname());
			showFhrFlexTable.setText((i+1), 2, alleFahrer.get(i).getEMail());
			
			
			//anzeigenButton erzeugen, zur Tabelle hinzufügen und Clickhandler zuweisen
			bearbeitenBtn = new Button("Fahrer bearbeiten");
			bearbeitenBtn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					mainView.openEditFahrer(id);
					
				}
			}); 
			showFhrFlexTable.setWidget( (i+1), 3, bearbeitenBtn);
			
			
			//LoeschenButton erzeugen, zur Tabelle hinzufügen und Clickhandler zuweisen
			loeschenBtn = new Button("Fahrer löschen");
			loeschenBtn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if (Window.confirm("Wollen Sie den Fahrer wirklich löschen? ")){
					serviceImpl.deleteFahrer(id);
					}	
				}
			});
			showFhrFlexTable.setWidget((i+1), 4, loeschenBtn);	
				
		}
		
		
	}

}
