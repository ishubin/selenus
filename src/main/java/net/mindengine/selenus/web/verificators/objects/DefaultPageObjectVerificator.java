package net.mindengine.selenus.web.verificators.objects;

import net.mindengine.oculus.experior.framework.verification.Provider;
import net.mindengine.oculus.experior.reporter.Report;
import net.mindengine.oculus.experior.reporter.ReportIcon;
import net.mindengine.selenus.web.Page;
import net.mindengine.selenus.web.objects.AbstractPageObject;
import net.mindengine.selenus.web.objects.form.FormElement;
import net.mindengine.selenus.web.objects.form.FormSelectable;

public class DefaultPageObjectVerificator implements PageObjectVerificator {

	private static final String SELENUS_PAGE_OBJECT_VERIFICATOR = "Selenus.PageObjectVerificator";
	
	private static final String DEFAULT_IS_AVALIABLE_PASS = "${fullName} is available as expected on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_AVALIABLE_FAIL = "${fullName} is not available on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_NOT_AVALIABLE_PASS = "${fullName} is not available as expected on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_NOT_AVALIABLE_FAIL = "${fullName} is available but it is not expected on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_DISPLAYED_PASS = "${fullName} is displayed as expected on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_DISPLAYED_FAIL = "${fullName} is not displayed on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_NOT_DISPLAYED_PASS = "${fullName} is not displayed as expected on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_NOT_DISPLAYED_FAIL = "${fullName} is displayed but it is not expected on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_ENABLED_PASS = "${fullName} is enabled as expected on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_ENABLED_FAIL = "${fullName} is disabled on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_DISABLED_PASS = "${fullName} is disabled as expected on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_DISABLED_FAIL = "${fullName} is enabled on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_CHECKED_PASS = "${fullName} is checked as expected on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_CHECKED_FAIL = "${fullName} is unchecked on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_UNCHECKED_PASS = "${fullName} is unchecked as expected on [b]${page.name}[/b] page";
	private static final String DEFAULT_IS_UNCHECKED_FAIL = "${fullName} is checked on [b]${page.name}[/b] page";
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
	
	
	@Override
	public boolean isEnabled() {
		if ( findFormObject().isEnabled() ) {
			reportInfo(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isEnabled.pass", DEFAULT_IS_ENABLED_PASS);
			return true;
		} else {
			reportError(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isEnabled.fail", DEFAULT_IS_ENABLED_FAIL);
			return false;
		}
	}

	@Override
	public boolean isDisabled() {
		if ( !findFormObject().isEnabled() ) {
			reportInfo(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isDisabled.pass", DEFAULT_IS_DISABLED_PASS);
			return true;
		} else {
			reportError(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isDisabled.fail", DEFAULT_IS_DISABLED_FAIL);
			return false;
		}
	}

	@Override
	public boolean isChecked() {
		if ( findFormSelectable().isChecked() ) {
			reportInfo(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isChecked.pass", DEFAULT_IS_CHECKED_PASS);
			return true;
		} else {
			reportError(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isChecked.fail", DEFAULT_IS_CHECKED_FAIL);
			return false;
		}
	}

	@Override
	public boolean isUnchecked() {
		if ( !findFormSelectable().isChecked() ) {
			reportInfo(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isUnchecked.pass", DEFAULT_IS_UNCHECKED_PASS);
			return true;
		} else {
			reportError(SELENUS_PAGE_OBJECT_VERIFICATOR + ".isUnchecked.fail", DEFAULT_IS_UNCHECKED_FAIL);
			return false;
		}
	}
	
	private FormElement findFormObject() {
		AbstractPageObject pageObject = findPageObject();
		if ( pageObject instanceof FormElement ) {
			return (FormElement) pageObject;
		}
		else throw new IllegalArgumentException(pageObject.getFullName() + " is not a form object");
	}
	
	private FormSelectable findFormSelectable() {
		AbstractPageObject pageObject = findPageObject();
		if ( pageObject instanceof FormSelectable ) {
			return (FormSelectable) pageObject;
		}
		else throw new IllegalArgumentException(pageObject.getFullName() + " is not a form selectable object");
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
