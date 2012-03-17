package net.mindengine.selenus.web.objects;

public class Link extends AbstractPageObject {

	public String getReference() {
		return findWebDriverElement().getAttribute("href");
	}
	
	@Override
	public String getTypeString() {
		return "link";
	}

}
