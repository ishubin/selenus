package net.mindengine.selenus.web.objects;

import java.util.Iterator;

import net.mindengine.selenus.web.factory.PageObjectFactory;

import org.openqa.selenium.WebElement;

public class PageObjectListIterator<T extends AbstractPageObject> implements Iterator<T> {

	private PageObjectList<T> _pageObjectList;
	private PageObjectFactory _pageObjectFactory;
	private Iterator<WebElement> _webDriverIterator;
	private Class<T> _elementType;
	
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
		WebElement element = webDriverIterator().next();
		AbstractPageObject pageObject =  this._pageObjectFactory.createPageObject(_pageObjectList, this._elementType);
		pageObject.setWebDriverElement(element);
		return (T) pageObject;
	}

	@Override
	public void remove() {
		//Not doing anything here as this iterator should be in read-only mode
		throw new IllegalArgumentException("Cannot remove elements of page object list");
	}

}
