package ca.xnor.utils;
/*
 * Copyright 2011 John Blackwood
 * All rights reserved.
 * 
 * Limited license granted for use in this program only, all other rights reserved.
 */


import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Pair.
 *
 * @param <T1> the type of the first element in the pair
 * @param <T2> the type of the second element in the pair
 * @author John Blackwood <john@xnor.ca>
 */
public class Pair<T1, T2> {
	
	/** The first element. */
	public final T1	first;
	
	/** The second element. */
	public final T2	second;
	
	/**
	 * Instantiates a new pair.
	 *
	 * @param first the first element of the pair
	 * @param second the second element of the pair
	 */
	public Pair(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * First comparator.
	 *
	 * @param <T1> the generic type of the first element
	 * @param <T2> the generic type of the second element
	 * @return a comparator for the first element of the pair
	 */
	public static <T1 , T2> Comparator<Pair<T1, T2>> firstComparator() {
		return new Comparator<Pair<T1, T2>>() {
			@SuppressWarnings("unchecked")
			public int compare(Pair<T1, T2> arg0, Pair<T1, T2> arg1) {
				return ((Comparable<T1>)arg0.first).compareTo(arg1.first);
			}
		};
	}
	
	/**
	 * Second comparator.
	 *
	 * @param <T1> the generic type of the first element
	 * @param <T2> the generic type of the second element
	 * @return a comparator for the second element of the pair
	 */
	public static <T1, T2> Comparator<Pair<T1, T2>> secondComparator() {
		return new Comparator<Pair<T1, T2>>() {
			@SuppressWarnings("unchecked")
			public int compare(Pair<T1, T2> arg0, Pair<T1, T2> arg1) {
				return ((Comparable<T2>)arg0.second).compareTo(arg1.second);
			}
		};
	}
	
	/**
	 * Get a list of pairs from a map entry set.
	 *
	 * @param <T1> the type of the map key
	 * @param <T2> the type of the map value
	 * @param es the entry set for the map
	 * @return a list of pairs for each key/value in the map
	 */
	public static <T1, T2> List<Pair<T1,T2>> getPairList(Set<Map.Entry<T1,T2>> es) {
		List<Pair<T1,T2>> l = new ArrayList<Pair<T1, T2>>(es.size());
		for( Map.Entry<T1,T2> e : es )
			l.add(new Pair<T1,T2>(e.getKey(), e.getValue()));
		return l;
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "<" + first.toString() + ", " + second.toString() + ">";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair<?,?> other = (Pair<?,?>) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}
	
}