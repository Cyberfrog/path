package com.path;

import  java.util.*;
public class PathArgsMapper{
	Map<String,List<String>> roots;
	String source;
	String destination;
	CountryMapper countrysMap;
	CostMapper costMap;
	public PathArgsMapper(String a[]) throws Exception{
		int optionIndex = Arrays.asList(a).indexOf("-f");
		int cityOptionIndex = Arrays.asList(a).indexOf("-c");
		int lastIndex = a.length - 1;
		countrysMap = new CountryMapper(a[cityOptionIndex+1]);
		source = a[lastIndex-1];
		destination =a[lastIndex];
		if(optionIndex>=0){
			String file=a[optionIndex+1];
			try{			
			roots = PathReader.readPath(file);
			costMap =new CostMapper(file);
			}catch(Exception e){
				throw new Exception(file);
			}
		}else{
			roots = Paths.defoultRoots;
		}
	}
}