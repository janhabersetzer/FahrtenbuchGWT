package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

public class MenuView extends Composite{
	
	// Attribute fuer Aufrufe
	private MainView mainView;
	
	//Panels
	private HorizontalPanel hPanel = new HorizontalPanel();
	private HorizontalPanel btnHPanel = new HorizontalPanel();
	
	public MenuView(MainView mainView){
		initWidget(hPanel);
		
		
		Image logo = new Image("MeinErstesAutobuch.jpg");
		logo.setHeight("80px");
		logo.setWidth("80px");
		logo.getElement().getStyle().setProperty("margin", "10px");
		logo.setStyleName("logo-image");
		hPanel.add(logo);
		
		//CSS
		btnHPanel.setSpacing(3);
		hPanel.setStyleName("menubar-panel");
		hPanel.setWidth("1300px");
		hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		this.mainView = mainView;
		
		Button fahrtenbuecherBtn = new Button("Fahrtenbücher");
		fahrtenbuecherBtn.addStyleName("fahrtenbuch-menubutton");
		fahrtenbuecherBtn.setHeight("30px");
		fahrtenbuecherBtn.addClickHandler(new FbClickHandlder());
		btnHPanel.add(fahrtenbuecherBtn);
		
		Button fahrerBtn = new Button("Fahrer");
		fahrerBtn.addStyleName("fahrtenbuch-menubutton");
		fahrerBtn.setHeight("30px");
		fahrerBtn.addClickHandler(new FhrClickHandler());
		btnHPanel.add(fahrerBtn);
		
		Button reportBtn = new Button("Reports");
		reportBtn.addStyleName("fahrtenbuch-menubutton");
		reportBtn.setHeight("30px");
		reportBtn.addClickHandler(new RpClickHandlder());
		btnHPanel.add(reportBtn);	
		
		hPanel.add(btnHPanel);
		
		
		

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
