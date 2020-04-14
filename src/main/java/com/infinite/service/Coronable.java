package com.infinite.service;

import java.util.Map;

import com.infinite.bean.Corona;

public interface Coronable {
	
	public Map<String,Corona> getCoronaData();
	
	public Map<String,Corona> getCoronaSortedData(String sortBy,int limit);
	
	public Corona getCoronaCountryData(String countryName);

}
