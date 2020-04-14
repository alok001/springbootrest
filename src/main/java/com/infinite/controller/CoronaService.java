package com.infinite.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.infinite.bean.Corona;
import com.infinite.service.Coronable;
import com.infinite.service.CovidAPI;

@Service
public class CoronaService implements Coronable{

	static Map<String,Corona> coronaList=null;

	
	@Override
	public Map<String, Corona> getCoronaData() {
		
		 coronaList=CovidAPI.getCanvasjsDataList();
		 return coronaList;
	}

	@Override
	public Map<String, Corona> getCoronaSortedData(String sortBy,int limit) {
		
		 coronaList=CovidAPI.getCanvasjsDataList();
		 Comparator<Corona> comparator=null;
		 if("totalconfirm".equalsIgnoreCase(sortBy)) {
			 comparator= Comparator.comparing(Corona::getTotalConfirm).reversed();
		 }
		 else if("totaldeath".equalsIgnoreCase(sortBy)) {
			 comparator= Comparator.comparing(Corona::getTotalDeaths).reversed();
		 }
		 
		 else if("totalrecover".equalsIgnoreCase(sortBy)) {
			 comparator= Comparator.comparing(Corona::getTotalRecover).reversed();
		 }
		 else if("totalnewconfirm".equalsIgnoreCase(sortBy)) {
			 comparator= Comparator.comparing(Corona::getTotalNewConfirm).reversed();
		 }
		 
		 else if("totalnewrecover".equalsIgnoreCase(sortBy)) {
			 comparator= Comparator.comparing(Corona::getTotalNewRecover).reversed();
		 }
		 else if("totalnewdeaths".equalsIgnoreCase(sortBy)) {
			 comparator= Comparator.comparing(Corona::getTotalNewDeaths).reversed();
		 }
		 else {
			 comparator= Comparator.comparing(Corona::getTotalConfirm).reversed();

		 }
		// Integer limit_val=limit;
		 Stream<Map.Entry<String,Corona>> sorted=null;
		 if(limit!=0) {
				sorted=coronaList.entrySet().stream().sorted(Map.Entry.comparingByValue(comparator)).limit(limit);

		 }
		 else {
			 
					sorted=coronaList.entrySet().stream().sorted(Map.Entry.comparingByValue(comparator));
		 }
			
			
			Map<String,Corona> result = new HashMap<>();
			
			result=sorted.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			System.out.println("result " + result);
			return result;
		
	}

	@Override
	public Corona getCoronaCountryData(String countryName) {
		 coronaList=CovidAPI.getCanvasjsDataList();
		 
		 return coronaList.get(countryName);
		
	}

}
