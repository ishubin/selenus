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
package net.mindengine.selenus.sampleapp.pages;

import org.openqa.selenium.support.FindBy;

import net.mindengine.selenus.annotations.Named;
import net.mindengine.selenus.web.Page;
import net.mindengine.selenus.web.objects.Link;
import net.mindengine.selenus.web.objects.WebLayout;

@Named("Main")
public class MainPage extends Page {

	@Named("Header") @FindBy(id="logo")
	private Link headerLink;
	
	@Named("Login") @FindBy(css="#loginLink")
	private Link loginLink;
	
	@Named("Login") @FindBy(xpath="//div[@id='login-popup']")
	private LoginPopup loginPopup;

	
	public static class Menu extends WebLayout {
		@Named("Articles") @FindBy(linkText="Articles")
		private Link articlesLink;

		public Link getArticlesLink() {
			return articlesLink;
		}
	}
	
	@Named("Menu") @FindBy(id="navigation")
	private Menu menu;
	
	public Menu getMenu() {
		return menu;
	}
	public Link getHeaderLink() {
		return headerLink;
	}

	public Link getLoginLink() {
		return loginLink;
	}

	public LoginPopup getLoginPopup() {
		return loginPopup;
	}
	
}
