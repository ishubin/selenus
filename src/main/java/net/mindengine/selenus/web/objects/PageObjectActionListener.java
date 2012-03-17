package net.mindengine.selenus.web.objects;

public interface PageObjectActionListener {
	
	public void onClick(AbstractPageObject pageObject);
	
	public void onDragAndDrop(AbstractPageObject pageObject, AbstractPageObject targetPageObject);
	
	public void onDragAndDropBy(AbstractPageObject pageObject, int xOffset, int yOffset);
	
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
