package net.mindengine.selenus.exceptions;

import net.mindengine.selenus.web.Page;
import net.mindengine.selenus.web.objects.AbstractPageObject;

public class PageObjectIsNotAvailableException extends SelenusException {

    /**
     * 
     */
    private static final long serialVersionUID = -6353908826822920520L;
    private AbstractPageObject pageObject;

    
    public PageObjectIsNotAvailableException( AbstractPageObject pageObject) {
        super(msg(pageObject));
        this.pageObject = pageObject;
    }
    
    private static String msg(AbstractPageObject pageObject) {
        
        Page page = pageObject.searchForPage();
        
        String message = pageObject.getFullNonPrettyName() + " is not avalaible on ";
        
        if ( page != null ) {
            message += " '" + page.getName() + "' page"; 
        }
        return message;
    }

    public PageObjectIsNotAvailableException( AbstractPageObject pageObject, Throwable cause) {
        super(msg(pageObject), cause);
        this.pageObject = pageObject;
    }

    public AbstractPageObject getPageObject() {
        return pageObject;
    }
}
