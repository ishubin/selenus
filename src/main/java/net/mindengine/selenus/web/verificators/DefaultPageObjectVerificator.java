package net.mindengine.selenus.web.verificators;

import net.mindengine.oculus.experior.framework.verification.Provider;
import net.mindengine.oculus.experior.reporter.Report;
import net.mindengine.oculus.experior.reporter.ReportIcon;
import net.mindengine.selenus.web.Page;
import net.mindengine.selenus.web.objects.AbstractPageObject;

public class DefaultPageObjectVerificator implements PageObjectVerificator {

	private static final String SELENUS_PAGE_OBJECT_VERIFICATOR = "Selenus.PageObjectVerificator";
	private static final String DEFAULT_IS_AVALIABLE_PASS = null;
	private static final String DEFAULT_IS_AVALIABLE_FAIL = null;
	private static final String DEFAULT_IS_NOT_AVALIABLE_PASS = null;
	private static final String DEFAULT_IS_NOT_AVALIABLE_FAIL = null;
	private static final String DEFAULT_IS_DISPLAYED_PASS = null;
	private static final String DEFAULT_IS_DISPLAYED_FAIL = null;
	private static final String DEFAULT_IS_NOT_DISPLAYED_PASS = null;
	private static final String DEFAULT_IS_NOT_DISPLAYED_FAIL = null;
	private Provider<AbstractPageObject> provider;
	private Report report;
	
	public DefaultPageObjectVerificator(Provider<AbstractPageObject> provider, Report report) {
		setProvider(provider);
		setReport(report);
	}
	
	private AbstractPageObject _pageObject = null;
	
	
	private void reportInfo(String messageName, String defaultMessage) {
		if(report!=null) {
			report.info(message(messageName, defaultMessage)).icon(ReportIcon.VALIDATION_PASSED);
		}
	}
	
	private void reportError(String messageName, String defaultMessage) {
		if(report!=null) {
			report.error(message(messageName, defaultMessage)).icon(ReportIcon.VALIDATION_FAILED);
		}
	}
	
	private String message(String messageName, String defaultMessage) {
		
		Page page = findPageObject().searchForPage();
		String pageName;
		
		if ( page != null ) {
			if( page.getName() != null ) {
				pageName = page.getName();
			}
			else {
				pageName = page.getClass().getSimpleName();
			}
		}
		else {
			pageName = "Unknown";
		}
		return report.message(messageName, defaultMessage)
			.put("name", findPageObject().getName())
			.put("fullName", findPageObject().getFullName())
			.put("type", findPageObject().getTypeString())
			.put("page.name", pageName).toString();
	}

	@Override
	public AbstractPageObject findPageObject() {
		if ( _pageObject ==null) {
			_pageObject = provider.provide();
		}
		return _pageObject;
	}

	@Override
	public boolean isAvailable() {
		if (findPageObject().isAvailable()) {
			reportInfo(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isAvailable.pass", DEFAULT_IS_AVALIABLE_PASS);
			return true;
		} else {
			reportError(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isAvailable.fail", DEFAULT_IS_AVALIABLE_FAIL);
			return false;
		}
	}

	@Override
	public boolean isNotAvailable() {
		if (!findPageObject().isAvailable()) {
			reportInfo(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isNotAvailable.pass", DEFAULT_IS_NOT_AVALIABLE_PASS);
			return true;
		} else {
			reportError(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isNotAvailable.fail", DEFAULT_IS_NOT_AVALIABLE_FAIL);
			return false;
		}
	}

	@Override
	public boolean isDisplayed() {
		if (findPageObject().isDisplayed()) {
			reportInfo(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isDisplayed.pass", DEFAULT_IS_DISPLAYED_PASS);
			return true;
		} else {
			reportError(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isDisplayed.fail", DEFAULT_IS_DISPLAYED_FAIL);
			return false;
		}
	}

	@Override
	public boolean isNotDisplayed() {
		if (!findPageObject().isDisplayed()) {
			reportInfo(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isNotDisplayed.pass", DEFAULT_IS_NOT_DISPLAYED_PASS);
			return true;
		} else {
			reportError(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isNotDisplayed.fail", DEFAULT_IS_NOT_DISPLAYED_FAIL);
			return false;
		}
	}

	public Provider<AbstractPageObject> getProvider() {
		return provider;
	}

	public void setProvider(Provider<AbstractPageObject> provider) {
		this.provider = provider;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

}
