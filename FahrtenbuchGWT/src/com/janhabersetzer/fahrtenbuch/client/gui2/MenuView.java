package com.janhabersetzer.fahrtenbuch.client.gui2;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;


public class MenuView extends Composite {
	
	HorizontalPanel hPanel = new HorizontalPanel();
	
	
	public MenuView() {
		initWidget(this.hPanel);
		
		
		Button btn1 = new Button("Button1");
		btn1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		this.hPanel.add(btn1);
		
		Button btn2 = new Button("Button2");
		btn1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		this.hPanel.add(btn2);
	}
		
}
