package com.imaginea.spreadsheet;

import org.json.simple.JSONObject;

import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;

@SuppressWarnings("unchecked")
public class MyWorksheet {

	public JSONObject getRowData(ListFeed listFeed, ListEntry listEntry) {
		JSONObject jsonSingleRow = new JSONObject();
		for (String tag : listEntry.getCustomElements().getTags()) {
			jsonSingleRow.put(tag, listEntry.getCustomElements().getValue(tag));
		}
		return jsonSingleRow;
	}
}
