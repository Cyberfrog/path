package com.path;

import  java.util.*;
import java.io.*;
public class CountryMapper {
	Map<String,String> citys ;
	public CountryMapper(String file)throws Exception{
		citys = readCitysInfo(file);
	}
	public String getCountry(String city){
		return citys.get(city);
	}
	static Map<String,String> readCitysInfo(String file) throws Exception{
		Map<String,String> citys = new HashMap<String,String>();
 		try{
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line ="";
			while((line=in.readLine())!=null){
				String cityInfo[] = line.split("[,]");
				citys.put(cityInfo[0],cityInfo[1]);
			}
		}catch(Exception e){
			throw new Exception(file);
		}
		return citys;
	}
}