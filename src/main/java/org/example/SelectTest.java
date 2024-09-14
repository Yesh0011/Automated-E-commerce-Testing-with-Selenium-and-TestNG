package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//Go to the ebay site
public class SelectTest extends Main{
    @Test(priority = 1)
    @Parameters({"ebay"})
    public void getUrl(String ebay){
        driver.get(ebay);
    }

    //Check the url is correct or not
    @Test(priority = 2)
    public void verifyUrl(){
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.ebay.com/");
    }

    //Select a category as Cell Phones & Accessories
    @Test(priority = 3)
    @Parameters({"ebay"})
    public void selectCategory() {
        WebElement dropDown = driver.findElement(By.id("gh-cat"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("Cell Phones & Accessories");
    }

    //Verify the selected category is correct or not
    @Test(priority = 4)
    @Parameters({"ebay"})
    public void verifyCategory(String ebay) {
        WebElement selectedCategoryElement = driver.findElement(By.id("gh-cat"));
        String selectedCategoryText = selectedCategoryElement.getText();
        Assert.assertTrue(selectedCategoryText.contains("Cell Phones & Accessories"), "Expected category 'Cell Phones & Accessories' not found in selected category: " + selectedCategoryText);

    }

    //Search mobile phone in the search bar
    @Test(priority = 5)
    @Parameters({"ebay"})
    public void searchMobile(String ebay) {
        WebElement searchBar = driver.findElement(By.id("gh-ac"));
        searchBar.sendKeys("Mobile Phone");
        driver.findElement(By.id("gh-btn")).click();
    }

    //Assert first 5 values
    @Test(priority = 7)
    public void assertSearchResultValueMobilePhone() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-item")));

        List<WebElement> searchResults = driver.findElements(By.cssSelector(".s-item"));
        for (int i = 0; i < Math.min(5, searchResults.size()); i++) {
            String itemTitle = searchResults.get(i).findElement(By.cssSelector(".s-item__title")).getText();
            String expectedTitle = "Mobile Phone";

            // Check for exact match using equalsIgnoreCase
            if (itemTitle.trim().equalsIgnoreCase(expectedTitle)) {
                System.out.println("Search result #" + (i + 1) + " contains 'Mobile Phone' in title: " + itemTitle);
            } else {
                Assert.fail("Search result #" + (i + 1) + " does not contain 'Mobile Phone' in title: " + itemTitle);
            }
        }
    }

    //Print asserted values to the console
    @Test(priority = 8)
    public void extractAndPrintDetails(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-item")));

        List<WebElement> searchResults = driver.findElements(By.cssSelector(".s-item"));
        for (int i = 0; i < Math.min(5, searchResults.size()); i++) {
            WebElement item = searchResults.get(i);
            String itemName = item.findElement(By.cssSelector(".s-item__title")).getText();
            String itemPrice = item.findElement(By.cssSelector(".s-item__price")).getText();
            System.out.println("Item #" + (i + 1) + ": " + itemName + " - Price: " + itemPrice);
        }
    }

    //Select the first item in the window
    @Test(priority = 9)
    public void selectFirstItem() throws InterruptedException {
        // Select the first search item
        WebElement firstItem = driver.findElement(By.xpath("//*[@id=\"srp-river-results\"]/ul/li[2]/div/div[2]/a/div"));
        firstItem.click();
        Thread.sleep(4000);

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }




}
