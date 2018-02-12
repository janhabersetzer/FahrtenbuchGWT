package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;


public class MainView extends Composite {
	
	//Attribute für asynchrone/ server Aufrufe
	
	private FahrtenbuchClientImpl serviceImpl;
	
	//Attribute der einzelen Views
	
	private ContAlleFahrzeuge contAlleFahrzeuge;
	
	private ContAlleFahrer contAlleFahrer;
	
	private ContAlleReports contAlleReports;
	
	private ContFahrzeug contFahrzeug;
	
	private ContEditFahrt contEditFahrt;
	
	//Gibt anhand der Zahle Aussage, welcher Content Grad angezeigt wird.
	// private int currentContIndex;
	
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
	
	
	//**************Offenen-Methoden**********************************
	
	// Oeffnen der Menue - Ansichen aus dem MenuView
	
	public void openAlleFhrzCont(){
		this.contentPanel.clear();
		contAlleFahrzeuge = new ContAlleFahrzeuge(this, serviceImpl);
//		this.setCurrentContIndex(contAlleFahrzeuge);
		this.contentPanel.add(contAlleFahrzeuge);
	}
	
	public void openAlleFahrerCont(){
		this.contentPanel.clear();
		contAlleFahrer = new ContAlleFahrer();
//		this.setCurrentContIndex(contAlleFahrer);
		this.contentPanel.add(contAlleFahrer);	
	}
	
	public void openAlleReportsCont(){
		this.contentPanel.clear();
		contAlleReports = new ContAlleReports(this, serviceImpl);
//		this.setCurrentContIndex(contAlleReports);
		this.contentPanel.add(contAlleReports);	
	}
	
	//Oeffnen der Unterseiten
	
	public void openFahrzeugCont(int id){
		this.contentPanel.clear();
		this.contFahrzeug = new ContFahrzeug(id, this, serviceImpl);
//		this.setCurrentContIndex(contFahrzeug);
		this.contentPanel.add(contFahrzeug);
	}
	
	public void openEditFahrt(int id){
		this.contentPanel.clear();
		this.contEditFahrt = new ContEditFahrt(id, this, serviceImpl);
//		this.setCurrentContIndex(contFahrzeug);
		this.contentPanel.add(contEditFahrt);
	}
	
	//**************Getter/Setter***********************
	
//	public void setCurrentContIndex(Object o){
//		if(o instanceof ContAlleFahrer){
//			this.currentContIndex= 0;
//		}else if(o instanceof ContAlleFahrzeuge){
//			this.currentContIndex= 1;
//		}else if(o instanceof ContAlleReports){
//			this.currentContIndex= 2;
//		}else if(o instanceof ContFahrzeug){
//			this.currentContIndex= 3;
//		}
//	}
	
//	public int getCurrentCont(){
//		return this.currentContIndex;
//	}
	
	public  ContAlleFahrzeuge getContAlleFahrzeuge(){
		return this.contAlleFahrzeuge;
	}
	
	public ContAlleFahrer getContAlleFahrer(){
		return this.contAlleFahrer;
	}
	
	public ContAlleReports getContAlleReprots(){
		return this.contAlleReports;
	}
	
	public  ContFahrzeug getContFahrzeug(){
		return this.contFahrzeug;
	}
	
	public ContEditFahrt getContEditFahrt(){
		return this.contEditFahrt;
	}

}
