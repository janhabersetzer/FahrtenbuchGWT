package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;

public class MainView extends Composite {
	
	//Attribute fuer Aufrufe
	
	FahrtenbuchClientImpl serviceImpl;
	
	
	//Panels
	
	private VerticalPanel vPanel= new VerticalPanel();
	private VerticalPanel contentPanel;
	
	
	
	public MainView(FahrtenbuchClientImpl serviceImpl){
		initWidget(this.vPanel);
		
		this.serviceImpl= serviceImpl;
		
		MenuView menuView = new MenuView(this);
		this.vPanel.add(menuView);
	
		contentPanel= new VerticalPanel();
		this.vPanel.add(contentPanel);
	}
	
	
	//**************Methoden**********************************
	
	public void openAlleFhrzCont(){
		this.contentPanel.clear();
		ContAlleFahrzeuge contAlleFahrzeuge = new ContAlleFahrzeuge(this, serviceImpl);
		this.contentPanel.add(contAlleFahrzeuge);
	}
	
	public void openAlleFahrerCont(){
		this.contentPanel.clear();
		ContAlleFahrer contAlleFahrer = new ContAlleFahrer();
		this.contentPanel.add(contAlleFahrer);	
	}
	
	public void openAlleReportsCont(){
		this.contentPanel.clear();
		ContAlleReports contAlleReports = new ContAlleReports();
		this.contentPanel.add(contAlleReports);	
	}

}
