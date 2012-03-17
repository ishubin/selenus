package net.mindengine.selenus.web.objects.form;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FormList extends AbstractFormObject {
	
	public class ListOption {

		private WebElement webElement;
		public ListOption (WebElement webElement) {
			
		}
		
		public WebElement getWebElement() {
			return webElement;
		}
		public void setWebElement(WebElement webElement) {
			this.webElement = webElement;
		}
		
		public boolean isSelected() {
			return webElement.isSelected();
		}
		
		public String getValue() {
			return webElement.getAttribute("value");
		}
		
		public String getText() {
			return webElement.getText();
		}
	}

	public List<ListOption> getOptions() {
		return convertOptions(webDriverSelect().getOptions());
	}
	
	public List<ListOption> getSelectedOptions() {
		return convertOptions(webDriverSelect().getAllSelectedOptions());
	}

	private List<ListOption> convertOptions(List<WebElement> webDriverOptions) {
		List<ListOption> options = new LinkedList<FormList.ListOption>();
		if(webDriverOptions != null) {
			for(WebElement webElement : webDriverOptions) {
				options.add(new ListOption(webElement));
			}
		}
		return options;
	}
	
	public void selectByValue(String value) {
		webDriverSelect().selectByValue(value);
	}
	
	public void selectByIndex(int index) {
		webDriverSelect().selectByIndex(index);
	}
	
	public void selectByText(String text) {
		webDriverSelect().selectByVisibleText(text);
	}
	
	public void deselectAll() {
		webDriverSelect().deselectAll();
	}
	
	public void deselectByIndex(int index) {
		webDriverSelect().deselectByIndex(index);
	}
	
	public void deselectByValue(String value) {
		webDriverSelect().deselectByValue(value);
	}
	
	public void deselectByText(String text) {
		webDriverSelect().deselectByVisibleText(text);
	}
	
	
	
	public boolean isMultiple() {
		return webDriverSelect().isMultiple();
	}

	private Select webDriverSelect() {
		return new Select(findWebDriverElement());
	}
	
	
	@Override
	public String getTypeString() {
		return "list";
	}

}
