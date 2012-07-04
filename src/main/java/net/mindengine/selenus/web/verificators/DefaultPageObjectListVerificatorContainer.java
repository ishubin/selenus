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
package net.mindengine.selenus.web.verificators;

import net.mindengine.oculus.experior.framework.verification.Provider;
import net.mindengine.oculus.experior.framework.verification.number.AssertionNumberVerificator;
import net.mindengine.oculus.experior.framework.verification.number.NumberVerificator;
import net.mindengine.selenus.web.objects.AbstractPageObject;
import net.mindengine.selenus.web.objects.PageObjectList;

public class DefaultPageObjectListVerificatorContainer extends DefaultPageObjectVerificatorContainer {

	public DefaultPageObjectListVerificatorContainer(boolean assertion, AbstractPageObject pageObject, VerificatorProvider verificatorProvider) {
		super(assertion, pageObject, verificatorProvider);
	}
	
	public NumberVerificator<Integer> size() {
		NumberVerificator<Integer> numberVerificator = findVericatorProvider().numberVerificator(this.pageObject, "Size of elements in " + this.pageObject.getFullName(), new Provider<Integer>() {
			@Override
			public Integer provide() {
				return ((PageObjectList<?>)pageObject).size();
			}
		});
		
		if ( assertion ) {
			return new AssertionNumberVerificator<Integer>(numberVerificator);
		}
		return numberVerificator;
	}
}
