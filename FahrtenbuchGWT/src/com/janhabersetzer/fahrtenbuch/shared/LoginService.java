package com.janhabersetzer.fahrtenbuch.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.janhabersetzer.fahrtenbuch.client.gui.LoginInfo;




@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	
	public LoginInfo login(String requestUri) throws IllegalArgumentException;

}
