package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public void openLoginForm(){
        //wd.findElement(By.cssSelector("a[href='/login?url=%2Fsearch']")).click();
        //wd.findElement(By.cssSelector("a[href ^='/login']")).click();
        // wd.findElement(By.xpath("//a[starts-with(@href,'/login']")).click();
        wd.findElement(By.xpath("//a[text()=' Log in ']")).click();
    }

    public void fillLoginForm(String email,String password){
        type(By.id("email"),email);
        takeScreenShots("/Users/tayahatum/Qa34/Qa34_IlCarro/src/test/screenshots/screen-1.png");
        type(By.id("password"),password);


    }
    public void fillLoginForm(User user){
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());

        // for jenkins
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"),user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        //click(By.id("terms-of-use"));
        click(By.cssSelector("label[for='terms-of-use']"));
    }
    public void checkPolicyXY() {

        WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
        Rectangle rect = label.getRect();
        int xOffSet=rect.getWidth()/2;
        int yOffSet = rect.getHeight()/2;

        Actions actions = new Actions(wd);
        actions.moveToElement(label,-xOffSet,0).click().release().perform();



    }

    public void clickOk() {
        if(isElementPresent(By.xpath("//button[text()='Ok']"))) {
            click(By.xpath("//button[text()='Ok']"));
        }
    }

    public boolean isLogged() {

        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
    }

    public void logout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }




    public boolean isErrorPasswordFormatDisplayed() {
        System.out.println(wd.findElement(By.cssSelector("div.error div:last-child")).getText());

        boolean lastChild = new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .textToBePresentInElement(wd.findElement(By.cssSelector("div.error div:last-child")), "Password must contain 1 uppercase letter, 1 lowercase letter and one number"));

        return lastChild;
    }

    public boolean isErrorPasswordSizeDisplayed() {
        System.out.println(wd.findElement(By.cssSelector("div.error div:first-child")).getText());
        return new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .textToBePresentInElement(wd.findElement(By.cssSelector("div.error div:first-child")), "Password must contain minimum 8 symbols"));

    }



    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        clickOk();
    }
}
