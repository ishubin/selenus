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
