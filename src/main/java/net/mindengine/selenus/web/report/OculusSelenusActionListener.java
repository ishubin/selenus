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
package net.mindengine.selenus.web.report;

import net.mindengine.oculus.experior.reporter.MessageBuilder;
import net.mindengine.oculus.experior.reporter.Report;
import net.mindengine.oculus.experior.reporter.ReportDesign;
import net.mindengine.oculus.experior.reporter.ReportIcon;
import net.mindengine.selenus.web.Browser;
import net.mindengine.selenus.web.objects.AbstractPageObject;
import net.mindengine.selenus.web.objects.SelenusActionListener;

import org.openqa.selenium.By;

/**
 * Used for reporting of all actions in page object using Oculus reporter
 * @author ishubin
 *
 */
public class OculusSelenusActionListener implements SelenusActionListener {

	
	private static final String CLICK_DEFAULT_MESSAGE = "Click ${pageObject.fullName}";
	private static final String DRAG_AND_DROP_DEFAULT_MESSAGE = "Drag ${pageObject.fullName} and drop it on ${targetPageObject.fullName}";
	private static final String DRAG_AND_DROP_BY_DEFAULT_MESSAGE = "Drag ${pageObject.fullName} and drop it by [${xOffset}, ${yOffset}] offset";
	private static final String SELECT_BY_VALUE_DEFAULT_MESSAGE = "Select value \"[string]${value}[/string]\" in ${pageObject.fullName}";
	private static final String SELECT_BY_INDEX_DEFAULT_MESSAGE = "Select #${index} option in ${pageObject.fullName}";
	private static final String SELECT_BY_TEXT_DEFAULT_MESSAGE = "Select option \"[string]${text}[/string]\" in ${pageObject.fullName}";
	private static final String DESELECT_ALL_DEFAULT_MESSAGE = "Remove selection from all options in ${fullName}";
	private static final String DESELECT_BY_INDEX_DEFAULT_MESSAGE = "Remove selection for #${index} option in ${pageObject.fullName}";
	private static final String DESELECT_BY_VALUE_DEFAULT_MESSAGE = "Remove selection for value \"[string]${value}[/string]\" in ${pageObject.fullName}";
	private static final String DESELECT_BY_TEXT_DEFAULT_MESSAGE = "Remove selection for option \"[string]${text}[/string]\" in ${pageObject.fullName}";
	private static final String TYPE_KEYS_DEFAULT_MESSAGE = "Type keys \"[string]${keys}[/string]\" in ${pageObject.fullName}";
	private static final String TYPE_DEFAULT_MESSAGE = "Type text \"[string]${text}[/string]\" in ${pageObject.fullName}";
	private static final String CLEAR_DEFAULT_MESSAGE = "Clear text in ${pageObject.fullName}";
	private static final String OPENURL_DEFAULT_MESSAGE = "Open [url]${url}[/url] in \"[string]${browser.name}[/string]\" [b]${browser.type}[/b] browser";
	private Report report;
	
	public OculusSelenusActionListener(Report report) {
		setReport(report);
	}
	
	private MessageBuilder msg(String method, String defaultMessage, AbstractPageObject pageObject) {
		return report.message("Selenus." + method, defaultMessage)
				.put("pageObject", pageObject);
	}
	
	protected String locatorToString(By by) {
	    if ( by != null ) {
	        return by.toString();
	    }
	    else {
	        return "Unknown locator";
	    }
	}
	
	protected String findFullLocator(AbstractPageObject pageObject) {
	    String locator = ReportDesign.locator(locatorToString(pageObject.getLocator()));
	    if ( pageObject.getParentLayout() != null  ) {
	        locator = findFullLocator(pageObject.getParentLayout()) + " > " + locator;
	    }
	    return locator;
	}
	
	@Override
	public void openUrl(String url, Browser browser) {
		if ( report != null ) {
			report.info(report.message("Selenus.openUrl", OPENURL_DEFAULT_MESSAGE).put("url", url).put("browser", browser).toString()).icon(ReportIcon.OPEN);
		}
	}
	
	@Override
	public void click(AbstractPageObject pageObject) {
		if ( report != null ) {
			report.info(msg("click", CLICK_DEFAULT_MESSAGE, pageObject).toString()).icon(ReportIcon.UI_CLICK).details(findFullLocator(pageObject));
		}
	}

	@Override
	public void dragAndDrop(AbstractPageObject pageObject, AbstractPageObject targetPageObject) {
		if ( report != null ) {
			report.info(msg("dragAndDrop", DRAG_AND_DROP_DEFAULT_MESSAGE, pageObject)
					.put("targetPageObject", targetPageObject)
					.toString()).icon(ReportIcon.UI_CLICK).details("pageObject: " + findFullLocator(pageObject) + ReportDesign.breakline() + "target: " +findFullLocator(targetPageObject));
		}

	}

	@Override
	public void dragAndDropBy(AbstractPageObject pageObject, int xOffset, int yOffset) {
		if ( report != null ) {
			report.info(msg("dragAndDropBy", DRAG_AND_DROP_BY_DEFAULT_MESSAGE, pageObject)
					.put("xOffset", xOffset)
					.put("yOffset", yOffset)
					.toString()).icon(ReportIcon.UI_CLICK).details(findFullLocator(pageObject));
		}
	}

	@Override
	public void selectByValue(AbstractPageObject pageObject, String value) {
		if ( report != null ) {
			report.info(msg("selectByValue", SELECT_BY_VALUE_DEFAULT_MESSAGE, pageObject).put("value", value).toString()).icon(ReportIcon.UI_CLICK).details(findFullLocator(pageObject));
		}
	}

	@Override
	public void selectByIndex(AbstractPageObject pageObject, int index) {
		if ( report != null ) {
			report.info(msg("selectByIndex", SELECT_BY_INDEX_DEFAULT_MESSAGE, pageObject).put("index", index).toString()).icon(ReportIcon.UI_CLICK).details(findFullLocator(pageObject));
		}
	}

	@Override
	public void selectByText(AbstractPageObject pageObject, String text) {
		if ( report != null ) {
			report.info(msg("selectByText", SELECT_BY_TEXT_DEFAULT_MESSAGE, pageObject).put("text", text).toString()).icon(ReportIcon.UI_CLICK).details(findFullLocator(pageObject));
		}
	}

	@Override
	public void deselectAll(AbstractPageObject pageObject) {
		if ( report != null ) {
			report.info(msg("deselectAll", DESELECT_ALL_DEFAULT_MESSAGE, pageObject).toString()).icon(ReportIcon.UI_CLICK).details(findFullLocator(pageObject));
		}
	}

	@Override
	public void deselectByIndex(AbstractPageObject pageObject, int index) {
		if ( report != null ) {
			report.info(msg("deselectByIndex", DESELECT_BY_INDEX_DEFAULT_MESSAGE, pageObject).put("index", index).toString()).icon(ReportIcon.UI_CLICK).details(findFullLocator(pageObject));
		}
	}

	@Override
	public void deselectByValue(AbstractPageObject pageObject, String value) {
		if ( report != null ) {
			report.info(msg("deselectByValue", DESELECT_BY_VALUE_DEFAULT_MESSAGE, pageObject).put("value", value).toString()).icon(ReportIcon.UI_CLICK).details(findFullLocator(pageObject));
		}
	}

	@Override
	public void deselectByText(AbstractPageObject pageObject, String text) {
		if ( report != null ) {
			report.info(msg("deselectByText", DESELECT_BY_TEXT_DEFAULT_MESSAGE, pageObject).put("text", text).toString()).icon(ReportIcon.UI_CLICK).details(findFullLocator(pageObject));
		}
	}

	@Override
	public void typeKeys(AbstractPageObject pageObject, String keys) {
		if ( report != null ) {
			report.info(msg("typeKeys", TYPE_KEYS_DEFAULT_MESSAGE, pageObject).put("keys", keys).toString()).icon(ReportIcon.UI_TYPE).details(findFullLocator(pageObject));
		}
	}

	@Override
	public void type(AbstractPageObject pageObject, String text) {
		if ( report != null ) {
			report.info(msg("type", TYPE_DEFAULT_MESSAGE, pageObject).put("text", text).toString()).icon(ReportIcon.UI_TYPE).details(findFullLocator(pageObject));
		}
	}

	@Override
	public void clear(AbstractPageObject pageObject) {
		if ( report != null ) {
			report.info(msg("clear", CLEAR_DEFAULT_MESSAGE, pageObject).toString()).icon(ReportIcon.UI_TYPE).details(findFullLocator(pageObject));
		}
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

}
