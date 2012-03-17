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

import net.mindengine.selenus.annotations.Named;
import net.mindengine.selenus.web.Page;
import net.mindengine.selenus.web.objects.PageObjectList;
import net.mindengine.selenus.web.objects.WebLayout;
import net.mindengine.selenus.web.objects.form.TextField;

import org.openqa.selenium.support.FindBy;

@Named("Some sample page")
public class SamplePage extends Page {
	
	@Named("Sample text-field") @FindBy(xpath="//sample-x-path-for-text-field")
	private TextField textField;
	
	@Named("Sample web-layout") @FindBy(xpath="//sample-x-path-for-layout")
	private WebLayout layout;
	
	@FindBy(xpath="//sample-x-path-for-layouts-list")
	private PageObjectList<WebLayout> layouts;

	public TextField getTextField() {
		return textField;
	}

	public void setTextField(TextField textField) {
		this.textField = textField;
	}

	public PageObjectList<WebLayout> getLayouts() {
		return layouts;
	}

	public void setLayouts(PageObjectList<WebLayout> layouts) {
		this.layouts = layouts;
	}

	public WebLayout getLayout() {
		return layout;
	}

	public void setLayout(WebLayout layout) {
		this.layout = layout;
	}
}
