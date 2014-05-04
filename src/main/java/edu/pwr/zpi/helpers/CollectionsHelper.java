package edu.pwr.zpi.helpers;

import java.util.Collection;
import java.util.Map;

public class CollectionsHelper {
	/**
	 * Private constructor.
	 * Prevents class instantiation.
	 */
	private CollectionsHelper() {}
	
	/**
	 * 
	 * @param collection
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String flatten(Collection collection) {
		StringBuilder b = new StringBuilder("[");
		
		if (collection != null && collection.isEmpty() == false ) {
			for (Object o : collection) {
				b.append(o.toString()).append(",");
			}
			b.deleteCharAt(b.length() -1);
		}
		
		b.append("]");
		return b.toString();		
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String flatten(Map map) {
		StringBuilder b = new StringBuilder("[");
		
		if (map != null && map.isEmpty() == false ) {
			for (Object o : map.keySet()) {
				b.append("<k=").append(o.toString());
				b.append(",v=").append(map.get(o).toString()).append(">,");
			}
			b.deleteCharAt(b.length() -1);
		}
		
		b.append("]");
		return b.toString();		
	}
	
	/**
	 * 
	 * @param intarray
	 * @return
	 */
	public static String flatten(int [] intarray) {
		StringBuilder b = new StringBuilder("[");
		
		if (intarray != null && intarray.length > 0) {
			for (int i : intarray) {
				b.append(i).append(",");
			}
			b.deleteCharAt(b.length() -1);
		}
		
		b.append("]");
		return b.toString();		
	}
	
	/**
	 * 
	 * @param strarray
	 * @return
	 */
	public static String flatten(String [] strarray) {
		StringBuilder b = new StringBuilder("[");
		
		if (strarray != null && strarray.length > 0) {
			for (String str : strarray) {
				b.append(str).append(",");
			}
			b.deleteCharAt(b.length() -1);
		}
		
		b.append("]");
		return b.toString();		
	}
}
