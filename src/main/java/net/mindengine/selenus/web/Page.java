package net.mindengine.selenus.web;

import java.util.List;

import net.mindengine.selenus.exceptions.NoBrowserSpecifiedException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Page {

	private String name;
	private Browser browser;
		
	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
	
	public WebElement findElement(By locator) {
		return browser().findElement(locator);
	}
	
	public List<WebElement> findElements(By locator) {
		return browser().findElements(locator);
	}
	
	private Browser browser() {
		if( browser == null ) {
			throw new NoBrowserSpecifiedException("There is no browser specified in page: " + getClass());
		}
		return browser;
	}
	
	public String getSource() {
		return browser().getSource();
	}
	
	public String getLocation() {
		return browser().getCurrentUrl();
	}

	public boolean verifyPage() {
		//TODO verifyPage method
		return false;
	}
	
	public void assertPage() {
		//TODO assertPage method
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
