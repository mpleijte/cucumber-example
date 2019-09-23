# cucumber-example
Hello Word example for testing test NS reisplanner

* download [chromedriver](https://chromedriver.chromium.org/downloads) (_if you don't have it already_) 
* clone this .git project 
`git clone git@github.com:mpleijte/cucumber-example.git`
* open terminal and change directory to root of cloned project
* run `mvn test`*


\* Note that [ReisPlanner.java](https://github.com/mpleijte/cucumber-example/blob/master/src/test/java/step_definitions/ReisPlanner.java) has hardcoded path to chromedriver.exe
```
public static ChromeDriver getDriver() {
    final String binaryPath = "C:\\applications\\drivers\\chromedriver.exe";
    java.lang.System.setProperty("webdriver.chrome.driver", binaryPath);

    ChromeOptions options = new ChromeOptions();
    options.addArguments("disable-infobars");

    return new ChromeDriver(options);
} 
```
\** Note that chromedriver and your current chromebrowser should be version compatible (will throw error if incompatible) 

