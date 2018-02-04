package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ContAlleReports extends Composite{
	
	 private VerticalPanel vPanel = new VerticalPanel();
	 
	 private Label lbl = new Label("Hier ist der ContAlleReports Content");
	 
	 
	 public ContAlleReports(){
		 initWidget(this.vPanel);
		 vPanel.add(lbl);
	 }

}
