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
				return getParentLayout().findWebDriverElement().findElements(getLocator());
			}
			else {
				getPage().findElements(getLocator());
			}
		}
		return _webDriverElements;
	}
	
	public int size() {
		return findWebDriverElements().size();
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


}
