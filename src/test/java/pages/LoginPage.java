package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private By firsNameLocator = By.name("input1");
    private By lastNameLocator = By.name("input2");
    private By emailLocator = By.name("input3");
    private By colorRadioLocator = null;
    private By captchaBoxLocator = By.id("recaptcha-anchor");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String firstname) {
        WebElement firstNameInput = (new WebDriverWait(this.driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.visibilityOfElementLocated(firsNameLocator));
        firstNameInput.sendKeys(firstname);
    }

    public void setLastNameInput(String lastname) {
        driver.findElement(lastNameLocator).sendKeys(lastname);
    }

    public void setEmail(String email) {
        driver.findElement(emailLocator).sendKeys(email);
    }

    public void setColor(String color) {
        String myColor = color.toLowerCase();
        switch (myColor) {
            case "red":
                colorRadioLocator = By.id("option1");
                break;
            case "green":
                colorRadioLocator = By.id("option2");
                break;
            default:
                System.out.println("Invalid color");
                break;
        }
        driver.findElement(colorRadioLocator).click();
    }

    public void setCaptcha(){
        String parentWindowHandle = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));

        this.driver.switchTo().frame(this.driver.findElement(By.xpath("//iframe")));
        WebElement captchaBox = wait.until(ExpectedConditions.elementToBeClickable(captchaBoxLocator));
        captchaBox.click();

        driver.switchTo().window(parentWindowHandle);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='recaptcha challenge expires in two minutes']")));

        WebElement audioCaptcha = (new WebDriverWait(this.driver, Duration.ofSeconds(20)))
                .until(ExpectedConditions.elementToBeClickable(By.id("recaptcha-audio-button")));
        if(audioCaptcha.isDisplayed()){
            audioCaptcha.click();
        }

    }
}