package com.imaginea.spreadsheet;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

@SuppressWarnings("unchecked")
public class MySpreadsheets {
	private final Logger LOGGER = Logger.getLogger(MySpreadsheets.class
			.getName());
	private MySpreadsheetService service;
	private User user;
	private MySpreadsheet spreadsheet;

	public MySpreadsheets() throws AuthenticationException, IOException {
		user = new User();
		service = new MySpreadsheetService("SpreadsheetReader", user);
		spreadsheet = new MySpreadsheet(service);
	}

	public List<SpreadsheetEntry> getSpreadsheets() throws ServiceException,
			IOException {
		List<SpreadsheetEntry> spreadsheets = null;
		try {
			URL SPREADSHEET_FEED_URL = new URL(
					"https://spreadsheets.google.com/feeds/spreadsheets/private/full");
			SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
					SpreadsheetFeed.class);
			spreadsheets = feed.getEntries();
			if (spreadsheets.size() == 0) {
				throw new RuntimeException("No Spreadsheets found");
			}
		} catch (ServiceException serviceException) {
			LOGGER.log(Level.SEVERE, "Service Exception:",serviceException.getMessage());
			throw new ServiceException("Service Exception");
		}
		return spreadsheets;
	}

	public JSONArray getSpreadsheetData() throws IOException, ServiceException {
		List<SpreadsheetEntry> spreadsheets = getSpreadsheets();
		JSONArray jsonSpreadsheetData = new JSONArray();
		try {
			for (SpreadsheetEntry spreadsheetEntry : spreadsheets) {
				JSONObject jsonSpreadsheet = new JSONObject();
				List<WorksheetEntry> worksheets = spreadsheet
						.getWorksheets(spreadsheetEntry);
				for (WorksheetEntry worksheetEntry : worksheets) {
					JSONObject jsonWorksheet = spreadsheet
							.getWorksheetData(worksheetEntry);
					jsonSpreadsheet.put(spreadsheetEntry.getTitle()
							.getPlainText(), jsonWorksheet);
				}
				jsonSpreadsheetData.add(jsonSpreadsheet);
			}
		} catch (ServiceException serviceException) {
			LOGGER.log(Level.SEVERE, "Service Exception:",
					serviceException.getMessage());
			throw new ServiceException("Service Exception", serviceException);
		}
		return jsonSpreadsheetData;
	}

}
