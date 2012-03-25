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
package net.mindengine.selenus.web.objects;

public interface PageObjectActionListener {
	
	public void click(AbstractPageObject pageObject);
	
	public void dragAndDrop(AbstractPageObject pageObject, AbstractPageObject targetPageObject);
	
	public void dragAndDropBy(AbstractPageObject pageObject, int xOffset, int yOffset);
	
	public void selectByValue(AbstractPageObject pageObject, String value);
	
	public void selectByIndex(AbstractPageObject pageObject, int index);
	
	public void selectByText(AbstractPageObject pageObject, String text);
	
	public void deselectAll(AbstractPageObject pageObject);
	
	public void deselectByIndex(AbstractPageObject pageObject, int index);
	
	public void deselectByValue(AbstractPageObject pageObject, String value);
	
	public void deselectByText(AbstractPageObject pageObject, String text);
	
	public void typeKeys(AbstractPageObject pageObject, String keys);
	
	public void type(AbstractPageObject pageObject, String text);
	
	public void clear(AbstractPageObject pageObject);

}
