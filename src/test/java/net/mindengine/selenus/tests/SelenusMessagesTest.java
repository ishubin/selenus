package net.mindengine.selenus.tests;

import org.junit.Test;

import junit.framework.Assert;
import net.mindengine.oculus.experior.ExperiorConfig;
import net.mindengine.oculus.experior.framework.report.DefaultReport;
import net.mindengine.oculus.experior.reporter.Report;
import net.mindengine.selenus.web.objects.Link;

public class SelenusMessagesTest {
    
    @Test
    public void checkThatSelenusMessagesAreLoadedFromResources() {
        Report report = new DefaultReport(ExperiorConfig.getInstance().getReportConfiguration());
        
        
        Link link = new Link();
        link.setName("myLink");
        Assert.assertEquals("Clear text in [b]myLink[/b] link", report.message("Selenus.clear").put("pageObject", link).toString());
    }

}
