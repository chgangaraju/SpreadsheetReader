package com.imaginea.spreadsheet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
	private final Logger LOGGER = Logger.getLogger(User.class.getName());
	private String email;
	private String password;

	public User() throws IOException {
		setLoginCredentials();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void createLoginProperties() throws IOException {
		Properties properties = new Properties();
		properties.setProperty("email", "");
		properties.setProperty("password", "");
		properties.store(new FileOutputStream("login.properties"), null);
	}

	public User setLoginCredentials() throws IOException {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("login.properties"));
			this.setEmail(properties.getProperty("email"));
			this.setPassword(properties.getProperty("password"));
		} catch (Exception exception) {
			LOGGER.log(Level.SEVERE, "Exception:", exception.getMessage());
			createLoginProperties();
			setLoginCredentials();
		}
		return this;
	}
}
