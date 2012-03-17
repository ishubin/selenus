package net.mindengine.selenus.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Frame extends Browser {

	private Browser browser;
	
	public Frame(Browser browser) {
		setDriver(browser.getDriver());
	}
	
	public Frame(Browser browser, String name) {
		setDriver(browser.getDriver());
		setName(name);
	}
	
	@Override
	public WebElement findElement(By locator) {
		getDriver().switchTo().frame(getName());
		return super.findElement(locator);
	}
	
	@Override
	public List<WebElement> findElements(By locator) {
		getDriver().switchTo().frame(getName());
		return super.findElements(locator);
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
}
