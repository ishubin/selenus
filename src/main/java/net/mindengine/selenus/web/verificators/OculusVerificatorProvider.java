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

import java.util.List;

import net.mindengine.oculus.experior.framework.verification.Provider;
import net.mindengine.oculus.experior.framework.verification.collections.DefaultNumberCollectionVerificator;
import net.mindengine.oculus.experior.framework.verification.collections.DefaultTextCollectionVerificator;
import net.mindengine.oculus.experior.framework.verification.collections.SimpleNumberCollectionVerificator;
import net.mindengine.oculus.experior.framework.verification.collections.SimpleTextCollectionVerificator;
import net.mindengine.oculus.experior.framework.verification.number.DefaultNumberVerificator;
import net.mindengine.oculus.experior.framework.verification.number.NumberVerificator;
import net.mindengine.oculus.experior.framework.verification.text.DefaultTextVerificator;
import net.mindengine.oculus.experior.framework.verification.text.TextVerificator;
import net.mindengine.oculus.experior.reporter.Report;
import net.mindengine.selenus.web.objects.AbstractPageObject;
import net.mindengine.selenus.web.verificators.objects.DefaultPageObjectVerificator;
import net.mindengine.selenus.web.verificators.objects.PageObjectVerificator;

public class OculusVerificatorProvider implements VerificatorProvider {

	private Report report;
	
	public OculusVerificatorProvider(Report report) {
		setReport(report);
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	@Override
	public <T extends Number> NumberVerificator<T> numberVerificator(AbstractPageObject pageObject, String itemName, Provider<T> realValueProvider) {
		return new DefaultNumberVerificator<T>(realValueProvider, itemName, report);
	}

	@Override
	public TextVerificator textVerificator(AbstractPageObject abstractPageObject, String itemName, Provider<String> realTextProvider) {
		return new DefaultTextVerificator(realTextProvider, itemName, report);
	}

	@Override
	public SimpleTextCollectionVerificator textCollectionVerificator(AbstractPageObject abstractPageObject, String itemName, Provider<List<String>> realCollectionProvider) {
		return new DefaultTextCollectionVerificator(realCollectionProvider, itemName, report);
	}

	@Override
	public <T extends Number> SimpleNumberCollectionVerificator<T> numberCollectionVerificator(AbstractPageObject abstractPageObject, String itemName, Provider<List<T>> realCollectionProvider) {
		return new DefaultNumberCollectionVerificator<T>(realCollectionProvider, itemName, report);
	}

	@Override
	public PageObjectVerificator standardPageObjectVerificator(Provider<AbstractPageObject> provider) {
		return new DefaultPageObjectVerificator(provider, report);
	}
	
}
