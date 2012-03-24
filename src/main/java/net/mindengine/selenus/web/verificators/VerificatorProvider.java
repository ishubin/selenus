package net.mindengine.selenus.web.verificators;

import java.util.List;

import net.mindengine.oculus.experior.framework.verification.Provider;
import net.mindengine.oculus.experior.framework.verification.collections.SimpleNumberCollectionVerificator;
import net.mindengine.oculus.experior.framework.verification.collections.SimpleTextCollectionVerificator;
import net.mindengine.oculus.experior.framework.verification.number.NumberVerificator;
import net.mindengine.oculus.experior.framework.verification.text.TextVerificator;
import net.mindengine.selenus.web.objects.AbstractPageObject;

public interface VerificatorProvider {

	<T extends Number> NumberVerificator<T> numberVerificator(AbstractPageObject pageObject, String itemName, Provider<T> realValueProvider);

	TextVerificator textVerificator(AbstractPageObject abstractPageObject, String itemName, Provider<String> realTextProvider);
	
	SimpleTextCollectionVerificator textCollectionVerificator(AbstractPageObject abstractPageObject, String itemName, Provider<List<String>> realCollectionProvider);
	
	<T extends Number> SimpleNumberCollectionVerificator<T> numberCollectionVerificator(AbstractPageObject abstractPageObject, String itemName, Provider<List<T>> realCollectionProvider);
}
