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
