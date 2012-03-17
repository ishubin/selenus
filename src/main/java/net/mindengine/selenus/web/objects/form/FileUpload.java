package net.mindengine.selenus.web.objects.form;

import java.io.File;

import org.openqa.selenium.WebElement;

public class FileUpload extends AbstractFormObject {

	public void setFile(File file) {
		WebElement webElement = findWebDriverElement();
		webElement.clear();
		webElement.sendKeys(file.getAbsolutePath());
	}
	
	@Override
	public String getTypeString() {
		return "file upload";
	}

}
