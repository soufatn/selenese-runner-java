package jp.vmi.selenium.selenese.command;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import jp.vmi.selenium.selenese.Runner;
import jp.vmi.selenium.selenese.TestBase;
import jp.vmi.selenium.selenese.TestCase;
import jp.vmi.selenium.selenese.result.Result;
import jp.vmi.selenium.webdriver.WebDriverManager;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * test for {@link Assertion}.
 */
public class AssertionTest extends TestBase {

    /**
     * test of user friendly assertion message.
     *
     * @throws IOException exception.
     */
    @Test
    public void userFriendlyAssertionMessage() throws IOException {
        Command cmd01 = new Open(1, "open", new String[] { "/assertion.html" }, "open", false);
        Command cmd02 = new Assertion(2, "assertTitle", new String[] { "title", "title" }, "assert", "getTitle", false, false);

        File selenesefile = File.createTempFile("selenese", ".html");

        TestCase testcase = new TestCase();
        WebDriverManager wdm = WebDriverManager.getInstance();
        wdm.setWebDriverFactory(WebDriverManager.HTMLUNIT);
        Runner runner = new Runner();
        runner.setDriver(wdm.get());
        testcase.initialize(selenesefile.getPath(), "test", runner, ws.getUrl());

        Result result;
        result = cmd01.doCommand(testcase, runner);
        result = cmd02.doCommand(testcase, runner);

        assertThat(result.getMessage(), is("Failure: Assertion failed (Result: [assertion test] / Expected: [title])"));
    }
}
