package com.imaginea.spreadsheet;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

public class UserTest extends TestCase {
	@Test
	public void testSetLoginCredentials() throws IOException {
		User user = new User();
		assertNotNull(user);
	}
}
