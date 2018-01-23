package com.janhabersetzer.fahrtenbuch.client.gui;



import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class MainView extends Composite{
	
	VerticalPanel verticalPanel = new VerticalPanel(); 
	HorizontalPanel horSeg = new HorizontalPanel();
    
    
    //dock.add(vPanel, DockPanel.NORTH);
    //dock.add(vPanel2, DockPanel.SOUTH);
	
	
	
	public MainView(){
		initWidget(this.verticalPanel);
		
		
		MenuView menu = new MenuView();
		
		NavView navi = new NavView();
		
		this.horSeg.add(menu);
		this.horSeg.add(navi);
		
		this.verticalPanel.add(horSeg);
		
	}
	
	
	
	

	

}
