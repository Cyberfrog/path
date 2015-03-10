package com.path;

import java.util.*;
import java.io.*;
public class PathReader{

	public static Map<String,List<String>> readPath(String file) throws Exception{
		Map<String,List<String>> roots = new HashMap<String,List<String>>();
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line ="";
		while((line=in.readLine())!=null){
			String path[] = line.split("[,]");
			List<String> dests = roots.get(path[0]);
			if(dests!=null){
				dests.add(path[1]);
			}else{
				dests = new ArrayList<String>();
				dests.add(path[1]);				
				roots.put(path[0],dests);
			}
			dests = roots.get(path[1]);
			if(dests!=null){
				dests.add(path[0]);
			}else{
				dests = new ArrayList<String>();
				dests.add(path[0]);				
				roots.put(path[1],dests);
			}
		}
		return roots;
	}
}