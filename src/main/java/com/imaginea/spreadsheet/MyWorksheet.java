package com.imaginea.spreadsheet;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;

import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

@SuppressWarnings("unchecked")
public class MyWorksheet {
	private final Logger LOGGER = Logger.getLogger(MyWorksheet.class.getName());
	private MySpreadsheetService service;

	public MyWorksheet(MySpreadsheetService service) throws IOException {
		this.service = service;
	}

	public List<ListEntry> getRows(WorksheetEntry worksheetEntry)
			throws ServiceException, IOException {
		List<ListEntry> listEntries;
		try {
			URL listFeedUrl = worksheetEntry.getListFeedUrl();
			ListFeed listFeed = service.getFeed(listFeedUrl, ListFeed.class);
			listEntries = listFeed.getEntries();
		} catch (ServiceException serviceException) {
			LOGGER.log(Level.SEVERE, "Service Exception:",
					serviceException.getMessage());
			throw new ServiceException("Service Exception", serviceException);
		}
		return listEntries;
	}

	public JSONObject getRowData(ListEntry listEntry) {
		JSONObject jsonSingleRow = new JSONObject();
		for (String tag : listEntry.getCustomElements().getTags()) {
			jsonSingleRow.put(tag, listEntry.getCustomElements().getValue(tag));
		}
		return jsonSingleRow;
	}
}
