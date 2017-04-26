/*******************************************************************************
 *
 * Project : EDI On Line Support Tool v1.0
 *
 * Package : com.bcbsm.edi.util
 *
 * Date    : May 25, 2010 2:30:39 PM
 *
 * Author  : Mrutyumjaya Rao Chennu <mchennu@miraclesoft.com>
 *
 * File    : DataUtility.java
 *
 * Copyright 2010 Blue Cross Blue Shield of Michigan All rights reserved.
 * BCBSM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.mirage.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DataUtility {
	
	public static DataUtility _instance;
	private DataUtility(){
		
	}
	
	public static DataUtility getInstance()throws ServiceLocatorException {
		if(_instance == null){
			_instance = new DataUtility();
		}
		return _instance;
	}

	// This method is used for getting the Map object keys in the format of
	// String array
	public String[] readMapKeys(Map<String, String> mapObj)
			throws ServiceLocatorException {
		
		String keysArray[] = new String[mapObj.size()];
		
		Set<String> keysSet = mapObj.keySet();
		Iterator<String> keysItr = keysSet.iterator();
		
		int index = 0;
		while (keysItr.hasNext()) {
			keysArray[index] = (String) keysItr.next();
			index++;
		}
		
		return keysArray;
	}
         public  Map getMapSortByValue(Map unsortMap) {	 
	List list = new LinkedList(unsortMap.entrySet());
 
	Collections.sort(list, new Comparator() {
		public int compare(Object o1, Object o2) {
			return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
		}
	});
 
	Map sortedMap = new LinkedHashMap();
	for (Iterator it = list.iterator(); it.hasNext();) {
		Map.Entry entry = (Map.Entry) it.next();
		sortedMap.put(entry.getKey(), entry.getValue());
	}
	return sortedMap;
}

}
