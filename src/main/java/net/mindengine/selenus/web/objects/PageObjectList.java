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

import java.util.Iterator;
import java.util.List;

import net.mindengine.selenus.exceptions.InvalidPageObjectException;
import net.mindengine.selenus.web.factory.PageObjectFactory;

import org.openqa.selenium.WebElement;
/**
 * Represents a list of page objects like: buttons, links, layouts etc.
 * @author ishubin
 *
 * @param <T>
 */
public class PageObjectList<T extends AbstractPageObject> extends WebLayout implements Iterable<T> {

	private Class<T> elementType;
	
	public PageObjectList (Class<T> elementType) {
		this.setElementType(elementType);
	}
	
	private List<WebElement> _webDriverElements;
	private PageObjectFactory pageObjectFactory;
	
	public synchronized List<WebElement> findWebDriverElements () {
		if( _webDriverElements == null ) {
			if ( getParentLayout() != null) {
				_webDriverElements = getParentLayout().findWebDriverElement().findElements(getLocator());
			}
			else {
				_webDriverElements = getPage().findElements(getLocator());
			}
		}
		return _webDriverElements;
	}
	
	public int size() {
		return findWebDriverElements().size();
	}
	
	@SuppressWarnings("unchecked")
	public T get(int index) {
		WebElement element = findWebDriverElements().get(index);
		AbstractPageObject pageObject =  getPageObjectFactory().createPageObject(this, getElementType());
		pageObject.setWebDriverElement(element);
		return (T) pageObject;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new PageObjectListIterator<T>(this, pageObjectFactory, getElementType());
	}
	
	public PageObjectFactory findPageObjectFactory() {
		if (this.pageObjectFactory == null ) {
			throw new InvalidPageObjectException("Cannot instantiate child objects. PageObjectFactory should be provided");
		}
		return this.pageObjectFactory;
	}

	public PageObjectFactory getPageObjectFactory() {
		return pageObjectFactory;
	}

	public void setPageObjectFactory(PageObjectFactory pageObjectFactory) {
		this.pageObjectFactory = pageObjectFactory;
	}

	public Class<T> getElementType() {
		return elementType;
	}

	public void setElementType(Class<T> elementType) {
		this.elementType = elementType;
	}

	@Override
	public String getTypeString() {
		return "list";
	}

}