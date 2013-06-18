package com.imaginea.spreadsheet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.junit.Test;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class SpreadsheetReaderTest {
	@Test
	public void testGetSpreadsheetService() throws AuthenticationException, IOException {
		User user = new User();
		SpreadsheetReader spreadsheetReader = new SpreadsheetReader();
		try {
			user.setEmail("user@email.com");
			user.setPassword("password"); // wrong password
			spreadsheetReader.getSpreadsheetService();
		} catch (Exception e) {
			assertSame(AuthenticationException.class, e.getClass());
		}

		user = user.setLoginCredentials();
		SpreadsheetService service = spreadsheetReader.getSpreadsheetService();
		assertSame(SpreadsheetService.class, service.getClass());
	}

	@Test
	public void testGetSpreadsheets() throws ServiceException, IOException {
		SpreadsheetReader spreadsheetReader = new SpreadsheetReader();
		List<SpreadsheetEntry> spreadsheets = spreadsheetReader
				.getSpreadsheets();
		assertNotNull(spreadsheets);
	}
	
	@Test
	public void testGetSpreadsheetData() throws IOException, ServiceException {
		SpreadsheetReader spreadsheetReader=new SpreadsheetReader();
		JSONArray spreadsheetData = spreadsheetReader.getSpreadsheetData();
		assertNotNull(spreadsheetData);
	}
}
