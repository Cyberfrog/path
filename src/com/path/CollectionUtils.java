package com.path;

import java.util.*;
interface ListMapper<E,K> {
	public K map(E element);
}
interface ListFilter<E>{
	public boolean check(E element);
}
interface ListReducer<E,K>{
	public K compare(K previousVal, E currentVal);
}
public class CollectionUtils{
	public static <E,K> List<K> map(List<E> list, ListMapper<E,K> mapper){
		List<K> newlist = new ArrayList<K>();
		for(E l :list){
			K element = mapper.map(l);
			newlist.add(element);
		} 
		return newlist;
	}
	public static <E> List<E> filter(List<E>list , ListFilter<E> predicate){
		List<E> newlist = new ArrayList<E>();
		for(E l :list){
			if(predicate.check(l)){
				newlist.add(l);
			}
		} 
		return newlist;
	}
	public static <E,K> K reduce(List<E> list, ListReducer<E,K> reducer, K initial){
		K pv =initial;
		for(E l :list){
			pv = reducer.compare(pv,l);
		} 
		return pv;
	}
}