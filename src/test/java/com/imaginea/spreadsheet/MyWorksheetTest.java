package com.imaginea.spreadsheet;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;

public class MyWorksheetTest {

	@Test
	public void testGetRowData() throws IOException, ServiceException {
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
		URL listFeedUrl = worksheetEntry.getListFeedUrl();
		ListFeed listFeed = service.getFeed(listFeedUrl, ListFeed.class);
		ListEntry listEntry = listFeed.getEntries().get(0);
		JSONObject eachRow = new MyWorksheet(service).getRowData(listEntry);
		assertNotNull(eachRow);
	}
}
