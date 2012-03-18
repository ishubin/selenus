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
package net.mindengine.selenus.tests.sampleapp;

import net.mindengine.selenus.sampleapp.pages.ArticlesPage;
import net.mindengine.selenus.sampleapp.pages.MainPage;
import net.mindengine.selenus.web.Browser;
import net.mindengine.selenus.web.factory.PageFactory;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SampleAppTest {
	public final static String SAMPLE_APP_URL = "http://localhost:10000";
	
	public static Browser browser;
	
	@BeforeClass
	public static void init() {
		browser = new Browser(new FirefoxDriver());
		browser.setPageFactory(PageFactory.defaultPageFactory());
	}
	
	@AfterClass
	public static void close() {
		browser.close();
	}
	
	@Before
	public void beforeTest() {
		browser.open(SAMPLE_APP_URL);
	}
	
	@Test
	public void loginPopupSuccessfullyUsesElements() {
		MainPage mainPage = browser.page(MainPage.class);
		Assert.assertEquals("SELENUS SAMPLE WEBSITE", mainPage.getHeaderLink().getText());
		
		mainPage.getLoginLink().clickAndWaitFor(mainPage.getLoginPopup());
		
		mainPage.getLoginPopup().getLoginTextField().type("testuser");
		mainPage.getLoginPopup().getPasswordTextField().type("test123");
		
		mainPage.getLoginPopup().getLoginButton().click();
	}
	
	@Test
	public void readsArticlesUsingPageObjectList() {
		ArticlesPage page = browser.page(ArticlesPage.class);
		page.getMenu().getArticlesLink().click();
		
		Assert.assertEquals(3, page.getArticles().size());
		Assert.assertEquals("Title 1", page.getArticles().get(0).getTitleLabel().getText());
		
		Assert.assertEquals("Title 2", page.getArticles().get(1).getTitleLabel().getText());
		
		Assert.assertEquals("Title 3", page.getArticles().get(2).getTitleLabel().getText());
		
		String[] expectedAuthors = new String[]{"Author 1", "Author 2", "Author 3"};
		
		int i=0;
		for (ArticlesPage.ArticleLayout article : page.getArticles()) {
			Assert.assertEquals(expectedAuthors[i], article.getAuthorLink().getText());
			Assert.assertEquals(page.getArticles(), article.getParentLayout());
			Assert.assertEquals(article, article.getDateLabel().getParentLayout());
			i++;
		}
		
	}

}
