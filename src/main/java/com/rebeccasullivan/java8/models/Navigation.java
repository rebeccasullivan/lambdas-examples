package com.rebeccasullivan.java8.models;

import java.util.List;

public class Navigation {

    private String linkText;
    private String linkUrl;
    private boolean isActive;
    private List<Navigation> children;
    
	public String getLinkText() {
		return linkText;
	}
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public List<Navigation> getChildren() {
		return children;
	}
	public void setChildren(List<Navigation> children) {
		this.children = children;
	}
	
	@Override
	public String toString() {
		String format = "Navigation{linkText='%s', linkUrl='%s', isActive='%s', children='%s'}";
		return String.format(format, linkText, linkUrl, isActive, children);
	}
	
}