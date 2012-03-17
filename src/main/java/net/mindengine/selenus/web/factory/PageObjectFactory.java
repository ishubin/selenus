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
