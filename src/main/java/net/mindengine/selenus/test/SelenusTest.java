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
package net.mindengine.selenus.test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import net.mindengine.oculus.experior.annotations.DataProvider;
import net.mindengine.oculus.experior.annotations.events.OnException;
import net.mindengine.oculus.experior.framework.report.OculusReportFileUploader;
import net.mindengine.oculus.experior.framework.test.OculusTest;
import net.mindengine.oculus.experior.reporter.ReportDesign;
import net.mindengine.oculus.experior.reporter.ReportIcon;
import net.mindengine.oculus.experior.test.descriptors.TestInformation;
import net.mindengine.oculus.experior.test.resolvers.dataprovider.DataSourceInformation;
import net.mindengine.selenus.exceptions.SelenusException;
import net.mindengine.selenus.web.Browser;
import net.mindengine.selenus.web.Page;
import net.mindengine.selenus.web.factory.DefaultPageFactory;
import net.mindengine.selenus.web.factory.DefaultPageObjectFactory;
import net.mindengine.selenus.web.factory.PageFactory;
import net.mindengine.selenus.web.objects.SelenusActionListener;
import net.mindengine.selenus.web.report.OculusSelenusActionListener;
import net.mindengine.selenus.web.verificators.OculusVerificatorProvider;
import net.mindengine.selenus.web.verificators.VerificatorProvider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.iphone.IPhoneDriver;

import com.thoughtworks.selenium.SeleniumException;

/**
 * Base test for easier use of selenus with oculus reporter.
 * Contains providers for browsers and pages
 * @author ishubin
 *
 */
public class SelenusTest extends OculusTest {

	public static final String FIREFOX = "firefox";
	public static final String CHROME = "chrome";
	public static final String ANDROID = "android";
	public static final String IPHONE = "iphone";
	public static final String IE = "firefox";
	public static final String HTMLUNIT = "htmlunit";
	public static final String BROWSER = "browser";
	
	private PageFactory pageFactory;
	private SelenusActionListener selenusActionListener;
	private VerificatorProvider verificatorProvider;
	
	/**
	 * Default type of browser which is defined in suite
	 */
	private String defaultBrowserType = FIREFOX;
	
	private List<Browser> _usedBrowsers = new LinkedList<Browser>();
	
	
	public void takeScreenshotOfAllBrowsers() {
		for ( Browser browser : _usedBrowsers ) {
			takeScreenshot(browser);
		}
	}
	
	public void takeScreenshot(Browser browser) {
		File screenShotFile = browser.takeScreenshot();
		OculusReportFileUploader uploader = OculusReportFileUploader.getStandardFileUploader();
		
		try {
			String fileId = uploader.upload(screenShotFile);
			String browserName = browser.getType();
			if ( browser.getName() != null ) {
				browserName = browser.getName() + " " + browserName;
			}
			report.info("Screenshot in " + ReportDesign.bold(browserName) + " browser").icon(ReportIcon.SCREENSHOT).details(ReportDesign.screenshot(fileId));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onBeforeTest(TestInformation testInformation) throws Exception {
		super.onBeforeTest(testInformation);
		
		if( suite != null ) {
			if ( suite.getParameters() != null) {
				if ( suite.getParameters().containsKey(BROWSER) ) {
					setDefaultBrowserType(suite.getParameters().get(BROWSER));
				}
			}
		}
		if ( pageFactory == null ) {
		    pageFactory = new DefaultPageFactory(new DefaultPageObjectFactory());
		}
		
		if ( selenusActionListener == null ) {
		    selenusActionListener = new OculusSelenusActionListener(getReport());
		}
		
		if ( verificatorProvider == null ) {
		    verificatorProvider = new OculusVerificatorProvider(getReport());
		}
	}
	
	
	@OnException({SelenusException.class, AssertionError.class, SeleniumException.class})
	protected void takeScreenshotsOnError() {
		takeScreenshotOfAllBrowsers();
	}
	
	@Override
	public void onAfterTest(TestInformation testInformation) throws Exception {
		super.onAfterTest(testInformation);
		
		for ( Browser browser : _usedBrowsers) {
			browser.close();
		}
	}
	
	@DataProvider
	protected Browser browser(DataSourceInformation dataSourceInformation) {
		if ( dataSourceInformation.getType() != null && !dataSourceInformation.getType().isEmpty()) {
			try {
				
				String browserType = dataSourceInformation.getType();
				if ( browserType == null || browserType.isEmpty() ) {
					browserType = getSuiteParameter(BROWSER);
				}
				if ( browserType == null || browserType.isEmpty() ) {
					browserType = getDefaultBrowserType();
				}
				
				Browser browser = new Browser(createWebDriver(dataSourceInformation.getType()));
				browser.setSelenusActionListener(getSelenusActionListener());
				browser.setType(browserType);
				browser.setName(dataSourceInformation.getName());
				_usedBrowsers.add(browser);
				return browser;
			}
			catch (Exception e) {
				throw new IllegalArgumentException("Cannot create browser for type: " + dataSourceInformation.getType());
			}
		}
		
		return null;
	}
	
	@DataProvider
	protected Page page(DataSourceInformation dataSourceInformation) {
		Class<?> pageClass = dataSourceInformation.getObjectType();
		if ( Page.class.isAssignableFrom(pageClass) ){
			return (Page) pageFactory.createPage(pageClass, getSelenusActionListener(), getVerificatorProvider());
		}
		else throw new IllegalArgumentException("Cannot create page for " + pageClass.getName());
	}
	
	
	protected WebDriver createWebDriver(String type) throws Exception {
		if ( type == null) {
			throw new NullPointerException("Browser type should not be null");
		}
		
		if ( type.equals(FIREFOX)) {
			return new FirefoxDriver();
		}
		else if ( type.equals(IE)) {
			return new InternetExplorerDriver();
		}
		else if ( type.equals(CHROME)) {
			return new ChromeDriver();
		}
		else if ( type.equals(ANDROID)) {
			return new AndroidDriver();
		}
		else if ( type.equals(IPHONE)) {
			return new IPhoneDriver();
		}
		else if ( type.equals(HTMLUNIT)) {
			return new HtmlUnitDriver();
		}
		
		throw new IllegalArgumentException("Don't know browser type '" + type + "'");
	}

	public PageFactory getPageFactory() {
		return pageFactory;
	}

	public void setPageFactory(PageFactory pageFactory) {
		this.pageFactory = pageFactory;
	}

	public SelenusActionListener getSelenusActionListener() {
		return selenusActionListener;
	}

	public void setSelenusActionListener(SelenusActionListener selenusActionListener) {
		this.selenusActionListener = selenusActionListener;
	}

	public VerificatorProvider getVerificatorProvider() {
		return verificatorProvider;
	}

	public void setVerificatorProvider(VerificatorProvider verificatorProvider) {
		this.verificatorProvider = verificatorProvider;
	}

	public String getDefaultBrowserType() {
		return defaultBrowserType;
	}

	public void setDefaultBrowserType(String defaultBrowserType) {
		this.defaultBrowserType = defaultBrowserType;
	}

}
