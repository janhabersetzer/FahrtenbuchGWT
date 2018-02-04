package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView extends Composite {
	private VerticalPanel vPanel= new VerticalPanel();
	private VerticalPanel contentPanel;
	
	
	public MainView(){
		initWidget(this.vPanel);
		
		MenuView menuView= new MenuView(this);
		this.vPanel.add(menuView);
	
		contentPanel= new VerticalPanel();
		this.vPanel.add(contentPanel);
	}
	
	
	
	
	public void openFahrtenbuchMenuCont(){
		this.contentPanel.clear();
		ContAlleFahrzeuge contAlleFahrzeuge = new ContAlleFahrzeuge();
		this.contentPanel.add(contAlleFahrzeuge);
	}
	
	public void openFahrerMenuCont(){
		this.contentPanel.clear();
		ContAlleFahrer contAlleFahrer = new ContAlleFahrer();
		this.contentPanel.add(contAlleFahrer);
		
	}
	

	
	public void openReportMenuCont(){
		this.contentPanel.clear();
		ContAlleReports contAlleReports = new ContAlleReports();
		this.contentPanel.add(contAlleReports);
		
	}

}
