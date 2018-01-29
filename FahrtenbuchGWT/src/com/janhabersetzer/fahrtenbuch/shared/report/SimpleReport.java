package com.janhabersetzer.fahrtenbuch.shared.report;

import java.util.Vector;

public abstract class SimpleReport extends Report{
	/**
	   * 
	   */
	  private static final long serialVersionUID = 1L;

	  /**
	   * Tabelle mit Positionsdaten. Die Tabelle wird zeilenweise in diesem
	   * <code>Vector</code> abgelegt.
	   */
	  private Vector<Row> table = new Vector<Row>();

	  /**
	   * Hinzufügen einer Zeile.
	   * 
	   * @param r die hinzuzufügende Zeile
	   */
	  public void addRow(Row r) {
	    this.table.addElement(r);
	  }

	  /**
	   * Entfernen einer Zeile.
	   * 
	   * @param r die zu entfernende Zeile.
	   */
	  public void removeRow(Row r) {
	    this.table.removeElement(r);
	  }

	  /**
	   * Auslesen sämtlicher Positionsdaten.
	   * 
	   * @return die Tabelle der Positionsdaten
	   */
	  public Vector<Row> getRows() {
	    return this.table;
	  }
}
