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
import net.mindengine.oculus.experior.framework.verification.text.AssertionTextVerificator;
import net.mindengine.oculus.experior.framework.verification.text.TextVerificator;
import net.mindengine.oculus.experior.reporter.ReportDesign;
import net.mindengine.selenus.web.objects.AbstractPageObject;
import net.mindengine.selenus.web.verificators.objects.AssertionPageObjectVerificator;
import net.mindengine.selenus.web.verificators.objects.PageObjectVerificator;

public class DefaultPageObjectVerificatorContainer {
	protected boolean assertion = false;
	protected AbstractPageObject pageObject;
	private VerificatorProvider verificatorProvider;
	
	public DefaultPageObjectVerificatorContainer(boolean assertion, AbstractPageObject pageObject, VerificatorProvider verificatorProvider) {
		super();
		this.assertion = assertion;
		this.pageObject = pageObject;
		this.verificatorProvider = verificatorProvider;
	}
	
	public PageObjectVerificator it() {
		PageObjectVerificator verificator = findVericatorProvider().standardPageObjectVerificator(new Provider<AbstractPageObject>() {
			@Override
			public AbstractPageObject provide() {
				return pageObject;
			}
		});
		
		if ( assertion ) {
			return new AssertionPageObjectVerificator(verificator);
		}
		else return verificator;
	}
	
	
	public TextVerificator attribute(final String attributeName) {
	    TextVerificator textVerificator = findVericatorProvider().textVerificator(pageObject, "Attribute " + ReportDesign.string(attributeName) + " of " + pageObject.getFullName(), new Provider<String>() {
            @Override
            public String provide() {
                return pageObject.getTagAttribute(attributeName);
            }
        });
        if ( assertion ) {
            return new AssertionTextVerificator(textVerificator);
        }
        else return textVerificator;
	}
	
	public TextVerificator text() {
		TextVerificator textVerificator = findVericatorProvider().textVerificator(pageObject, "Text of " + pageObject.getFullName(), new Provider<String>() {
			@Override
			public String provide() {
				return pageObject.getText();
			}
		});
		if ( assertion ) {
			return new AssertionTextVerificator(textVerificator);
		}
		else return textVerificator;
	}
	
	protected TextVerificator textVerificator(String itemName, Provider<String> provider) {
		return findVericatorProvider().textVerificator(this.pageObject, itemName, provider);
	}

	protected VerificatorProvider findVericatorProvider() {
		if ( verificatorProvider == null ) {
			throw new IllegalArgumentException("Verificator provider is not specified");
		}
		return verificatorProvider;
	}
}
