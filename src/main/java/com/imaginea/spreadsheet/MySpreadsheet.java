package com.imaginea.spreadsheet;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;

@SuppressWarnings("unchecked")
public class MySpreadsheet {
	private final Logger LOGGER = Logger.getLogger(MySpreadsheet.class
			.getName());
	private MySpreadsheetService service;
	private MyWorksheet worksheet;

	public MySpreadsheet(MySpreadsheetService service) throws IOException {
		worksheet = new MyWorksheet(service);
		this.service = service;
	}

	public List<WorksheetEntry> getWorksheets(SpreadsheetEntry spreadsheetEntry)
			throws IOException, ServiceException {
		List<WorksheetEntry> workSheets;
		try {
			URL workFeedURL = spreadsheetEntry.getWorksheetFeedUrl();
			WorksheetFeed worksheetFeed = service.getFeed(workFeedURL,
					WorksheetFeed.class);
			workSheets = worksheetFeed.getEntries();
		} catch (ServiceException serviceException) {
			LOGGER.log(Level.SEVERE, "Service Exception:",
					serviceException.getMessage());
			throw new ServiceException("Service Exception", serviceException);
		}
		return workSheets;
	}

	public JSONObject getWorksheetData(WorksheetEntry worksheetEntry)
			throws IOException, ServiceException {
		JSONObject jsonWorksheet = new JSONObject();
		try {
			List<ListEntry> lists = worksheet.getRows(worksheetEntry);
			JSONArray jsonRows = new JSONArray();
			for (ListEntry listEntry : lists) {
				JSONObject jsonEachRow = worksheet.getRowData(listEntry);
				jsonRows.add(jsonEachRow);
			}
			jsonWorksheet.put(worksheetEntry.getTitle().getPlainText(),
					jsonRows);
		} catch (ServiceException serviceException) {
			LOGGER.log(Level.SEVERE, "Service Exception:",
					serviceException.getMessage());
			throw new ServiceException("Service Exception", serviceException);
		}
		return jsonWorksheet;
	}
}
