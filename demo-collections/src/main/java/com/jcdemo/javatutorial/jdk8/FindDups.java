package com.jcdemo.javatutorial.jdk8;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Using JDK 8 Aggregate Operations to print out all distinct words in its argument list.
 * @author Jevoncode
 *
 */
public class FindDups {
	public static void main(String[] args) {
		Set<String> distinctWords = Arrays.asList(args).stream()
				.collect(Collectors.toSet());

		System.out.println(distinctWords.size() + " distinct words: "
				+ distinctWords);
	}
}
