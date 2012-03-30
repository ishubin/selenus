/*******************************************************************************
* Copyright 2012 Ivan Shubin http://mindengine.net
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/
package net.mindengine.selenus.web;

import java.io.File;
import java.util.List;

import net.mindengine.selenus.exceptions.InvalidPageException;
import net.mindengine.selenus.exceptions.NoWebDriverException;
import net.mindengine.selenus.web.factory.PageFactory;
import net.mindengine.selenus.web.objects.SelenusActionListener;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Browser {
	private String name;
	private WebDriver driver;
	private String type;
	private PageFactory pageFactory;
	private SelenusActionListener selenusActionListener;
	
	public Browser () {
	}
	
	public void open(String url) {
		findDriver().get(url);
		
		if ( selenusActionListener != null ) {
			selenusActionListener.openUrl(url, this);
		}
	}
	
	public void close() {
		findDriver().close();
	}
	
	public String getCurrentUrl() {
		return findDriver().getCurrentUrl();
	}
	
	public String getTitle() {
		return findDriver().getTitle();
	}
	
	public String getSource() {
		return findDriver().getPageSource();
	}
	
	public Browser (WebDriver driver) {
		this.driver = driver;
	}
	
	public Browser (WebDriver driver, String name) {
		this.driver = driver;
		this.name = name;
	}
	
	public WebDriver findDriver() {
		if ( driver == null ) {
			throw new NoWebDriverException("There is no WebDriver instance for browser");
		}
		return driver;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebElement findElement(By locator) {
		return findDriver().findElement(locator);
	}
	
	public List<WebElement> findElements(By locator) {
		return findDriver().findElements(locator);
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PageFactory getPageFactory() {
		return pageFactory;
	}

	public void setPageFactory(PageFactory pageFactory) {
		this.pageFactory = pageFactory;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Page> T page(Class<T> pageClass) {
		if( pageFactory == null ) {
			throw new InvalidPageException("There is no page factory specified");
		}
		Page page = pageFactory.createPage(pageClass);
		page.setBrowser(this);
		return (T) page;
	}

	public SelenusActionListener getSelenusActionListener() {
		return selenusActionListener;
	}

	public void setSelenusActionListener(SelenusActionListener selenusActionListener) {
		this.selenusActionListener = selenusActionListener;
	}

	/**
	 * Takes screenshot of a browser in PNG format
	 * @return
	 */
	public File takeScreenshot() {
		return ((TakesScreenshot)findDriver()).getScreenshotAs(OutputType.FILE);
	}

}
