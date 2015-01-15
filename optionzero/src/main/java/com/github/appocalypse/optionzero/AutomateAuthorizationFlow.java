package com.github.appocalypse.optionzero;

public interface AutomateAuthorizationFlow {
	/**
	 * @param url
	 * @param user
	 * @param pass
	 * @return session token
	 */
	public String execute(String url, String user, String pass);
}
