package com.rebeccasullivan.java8.models;

public class Page {

    private String path;
    
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		String format = "Page{path='%s'}";
		return String.format(format, path);
	}
	
}