package com.infinite.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

import com.infinite.bean.Corona;

import org.json.JSONArray;

public class CovidAPI {
	
	 static JSONObject json = null;
	
	static Map<String,Corona> coronaList=new HashMap<>();
	
	static Map<String,Corona> coronaListUpdated=new HashMap<>();

	 
	 static {

		try {
			coronaListUpdated = readJsonFromUrl("https://api.covid19api.com/summary");
			System.out.println("json data " + json);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private  static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public  static Map<String,Corona> readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			//System.out.println("json text" + jsonText);

			JSONObject outerObject = new JSONObject(jsonText);
		    JSONArray jsonArray = outerObject.getJSONArray("Countries");
		    System.out.println("length " + jsonArray.length());
		    JSONObject finalObject=null;
		      
		     

		    for (int i = 0, size = jsonArray.length(); i < size; i++)
		    {
		      JSONObject objectInArray = jsonArray.getJSONObject(i);
		      String country="";
		      // "...and get thier component and thier value."
		      String[] elementNames = JSONObject.getNames(objectInArray);
		      //{"Country":"Guatemala","Slug":"guatemala","NewConfirmed":8,
		      //"TotalConfirmed":46,"NewDeaths":0,
		      //"TotalDeaths":1,"NewRecovered":0,"TotalRecovered":12},
		    // System.out.printf("%d ELEMENTS IN CURRENT OBJECT:\n", elementNames.length);
		     int totalConfirm=0,totalDeath=0,totalRecover=0;
		     List<Corona> countryList=new ArrayList<>();
		     Corona corona=new Corona();
		     
		      for (String elementName : elementNames)
		      {
		    	  if(elementName.equalsIgnoreCase("country")) {
		    		  country=objectInArray.getString(elementName);
		    	  }
		    	  if(elementName.equalsIgnoreCase("totalconfirmed")) {	    		  
		    		  corona.setTotalConfirm(  objectInArray.getInt(elementName));
		    		
			    	  }
		    	  if(elementName.equalsIgnoreCase("TotalDeaths")) {
		  		            //totalDeath=;
		  		            corona.setTotalDeaths(objectInArray.getInt(elementName));
		  		          }
	
		    	  if(elementName.equalsIgnoreCase("TotalRecovered")) {
		    		  			corona.setTotalRecover(objectInArray.getInt(elementName));
			    		  		//totalRecover=objectInArray.getInt(elementName);
				    	  }
		    	  if(elementName.equalsIgnoreCase("NewConfirmed")) {
  		  			corona.setTotalNewConfirm(objectInArray.getInt(elementName));
	    		  		//totalRecover=objectInArray.getInt(elementName);
		    	  }
		    	  if(elementName.equalsIgnoreCase("NewDeaths")) {
	  		  			corona.setTotalNewDeaths(objectInArray.getInt(elementName));
		    		  		//totalRecover=objectInArray.getInt(elementName);
			    	  }
		    	  if(elementName.equalsIgnoreCase("NewRecovered")) {
	  		  			corona.setTotalNewRecover(objectInArray.getInt(elementName));
		    		  		//totalRecover=objectInArray.getInt(elementName);
			    	  }
		      }
		      countryList.add(corona);
		     
		      
		      coronaList.put(country, corona);
		      System.out.println();
		    }
		      //finalObject =new JSONObject(coronaList);
		      //System.out.println(finalObject);
		      System.out.println("list corona"+ coronaList.toString());

			return coronaList;
		} finally {
			is.close();
		}
	}

	public static Map<String,Corona> getCanvasjsDataList() {
		
		return coronaListUpdated;
	}

	public static void main (String args[]) {
		CovidAPI obj=new CovidAPI();
		
		sortDataBy("", getCanvasjsDataList());
	}
	
	public static Map<String,Corona> sortDataBy(String totalConfirm, Map<String,Corona> coronaList){
		
	Comparator<Corona> comparator = Comparator.comparing(Corona::getTotalConfirm).reversed();
	Stream<Map.Entry<String,Corona>> sorted =
			coronaList.entrySet().stream().sorted(Map.Entry.comparingByValue(comparator));
System.out.println("comparator");
////		coronaList.values()
////		   .forEach(l -> l.sort(comparator));
////		System.out.println(coronaList.toString());
//		
//		 Map<String,List<Corona>> list=coronaList.entrySet().stream().sorted(comparingByValue());
		//TreeMap<String, List<Corona>> sorted = new TreeMap<>(coronaList);
		
		//sorted.forEach(System.out::println);
Map<String,Corona> result = new HashMap<>();

result=sorted.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
System.out.println("result " + result.toString());
return result;
		
		
		
		
	}
}
