package com.janhabersetzer.fahrtenbuch.client.gui2;

import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchGWT;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;





public class CreateFahrt extends VerticalPanel {
	
	private Fahrer fahrerprofil= FahrtenbuchGWT.getfhr();
	
	/**
	 * Attribut, das dazu dient, die hier erstellte Fahrt einem Fahrzeug zuzuordnen
	 */
	
	private Fahrzeug fahrzeugProfil;
	
	/**
	 * Panels erzeugen.
	 */
	private VerticalPanel verPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	/**
	 * Widgets erzeugen.
	 */
	private Label ueberschriftLabel = new Label("Fahrt anlegen:");
	private FlexTable createFahrtFlexTable = new FlexTable();
	private DatePicker fahrtDatumPicker = new DatePicker();
	private TextBox zielbeschreibungTextBox = new TextBox();
	private TextBox kmStartBox = new TextBox();
	private TextBox kmEndBox = new TextBox();
	private TextBox kmPrivatBox = new TextBox();
	private TextBox kmArbeitsWBox = new TextBox();
	private TextBox kmBetriebsFBox  = new TextBox();
	private TextBox kommentarBox = new TextBox();
	private Button createFahrtButton = new Button("Suchprofil anlegen");
	private Button abbrechenButton = new Button("Abbrechen");
	private Label reqLabel1 = new Label("* Hinweis: die Fahrt darf max. 7 Tage in der Vergangenheit liegen (Pflichtfeld)");
	private Label reqLabel2 = new Label("* Pflichtfeld");
	private Label reqLabel3 = new Label("* Pflichtfeld");
	private Label reqLabel4 = new Label("* Pflichtfeld");
	private Label warnungLabel = new Label();
	
	
	/**
	 * Konstruktor
	 */
	
	public CreateFahrt(Fahrzeug fahrzeugProfil){
		this.fahrzeugProfil = fahrzeugProfil;
		build();
	}
	
	
	public void build(){
		this.add(verPanel);
		
		/**
		 * CSS anwenden und die Tabelle formatieren.
		 */
		ueberschriftLabel.addStyleName("fahrtenbuch-label"); 
		reqLabel1.setStyleName("red_label");
		reqLabel2.setStyleName("red_label");
		reqLabel3.setStyleName("red_label");
		reqLabel4.setStyleName("red_label");
		warnungLabel.setStyleName("red_label");
		createFahrtFlexTable.addStyleName("FlexTable");
		createFahrtFlexTable.setCellPadding(6);
		createFahrtFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");
		
		//Erste Spalte der Tabelle anlegen
		createFahrtFlexTable.setText(0, 0, "Fahrtdatum");
		createFahrtFlexTable.setText(1, 0, "Zielbeschreibung");
		createFahrtFlexTable.setText(2, 0, "Km (Start)");
		createFahrtFlexTable.setText(3, 0, "Km (Endstand)");
		createFahrtFlexTable.setText(4, 0, "Km Privat");
		createFahrtFlexTable.setText(5, 0, "Km Arbeitsweg");
		createFahrtFlexTable.setText(6, 0, "Km Betriebsfahrt");
		createFahrtFlexTable.setText(7, 0, "Kommentar");
		
		//Widgets in Tabelle einfügen
		createFahrtFlexTable.setWidget(0, 2, fahrtDatumPicker);
		createFahrtFlexTable.setWidget(0, 3, reqLabel1);
		
		createFahrtFlexTable.setWidget(1, 2, zielbeschreibungTextBox);
		createFahrtFlexTable.setWidget(1, 3, reqLabel2);
		
		createFahrtFlexTable.setWidget(2, 2, kmStartBox);
		createFahrtFlexTable.setWidget(2, 3, reqLabel2);
		
		createFahrtFlexTable.setWidget(3, 2, kmEndBox);
		createFahrtFlexTable.setWidget(3, 3, reqLabel2);
		
		createFahrtFlexTable.setWidget(4, 2, kmPrivatBox);
		createFahrtFlexTable.setWidget(4, 3, reqLabel2);
		
		createFahrtFlexTable.setWidget(5, 2, kmArbeitsWBox);
		createFahrtFlexTable.setWidget(5, 3, reqLabel2);
		
		createFahrtFlexTable.setWidget(6, 2, kmBetriebsFBox);
		createFahrtFlexTable.setWidget(6, 3, reqLabel2);
		
		createFahrtFlexTable.setWidget(7, 2, kommentarBox);
		createFahrtFlexTable.setWidget(7, 3, reqLabel2);
		
		
		
		/**
		 * Clickhandler für den createFahrtButton
		 * Dieser ruft die Methode ueberpruefeFahrt() auf, welche wiederrum legeFahrtAn() aufruft.
		 * Siehe diese beiden Funktionen
		 */
		
		createFahrtButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//ueberpruefeFahrt();
				
			}
		});
		
		/**
		 * Clickhandler für den abbrechenButton
		 * Dieser ruft die Sicht mit dem Fahrzeug auf, über die der Nutzer auf diese CreateFahrt-Sicht gelangte.
		 * Das Fahrzeugprofil ist in fahrtProfil hinterlegt
		 */
		
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
					ShowFahrzeug showFahrzeug = new ShowFahrzeug(fahrzeugProfil);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showFahrzeug);		
			}
		});
		//Ende Build
	}
	
	
	
	
	
	
	
	
//Ende	Klasse
}
