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

import net.mindengine.selenus.web.objects.WebLayout;

/**
 * Used for instantiating page objects
 * @author ishubin
 *
 */
public abstract class PageObjectFactory {

	/**
	 * Creates and returns an instance of page object for a specified class
	 * @param pageObjectClass Type of page object
	 * @return New instance of page object
	 */
	public abstract <T> T createPageObject(Class<T> pageObjectClass);
	
	/**
	 * Creates and returns an instance of page object which belongs to a specified web-layout
	 * @param layout Web-layout in which page object is located 
	 * @param pageObjectClass Type of page object
	 * @return New instance of page object
	 */
	public abstract <T> T createPageObject(WebLayout layout, Class<T> pageObjectClass);
	
	/**
	 * Creates and returns an instance of page object for a specified class
	 * @param pageObjectClass Type of page object
	 * @return New instance of page object
	 */
	public abstract <T> T createPageObject(Field field);
	
	/**
	 * Creates and returns an instance of page object which belongs to a specified web-layout
	 * @param layout Web-layout in which page object is located 
	 * @param field Field of page class which represents this page object
	 * @return New instance of page object
	 */
	public abstract <T> T createPageObject(WebLayout layout, Field field);
}
