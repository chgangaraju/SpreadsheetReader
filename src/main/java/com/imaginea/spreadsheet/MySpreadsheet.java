package com.imaginea.spreadsheet;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;

@SuppressWarnings("unchecked")
public class MySpreadsheet {
	private final Logger LOGGER = Logger.getLogger(MySpreadsheet.class
			.getName());
	private SpreadsheetService service;
	private MyWorksheet worksheet;

	public MySpreadsheet(SpreadsheetService service) {
		worksheet = new MyWorksheet();
		this.service = service;
	}

	public List<WorksheetEntry> getWorksheets(SpreadsheetEntry spreadSheetEntry)
			throws IOException, ServiceException {
		List<WorksheetEntry> workSheets;
		try {
			WorksheetFeed worksheetFeed = service
					.getFeed(spreadSheetEntry.getWorksheetFeedUrl(),
							WorksheetFeed.class);
			workSheets = worksheetFeed.getEntries();
		} catch (ServiceException serviceException) {
			LOGGER.log(Level.SEVERE, "Service Exception:",
					serviceException.getMessage());
			throw new ServiceException("Service Exception", serviceException);
		}
		return workSheets;
	}

	public JSONObject getWorksheetData(WorksheetEntry workSheetEntry)
			throws IOException, ServiceException {
		JSONObject jsonWorksheet = new JSONObject();
		try {
			URL listFeedUrl = workSheetEntry.getListFeedUrl();
			ListFeed listFeed = service.getFeed(listFeedUrl, ListFeed.class);
			JSONArray jsonRows = new JSONArray();
			for (ListEntry listEntry : listFeed.getEntries()) {
				JSONObject jsonEachRow = worksheet.getRowData(listFeed,
						listEntry);
				jsonRows.add(jsonEachRow);
			}
			jsonWorksheet.put(workSheetEntry.getTitle().getPlainText(),
					jsonRows);
		} catch (ServiceException serviceException) {
			LOGGER.log(Level.SEVERE, "Service Exception:",
					serviceException.getMessage());
			throw new ServiceException("Service Exception", serviceException);
		}
		return jsonWorksheet;
	}
}
