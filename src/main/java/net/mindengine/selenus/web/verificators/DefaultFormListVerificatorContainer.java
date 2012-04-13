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

import java.util.LinkedList;
import java.util.List;

import net.mindengine.oculus.experior.framework.verification.Provider;
import net.mindengine.oculus.experior.framework.verification.collections.AssertionTextCollectionVerificator;
import net.mindengine.oculus.experior.framework.verification.collections.SimpleTextCollectionVerificator;
import net.mindengine.selenus.web.objects.form.FormList;
import net.mindengine.selenus.web.objects.form.FormList.ListOption;

public class DefaultFormListVerificatorContainer extends DefaultPageObjectVerificatorContainer {

	public DefaultFormListVerificatorContainer(boolean assertion, FormList pageObject, VerificatorProvider verificatorProvider) {
		super(assertion, pageObject, verificatorProvider);
	}

	
	protected FormList findFormList() {
		return (FormList)pageObject;
	}
	
	public SimpleTextCollectionVerificator values() {
		SimpleTextCollectionVerificator textCollectionVerificator = findVericatorProvider().textCollectionVerificator(pageObject, "Text of " + pageObject.getFullName(), new Provider<List<String>>() {
			@Override
			public List<String> provide() {
				return collectValues(findFormList().getOptions());
			}
		});
		
		if ( assertion ) {
			return new AssertionTextCollectionVerificator(textCollectionVerificator);
		}
		else {
			return textCollectionVerificator;
		}
	}
	
	protected List<String> collectValues(List<ListOption> options) {
		List<String> values = new LinkedList<String>();
		for ( ListOption option : options ) {
			values.add(option.getValue());
		}
		return values;
	}


	public SimpleTextCollectionVerificator optionLabels() {
		SimpleTextCollectionVerificator textCollectionVerificator = findVericatorProvider().textCollectionVerificator(pageObject, "Text of " + pageObject.getFullName(), new Provider<List<String>>() {
			@Override
			public List<String> provide() {
				return collectOptionLabels(findFormList().getOptions());
			}
		});
		
		if ( assertion ) {
			return new AssertionTextCollectionVerificator(textCollectionVerificator);
		}
		else {
			return textCollectionVerificator;
		}
	}
	
	protected List<String> collectOptionLabels(List<ListOption> options) {
		List<String> values = new LinkedList<String>();
		for ( ListOption option : options ) {
			values.add(option.getText());
		}
		return values;
	}


	public SimpleTextCollectionVerificator selectedValues() {
		SimpleTextCollectionVerificator textCollectionVerificator = findVericatorProvider().textCollectionVerificator(pageObject, "Text of " + pageObject.getFullName(), new Provider<List<String>>() {
			@Override
			public List<String> provide() {
				return collectValues(findFormList().getSelectedOptions());
			}
		});
		
		if ( assertion ) {
			return new AssertionTextCollectionVerificator(textCollectionVerificator);
		}
		else {
			return textCollectionVerificator;
		}
	}
	
	public SimpleTextCollectionVerificator selectedOptionLabels() {
		SimpleTextCollectionVerificator textCollectionVerificator = findVericatorProvider().textCollectionVerificator(pageObject, "Text of " + pageObject.getFullName(), new Provider<List<String>>() {
			@Override
			public List<String> provide() {
				return collectOptionLabels(findFormList().getSelectedOptions());
			}
		});
		
		if ( assertion ) {
			return new AssertionTextCollectionVerificator(textCollectionVerificator);
		}
		else {
			return textCollectionVerificator;
		}
	}
}
