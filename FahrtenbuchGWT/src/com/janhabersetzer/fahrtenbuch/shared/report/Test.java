package com.janhabersetzer.fahrtenbuch.shared.report;


import com.janhabersetzer.fahrtenbuch.server.FahrtenbuchAdministrationImpl;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrzeug;

public class Test {

	public static void main(String[] args) {
	
		testReport();
	}
	
	
	public static void  testGetFahrerByEmail(){
		
		FahrtenbuchAdministrationImpl fbadmin = new FahrtenbuchAdministrationImpl();
		fbadmin.init();
		
		String emailAdress = "jh200@hdm-stuttgart.de";
		Fahrer d = fbadmin.getFahrerByEmail(emailAdress);
		System.out.println(d);
	}
	
	
	public static void testReport(){
		
		Fahrzeug v = new Fahrzeug();
		v.setId(1);
		
		FahrtenbuchAdministrationImpl fbadmin = new FahrtenbuchAdministrationImpl();
		fbadmin.init();
		
		AlleFahrtenVonFahrzeugReport report = fbadmin.createAlleFahrtenVonFahrzeugReport(v);
		
		HTMLReportWriter writer = new HTMLReportWriter();
		writer.process(report);
		System.out.print(writer.getReportText());
	}

}
