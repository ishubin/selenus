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
import net.mindengine.selenus.web.objects.PageObjectList;
import net.mindengine.selenus.web.objects.WebLayout;

@Named("Articles")
public class ArticlesPage extends MainPage {

	public static class ArticleLayout extends WebLayout {
		@Named("Title") @FindBy(className="article-title")
		private Label titleLabel;
		
		@Named("Date") @FindBy(className="article-date")
		private Label dateLabel;
		
		@Named("Text") @FindBy(className="article-text")
		private Label textLabel;
		
		@Named("Author") @FindBy(css=".article-footer a")
		private Link authorLink;

		public Label getTitleLabel() {
			return titleLabel;
		}
		public Label getDateLabel() {
			return dateLabel;
		}
		public Label getTextLabel() {
			return textLabel;
		}
		public Link getAuthorLink() {
			return authorLink;
		}
	}
	
	@Named("Articles") @FindBy(xpath="//div[@class='short-article']")
	private PageObjectList<ArticleLayout> articles;

	public PageObjectList<ArticleLayout> getArticles() {
		return articles;
	}
	
}
