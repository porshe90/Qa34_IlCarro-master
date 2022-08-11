package manager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    WebDriver wd;
    HelperUser helperUser;
    HelperCar car;
    HelperSearch search;
    String browser; //firefox

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init(){
        if(browser.equals(Browser.CHROME.browserName())) {  //chrome
            wd=new ChromeDriver();
        }else if(browser.equals(Browser.FIREFOX.browserName())){   //firefox
             wd=new FirefoxDriver();
        }

       WebDriverListener listener = new MyListener();
       wd=new EventFiringDecorator(listener).decorate(wd);

        logger.info("All Tests run in Chrome Driver");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://ilcarro-1578153671498.web.app");
        logger.info("Current Url --> " +wd.getCurrentUrl());
        helperUser=new HelperUser(wd);
        car= new HelperCar(wd);
        search = new HelperSearch(wd);

    }

    public void stop(){

        wd.quit();
    }

    public HelperSearch search() {
        return search;
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar car() {
        return car;
    }
}
