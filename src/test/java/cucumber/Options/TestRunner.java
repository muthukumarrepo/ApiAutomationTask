package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features", plugin = {"pretty", "html:target/cucumber-html-report"}, publish = true, glue= {"stepDefinitions"})
public class TestRunner {

}