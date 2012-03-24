package net.mindengine.selenus.sampleapp;

import net.mindengine.oculus.experior.annotations.Action;
import net.mindengine.oculus.experior.annotations.DataSource;
import net.mindengine.oculus.experior.annotations.Test;
import net.mindengine.selenus.sampleapp.pages.ArticlesPage;
import net.mindengine.selenus.sampleapp.pages.MainPage;
import net.mindengine.selenus.test.SelenusTest;
import net.mindengine.selenus.tests.sampleapp.SampleAppTest;
import net.mindengine.selenus.web.Browser;

@Test(name="Selenus Sample test", project="SELENUS-SAMPLE-WEBSITE")
public class SelenusSample extends SelenusTest {

	@DataSource(provider="browser", type="firefox")
	protected Browser browser;
	
	@Action(name="Open Home page", next="checkArticles")
	public void openArticlesPage(@DataSource(dependencies={"browser"}) MainPage mainPage) {
		browser.open(SampleAppTest.SAMPLE_APP_URL);
		mainPage.getHeaderLink().verifyThat().text().is("SELENUS SAMPLE WEBSITE");
		mainPage.getMenu().getArticlesLink().click();
	}
	
	@Action(name="Check articles")
	public void checkArticles(@DataSource(dependencies={"browser"}) ArticlesPage articlesPage) {
		//articlesPage.getArticles().verify().Size().is(3);
		articlesPage.getArticles().get(0).getTitleLabel().verifyThat().text().is("Title 1");
		articlesPage.getArticles().get(0).getTitleLabel().assertThat().text().is("Title 2");
	}
}
