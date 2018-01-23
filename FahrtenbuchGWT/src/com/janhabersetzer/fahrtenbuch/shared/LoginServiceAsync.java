package com.janhabersetzer.fahrtenbuch.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.janhabersetzer.fahrtenbuch.client.gui.LoginInfo;

public interface LoginServiceAsync {
		
		/**
		 * @see de.hdm.gruppe7.partnerboerse.server.LoginServiceImpl#login(String)
		 */	
		public void login(String requestUri, AsyncCallback<LoginInfo> callback);

	
}
