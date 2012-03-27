package net.mindengine.selenus.web.verificators;

import net.mindengine.selenus.web.objects.AbstractPageObject;

public interface PageObjectVerificator {
	
	public AbstractPageObject findPageObject();
	
	public boolean isAvailable();
	public boolean isNotAvailable();
	public boolean isDisplayed();
	public boolean isNotDisplayed();
}
