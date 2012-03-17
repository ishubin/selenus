package net.mindengine.selenus.web.objects.form;

import net.mindengine.selenus.web.objects.AbstractPageObject;

public abstract class AbstractFormObject extends AbstractPageObject {


	public String getValue() {
		return getTagAttribute("value");
	}
	
	public boolean isEnabled() {
		return findWebDriverElement().isEnabled();
	}
	
}
