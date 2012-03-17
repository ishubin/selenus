package net.mindengine.selenus.tests;

import java.lang.reflect.Field;

import net.mindengine.selenus.samplepages.SamplePage;
import net.mindengine.selenus.web.factory.DefaultPageObjectFactory;
import net.mindengine.selenus.web.factory.PageObjectFactory;
import net.mindengine.selenus.web.objects.AbstractPageObject;
import net.mindengine.selenus.web.objects.Image;
import net.mindengine.selenus.web.objects.Label;
import net.mindengine.selenus.web.objects.Link;
import net.mindengine.selenus.web.objects.PageObjectList;
import net.mindengine.selenus.web.objects.WebLayout;
import net.mindengine.selenus.web.objects.form.Button;
import net.mindengine.selenus.web.objects.form.Checkbox;
import net.mindengine.selenus.web.objects.form.FileUpload;
import net.mindengine.selenus.web.objects.form.FormList;
import net.mindengine.selenus.web.objects.form.RadioButton;
import net.mindengine.selenus.web.objects.form.TextField;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By.ByXPath;

public class DefaultPageObjectFactoryTest {

	public static PageObjectFactory factory;
	public static Class<?>[] providedPageObjects = new Class<?>[]{Label.class, TextField.class, Button.class, Link.class, FileUpload.class, Image.class, Checkbox.class, RadioButton.class, FormList.class};
	
	@BeforeClass
	public static void initialize() {
		factory = new DefaultPageObjectFactory();
	}
	
	
	@Test
	public void createsSimplePageObjectByItsClass() {
		TextField textField = factory.createPageObject(TextField.class);
		Assert.assertNotNull(textField);
		
		for(Class<?> pageObjectClass : providedPageObjects) {
			Object instantiatePageObject = factory.createPageObject(pageObjectClass);
			Assert.assertNotNull(instantiatePageObject);
		}
	}
	
	@Test
	public void createsPageObjectList () {
		PageObjectList<?> pageObjectList = factory.createPageObject(PageObjectList.class);
	    Assert.assertNotNull(pageObjectList);
	    Assert.assertNull(pageObjectList.getElementType());
	}
	
	@Test
	public void createsSimplePageObjectInsideWebLayoutByItsClass() {
		WebLayout layout = factory.createPageObject(WebLayout.class);
		
		TextField textField = factory.createPageObject(layout, TextField.class);
		Assert.assertNotNull(textField);
		
		for(Class<?> pageObjectClass : providedPageObjects) {
			AbstractPageObject pageObject = (AbstractPageObject) factory.createPageObject(layout, pageObjectClass);
			Assert.assertNotNull(pageObject);
			Assert.assertEquals(layout, pageObject.getParentLayout());
		}
	}
	
	@Test
	public void createsPageObjectWithFieldAnnotations() throws SecurityException, NoSuchFieldException {
		Field textFieldField = SamplePage.class.getDeclaredField("textField");
		
		TextField textField = (TextField) factory.createPageObject(textFieldField);
		Assert.assertNotNull(textField);
		Assert.assertEquals("Sample text-field", textField.getName());
		Assert.assertNotNull(textField.getLocator());
		Assert.assertTrue(textField.getLocator() instanceof ByXPath);
		
	}
	
	@Test
	public void createsPageObjectListWithFieldAnnotations() throws SecurityException, NoSuchFieldException {
		Field layoutsField = SamplePage.class.getDeclaredField("layouts");
		
		PageObjectList<?> layouts = factory.createPageObject(layoutsField);
		Assert.assertNotNull(layouts);
		Assert.assertEquals("layouts", layouts.getName());
		Assert.assertNotNull(layouts.getLocator());
		Assert.assertTrue(layouts.getLocator() instanceof ByXPath);
		Assert.assertEquals(WebLayout.class, layouts.getElementType());
		
	}
	
	@Test
	public void createsPageObjectWithFieldAnnotationsInsideWebLayout() throws SecurityException, NoSuchFieldException {
		WebLayout layout = factory.createPageObject(WebLayout.class);
		Field textFieldField = SamplePage.class.getDeclaredField("textField");
		
		TextField textField = (TextField) factory.createPageObject(layout, textFieldField);
		Assert.assertNotNull(textField);
		Assert.assertEquals("Sample text-field", textField.getName());
		Assert.assertNotNull(textField.getLocator());
		Assert.assertTrue(textField.getLocator() instanceof ByXPath);
		Assert.assertEquals(layout, textField.getParentLayout());
		
	}
}
