package net.mindengine.selenus.tests.sampleapp;

import java.io.File;

import net.mindengine.oculus.experior.exception.TestConfigurationException;
import net.mindengine.oculus.experior.suite.Suite;
import net.mindengine.oculus.experior.suite.XmlSuiteParser;
import net.mindengine.oculus.experior.test.TestLauncher;

import org.junit.Test;

public class SelenusOculusTest {

	@Test
	public void selenusOculusTest() throws TestConfigurationException {
		Suite suite;
        try {
            suite = XmlSuiteParser.parse(new File(getClass().getResource("/suite-simple.xml").getFile()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        TestLauncher launcher = new TestLauncher();
        launcher.setSuite(suite);
        launcher.launch();
	}
}
