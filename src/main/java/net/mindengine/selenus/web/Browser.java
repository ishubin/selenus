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

import java.util.List;

import net.mindengine.selenus.exceptions.NoWebDriverException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Browser {
	private String name;
	private WebDriver driver;
	private String type;
	
	public Browser () {
	}
	
	public void open(String url) {
		findDriver().get(url);
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

}
