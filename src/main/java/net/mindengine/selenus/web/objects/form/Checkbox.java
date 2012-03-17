package net.mindengine.selenus.web.objects.form;

import org.openqa.selenium.WebElement;

public class Checkbox extends AbstractFormObject {

	
	public boolean isChecked() {
		return findWebDriverElement().isSelected();
	}
	
	public void check() {
		WebElement webElement = findWebDriverElement();
		if( !webElement.isSelected() ) {
			webElement.click();
		}
	}
	
	public void uncheck() {
		WebElement webElement = findWebDriverElement();
		if( webElement.isSelected() ) {
			webElement.click();
		}
	}
	
	
	
	@Override
	public String getTypeString() {
		return "checkbox";
	}

}
