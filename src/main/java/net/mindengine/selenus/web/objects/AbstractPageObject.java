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
package net.mindengine.selenus.web.objects;

import net.mindengine.selenus.exceptions.InvalidPageObjectException;
import net.mindengine.selenus.web.Browser;
import net.mindengine.selenus.web.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class AbstractPageObject {

	private String name;
	private By locator;
	private Page page;
	private WebLayout parentLayout;
	private PageObjectActionListener pageObjectActionListener;
	/**
	 * Used in findWebDriverElement method so it is instantiated only once
	 */
	private WebElement _webDriverElement;
	
	protected void setWebDriverElement(WebElement webDriverElement) {
		this._webDriverElement = webDriverElement;
	}
	
	public Browser findBrowser() {
		return findPage().getBrowser();
	}
	
	public void dragAndDrop(AbstractPageObject target) {
		Actions builder = new Actions(findBrowser().findDriver());
		builder.dragAndDrop(findWebDriverElement(), target.findWebDriverElement()).build().perform();
	}
	
	public void dragAndDrop(int xOffset, int yOffset) {
		Actions builder = new Actions(findBrowser().findDriver());
		builder.dragAndDropBy(findWebDriverElement(), xOffset, yOffset).build().perform();
	}
	
	public void click() {
		findWebDriverElement().click();
	}
	
	public Dimension getSize() {
		return findWebDriverElement().getSize();
	}
	
	public boolean isAvailable() {
		try {
			findWebDriverElement();
		}
		catch (NoSuchElementException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean isDisplayed() {
		if ( isAvailable() ) {
			return findWebDriverElement().isDisplayed();
		}
		else return false;
	}
	
	/**
	 * Searches for {@link WebElement} corresponding to this page object.
	 * In case if the page object is part of {@link WebLayout} - it searches in scope of layout, otherwise - in scope of page
	 * @return web-element of WebDriver which corresponds to this page object
	 */
	public synchronized WebElement findWebDriverElement() {
		if ( _webDriverElement == null) {
			_webDriverElement = instantiateWebDriverElement();
		}
		return _webDriverElement;
	}
	
	private WebElement instantiateWebDriverElement() {
		if ( locator == null ) {
			throw new InvalidPageObjectException("There is no locator specified for webelement " + getClass());
		}
		
		if ( parentLayout != null ) {
			return parentLayout.findWebDriverElement().findElement(getLocator());
		}
		else {
			return findPage().findElement(getLocator());
		}
	}

	protected Page findPage() {
		if( parentLayout != null ) {
			return parentLayout.findPage();
		}
		else {
			if (page == null) {
				throw new InvalidPageObjectException("There is no page specified for webelement " + getClass());
			}
			return page;
		}
	}
	
	/**
	 * Returns HTML tags attribute value
	 * @param name Name of HTML tag attribute
	 * @return Returns value of HTML tag or null if there is no attribute with specified name
	 */
	public String getTagAttribute(String name) {
		return this.findWebDriverElement().getAttribute(name);
	}
	
	/**
	 * Return type of the page object which can be later used in reporting
	 * @return
	 */
	public abstract String getTypeString();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public By getLocator() {
		return locator;
	}
	public void setLocator(By locator) {
		this.locator = locator;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public WebLayout getParentLayout() {
		return parentLayout;
	}
	public void setParentLayout(WebLayout parentLayout) {
		this.parentLayout = parentLayout;
	}

	public PageObjectActionListener getPageObjectActionListener() {
		return pageObjectActionListener;
	}

	public void setPageObjectActionListener(PageObjectActionListener pageObjectActionListener) {
		this.pageObjectActionListener = pageObjectActionListener;
	}
}
