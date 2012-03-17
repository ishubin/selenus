package net.mindengine.selenus.web.objects;

public class Image extends AbstractPageObject {
	
	public String getSource() {
		return getTagAttribute("src");
	}

	@Override
	public String getTypeString() {
		return "image";
	}

}
