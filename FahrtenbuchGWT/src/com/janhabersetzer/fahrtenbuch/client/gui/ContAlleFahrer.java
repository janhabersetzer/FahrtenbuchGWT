package com.janhabersetzer.fahrtenbuch.client.gui;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ContAlleFahrer extends Composite{
	
	private VerticalPanel vPanel= new VerticalPanel();
	
	private Label lbl = new Label("Hier ist der ContAlleFahrer Content");
	
	public ContAlleFahrer(){
		initWidget(this.vPanel);
		vPanel.add(lbl);
	}

}
