package com.imaginea.spreadsheet;

import java.io.IOException;

import org.json.simple.JSONArray;

import com.google.gdata.util.ServiceException;

public class SpreadsheetReader {
	public static void main(String[] args) throws ServiceException, IOException {
		MySpreadsheets spreadsheets = new MySpreadsheets();
		JSONArray jsonSpreadsheetData = spreadsheets.getSpreadsheetData();
		System.out.println(jsonSpreadsheetData);
	}
}
