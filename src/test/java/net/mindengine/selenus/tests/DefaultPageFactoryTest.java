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
package net.mindengine.selenus.tests;

import net.mindengine.selenus.sampleapp.pages.MainPage;
import net.mindengine.selenus.samplepages.PageObjectListenerMock;
import net.mindengine.selenus.samplepages.SamplePage;
import net.mindengine.selenus.web.factory.DefaultPageFactory;
import net.mindengine.selenus.web.factory.DefaultPageObjectFactory;
import net.mindengine.selenus.web.factory.PageFactory;
import net.mindengine.selenus.web.objects.SelenusActionListener;
import net.mindengine.selenus.web.objects.WebLayout;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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
		SelenusActionListener listener = new PageObjectListenerMock();
		SamplePage page = factory.createPage(SamplePage.class, listener, null);
		
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
	
	@Test
	public void createsPageWithComplexWebLayouts() {
		MainPage page = factory.createPage(MainPage.class);
		Assert.assertNotNull(page);
		Assert.assertNotNull(page.getLoginPopup());
		Assert.assertNotNull(page.getLoginPopup().getLoginTextField());
		Assert.assertEquals(page.getLoginPopup(), page.getLoginPopup().getLoginTextField().getParentLayout());
	}
}
