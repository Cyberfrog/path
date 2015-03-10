package com.path;

import java.util.*;
import java.io.*;
public class CostMapper{
	Map<String,Map<String,Integer>> costMap;
	public CostMapper(String file) throws Exception{
		costMap = readCost(file);
	}
	static Map<String,Map<String,Integer>> readCost(String file) throws Exception{
		Map<String,Map<String,Integer>> roots = new HashMap<String,Map<String,Integer>>();
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line ="";
		while((line=in.readLine())!=null){
			String path[] = line.split("[,]");
			Map<String,Integer> dests = roots.get(path[0]);
			if(dests!=null){
				dests.put(path[1],Integer.parseInt(path[2]));
			}else{
				dests = new HashMap<String,Integer>();
				dests.put(path[1],Integer.parseInt(path[2]));				
				roots.put(path[0],dests);
			}
			dests = roots.get(path[1]);
			if(dests!=null){
				dests.put(path[0],Integer.parseInt(path[2]));
			}else{
				dests = new HashMap<String,Integer>();
				dests.put(path[0],Integer.parseInt(path[2]));				
				roots.put(path[1],dests);
			}
		}
		return roots;
	}
	private Integer getCostForDirectPath(String source,String dests){
			return costMap.get(source).get(dests);		
	}
	public Integer getCost(ArrayDeque<String> path){
		Integer cost = 0;
		Iterator<String> i = path.iterator();
		String source = i.next();
		while(i.hasNext()){
			String dest =i.next();
			cost+=getCostForDirectPath(source,dest);
			source =dest;
		}
		return cost;
	} 
	public Map<String,Integer> mapCost(List<ArrayDeque<String>> allpaths){
		Map<String,Integer> costList = new HashMap<String,Integer>();
		for(ArrayDeque<String> path:allpaths){
			costList.put(String.join("->", path), this.getCost(path));
		}
		return costList;
	}
    public List<String> Sort(final Map<String,Integer> allCost){
        List <String> sorted_list ;
        String keys[] = allCost.keySet().toArray(new String[0]);
        Arrays.sort(keys, new Comparator<String>() {
            @Override
            public int compare(String p1, String p2) {
             return allCost.get(p1) - allCost.get(p2);
            };
        });
        sorted_list =Arrays.asList(keys);
        return sorted_list;
    }
}