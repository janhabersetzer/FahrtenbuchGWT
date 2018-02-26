package com.janhabersetzer.fahrtenbuch.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class ContCreateFahrt extends Composite{
	
	// Attribute fuer Aufrufe
	private MainView mainView;
	
	private FahrtenbuchClientImpl serviceImpl;
	
	private int fahrtId;
	
	private int fahrzeugId;
	
	private int fahrerId;
	
	private Fahrt fahrtProfil;
	
	private Vector<Fahrer> alleFahrer;
	
	private Fahrzeug fahrzeugProfil;

	
	
	
	// Datumsformat für die Transformationen
	private DateTimeFormat formatterDTF = DateTimeFormat.getFormat("yyyy.MM.dd");
	
	//Panels
	private VerticalPanel vPanel= new VerticalPanel();
	private FlexTable showFahrtFlexTable = new FlexTable();
	
	
	private Label ueberschriftLabel = new Label("Neue Fahrt erstellen: ");
	private Label warnungLabel = new Label();
	private Label reqLabel1= new Label("* Pflichtfeld / Format yyyy.MM.dd");
	private Label reqLabel2= new Label("* Pflichtfeld");
	private Label reqLabel3= new Label("* Pflichtfeld");
	private Label reqLabel4= new Label("* Pflichtfeld");
	private Label reqLabel5= new Label("* Pflichtfeld");
	private Label reqLabel6= new Label("* Pflichtfeld");
	private Label reqLabel7= new Label("* Pflichtfeld");
	private Label infoLabel1 = new Label("* Automatisch gesetzt");
	private Label infoLabel2 = new Label("* Automatisch gesetzt");

	
	
	//Widgets die durch oeffneBearbeiten(Fahrt fahrt) dazukommen
	TextBox fahrtDatumTBox = new TextBox();
	TextBox zielTBox = new TextBox();
	TextBox kmEndTBox = new TextBox();
	TextBox privatTBox = new TextBox();
	TextBox arbeitsTBox = new TextBox();
	TextBox betriebsTBox = new TextBox();
	TextBox kommentarTBox = new TextBox();
	ListBox kennzeichenLBox = new ListBox();;
	ListBox eMailLBox = new ListBox();
	Button speichernBtn;
	Button abbrechenBtn;
	
	
	
	public ContCreateFahrt(Fahrzeug fahrzeugProfil, MainView mainView, FahrtenbuchClientImpl serviceImpl){
		initWidget(this.vPanel);
		
		this.fahrzeugProfil = fahrzeugProfil;
		this.fahrzeugId = fahrzeugProfil.getId();
		this.mainView = mainView;
		this.serviceImpl = serviceImpl;
		
		//Da der aktuelle kilometerStand des Fahrzuegs der StartKm stand der neuen Fahrt sein muss, wird dieser entsprechen gesetzt
		this.fahrtProfil = new Fahrt();
		this.fahrtProfil.setKmStart(fahrzeugProfil.getKm());
		
		//Auch der Femdschlüssel als FahzeugId dieser Fahrt kann nicht geändert werden und wird gesetzt
		this.fahrtProfil.setSourceFahrzeugId(this.fahrzeugId);
		
		
		/*
		 * CSS anwenden und beide Tabellen zu formatieren.
		 */
		ueberschriftLabel.addStyleName("fahrtenbuch-label");
		warnungLabel.setStyleName("red_label");
		infoLabel1.setStyleName("grey_label");
		infoLabel2.setStyleName("grey_label");
		reqLabel1.setStyleName("grey_label");
		reqLabel2.setStyleName("grey_label");
		reqLabel3.setStyleName("grey_label");
		reqLabel4.setStyleName("grey_label");
		reqLabel5.setStyleName("grey_label");
		reqLabel5.setStyleName("grey_label");
		reqLabel6.setStyleName("grey_label");
		reqLabel7.setStyleName("grey_label");
		showFahrtFlexTable.addStyleName("FlexTable");
		showFahrtFlexTable.setCellPadding(6);
		showFahrtFlexTable.getRowFormatter().addStyleName(0, "TableHeader");
		
		
		/**
		 * Erste Spalte der Fahrten-Tabelle festlegen.
		 */
		showFahrtFlexTable.setText(0, 0, "Fahrt-Nr.");
		showFahrtFlexTable.setText(0, 1, "Fahrtdatum");
		showFahrtFlexTable.setText(0, 2, "Zielbeschreibung");
		showFahrtFlexTable.setText(0, 3, "Km (Start)");
		showFahrtFlexTable.setText(0, 4, "Km (Endstand)");
		showFahrtFlexTable.setText(0, 5, "Km Privat");
		showFahrtFlexTable.setText(0, 6, "Km Arbeitsweg");
		showFahrtFlexTable.setText(0, 7, "Km Betriebsfahrt");
		showFahrtFlexTable.setText(0, 8, "Kommentar");
		showFahrtFlexTable.setText(0, 9, "Bearbeitungsdatum");
		showFahrtFlexTable.setText(0, 10, "Fahrzeug (Kennzeichen)");
		showFahrtFlexTable.setText(0, 11, "EMail(Fahrer)");
		showFahrtFlexTable.setText(0, 12, "Speichern");
		showFahrtFlexTable.setText(0, 13, "Abbrechen");
		
		//Hinweis Label
		showFahrtFlexTable.setWidget(2, 1, reqLabel1);
		showFahrtFlexTable.setWidget(2, 2, reqLabel2);
		showFahrtFlexTable.setWidget(2, 3, infoLabel1);
		showFahrtFlexTable.setWidget(2, 4, reqLabel3);
		showFahrtFlexTable.setWidget(2, 5, reqLabel4);
		showFahrtFlexTable.setWidget(2, 6, reqLabel5);
		showFahrtFlexTable.setWidget(2, 7, reqLabel6);
		showFahrtFlexTable.setWidget(2, 8, reqLabel7);
		showFahrtFlexTable.setWidget(2, 9, infoLabel2);
		
		
		
		serviceImpl.getAlleFahrer();


		vPanel.add(ueberschriftLabel);
		vPanel.add(showFahrtFlexTable);
		
		oeffneErstellen();
		
		
	}
	
	public void oeffneErstellen(){
		
	
		//Texte für Datenfelder setzen und in Tabelle einfügen
		
		
		//Fahrtdatum
		showFahrtFlexTable.setWidget(1, 1, fahrtDatumTBox);
		
		//Zielbeschreibung
		showFahrtFlexTable.setWidget(1, 2, zielTBox);
		//KmStart (gesetzt)
		showFahrtFlexTable.setText(1, 3,Integer.toString(fahrzeugProfil.getKm()));
		//KmEnd
		
		showFahrtFlexTable.setWidget(1, 4, kmEndTBox);
		//KmPrivat
		showFahrtFlexTable.setWidget(1, 5, privatTBox);
		//Km Arbeitsweg
		showFahrtFlexTable.setWidget(1, 6, arbeitsTBox);
		//Km Betriebsfahrt
		showFahrtFlexTable.setWidget(1, 7, betriebsTBox);
		//Kommentar
		showFahrtFlexTable.setWidget(1, 8, kommentarTBox);
		//Bearbeitungsdatum	wird automatisch gesetzt
		
		//Kennzeichen
		showFahrtFlexTable.setText(1, 10, fahrzeugProfil.getKennzeichen());
		
		//eMail wird in eigener Methode gesetzt wenn "Callback wieder da ist" siehe schreibeAlleFahrer(Vector<Fahrer>)
		

		
		//SpeichernButton
		speichernBtn = new Button("Speichern");
		speichernBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			
				//Eingabe der Texfelder prüfen
				if(pruefeEingabe()==true){
				
					try{
						schreibeNeueFahrt();
						}catch(RuntimeException e){
						
						}
					//Fahrzeug an server zum speichern uebergeben
					serviceImpl.saveFahrt(fahrtProfil);
					
				}
			}
		});
		showFahrtFlexTable.setWidget(1,	12, speichernBtn);
		
		
		abbrechenBtn = new Button("Abbrechen");
		abbrechenBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(Window.confirm("Wollen Sie das Erstellen abbrechen?")){
				ContCreateFahrt.this.mainView.openFahrzeugCont(fahrzeugId);
				}	
			}
		});
		showFahrtFlexTable.setWidget(1, 13, abbrechenBtn);
	}
	
	
	public Boolean pruefeEingabe(){
		//Prüfen, ob die Felder leer sind
		if (fahrtDatumTBox.getText().length() == 0) {
			warnungLabel.setText("Dieses Feld darf nicht leer sein");
			showFahrtFlexTable.setWidget(3, 1, warnungLabel);
		} 
		else if (zielTBox.getText().length() == 0) {
			warnungLabel.setText("Dieses Feld darf nicht leer sein");
			showFahrtFlexTable.setWidget(3, 2, warnungLabel);
		}
		else if (kmEndTBox.getText().length() == 0){
			warnungLabel.setText("Dieses Feld darf nicht leer sein");
			showFahrtFlexTable.setWidget(3, 4, warnungLabel);
		}
		else if(privatTBox.getText().length() == 0){
			privatTBox.setText("0");	
		}
		else if(arbeitsTBox.getText().length() == 0){
				arbeitsTBox.setText("0");
		}
		else if(betriebsTBox.getText().length() == 0){
			betriebsTBox.setText("0");
		}
		else if(betriebsTBox.getText().length() == 0){
			betriebsTBox.setText("0");
		}
		//Prüfe ob Kilometerendstand <= dem Kilometeranfangstand ist
		else if(Integer.valueOf(kmEndTBox.getText()) <= fahrtProfil.getKmStart()){
			warnungLabel.setText("Der Km(Endstand) dieser Fahrt muss größer als die Km (Start) sein");
			showFahrtFlexTable.setWidget(3, 4, warnungLabel);	
		}
		//Prüfe ob die Summe der Kathegorien der gesamt Strecke der Fahrt entspricht
		else if((Integer.valueOf(privatTBox.getText())+Integer.valueOf(betriebsTBox.getText())+Integer.valueOf(arbeitsTBox.getText())
			!= (Integer.valueOf(kmEndTBox.getText())-fahrtProfil.getKmStart()))){
				warnungLabel.setText("Die Summe von Km Privat, Km Arbeitsweg und Km Betriebsfahrt muss der Gesamtstrecke entsprechen");
				showFahrtFlexTable.setWidget(3, 4, warnungLabel);
		}
		else{
			return true;
		}
		return false;
		
	}
	

	
	public void schreibeAlleFahrer(Vector<Fahrer> vec){
		this.alleFahrer = vec;
		
		for(int i =0; i< alleFahrer.size(); i++){
			eMailLBox.addItem(alleFahrer.elementAt(i).getEMail());	
		}
		eMailLBox.setVisibleItemCount(1);
		showFahrtFlexTable.setWidget(1, 11, eMailLBox);
		
	}
	
	public void schreibeNeueFahrt(){
		
		//neueFahrt mit den Texfeldern beschreiben
		String fdateString = fahrtDatumTBox.getText();
		Date fahrtDate = formatterDTF.parse(fdateString);
		fahrtProfil.setFahrtDatum(fahrtDate);
	
		fahrtProfil.setZielBeschreibung(zielTBox.getText());
//		neueFahrt.setKmStart(fahrzeugProfil.getKm());
		fahrtProfil.setKmEnd(Integer.valueOf(kmEndTBox.getText()));
		fahrtProfil.setPrivatKm(Integer.valueOf(privatTBox.getText()));
		fahrtProfil.setArbeitswegKm(Integer.valueOf(arbeitsTBox.getText()));
		fahrtProfil.setBetriebsfahrtKm(Integer.valueOf(betriebsTBox.getText()));
		fahrtProfil.setKommentar(kommentarTBox.getText());
		//Fahrer muss  übernommen werden
		int index = eMailLBox.getSelectedIndex();
		fahrtProfil.setSourceFahrerId(alleFahrer.elementAt(index).getId());
		//Fahrzeug muss nicht übernommen werden, da nicht veränderbar und bereits im Konstruktor gesetzt
		
	}
	




	//***************GEtter/Setter***************************************
	public int getFahrtId() {
		return fahrtId;
	}

	public void setFahrtId(int fahrtId) {
		this.fahrtId = fahrtId;
	}

	public int getFahrzeugId() {
		return fahrzeugId;
	}

	public void setFahrzeugId(int fahrzeugId) {
		this.fahrzeugId = fahrzeugId;
	}

	public int getFahrerId() {
		return fahrerId;
	}

	public void setFahrerId(int fahrerId) {
		this.fahrerId = fahrerId;
	}
	
	public Vector<Fahrer> getalleFahrer() {
		return alleFahrer;
	}

	public void setFahrerProfil(Vector<Fahrer> alleFahrer) {
		this.alleFahrer = alleFahrer;
	}

	public Fahrzeug getFahrzeugProfil() {
		return fahrzeugProfil;
	}

	public void setFahrzeugProfil(Fahrzeug fahrzeugProfil) {
		this.fahrzeugProfil = fahrzeugProfil;
	}
	
	public Fahrt getFahrtProfil() {
		return fahrtProfil;
	}

	public void setFahrtProfil(Fahrt fahrtProfil) {
		this.fahrtProfil = fahrtProfil;
	}
}
