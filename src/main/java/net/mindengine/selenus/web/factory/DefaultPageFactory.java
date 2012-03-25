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
package net.mindengine.selenus.web.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;

import org.openqa.selenium.support.FindBy;

import net.mindengine.oculus.experior.ClassUtils;
import net.mindengine.selenus.annotations.Named;
import net.mindengine.selenus.exceptions.InvalidPageException;
import net.mindengine.selenus.web.Page;
import net.mindengine.selenus.web.objects.AbstractPageObject;
import net.mindengine.selenus.web.objects.SelenusActionListener;
import net.mindengine.selenus.web.objects.PageObjectList;
import net.mindengine.selenus.web.verificators.VerificatorProvider;

public class DefaultPageFactory extends PageFactory {
	
	public DefaultPageFactory(PageObjectFactory pageObjectFactory) {
		super(pageObjectFactory);
	}
	
	@Override
	public <T> T createPage(Class<T> pageClass) {
		return this.createPage(pageClass, null, null);
	}

	@Override
	public <T> T createPage(Class<T> pageClass, SelenusActionListener pageObjectActionListener, VerificatorProvider verificatorProvider) {
		try {
			Constructor<T> constructor = pageClass.getConstructor();
			T page = constructor.newInstance();
			
			Collection<Field> fields = ClassUtils.getAllFields(pageClass);
			
			for( Field field : fields ) {
				if ( field.getAnnotation(FindBy.class) != null ) {
					if ( AbstractPageObject.class.isAssignableFrom(field.getType()) ) {
						AbstractPageObject pageObject = (AbstractPageObject) getPageObjectFactory().createPageObject(field);
						if ( pageObject instanceof PageObjectList ) {
							PageObjectList<?> list = (PageObjectList<?>) pageObject;
							list.setPageObjectFactory(getPageObjectFactory());
						}
						
						ClassUtils.setFieldValue(field, page, pageObject);
						
						pageObject.setPageObjectActionListener(pageObjectActionListener);
						pageObject.setVerificatorProvider(verificatorProvider);
						
						if ( Page.class.isAssignableFrom(pageClass) ) {
							pageObject.setPage((Page) page);
						}
					}
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
