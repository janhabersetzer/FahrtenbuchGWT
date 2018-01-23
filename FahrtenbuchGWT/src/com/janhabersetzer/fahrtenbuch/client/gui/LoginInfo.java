package com.janhabersetzer.fahrtenbuch.client.gui;

import java.io.Serializable;



public class LoginInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String logoutUrl;
	private String loginUrl;
	private String emailAddress;
	private String nickname;
	private boolean loggedIn = false;

	/**
	 * Getter, der die e-Mail Adresse des Users zurückliefert
	 * 
	 * @return email
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Getter, der die Google Logout-URL zurückliefert
	 * 
	 * @return logout
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	 * Getter, der den Nickname des Users zurückliefert
	 * 
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Legt die e-Mail Adresse des Users fest
	 * 
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Legt die Google Logout-URL fest
	 * 
	 * @param logoutUrl
	 */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	/**
	 * Legt einen Nickname fuer den User fest
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Methode um abzufragen, ob ein Nutzer eingeloggt ist.
	 * @return loggedIn 
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	/**
	 * Methode um den Status auf loggedIn zu setzten
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * Getter fuer die LoginUrl
	 * @return loginUrl
	 */
	public String getLoginUrl() {
		return loginUrl;
	}
	
	/**
	 * Setter für die LoginUrl
	 * @param loginUrl
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}
