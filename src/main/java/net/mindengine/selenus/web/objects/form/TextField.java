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

public class TextField extends AbstractFormObject {

	
	/**
	 * Sends keys to text-field without erasing text
	 * @param keys
	 */
	public void typeKeys(String keys) {
		findWebDriverElement().sendKeys(keys);
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.typeKeys(this, keys);
		}
	}
	
	/**
	 * Removes the text and type the new one
	 * @param text
	 */
	public void type(String text) {
		findWebDriverElement().clear();
		findWebDriverElement().sendKeys(text);
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.type(this, text);
		}
	}
	
	/**
	 * Removes the text from text-field
	 */
	public void clear() {
		findWebDriverElement().clear();
		SelenusActionListener listener = findPageObjectActionListener();
		if ( listener != null ) {
			listener.clear(this);
		}
	}
	
	public String getText() {
		return getValue();
	}
	
	@Override
	public String getType() {
		return "text-field";
	}

}
