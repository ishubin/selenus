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
