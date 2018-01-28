package com.janhabersetzer.fahrtenbuch.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.ClientSideSettings;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class ShowAlleFahrzeuge extends VerticalPanel {
	
	
	/**
	 * Panels erzeugen.
	 */
	private VerticalPanel fahrzeugePanel = new VerticalPanel();
	private VerticalPanel infoPanel = new VerticalPanel();
	private HorizontalPanel horPanel = new HorizontalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();

	/**
	 * Widgets erzeugen.
	 */
	private Label ueberschriftLabel = new Label("Alle Fahrzeuge:");
	private FlexTable showFhrzFlexTable = new FlexTable();
	private Label infoLabel = new Label();
	private Button loeschenButton;
	private Button anzeigenButton;
	
	
	/**
	 * Konstruktor 
	 * @param fahrerProfil Fahrer Objekt des eingeloggten Fahrers
	 * @param fahrzeugProfil Fahrzueg Objekt des ausgewählten Fahrzeugs
	 */
	
	public ShowAlleFahrzeuge(){
		this.build();
	}
	
	
	
	public void build(){
		this.add(horPanel);
		horPanel.add(fahrzeugePanel);
		horPanel.add(infoPanel);
		
		
		/**
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
		showFhrzFlexTable.setText(1, 0, "Aktueller Kilometerstand");
		showFhrzFlexTable.setText(2, 0, "Modellbeschreibung");
		showFhrzFlexTable.setText(3, 0, "Farbe");
		showFhrzFlexTable.setText(4, 0, "Anzeigen");
		showFhrzFlexTable.setText(5, 0, "Löschen");
		
		

	}
	
	public void befuelleTabelle(){
		ClientSideSettings.getFahrtenbuchVerwaltung().getAlleFahrzeug(new AsyncCallback<Vector<Fahrzeug>>() {
			
			@Override
			public void onSuccess(Vector<Fahrzeug> result) {
				for (int i = 0; i < result.size(); i++){
					
					int fahrzeugId = result.get(i).getId();
					
					showFhrzFlexTable.setText(0, (i+1), result.get(i).getKennzeichen());
					showFhrzFlexTable.setText(1, (i+1), Integer.toString(result.get(i).getKm()));
					showFhrzFlexTable.setText(2, (i+1), result.get(i).getModellBeschreibung());
					showFhrzFlexTable.setText(3, (i+1), result.get(i).getFarbe());
					anzeigenButton = new Button("Fahrzeug anzeigen");
					showFhrzFlexTable.setWidget(4, (i+1), anzeigenButton);
					
					loeschenButton = new Button("Fahrzeug löschen");
					showFhrzFlexTable.setWidget(5, (i+1), loeschenButton);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private class AnzeigenClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			
			
		}
	}
	
	
	public void loescheFahrzeug(){
		if (Window.confirm("Möchten Sie dieses Fahrzeug wirklich löschen?")){
			
			ClientSideSettings.getFahrtenbuchVerwaltung().deleteFahrzeug(, new AsyncCallback<Void>() {
				@Override
				public void onSuccess() {
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});
			
		}
	}

}
