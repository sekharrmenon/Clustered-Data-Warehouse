package com.datawarehouse.service;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

public interface DealService {
	
	public String validateDeals(List<CSVRecord> csvRecords, String name) throws Exception;

	public boolean checkFileExist(String name);
}
