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

import net.mindengine.selenus.web.objects.SelenusActionListener;

import org.openqa.selenium.WebElement;

public class Checkbox extends AbstractFormObject implements FormSelectable {

	@Override
	public boolean isChecked() {
		return findWebDriverElement().isSelected();
	}
	
	public void check() {
		WebElement webElement = findWebDriverElement();
		if( !webElement.isSelected() ) {
			webElement.click();
			SelenusActionListener listener = findPageObjectActionListener();
			if ( listener != null ) {
				listener.click(this);
			}
		}
	}
	
	public void uncheck() {
		WebElement webElement = findWebDriverElement();
		if( webElement.isSelected() ) {
			webElement.click();
			SelenusActionListener listener = findPageObjectActionListener();
			if ( listener != null ) {
				listener.click(this);
			}
		}
	}
	
	@Override
	public String getTypeString() {
		return "checkbox";
	}

}
