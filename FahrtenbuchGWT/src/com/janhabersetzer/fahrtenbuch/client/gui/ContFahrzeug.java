package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class ContFahrzeug extends Composite{
	
	
	Fahrzeug fahrzeugprofil;
	
	int fahrzeugid;
	
	VerticalPanel vPanel = new VerticalPanel();
	
	private Label lbl = new Label("Hier ist der Content eines Fahrzeugs");
	
	
	ContFahrzeug(int id){
		initWidget(vPanel);
		this.fahrzeugid= id;
		vPanel.add(lbl);
	}

}
