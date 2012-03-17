package net.mindengine.selenus.samplepages;

import net.mindengine.selenus.annotations.Named;
import net.mindengine.selenus.web.Page;
import net.mindengine.selenus.web.objects.PageObjectList;
import net.mindengine.selenus.web.objects.WebLayout;
import net.mindengine.selenus.web.objects.form.TextField;

import org.openqa.selenium.support.FindBy;

@Named("Some sample page")
public class SamplePage extends Page {
	
	@Named("Sample text-field") @FindBy(xpath="//sample-x-path-for-text-field")
	private TextField textField;
	
	@Named("Sample web-layout") @FindBy(xpath="//sample-x-path-for-layout")
	private WebLayout layout;
	
	@FindBy(xpath="//sample-x-path-for-layouts-list")
	private PageObjectList<WebLayout> layouts;

	public TextField getTextField() {
		return textField;
	}

	public void setTextField(TextField textField) {
		this.textField = textField;
	}

	public PageObjectList<WebLayout> getLayouts() {
		return layouts;
	}

	public void setLayouts(PageObjectList<WebLayout> layouts) {
		this.layouts = layouts;
	}

	public WebLayout getLayout() {
		return layout;
	}

	public void setLayout(WebLayout layout) {
		this.layout = layout;
	}
}
