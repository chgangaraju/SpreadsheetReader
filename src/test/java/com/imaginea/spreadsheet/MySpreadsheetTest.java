package com.imaginea.spreadsheet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;

public class MySpreadsheetTest {

	@Test
	public void testGetWorksheets() throws ServiceException, IOException {
		MySpreadsheets mySpreadsheets=new MySpreadsheets();
		User user = new User();
		MySpreadsheetService service = new MySpreadsheetService("SpreadsheetReader", user);
		List<SpreadsheetEntry> spreadsheets = mySpreadsheets
				.getSpreadsheets();
		SpreadsheetEntry spreadsheetEntry = spreadsheets.get(0);
		MySpreadsheet spreadsheet = new MySpreadsheet(service);
		List<WorksheetEntry> worksheetEntry = spreadsheet
				.getWorksheets(spreadsheetEntry);
		assertSame(LinkedList.class, worksheetEntry.getClass());
	}

	@Test
	public void testGetWorksheetData() throws IOException, ServiceException {
		MySpreadsheets mySpreadsheets=new MySpreadsheets();
		User user = new User();
		MySpreadsheetService service = new MySpreadsheetService("SpreadsheetReader", user);
		List<SpreadsheetEntry> spreadsheets = mySpreadsheets
				.getSpreadsheets();
		SpreadsheetEntry spreadsheetEntry = spreadsheets.get(0);
		WorksheetFeed worksheetFeed = service.getFeed(
				spreadsheetEntry.getWorksheetFeedUrl(), WorksheetFeed.class);
		List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
		WorksheetEntry worksheetEntry = worksheets.get(0);
		MySpreadsheet spreadsheet = new MySpreadsheet(service);
		JSONObject worksheet = spreadsheet.getWorksheetData(worksheetEntry);
		assertNotNull(worksheet);
	}
}
