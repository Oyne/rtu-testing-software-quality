package orangeHRM.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("cucumber/mytest.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "classpath:orangeHRM.steps")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:resources.features")
public class CucumberTestRunner {
}
