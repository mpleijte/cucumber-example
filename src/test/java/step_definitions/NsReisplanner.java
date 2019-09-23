package step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Map;
import java.util.NoSuchElementException;

public class NsReisplanner {

    final private ChromeDriver DRIVER = getDriver();
    final private String COOKIE_CONTAINER = "//iframe[@id='r42CookieBar']";
    final private String COOKIE_ACCEPT_BUTTON = "//a[@class='button accept']";


    private final FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(DRIVER) {}
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(100))
            .ignoring(NoSuchElementException.class);

    @Given("^I am on the Reisplanner homepage$")
    public void visitNsReisplannerHomepage() {

        /** open homepage */
        DRIVER.get("https://www.ns.nl/reisplanner#/");
        fluentWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(COOKIE_CONTAINER)));

        /** switch to cookie popup */
        DRIVER.switchTo().frame("r42CookieBar");

        /** wait for accept button to present and click on it */

        fluentWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(COOKIE_ACCEPT_BUTTON)));
        WebElement acceptCookieBtn = DRIVER.findElement(By.xpath(COOKIE_ACCEPT_BUTTON));
        acceptCookieBtn.click();
    }

    @When("^I choose to travel FROM \"(.*)\"$")
    public void setTravelFromLocation(final String location) {
        /** Wait for input to be present*/
        String pattern = ("//*[@id='location-input-FROM-POSITIONED']");
        final WebElement INPUT_FROM_ELEMENT = DRIVER.findElement(By.xpath(pattern));
        fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(pattern)));
        INPUT_FROM_ELEMENT.click();

        /** Fill in FROM location and submit*/
        INPUT_FROM_ELEMENT.sendKeys(location);
        INPUT_FROM_ELEMENT.submit();
    }

    @When("^I choose to travel TO \"(.*)\"$")
    public void setTravelToLocation(final String location) {
        /** Wait for input to be present*/
        String pattern = ("//*[@id='location-input-TO-POSITIONED']");
        final WebElement INPUT_TO_ELEMENT = DRIVER.findElement(By.xpath(pattern));
        fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(pattern)));
        INPUT_TO_ELEMENT.click();

        /** Fill in TO location and submit*/
        INPUT_TO_ELEMENT.sendKeys(location);
        INPUT_TO_ELEMENT.submit();
    }

    @When("^I click ON \"(.*)\"$")
    public void clickButton(final String text) {
        String xpath = ("//button[.//span[normalize-space(text())='Plannen']]");
        WebElement element = DRIVER.findElement(By.xpath(xpath));
        fluentWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    @Then("the page shows reisadvies with following information:")
    public void thePageShowsReisadviesWithFollowingInformation(final DataTable table) {
        /** Wait for reisdetails-card to be available*/
        fluentWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='rp-reisdetails__journey']")));

        /** Loop through fields and check if they are visible on reisdetails-card  */
        Map<String, String> fields = table.rows(1).asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            String pattern = "//div[@class='rp-reisdetails__journey']/.//span[text()='"+entry.getValue()+"']";
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pattern)));
        }
    }

    @After()
    public void closeBrowser() {
        DRIVER.quit();
    }



    private static ChromeDriver getDriver() {
        final String binaryPath = "C:\\applications\\drivers\\chromedriver.exe";
        java.lang.System.setProperty("webdriver.chrome.driver", binaryPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-infobars",
                "--headless",
                "--start-maximized"
        );

        return new ChromeDriver(options);
    }

}

