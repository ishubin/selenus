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
package net.mindengine.selenus.web.verificators.objects;

import net.mindengine.selenus.exceptions.SelenusAssertionError;
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
			throw new SelenusAssertionError(findPageObject().getFullName() + " is not available");
		}
		return true;
	}

	@Override
	public boolean isNotAvailable() {
		if ( !verificator.isNotAvailable() ) {
			throw new SelenusAssertionError(findPageObject().getFullName() + " is available");
		}
		return true;
	}

	@Override
	public boolean isDisplayed() {
		if ( !verificator.isDisplayed() ) {
			throw new SelenusAssertionError(findPageObject().getFullName() + " is not visible");
		}
		return true;
	}

	@Override
	public boolean isNotDisplayed() {
		if ( !verificator.isNotDisplayed() ) {
			throw new SelenusAssertionError(findPageObject().getFullName() + " is visible");
		}
		return true;
	}


	@Override
	public boolean isEnabled() {
		if ( !verificator.isEnabled() ) {
			throw new SelenusAssertionError(findPageObject().getFullName() + " is disabled");
		}
		return true;
	}


	@Override
	public boolean isDisabled() {
		if ( !verificator.isDisabled() ) {
			throw new SelenusAssertionError(findPageObject().getFullName() + " is enabled");
		}
		return true;
	}


	@Override
	public boolean isChecked() {
		if ( !verificator.isChecked() ) {
			throw new SelenusAssertionError(findPageObject().getFullName() + " is unchecked");
		}
		return true;
	}


	@Override
	public boolean isUnchecked() {
		if ( !verificator.isUnchecked() ) {
			throw new SelenusAssertionError(findPageObject().getFullName() + " is checked");
		}
		return true;
	}
}
