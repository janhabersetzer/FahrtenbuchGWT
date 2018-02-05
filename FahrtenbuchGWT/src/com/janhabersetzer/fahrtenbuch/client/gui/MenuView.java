package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

public class MenuView extends Composite{
	
	// Attribute fuer Aufrufe
	private MainView mainView;
	
	//Panels
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	public MenuView(MainView mainView){
		initWidget(hPanel);
		
		this.mainView = mainView;
		
		Button fahrtenbuecherBtn = new Button("Fahrtenbücher");
		fahrtenbuecherBtn.addClickHandler(new FbClickHandlder());
		this.hPanel.add(fahrtenbuecherBtn);
		
		Button fahrerBtn = new Button("Fahrer");
		fahrerBtn.addClickHandler(new FhrClickHandler());
		this.hPanel.add(fahrerBtn);
		
		Button reportBtn = new Button("Reports");
		reportBtn.addClickHandler(new RpClickHandlder());
		this.hPanel.add(reportBtn);	
		
		Image logo = new Image("MeinErstesAutobuch.jpg");
		logo.setHeight("50px");
		logo.setWidth("50px");
		this.hPanel.add(logo);
	}
	
	
	private class FbClickHandlder implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			mainView.openAlleFhrzCont();
			
		}		
	}
	
	private class FhrClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			mainView.openAlleFahrerCont();
			
		}		
	}
	
	private class RpClickHandlder implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			mainView.openAlleReportsCont();
			
		}		
	}
	
}
