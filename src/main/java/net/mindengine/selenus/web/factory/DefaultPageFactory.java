package net.mindengine.selenus.web.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


import net.mindengine.selenus.annotations.Named;
import net.mindengine.selenus.exceptions.InvalidPageException;
import net.mindengine.selenus.web.Page;
import net.mindengine.selenus.web.objects.AbstractPageObject;
import net.mindengine.selenus.web.objects.PageObjectActionListener;
import net.mindengine.selenus.web.objects.PageObjectList;

public class DefaultPageFactory extends PageFactory {
	
	public DefaultPageFactory(PageObjectFactory pageObjectFactory) {
		super(pageObjectFactory);
	}
	
	@Override
	public <T> T createPage(Class<T> pageClass) {
		return this.createPage(pageClass, null);
	}

	@Override
	public <T> T createPage(Class<T> pageClass, PageObjectActionListener pageObjectActionListener) {
		try {
			
			Constructor<T> constructor = pageClass.getConstructor();
			T page = constructor.newInstance();
			
			Field[] fields = pageClass.getDeclaredFields();
			
			for( Field field : fields ) {
				if ( AbstractPageObject.class.isAssignableFrom(field.getType()) ) {
					AbstractPageObject pageObject = (AbstractPageObject) getPageObjectFactory().createPageObject(field);
					if ( pageObject instanceof PageObjectList ) {
						PageObjectList<?> list = (PageObjectList<?>) pageObject;
						list.setPageObjectFactory(getPageObjectFactory());
					}
					
					this.setPageObject(page, field, pageObject);
					pageObject.setPageObjectActionListener(pageObjectActionListener);
				}
			}
			
			Named named = pageClass.getAnnotation(Named.class);
			if ( named != null ) {
				if ( Page.class.isAssignableFrom(pageClass) ) {
					Page _page = (Page) page;
					_page.setName(named.value());
				}
			}
			
			return page;
		} catch (Exception e) {
			throw new InvalidPageException("Page " + pageClass.getName() + " doesn't have a default constructor", e);
		}
	}

}
