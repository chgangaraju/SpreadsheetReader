package com.imaginea.spreadsheet;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.util.AuthenticationException;

public class MySpreadsheetService extends SpreadsheetService {

	private final Logger LOGGER = Logger.getLogger(MySpreadsheetService.class
			.getName());
	private User user;

	public MySpreadsheetService(String applicationName, User user)
			throws AuthenticationException {
		super(applicationName);
		this.user = user;
		createSpreadsheetService();
	}

	public MySpreadsheetService createSpreadsheetService()
			throws AuthenticationException {
		try {
			this.setUserCredentials(user.getEmail(), user.getPassword());
		} catch (AuthenticationException authException) {
			LOGGER.log(Level.SEVERE, "Authentication Exception:",
					authException.getMessage());
			throw new AuthenticationException("Authentication Exception");
		}
		return this;
	}
}
