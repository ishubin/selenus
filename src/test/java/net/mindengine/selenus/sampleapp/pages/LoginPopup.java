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
import net.mindengine.selenus.web.objects.Label;
import net.mindengine.selenus.web.objects.Link;
import net.mindengine.selenus.web.objects.WebLayout;
import net.mindengine.selenus.web.objects.form.Button;
import net.mindengine.selenus.web.objects.form.TextField;

public class LoginPopup extends WebLayout {

	@Named("Login") @FindBy(css="#login-popup-login span") 
	private Label loginLabel;
	
	@Named("Login") @FindBy(css="#login-popup input[name=login]")
	private TextField loginTextField;
	
	@Named("Password") @FindBy(css="#login-popup-password span") 
	private Label passwordLabel;
	
	@Named("Password") @FindBy(css="#login-popup input[name=password]")
	private TextField passwordTextField;
	
	@Named("Button") @FindBy(css="#login-popup input[type=Submit]")
	private Button loginButton;
	
	@Named("Cancel") @FindBy(css="#login-popup .cancel")
	private Link cancelLink;

	public Label getLoginLabel() {
		return loginLabel;
	}

	public TextField getLoginTextField() {
		return loginTextField;
	}

	public Label getPasswordLabel() {
		return passwordLabel;
	}

	public TextField getPasswordTextField() {
		return passwordTextField;
	}

	public Button getLoginButton() {
		return loginButton;
	}

	public Link getCancelLink() {
		return cancelLink;
	}
}
