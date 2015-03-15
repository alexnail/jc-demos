package com.jevoncode.javatutorial;

/***
 * Programmers are often reluctant to employ immutable objects, because they
 * worry about the cost of creating a new object as opposed to updating an
 * object in place. The impact of object creation is often overestimated, and
 * can be offset by some of the efficiencies associated with immutable objects.
 * These include decreased overhead due to garbage collection, and the
 * elimination of code needed to protect mutable objects from corruption.
 *
 */
final public class ImmutableRGB {

	// Values must be between 0 and 255.
	final private int red;
	final private int green;
	final private int blue;
	final private String name;

	private void check(int red, int green, int blue) {
		if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0
				|| blue > 255) {
			throw new IllegalArgumentException();
		}
	}

	public ImmutableRGB(int red, int green, int blue, String name) {
		check(red, green, blue);
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.name = name;
	}

	public int getRGB() {
		return ((red << 16) | (green << 8) | blue);
	}

	public String getName() {
		return name;
	}

	public ImmutableRGB invert() {
		return new ImmutableRGB(255 - red, 255 - green, 255 - blue,
				"Inverse of " + name);
	}
}