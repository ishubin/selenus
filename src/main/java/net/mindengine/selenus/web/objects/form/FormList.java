/*******************************************************************************
* Copyright 2012 Ivan Shubin http://mindengine.net
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/
package net.mindengine.selenus.web.objects.form;

import java.util.LinkedList;
import java.util.List;

import net.mindengine.selenus.web.objects.SelenusActionListener;
import net.mindengine.selenus.web.verificators.DefaultFormListVerificatorContainer;

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
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.selectByValue(this, value);
		}
	}
	
	public void selectByIndex(int index) {
		webDriverSelect().selectByIndex(index);
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.selectByIndex(this, index);
		}
	}
	
	public void selectByText(String text) {
		webDriverSelect().selectByVisibleText(text);
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.selectByText(this, text);
		}
	}
	
	public void deselectAll() {
		webDriverSelect().deselectAll();
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.deselectAll(this);
		}
	}
	
	public void deselectByIndex(int index) {
		webDriverSelect().deselectByIndex(index);
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.deselectByIndex(this, index);
		}
	}
	
	public void deselectByValue(String value) {
		webDriverSelect().deselectByValue(value);
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.deselectByValue(this, value);
		}
	}
	
	public void deselectByText(String text) {
		webDriverSelect().deselectByVisibleText(text);
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.deselectByText(this, text);
		}
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
	
	@Override
	public DefaultFormListVerificatorContainer verifyThat() {
		return new DefaultFormListVerificatorContainer(false, this, findVerificatorProvider());
	}
	
	@Override
	public DefaultFormListVerificatorContainer assertThat() {
		return new DefaultFormListVerificatorContainer(true, this, findVerificatorProvider());
	}

}
