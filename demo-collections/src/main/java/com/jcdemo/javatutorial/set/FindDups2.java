package com.jcdemo.javatutorial.set;

import java.util.HashSet;
import java.util.Set;

/**
 * To know which words in the argument list occur only once and which occur more
 * than once, but Do not want any duplicates printed out repeatedly.
 *
 */
public class FindDups2 {
	public static void main(String[] args) {
		Set<String> uniques = new HashSet<String>();
		Set<String> dups = new HashSet<String>();

		for (String a : args)
			if (!uniques.add(a))
				dups.add(a);

		// Destructive set-difference
		uniques.removeAll(dups);

		System.out.println("Unique words:    " + uniques);
		System.out.println("Duplicate words: " + dups);
	}
}
