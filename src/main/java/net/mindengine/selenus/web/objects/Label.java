package net.mindengine.selenus.web.objects;

public class Label extends AbstractPageObject {

	public String getText() {
		return findWebDriverElement().getText();
	}
	
	@Override
	public String getTypeString() {
		return "label";
	}

}
