package com.janhabersetzer.fahrtenbuch.shared.bo;

public class Fahrzeug extends BusinessObject{

	private static final long serialVersionUID =1L;
	
	private String kennzeichen;
	
	private int km;
	
	private String modellBeschreibung;
	
	private String farbe;

	
//	Getter-/Setter
	
	
	public String getKennzeichen() {
		return kennzeichen;
	}

	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}

	public int getKm() {
		return this.km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getModellBeschreibung() {
		return this.modellBeschreibung;
	}

	public void setModellBeschreibung(String modellBeschreibung) {
		this.modellBeschreibung = modellBeschreibung;
	}

	public String getFarbe() {
		return this.farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	@Override
	public String toString() {
		return super.toString()+ "Fahrzeug [kennzeichen=" + this.kennzeichen + ", km=" + this.km + ", modellBeschreibung=" + this.modellBeschreibung
				+ ", farbe=" + this.farbe + "]";
	}
	
	
	
	
}
