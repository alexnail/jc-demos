package com.jc.log.io;

import java.io.File;
import java.io.FileFilter;
import java.util.Map;
import java.util.TreeMap;

import com.jc.commons.FileUtils;

public class LogFile implements FileFilter{
	private String dir;
	private File dirFile;
	private Map<String, File[]> serverAndFiles = new TreeMap<>();

	public LogFile(String dir){
		this.dir = dir;
		dirFile = new File(dir);
		if(!dirFile.isDirectory())
			throw new IllegalArgumentException(dir+"应为日志目录");
		init();
	}
	
	private void init() {
		File[] dirs = dirFile.listFiles();
		FileUtils.sortByName(dirs);
		for(File logDir:dirs){
			String server = logDir.getName();
			File[] logs = logDir.listFiles(this);
			FileUtils.sortByName(logs);
			serverAndFiles.put(server, logs);
		}
	}

	@Override
	public boolean accept(File file) {
		return file.getName().endsWith(".log");
	}

	public Map<String, File[]> getServerAndFiles() {
		return serverAndFiles;
	}
 
}
