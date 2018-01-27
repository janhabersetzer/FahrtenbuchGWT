package com.janhabersetzer.fahrtenbuch.client;

import javax.swing.text.AbstractDocument.ElementEdit;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.janhabersetzer.fahrtenbuch.client.gui.LoginInfo;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministration;
import com.janhabersetzer.fahrtenbuch.shared.FahrtenbuchAdministrationAsync;
import com.janhabersetzer.fahrtenbuch.shared.LoginServiceAsync;
import com.janhabersetzer.fahrtenbuch.shared.bo.Fahrer;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FahrtenbuchGWT implements EntryPoint {
	
	Fahrer fahrer = new Fahrer();

	
	/**
	 * Deklaraion der Labels fuer die Startseite(n)
	 */
	private Label begruessenN = new Label(
			"Willkommen bei deinem Fahrtenbuch. ");
	private Label begruessenN2 = new Label(
			"Klicke dich nun durch die Webseite und trage deine Fahrten ein");
	
	/**
	 * Deklaration fuer den Login und den Logout
	 */
	private static Fahrer fhr = null;
	
	private static LoginInfo loginInfo = null;
	
	private static String editorHtmlName = "FahrtenbuchGWT.html";

	private FahrtenbuchAdministrationAsync admin = ClientSideSettings.getFahrtenbuchVerwaltung();
	
	private LoginServiceAsync loginService = ClientSideSettings.getLoginService();
	
	
	public void onModuleLoad() {
		setStyles();

		/**
		 * Login-Methode aufrufen und anschließend auf die Hostpage leiten.
		 */
		loginService.login(GWT.getHostPageBaseURL() + editorHtmlName,
				loginExecute());	
	}
	
	/**
	 * AsyncCallback für die Login-Mathode. Bei erhalt der LoginInfos wir die Methode
	 * pruefeObFahrerNeu() aufgerufen.
	 * 
	 * @return
	 */
	private AsyncCallback<LoginInfo> loginExecute() {
		AsyncCallback<LoginInfo> asynCallback = new AsyncCallback<LoginInfo>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(LoginInfo result) {

				if (result.isLoggedIn()) {
					loginInfo = result;
					admin.pruefeObFahrerNeu(result.getEmailAddress(),
							pruefeObFahrerNeuExecute(result
									.getEmailAddress()));


				} else {
					
					Window.Location.replace(result.getLoginUrl());
				}
			}
		};

		return asynCallback;
	}
	
	/**
	 * AsyncCallback für die Methode pruefeObFahrerNeu(). Falls der Wert false ist wird die Methode
	 * getNutzerByEmail() aufgerufen, sonst wird der Nutzer auf CreateNutzerprofil() weitergeleitet.
	 * 
	 * @return
	 */
	private AsyncCallback<Boolean> pruefeObFahrerNeuExecute(String email) {
		AsyncCallback<Boolean> asynCallback = new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Boolean result) {

				if (!result) {
			
					admin.getFahrerByEmail(loginInfo.getEmailAddress(),
							getFahrerByEmailExecute(loginInfo.getEmailAddress()));
					
					RootPanel.get("Details").add(begruessenN);
					RootPanel.get("Details").add(begruessenN2);

				} else {
					
					CreateNutzerprofil createNutzerprofil = new CreateNutzerprofil("Np");
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(createNutzerprofil);
					
				}

			}
		};

		return asynCallback;
	}

	private AsyncCallback<Fahrer> getFahrerByEmailExecute(String emailAddress) {
		AsyncCallback<Fahrer> asynCallback = new AsyncCallback<Fahrer>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Fahrer result) {
				
				fhr = result;
			getMenu();

			}
		};
		return asynCallback;

	}

	/**
	 * Methode legt die CSS-Styles für verschiedene Labels fest.
	 */
	private void setStyles() {
		begruessenN.setStyleName("welcome-label");
		begruessenN2.setStyleName("welcome-label2");

	}

	/**
	 * Methode erzeugt ruft das Panel auf, durch welches die Menubar sichtbar wird.
	 */
	public static void getMenu() {
		RootPanel.get("Navigator").add(new Navigator(np));
	}
	
	
	//***************Getter-Teil*******************
	
	public static Fahrer getfhr() {
		return fhr;
	}

	
	public static LoginInfo getLoginInfo() {
		return loginInfo;
	}

}


	    
	  

