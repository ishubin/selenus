package net.mindengine.selenus.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.mindengine.selenus.samplepages.SamplePage;
import net.mindengine.selenus.web.factory.DefaultPageFactory;
import net.mindengine.selenus.web.factory.DefaultPageObjectFactory;
import net.mindengine.selenus.web.factory.PageFactory;
import net.mindengine.selenus.web.objects.PageObjectActionListener;
import net.mindengine.selenus.web.objects.WebLayout;
import net.mindengine.selenus.web.report.OculusPageObjectActionListener;

public class DefaultPageFactoryTest {

	private static PageFactory factory;
	
	@BeforeClass
	public static void initialize() {
		factory = new DefaultPageFactory( new DefaultPageObjectFactory());
	}
	
	@Test
	public void createsPage() {
		SamplePage page = factory.createPage(SamplePage.class);
		
		Assert.assertNotNull(page);
		Assert.assertEquals("Some sample page", page.getName());
		
		Assert.assertNotNull(page.getTextField());
		Assert.assertEquals("Sample text-field", page.getTextField().getName());
		
		Assert.assertNotNull(page.getLayouts());
		Assert.assertEquals("layouts", page.getLayouts().getName());
		Assert.assertEquals(WebLayout.class, page.getLayouts().getElementType());
		Assert.assertEquals(factory.getPageObjectFactory(), page.getLayouts().getPageObjectFactory());
	}
	
	
	@Test
	public void createsPageWithActionListener() {
		PageObjectActionListener listener = new OculusPageObjectActionListener();
		SamplePage page = factory.createPage(SamplePage.class, listener);
		
		Assert.assertNotNull(page);
		Assert.assertEquals("Some sample page", page.getName());
		
		Assert.assertNotNull(page.getTextField());
		Assert.assertEquals(listener, page.getTextField().getPageObjectActionListener());
		Assert.assertEquals("Sample text-field", page.getTextField().getName());
		
		Assert.assertNotNull(page.getLayouts());
		Assert.assertEquals("layouts", page.getLayouts().getName());
		Assert.assertEquals(WebLayout.class, page.getLayouts().getElementType());
		Assert.assertNotNull(page.getLayouts().getPageObjectFactory());
		Assert.assertEquals(listener, page.getLayouts().getPageObjectActionListener());
		Assert.assertEquals(factory.getPageObjectFactory(), page.getLayouts().getPageObjectFactory());
	}
	
}
