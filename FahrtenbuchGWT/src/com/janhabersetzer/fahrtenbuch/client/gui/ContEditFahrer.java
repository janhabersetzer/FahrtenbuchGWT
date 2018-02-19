package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.janhabersetzer.fahrtenbuch.client.FahrtenbuchClientImpl;

public class ContEditFahrer extends Composite{
	
	// Attribute fuer Aufrufe
	private MainView mainView;
	
	private FahrtenbuchClientImpl serviceImpl;
	
	private int fahrerId;
	
	
	ContEditFahrer(int id, MainView mainView, FahrtenbuchClientImpl serviceImpl){
		
		this.fahrerId = id;
		
		this.mainView = mainView;
		
		this.serviceImpl =  serviceImpl;
	}

}
