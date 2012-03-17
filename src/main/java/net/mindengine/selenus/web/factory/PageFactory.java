package net.mindengine.selenus.web.factory;

import java.lang.reflect.Field;

import net.mindengine.selenus.exceptions.InvalidPageException;
import net.mindengine.selenus.web.objects.PageObjectActionListener;

public abstract class PageFactory {

	private PageObjectFactory pageObjectFactory;
	public PageFactory(PageObjectFactory pageObjectFactory) {
		this.pageObjectFactory = pageObjectFactory;
	}
	
	public abstract <T> T createPage(Class<T> pageClass);

	public abstract <T> T createPage(Class<T> pageClass, PageObjectActionListener listener);

	public PageObjectFactory getPageObjectFactory() {
		return pageObjectFactory;
	}


	public void setPageObjectFactory(PageObjectFactory pageObjectFactory) {
		this.pageObjectFactory = pageObjectFactory;
	}
	
	protected void setPageObject(Object page, Field field, Object value) {
		field.setAccessible(true);
		try {
			field.set(page, value);
		} catch (Exception e) {
			throw new InvalidPageException("Cannot create page object for field '"+field.getName()+"' of "+field.getDeclaringClass().getName(), e);
		}
	}
}
