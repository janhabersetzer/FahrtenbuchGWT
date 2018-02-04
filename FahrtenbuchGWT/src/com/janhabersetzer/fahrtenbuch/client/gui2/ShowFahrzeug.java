package com.janhabersetzer.fahrtenbuch.client.gui2;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class ShowFahrzeug extends VerticalPanel {
	
	Fahrzeug fahrzeugProfil;
	
	/**
	 * Panels erzeugen.
	 */
	private VerticalPanel fahrzeugPanel = new VerticalPanel();
	private VerticalPanel infoPanel = new VerticalPanel();
	private HorizontalPanel horPanel = new HorizontalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();

	
	public ShowFahrzeug(Fahrzeug fahrzeugProfil) {
		this.fahrzeugProfil = fahrzeugProfil;
	}

}
