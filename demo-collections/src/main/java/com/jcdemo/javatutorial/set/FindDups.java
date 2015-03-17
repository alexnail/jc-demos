package com.jcdemo.javatutorial.set;

import java.util.HashSet;
import java.util.Set;

/**
 * Using the for-each Construct to print out all distinct words in its argument list.
 *
 */
public class FindDups {
	public static void main(String[] args) {
		Set<String> s = new HashSet<String>();
		for (String a : args)
			s.add(a);
		System.out.println(s.size() + " distinct words: " + s);
	}
}
