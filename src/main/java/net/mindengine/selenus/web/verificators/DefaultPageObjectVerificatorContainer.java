package net.mindengine.selenus.web.verificators;

import net.mindengine.oculus.experior.framework.verification.Provider;
import net.mindengine.oculus.experior.framework.verification.text.AssertionTextVerificator;
import net.mindengine.oculus.experior.framework.verification.text.TextVerificator;
import net.mindengine.selenus.web.objects.AbstractPageObject;

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
