package testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)


@CucumberOptions(features = "src/test/java/features/ValidateLoginAndProductDetails.feature",
        glue = "stepDefinitions",
        tags = "@ValidateUserDetailsAfterRegistration or @LoginAndAddProductToCart or @CheckOutAndValidateProductInPaymentsPage",
        plugin = {"pretty","html:target/cucumber-html-report","json:target/cucumber-html-report/cucumber.json"},
        monochrome = true,
        strict = true,
        dryRun = false
)

public class Runner {

}