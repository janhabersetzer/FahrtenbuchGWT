package com.janhabersetzer.fahrtenbuch.client.gui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Navigator extends HorizontalPanel {
	
	/**
	 * Vertikales Panel erzeugen
	 */
	VerticalPanel verPanel1 = new VerticalPanel();
	
	
	
	
	
	public Navigator(){
		
		Command cmd = new Command() {
		      public void execute() {
		        Window.alert("You selected a menu item!");
		      }
		    };
	
		
		//this.add(verPanel1);
		

	    // Make some sub-menus that we will cascade from the top menu.
	    MenuBar fooMenu = new MenuBar(true);
	    fooMenu.addItem("the", cmd);
	    fooMenu.addItem("foo", cmd);
	    fooMenu.addItem("menu", cmd);

	    MenuBar barMenu = new MenuBar(true);
	    barMenu.addItem("the", cmd);
	    barMenu.addItem("bar", cmd);
	    barMenu.addItem("menu", cmd);

	    MenuBar bazMenu = new MenuBar(true);
	    bazMenu.addItem("the", cmd);
	    bazMenu.addItem("baz", cmd);
	    bazMenu.addItem("menu", cmd);

	    // Make a new menu bar, adding a few cascading menus to it.
	    MenuBar menu = new MenuBar();
	    menu.addItem("foo", fooMenu);
	    menu.addItem("bar", barMenu);
	    menu.addItem("baz", bazMenu);

	    
	  }
}
	
