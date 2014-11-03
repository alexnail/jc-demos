package com.jevoncode.classloader.dynamic.urlclassloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import com.jevoncode.classloader.appclassloader.A;

public class CounterFacotry {

	public static ICounter newInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { getClassPath() }) {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				if ("com.jevoncode.classloader.dynamic.urlclassloader.Counter".equals(name))
					return findClass(name);
				return super.loadClass(name);
			}

		};
		//every time will load Counter for file:/D:/workspace-juno/demo-classloader/target/classes/
		//[Loaded com.jevoncode.classloader.dynamic.Counter from file:/D:/workspace-juno/demo-classloader/target/classes/]
		//My question, it doesn't cost disk IO resource? if not so, it would turn down the performance in some way, right?

		System.out.println("Using ClassLoader="+ urlClassLoader+", and it's parent="+urlClassLoader.getParent());
		return (ICounter) urlClassLoader.loadClass("com.jevoncode.classloader.dynamic.urlclassloader.Counter").newInstance();
	}

	private static URL getClassPath() {
		return CounterFacotry.class.getClassLoader().getResource(".");
	}
	
	public static void main(String[] args) {
		System.out.println(getClassPath());
	}
}
