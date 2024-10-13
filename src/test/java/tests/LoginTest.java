package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest {

    private ChromeDriver driver;
    private LoginPage loginPage;


    @BeforeMethod
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.google.com/recaptcha/api2/demo");
    }

    @Test
    public void test1(){
        loginPage = new LoginPage(this.driver);
        /*
        loginPage.setUsername("Pedro");
        loginPage.setLastNameInput("Lopez");
        loginPage.setEmail("email@mail.com");
        loginPage.setColor("RED");
        */
        loginPage.setCaptcha();
        System.out.println("");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}