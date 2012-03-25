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
