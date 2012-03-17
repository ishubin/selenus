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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import net.mindengine.selenus.annotations.Named;
import net.mindengine.selenus.exceptions.InvalidPageObjectException;
import net.mindengine.selenus.web.objects.AbstractPageObject;
import net.mindengine.selenus.web.objects.PageObjectList;
import net.mindengine.selenus.web.objects.WebLayout;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.Annotations;

public class DefaultPageObjectFactory extends PageObjectFactory {

	@Override
	public <T> T createPageObject(Class<T> pageObjectClass) {
		if ( pageObjectClass == null) {
			throw new NullPointerException("Page object class is not provided");
		}
		
		try {
			if ( PageObjectList.class.isAssignableFrom(pageObjectClass)) {
				return pageObjectClass.getConstructor(Class.class).newInstance(new Object[]{null});
			}
			else {
				return pageObjectClass.getConstructor().newInstance();
			}
		} catch (Exception e) {
			throw new InvalidPageObjectException("Cannot get default constructor for page object: "+ pageObjectClass, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T createPageObject(WebLayout layout, Class<T> pageObjectClass) {
		AbstractPageObject pageObject = (AbstractPageObject) this.createPageObject(pageObjectClass);
		pageObject.setParentLayout(layout);
		return (T) pageObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T createPageObject(Field field) {
		AbstractPageObject pageObject = (AbstractPageObject) this.createPageObject(field.getType());
		
		Named named = field.getAnnotation(Named.class);
		if( named != null ) {
			pageObject.setName(named.value());
		}
		else {
			pageObject.setName(field.getName());
		}
		
		if( field.getAnnotation(FindBy.class) != null || field.getAnnotation(FindBy.class) != null ) {
			Annotations annotations = new Annotations(field);
			pageObject.setLocator(annotations.buildBy());
		}
		
		//Trying to get generic type which identifies type of elements in page object list
		if ( PageObjectList.class.isAssignableFrom(field.getType()) ) {
			setGenericTypeAsElementTypeInPageObjectList(field, pageObject);
		}
		
		return (T) pageObject;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setGenericTypeAsElementTypeInPageObjectList(Field field,
			AbstractPageObject pageObject) {
		Type type = field.getGenericType();
		if ( type != null && type instanceof ParameterizedType ) {
			ParameterizedType parameterizedType = (ParameterizedType)type;
			Type[] types = parameterizedType.getActualTypeArguments();
			if ( types.length > 0) {
				PageObjectList pageObjectList = (PageObjectList)pageObject;
				pageObjectList.setElementType((Class) types[0]);
			}
			else throw new InvalidPageObjectException("PageObjectList element '" + field.getName() + "' is defined with incorrect generic type"); 
		}
		else {
			throw new InvalidPageObjectException("PageObjectList element '" + field.getName() + "' is defined without generic type");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T createPageObject(WebLayout layout, Field field) {
		AbstractPageObject pageObject = (AbstractPageObject) this.createPageObject(field);
		pageObject.setParentLayout(layout);
		return (T) pageObject;
	}
}
