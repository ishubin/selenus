package net.mindengine.selenus.web.verificators.objects;

import net.mindengine.selenus.web.objects.AbstractPageObject;

public class AssertionPageObjectVerificator implements PageObjectVerificator {

	private PageObjectVerificator verificator;
	
	public AssertionPageObjectVerificator(PageObjectVerificator verificator) {
		this.verificator = verificator;
	}
	
	
	@Override
	public AbstractPageObject findPageObject() {
		return verificator.findPageObject();
	}
	
	@Override
	public boolean isAvailable() {
		if ( !verificator.isAvailable() ) {
			throw new AssertionError(findPageObject().getFullName() + " is not available");
		}
		return true;
	}

	@Override
	public boolean isNotAvailable() {
		if ( !verificator.isNotAvailable() ) {
			throw new AssertionError(findPageObject().getFullName() + " is available");
		}
		return true;
	}

	@Override
	public boolean isDisplayed() {
		if ( !verificator.isDisplayed() ) {
			throw new AssertionError(findPageObject().getFullName() + " is not visible");
		}
		return true;
	}

	@Override
	public boolean isNotDisplayed() {
		if ( !verificator.isNotDisplayed() ) {
			throw new AssertionError(findPageObject().getFullName() + " is visible");
		}
		return true;
	}
}
