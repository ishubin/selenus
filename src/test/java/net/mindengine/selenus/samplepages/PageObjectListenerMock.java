package net.mindengine.selenus.samplepages;

import net.mindengine.selenus.web.objects.AbstractPageObject;
import net.mindengine.selenus.web.objects.PageObjectActionListener;

public class PageObjectListenerMock implements PageObjectActionListener{

	@Override
	public void onClick(AbstractPageObject pageObject) {
	}

	@Override
	public void onDragAndDrop(AbstractPageObject pageObject, AbstractPageObject targetPageObject) {
	}

	@Override
	public void onDragAndDropBy(AbstractPageObject pageObject, int xOffset, int yOffset) {
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
