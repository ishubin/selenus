package net.mindengine.selenus.web.objects.form;

public class TextField extends AbstractFormObject {

	
	/**
	 * Sends keys to text-field without erasing text
	 * @param keys
	 */
	public void typeKeys(String keys) {
		findWebDriverElement().sendKeys(keys);
	}
	
	/**
	 * Removes the text and type the new one
	 * @param text
	 */
	public void type(String text) {
		findWebDriverElement().clear();
		findWebDriverElement().sendKeys(text);
	}
	
	/**
	 * Removes the text from text-field
	 */
	public void clear() {
		findWebDriverElement().clear();
	}
	
	public String getText() {
		return getValue();
	}
	
	@Override
	public String getTypeString() {
		return "text-field";
	}

}
