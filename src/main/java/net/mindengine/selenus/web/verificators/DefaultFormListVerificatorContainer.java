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
