package com.janhabersetzer.fahrtenbuch.shared.bo;

import java.io.Serializable;

public abstract class BusinessObject implements Serializable{
private static final long serialVersionUID = 1L;
	
	private int id = 0;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	 /**
	   * Erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz.
	   * Dies kann selbstverständlich in Subklassen überschrieben werden.
	   */
	  @Override
	public String toString() {
	    /*
	     * Wir geben den Klassennamen gefolgt von der ID des Objekts zurück.
	     */
	    return this.getClass().getName() + " #" + this.id;
	  }
	
	  public boolean equals(Object o) {
		    /*
		     * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet
		     * werden kann, sind immer wichtig!
		     */
		    if (o != null && o instanceof BusinessObject) {
		      BusinessObject bo = (BusinessObject) o;
		      try {
		        if (bo.getId() == this.id)
		          return true;
		      }
		      catch (IllegalArgumentException e) {
		        /*
		         * Wenn irgendetwas schief geht, dann geben wir sicherheitshalber false
		         * zurück.
		         */
		        return false;
		      }
		    }
		    /*
		     * Wenn bislang keine Gleichheit bestimmt werden konnte, dann müssen
		     * schließlich false zurückgeben.
		     */
		    return false;
		  }
		  
		  /**
		   * <p>
		   * Erzeugen einer ganzen Zahl, die für das <code>BusinessObject</code> charakteristisch ist.
		   * </p>
		   * <p>
		   * Zusammen mit <code>equals</code> sollte diese Methode immer definiert werden. Manche Java-Klassen
		   * verwendenden <code>hashCode</code>, um initial ein Objekt (z.B. in einer Hashtable) zu identifizieren. Erst danach
		   * würde mit <code>equals</code> festgestellt, ob es sich tatsächlich um das gesuchte Objekt handelt.
		   */
		  @Override
		public int hashCode() {
			  return this.id;
		  }

}


