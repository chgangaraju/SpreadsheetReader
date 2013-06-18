package com.imaginea.spreadsheet;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.junit.Test;

import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.util.ServiceException;

public class MySpreadsheetReaderTest {

	@Test
	public void testGetSpreadsheets() throws ServiceException, IOException {
		MySpreadsheets mySpreadsheets = new MySpreadsheets();
		List<SpreadsheetEntry> spreadsheets = mySpreadsheets.getSpreadsheets();
		assertNotNull(spreadsheets);
	}

	@Test
	public void testGetSpreadsheetData() throws IOException, ServiceException {
		MySpreadsheets mySpreadsheets = new MySpreadsheets();
		JSONArray spreadsheetData = mySpreadsheets.getSpreadsheetData();
		assertNotNull(spreadsheetData);
	}
}
