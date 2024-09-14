package org.example;

//K.D.Y.Niwarthana
//AS2020979

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Main {

    protected static WebDriver driver;
    @BeforeTest

    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\ASUS\\IdeaProjects\\SeleniumAssignment\\target\\classes\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }


    @AfterTest
    public void tearDown(){


    }
    public static void main(String[] args) {
        // This is the main method where your test execution logic can be implemented
        // It's not where TestNG annotations like @BeforeTest should be placed
    }
}