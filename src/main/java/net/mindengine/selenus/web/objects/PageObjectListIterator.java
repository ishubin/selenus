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

import net.mindengine.selenus.web.factory.PageObjectFactory;

import org.openqa.selenium.WebElement;

public class PageObjectListIterator<T extends AbstractPageObject> implements Iterator<T> {

	private PageObjectList<T> _pageObjectList;
	private PageObjectFactory _pageObjectFactory;
	private Iterator<WebElement> _webDriverIterator;
	private Class<T> _elementType;
	private int _index = 0;
	
	protected PageObjectListIterator(PageObjectList<T> pageObjectList, PageObjectFactory pageObjectFactory, Class<T> elementType) {
		this._pageObjectList = pageObjectList;
		this._pageObjectFactory = pageObjectFactory;
		this._elementType = elementType;
	}

	protected synchronized Iterator<WebElement> webDriverIterator() {
		if ( _webDriverIterator == null) {
			_webDriverIterator = _pageObjectList.findWebDriverElements().iterator();
		}
		return _webDriverIterator;
	}
	
	@Override
	public boolean hasNext() {
		return webDriverIterator().hasNext();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T next() {
		_index++;
		WebElement element = webDriverIterator().next();
		AbstractPageObject pageObject =  this._pageObjectFactory.createPageObject(_pageObjectList, this._elementType);
		pageObject.setWebDriverElement(element);
		pageObject.setName("#" + _index + " item");
		return (T) pageObject;
	}

	@Override
	public void remove() {
		//Not doing anything here as this iterator should be in read-only mode
		throw new IllegalArgumentException("Cannot remove elements of page object list");
	}

}
