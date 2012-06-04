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

import net.mindengine.oculus.experior.reporter.ReportDesign;
import net.mindengine.selenus.exceptions.InvalidPageObjectException;
import net.mindengine.selenus.web.Browser;
import net.mindengine.selenus.web.Page;
import net.mindengine.selenus.web.verificators.DefaultPageObjectVerificatorContainer;
import net.mindengine.selenus.web.verificators.VerificatorProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class AbstractPageObject {

	public static final int DEFAULT_WAIT_TIMEOUT = 30;
	private String name;
	private By locator;
	private Page page;
	private WebLayout parentLayout;
	private SelenusActionListener pageObjectActionListener;
	private VerificatorProvider verificatorProvider;
	
	private Boolean useCache = false;
	
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
	
	public String getText() {
		return findWebDriverElement().getText();
	}
	
	public void dragAndDrop(AbstractPageObject target) {
		Actions builder = new Actions(findBrowser().findDriver());
		builder.dragAndDrop(findWebDriverElement(), target.findWebDriverElement()).build().perform();
		
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.dragAndDrop(this, target);
		}
	}
	

	public void dragAndDrop(int xOffset, int yOffset) {
		Actions builder = new Actions(findBrowser().findDriver());
		builder.dragAndDropBy(findWebDriverElement(), xOffset, yOffset).build().perform();
		
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.dragAndDropBy(this, xOffset, yOffset);
		}
	}
	
	public void click() {
		findWebDriverElement().click();
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.click(this);
		}
	}
	
	public void clickAndWaitFor(AbstractPageObject pageObject) {
		findWebDriverElement().click();
		pageObject.waitForItToAppear();
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.click(this);
		}
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
	    if ( _webDriverElement != null ) {
	        return _webDriverElement;
	    }
	    
	    WebElement webElement = instantiateWebDriverElement();
	    if ( getUseCache() ) {
	        this._webDriverElement = webElement;
	    }
	    return webElement;
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
	
	public Page searchForPage() {
		if( parentLayout != null ) {
			return parentLayout.findPage();
		}
		else {
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
	public abstract String getType();
	
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

	public SelenusActionListener getPageObjectActionListener() {
		return pageObjectActionListener;
	}

	public void setPageObjectActionListener(SelenusActionListener pageObjectActionListener) {
		this.pageObjectActionListener = pageObjectActionListener;
	}
	
	/**
	 * Waits for 30 seconds until the page object becomes visible
	 */
	public void waitForItToAppear() {
		waitForItToAppear(DEFAULT_WAIT_TIMEOUT);
	}
	
	/**
	 * Waits for specified amount of time until the page object becomes visible
	 * @param timeoutSeconds Time in seconds after which {@link TimeoutException} will be thrown
	 */
	public void waitForItToAppear(int timeoutSeconds) {
		if ( timeoutSeconds < 1 ) {
			throw new IllegalArgumentException("Timeout should not be negeative or zero");
		}
		while ( timeoutSeconds-- > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if ( this.isDisplayed() ) {
				return;
			}
		}
		throw new TimeoutException(this.toString() + " didn't appear on page");
	}
	
	
	public String getFullName() {
		StringBuilder builder = new StringBuilder();
		if ( this.getName() != null ) {
			builder.append(ReportDesign.bold(this.getName())).append(" ");
		}
		else { 
			builder.append("Unknown");
		}
		builder.append(this.getType());
		if ( this.parentLayout != null ) {
			builder.append(" in ").append(this.getParentLayout().getFullName());
		}
		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		if ( name != null) {
			text.append("\"").append(name).append("\" ");
		}
		text.append(this.getType());
		return super.toString();
	}
	
	public DefaultPageObjectVerificatorContainer verifyThat(){
		return new DefaultPageObjectVerificatorContainer(false, this, findVerificatorProvider());
	}
	
	public DefaultPageObjectVerificatorContainer assertThat(){
		return new DefaultPageObjectVerificatorContainer(true, this, findVerificatorProvider());
	}

	public VerificatorProvider getVerificatorProvider() {
		return verificatorProvider;
	}

	public void setVerificatorProvider(VerificatorProvider verificatorProvider) {
		this.verificatorProvider = verificatorProvider;
	}
	
	public VerificatorProvider findVerificatorProvider() {
		if ( verificatorProvider != null ) {
			return verificatorProvider;
		}
		else if (getParentLayout() != null) {
			return getParentLayout().findVerificatorProvider(); 
		}
		else {
			throw new InvalidPageObjectException("There is no verificator provider specified for page object: " + toString());
		}
	}
	
	public SelenusActionListener findPageObjectActionListener() {
		if ( this.pageObjectActionListener != null ) {
			return this.pageObjectActionListener;
		}
		else if ( this.getParentLayout() != null) {
			return getParentLayout().findPageObjectActionListener();
		}
		return null;
	}

    public Boolean getUseCache() {
        return useCache;
    }

    public void setUseCache(Boolean useCache) {
        this.useCache = useCache;
    }
	
}
