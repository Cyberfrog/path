package com.path;

import java.util.*;
public class Paths{
	static Map<String,List<String>> defoultRoots = new HashMap<String,List<String>>();
	static {
		List<String> bangalore = new ArrayList<String>();
		bangalore.add("Singapore");
		List<String> singapore = new ArrayList<String>();
		singapore.add("Seoul");
		singapore.add("Dubai");
		List<String> seoul = new ArrayList<String>();
		seoul.add("Beijing");
		List<String> beijing = new ArrayList<String>();
		beijing.add("Tokyo");

		defoultRoots.put("Bangalore",bangalore);
		defoultRoots.put("Singapore",singapore);
		defoultRoots.put("Seoul",seoul);
		defoultRoots.put("Beijing",beijing);
	}

	public static boolean isPresent(String city,Map<String,List<String>> roots){
		Set<String> keys =roots.keySet();
		if(keys.contains(city)){
			return true;
		}
		else{
			for(String src:keys){
				List<String> dests = roots.get(src);
				if(dests.contains(city)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isPath(String source,String destination,Map<String,List<String>> roots){ 
		return roots.get(source).contains(destination);
	}
	public static void main(String a[]){
			PathArgsMapper args;
		try{
			args = new PathArgsMapper(a);
			if(!isPresent(args.source,args.roots)){
				System.out.println("No city named \""+args.source+"\" in database");
				return;
			}
			if(!isPresent(args.destination,args.roots)){
				System.out.println("No city named \""+args.destination+"\" in database");
				return;
			}
			PathFinder pf =new PathFinder(args.roots);

			List<ArrayDeque<String>> allRoots = pf.findPath(args.source,args.destination);
            Map<String,Integer> allCost = args.costMap.mapCost(allRoots);
            pf.printSortedPathWithCountry(allCost, args.costMap.Sort(allCost),args.countrysMap);
//            pf.printPaths(allCost);
//			if(allRoots.size()>0){
//				for(int i=0;i<allRoots.size();i++){
//					ArrayDeque<String> path =allRoots.get(i);
//					pf.printPath(path,args.countrysMap,i);
//					System.out.println("Total :"+args.costMap.getCost(path));
//				}
//				return ;
//			}
			// allRoots = pf.findPath(args.destination,args.source);
			// if(allRoots.size()>0){
			// 	for(int i=0;i<allRoots.size();i++){
			// 		pf.printReversePath(allRoots.get(i),args.countrysMap,i);
			// 	}
			// }
		}catch(Exception e){
			System.out.println(e);
			System.out.println("No database named "+e.getMessage()+" found.");

		}
	}
}