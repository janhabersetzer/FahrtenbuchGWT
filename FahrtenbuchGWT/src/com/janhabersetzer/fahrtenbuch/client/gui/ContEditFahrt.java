package com.janhabersetzer.fahrtenbuch.client.gui;



import java.util.Date;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrt;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class ContEditFahrt extends Composite{
	
	// Attribute fuer Aufrufe
	private MainView mainView;
	
	private FahrtenbuchClientImpl serviceImpl;
	
	private int fahrtId;
	
	private int fahrzeugId;
	
	private int fahrerId;
	
	private Fahrt fahrtProfil;
	
	private Fahrer fahrerProfil;
	
	private Fahrzeug fahrzeugProfil;
	
	
	
	// Datumsformat für die Transformationen
	private DateTimeFormat formatterDTF = DateTimeFormat.getFormat("yyyy.MM.dd");
	
	//Panels
	private VerticalPanel vPanel= new VerticalPanel();
	private FlexTable showFahrtFlexTable = new FlexTable();
	
	
	private Label ueberschriftLabel = new Label("Fahrt bearbeiten: ");
	private Label warnungLabel = new Label();
	
	
	
	
	//Widgets die durch oeffneBearbeiten(Fahrt fahrt) dazukommen
	TextBox fahrtDatumTBox = new TextBox();
	TextBox zielTBox = new TextBox();
	TextBox kmEndTBox = new TextBox();
	TextBox privatTBox = new TextBox();
	TextBox arbeitsTBox = new TextBox();
	TextBox betriebsTBox = new TextBox();
	TextBox kommentarTBox = new TextBox();
	Button speichernBtn;
	Button abbrechenBtn;
	
	
	
	public ContEditFahrt(int fahrtId, Fahrzeug fahrzeugProfil, MainView mainView, FahrtenbuchClientImpl serviceImpl){
		initWidget(this.vPanel);
		
		this.fahrtId = fahrtId;
		this.fahrzeugProfil = fahrzeugProfil;
		this.mainView = mainView;
		this.serviceImpl = serviceImpl;
		
		this.serviceImpl.getFahrt(fahrtId);
		
		/*
		 * CSS anwenden und beide Tabellen zu formatieren.
		 */
		ueberschriftLabel.addStyleName("fahrtenbuch-label");
		warnungLabel.setStyleName("red_label");
		showFahrtFlexTable.addStyleName("FlexTable");
		showFahrtFlexTable.setCellPadding(6);
		showFahrtFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");
		
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
		
		serviceImpl.getFahrt(fahrtId);


		vPanel.add(ueberschriftLabel);
		vPanel.add(showFahrtFlexTable);
		
	}
	
	public void oeffneBearbeiten(Fahrt t){
		
		this.fahrtProfil = t;
		
		this.fahrzeugId = t.getSourceFahrzeugId();
		this.fahrerId = t.getSourceFahrerId();
		
		
		serviceImpl.getFahrer(t);
		serviceImpl.getFahrzeug(t);
		//Texte für Datenfelder setzen und in Tabelle einfügen
		
		showFahrtFlexTable.setText(1, 0, Integer.toString(t.getId()));
		
		//Fahrtdatum
		Date fahrtDatumUntil = t.getFahrtDatum();
		String datumString = formatterDTF.format(fahrtDatumUntil);
		fahrtDatumTBox.setText(datumString);
		showFahrtFlexTable.setWidget(1, 1, fahrtDatumTBox);
		
		//Zielbeschreibung
		zielTBox.setText(t.getZielBeschreibung());
		showFahrtFlexTable.setWidget(1, 2, zielTBox);
		//KmStart (gesetzt)
		showFahrtFlexTable.setText(1, 3,Integer.toString(t.getKmStart()));
		//KmEnd
		kmEndTBox.setText(Integer.toString(t.getKmEnd()));
		showFahrtFlexTable.setWidget(1, 4, kmEndTBox);
		//KmPrivat
		privatTBox.setText(Integer.toString(t.getPrivatKm()));
		showFahrtFlexTable.setWidget(1, 5, privatTBox);
		//Km Arbeitsweg
		arbeitsTBox.setText(Integer.toString(t.getArbeitswegKm()));
		showFahrtFlexTable.setWidget(1, 6, arbeitsTBox);
		//Km Betriebsfahrt
		betriebsTBox.setText(Integer.toString(t.getBetriebsfahrtKm()));
		showFahrtFlexTable.setWidget(1, 7, betriebsTBox);
		//Kommentar
		kommentarTBox.setText(t.getKommentar());
		showFahrtFlexTable.setWidget(1, 8, kommentarTBox);
		//Bearbeitungsdatum	
		Date bearbeitungsDatumUntil = t.getBearbeitungsdatum();
		String BDatumString = formatterDTF.format(bearbeitungsDatumUntil);
		showFahrtFlexTable.setText(1, 9, BDatumString);
		//Kennzeichen wird in eigener Methode gesetzt wenn Callback wieder da ist
		
		//eMail wird in eigener Methode gesetzt wenn Callback wieder da ist
		
		
		//SpeichernButton
		speichernBtn = new Button("Speichern");
		speichernBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				
				//Eingabe der Texfelder prüfen
				if(pruefeEingabe()==true){
				
					try{
						//fahrtProfil mit den Texfeldern beschreiben
						String fdateString = fahrtDatumTBox.getText();
						Date fahrtDate = formatterDTF.parse(fdateString);
						fahrtProfil.setFahrtDatum(fahrtDate);
					
						fahrtProfil.setZielBeschreibung(zielTBox.getText());
						//kmStart muss nicht übernommen werden, da nicht veränderbar
						fahrtProfil.setKmEnd(Integer.valueOf(kmEndTBox.getText()));
						fahrtProfil.setPrivatKm(Integer.valueOf(privatTBox.getText()));
						fahrtProfil.setArbeitswegKm(Integer.valueOf(arbeitsTBox.getText()));
						fahrtProfil.setBetriebsfahrtKm(Integer.valueOf(betriebsTBox.getText()));
						fahrtProfil.setKommentar(kommentarTBox.getText());
						//Fahrer muss nicht übernommen werden, da nicht veränderbar
						//Fahrzeug muss nicht übernommen werden, da nicht veränderbar
						}catch(RuntimeException e){
						
						}
					//Fahrzeug an server zum speichern uebergeben
					serviceImpl.updateFahrt(fahrtProfil);
				}
			}
		});
		showFahrtFlexTable.setWidget(1,	12, speichernBtn);
		
		
		abbrechenBtn = new Button("Abbrechen");
		abbrechenBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(Window.confirm("Wollen Sie das Bearbeiten abbrechen?")){
				ContEditFahrt.this.mainView.openFahrzeugCont(fahrzeugId);
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
	
	public void schreibeFahrzeug(Fahrzeug v){
		this.fahrzeugProfil = v;
		//Schreibe dann das Kennzeichen in die Spalte 
		showFahrtFlexTable.setText(1, 10, fahrzeugProfil.getKennzeichen());
	}
	
	public void schreibeFahrer(Fahrer d){
		this.fahrerProfil = d;
		showFahrtFlexTable.setText(1, 11, fahrerProfil.getEMail());
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
	
	public Fahrer getFahrerProfil() {
		return fahrerProfil;
	}

	public void setFahrerProfil(Fahrer fahrerProfil) {
		this.fahrerProfil = fahrerProfil;
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
