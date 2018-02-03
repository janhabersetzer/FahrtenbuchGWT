package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchGWT;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;




public class Navigator extends HorizontalPanel {
	
	
	private Fahrer fahrerProfil;
	
	//
	VerticalPanel verPanel1 = new VerticalPanel();
	
	
	/**
	 * Konstruktor der auch die onload()-Methode aufruft und so die Seite aufbaut.
	 */
	public Navigator(){
		this.fahrerProfil = FahrtenbuchGWT.getfhr();
		this.build();
	}
	
		
	/**
	 * Methode zum Aufbau der Seite
	 */
	public void build(){
		this.add(verPanel1);
		
		
		/*
		 * Ab hier wird die Menuebar erstellt. Dabei werden abhaengig von der
		 * Thematik einzelne vertikale aufklappbare Menues zur horizontalen
		 * Menuehauptleiste "menu" hinzugefuegt.
		 */
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		
		//Festlegen der Höhe, Breite und der CCS-Style ID
		menu.setWidth("100%");
		menu.setHeight("36px");

		menu.setAnimationEnabled(true);
		menu.setStyleName("MenuBar");
		
		
		/**
		 * Menu um alle Fahrzeuge anzuzeigen
		 */
		MenuBar fahrzeugMenu = new MenuBar(true);
		fahrzeugMenu.setAnimationEnabled(true);
		
		MenuItem alleFahrzeugeAnzeigen = fahrzeugMenu.addItem("Alle Fahrzeuge anzeigen", new Command() {
			
			@Override
			public void execute() {
				ShowAlleFahrzeuge showAlleFahrzeuge  = new ShowAlleFahrzeuge();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showAlleFahrzeuge);
				
			}
		});
		
		alleFahrzeugeAnzeigen.setStyleName("MenuItem");
		
		fahrzeugMenu.addSeparator();
		
		/**
		 * Menu um den aktuell angemeldeten Fahrer anzuzeigen
		 */
		
		MenuBar fahrerMenu = new MenuBar(true);
		fahrerMenu.setAnimationEnabled(true);
		
		MenuItem fahrerAnzeigen = fahrerMenu.addItem("Fahrer-Profil anzeigen", new Command() {
			
			@Override
			public void execute() {
				ShowFahrer showFahrer = new ShowFahrer(fahrerProfil);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showFahrer);	
			}
		});
		
		fahrerAnzeigen.setStyleName("MenuItem");
		
		fahrerMenu.addSeparator();
		
		/**
		 * Hinzufuegen der vertikalen Menueleisten nutzerProfilMenu,
		 * suchprofilMenu und partnervorschlaegeMenu zur horizontalen
		 * Hauptleiste "menu" und Benennung der Menueleisten in der Menuebar per
		 * String-Uebergabe.
		 */

		menu.addItem(new MenuItem("Fahrzeug Profile", fahrzeugMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Mein Fahrer Profil", fahrerMenu));
		menu.addSeparator();

		/**
		 * Hinzufügen der Menübar zum RootPanel
		 */
		RootPanel.get("Header").add(menu);
		
		
	}
}
	
