package com.janhabersetzer.fahrtenbuch.shared.report;

import com.janhabersetzer.fahrtenbuch.server.FahrtenbuchAdministrationImpl;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class Test {

	public static void main(String[] args) {
		
		FahrtenbuchAdministrationImpl fbadmin = new FahrtenbuchAdministrationImpl();
		
		fbadmin.init();
		
		
		Fahrzeug v = new Fahrzeug();
		v.setId(1);
		
		
		AlleFahrtenVonFahrzeugReport report = fbadmin.createAlleFahrtenVonFahrzeugReport(v);
		
		HTMLReportWriter writer = new HTMLReportWriter();
		writer.process(report);
		System.out.print(writer.getReportText());

	}

}
