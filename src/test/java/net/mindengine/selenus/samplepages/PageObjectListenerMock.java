package net.mindengine.selenus.samplepages;

import net.mindengine.selenus.web.Browser;
import net.mindengine.selenus.web.objects.AbstractPageObject;
import net.mindengine.selenus.web.objects.SelenusActionListener;

public class PageObjectListenerMock implements SelenusActionListener{

	@Override
	public void openUrl(String url, Browser browser) {
	}
	
	@Override
	public void click(AbstractPageObject pageObject) {
	}

	@Override
	public void dragAndDrop(AbstractPageObject pageObject, AbstractPageObject targetPageObject) {
	}

	@Override
	public void dragAndDropBy(AbstractPageObject pageObject, int xOffset, int yOffset) {
	}

	@Override
	public void selectByValue(AbstractPageObject pageObject, String value) {
	}

	@Override
	public void selectByIndex(AbstractPageObject pageObject, int index) {
	}

	@Override
	public void selectByText(AbstractPageObject pageObject, String text) {
	}

	@Override
	public void deselectAll(AbstractPageObject pageObject) {
	}

	@Override
	public void deselectByIndex(AbstractPageObject pageObject, int index) {
	}

	@Override
	public void deselectByValue(AbstractPageObject pageObject, String value) {
	}

	@Override
	public void deselectByText(AbstractPageObject pageObject, String text) {
	}

	@Override
	public void typeKeys(AbstractPageObject pageObject, String keys) {
	}

	@Override
	public void type(AbstractPageObject pageObject, String text) {
	}

	@Override
	public void clear(AbstractPageObject pageObject) {
	}

}
