package com.imaginea.spreadsheet;

import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Test;

import com.google.gdata.util.AuthenticationException;

public class MySpreadsheetServiceTest {

	@Test
	public void testGetSpreadsheetService() throws AuthenticationException,
			IOException {
		User user = new User();
		try {
			user.setEmail("user@email.com");
			user.setPassword("password"); // wrong password
			new MySpreadsheetService("SpreadsheetReader", user);
		} catch (Exception e) {
			assertSame(AuthenticationException.class, e.getClass());
		}
		user = new User();
		MySpreadsheetService service = new MySpreadsheetService(
				"SpreadsheetReader", user);
		assertSame(MySpreadsheetService.class, service.getClass());
	}
}
