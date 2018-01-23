package com.janhabersetzer.fahrtenbuch.client.gui;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NavView extends Composite{
	
	VerticalPanel navPanel = new VerticalPanel();
	
	
	private void NavView() {
		initWidget(navPanel);
		
		Label lbl = new Label("Hier sollte das Navi hin");
		
		this.navPanel.add(lbl);
	}
	

}
