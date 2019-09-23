import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@RunWith(Cucumber.class)
// ,tags="@unit and @browser")
@CucumberOptions(plugin = "pretty"
        ,features ="src/test/resources/features"
        ,tags="@browser")
public class RunCucumberTest {

    @BeforeClass
    public static void before() {

    }

}
