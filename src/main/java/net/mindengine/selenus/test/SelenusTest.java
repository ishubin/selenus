package net.mindengine.selenus.test;

import java.util.LinkedList;
import java.util.List;

import net.mindengine.oculus.experior.annotations.DataProvider;
import net.mindengine.oculus.experior.framework.test.OculusTest;
import net.mindengine.oculus.experior.test.descriptors.TestInformation;
import net.mindengine.oculus.experior.test.resolvers.dataprovider.DataSourceInformation;
import net.mindengine.selenus.web.Browser;
import net.mindengine.selenus.web.Page;
import net.mindengine.selenus.web.factory.DefaultPageFactory;
import net.mindengine.selenus.web.factory.DefaultPageObjectFactory;
import net.mindengine.selenus.web.factory.PageFactory;
import net.mindengine.selenus.web.objects.PageObjectActionListener;
import net.mindengine.selenus.web.report.OculusPageObjectActionListener;
import net.mindengine.selenus.web.verificators.OculusVerificatorProvider;
import net.mindengine.selenus.web.verificators.VerificatorProvider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.iphone.IPhoneDriver;

/**
 * Base test for easier use of selenus with oculus reporter.
 * Contains providers for browsers and pages
 * @author ishubin
 *
 */
public class SelenusTest extends OculusTest {

	public static final String FIREFOX = "firefox".intern();
	public static final String CHROME = "chrome".intern();
	public static final String ANDROID = "android".intern();
	public static final String IPHONE = "iphone".intern();
	public static final String IE = "firefox".intern();
	public static final String HTMLUNIT = "htmlunit".intern();
	public static final String BROWSER = "browser".intern();
	
	private PageFactory pageFactory;
	private PageObjectActionListener pageObjectActionListener;
	private VerificatorProvider verificatorProvider;
	
	/**
	 * Default type of browser which is defined in suite
	 */
	private String defaultBrowserType = FIREFOX;
	
	private List<Browser> _usedBrowsers = new LinkedList<Browser>();
	
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
		
		pageFactory = new DefaultPageFactory(new DefaultPageObjectFactory());
		pageObjectActionListener = new OculusPageObjectActionListener(getReport());
		verificatorProvider = new OculusVerificatorProvider(getReport());
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
				Browser browser = new Browser(createWebDriver(dataSourceInformation.getType()));
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
			return (Page) pageFactory.createPage(pageClass, getPageObjectActionListener(), getVerificatorProvider());
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

	public PageObjectActionListener getPageObjectActionListener() {
		return pageObjectActionListener;
	}

	public void setPageObjectActionListener(PageObjectActionListener pageObjectActionListener) {
		this.pageObjectActionListener = pageObjectActionListener;
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
