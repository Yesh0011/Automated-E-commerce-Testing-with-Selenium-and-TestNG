package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class Mobile extends Main{

    //Variable declarations
    private String itemTitleText;
    private String price;
    private String quantity;
    private String qty;
    private String title;
    private String checkprice;

    //Select the item name
    @Test(priority = 10)
    public void SelectItemName() {
        itemTitleText = driver.findElement(By.cssSelector("h1.x-item-title__mainTitle")).getText().trim();
        System.out.println("Item title:" + itemTitleText);
    }

    //Select the item price
    @Test(priority = 11)
    public void SelectItemPrice() {
        price = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[1]/div[3]/div/div/div[1]/span")).getText();
        System.out.println("Item price:" + price);
    }

    //select the quantity
    @Test(priority = 12)
    public void SelectItemQuantity() {
        quantity = driver.findElement(By.id("qtyTextBox")).getAttribute("value");
        System.out.println("Item Quantity: " + quantity);

    }


    //Select color type in the order
    @Test(priority = 13)
    public void setcolour() {
        try {
            WebElement selectBox = driver.findElement(By.cssSelector("select[selectboxlabel='color'],select[selectboxlabel='Manufacturer Color'],select[selectboxlabel='COLOR'],select[selectboxlabel='Color'],select[selectboxlabel='Colour"));
            Select selectBoxLabel = new Select(selectBox);
            selectBoxLabel.selectByIndex(1);
            System.out.println("Color is add");
        } catch (Exception e) {
            System.out.println("Color list not available");
        }
    }

    //Select plug value
    @Test(priority = 14)
    public void setplug() {
        try {
            WebElement selectBox = driver.findElement(By.cssSelector("select[selectboxlabel='plug'],select[selectboxlabel='Plug']"));
            Select selectBoxLabel = new Select(selectBox);
            selectBoxLabel.selectByIndex(1);
            System.out.println("Plug is add");
        } catch (Exception e) {
            System.out.println("Plug list not available");
        }
    }

    //Additionaly added. Reason : Sometimes the first item isn't the one that this assignment shows. In such a case the given item has capacity selection.
    @Test(priority = 15)
    public void setCapacity() {
        try {
            WebElement selectBox = driver.findElement(By.cssSelector("select[selectboxlabel='Storage Capacity'],select[selectboxlabel='Storage Capacity']"));
            Select selectBoxLabel = new Select(selectBox);
            selectBoxLabel.selectByIndex(2);
            System.out.println("Capacity is add");
        } catch (Exception e) {
            System.out.println("Capacity list not available");
        }
    }



    //Add selected items to cart
    @Test(priority = 16)

    public void addToTheCart() throws InterruptedException {
        try {
            WebElement addtoCart = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[1]/div[8]/ul/li[2]/div/a"));

            addtoCart.click();
            System.out.println("add to cart");
            Thread.sleep(4000);


        } catch (Exception e) {
            System.out.println("Not add to cart");


        }
        for (String newTabHandle : driver.getWindowHandles()) {
            driver.switchTo().window(newTabHandle);
        }
    }

    //Assert Shopping cart details
    @Test(priority = 17)
    public void assertShoppingcart() {
        //Check name
        String title = driver.findElement(By.className("item-title")).getText().trim();
        Assert.assertEquals(title, itemTitleText);
        System.out.println("Add to cart details");
        System.out.println("Item title:" + itemTitleText);

        //Check price
        try {
            String checkprice = driver.findElement(By.cssSelector("div[data-test-id=\"SUBTOTAL\"]")).getText().trim();
            Assert.assertEquals(checkprice, price);
            System.out.println("Item price:" + checkprice);
        } catch (Exception e) {
            System.out.println("Prices are not same");
        }

        //Check quantity
        String qty = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div[1]/div/ul/li/div[1]/div/div/div[1]/div/div[3]/div/div[1]/div/span/span")).getText().trim();
        Assert.assertEquals(qty, quantity);
        System.out.println("Item price:" + quantity);


    }

    //Checkout the order
    @Test(priority = 18)
    public void checkOut() {

        WebElement checkout = driver.findElement(By.xpath("//button[@class='btn btn--primary btn--large']"));
        checkout.click();
        System.out.println("Check out");

        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            if (alert != null) {
                System.out.println("Alert is present. Dismissing...");
                alert.dismiss();
                System.out.println("Alert dismissed");
            } else {
                System.out.println("No alert present");
            }

        } catch (Exception e) {
            System.out.println("Exception occurred while handling alert: " + e.getMessage());
        }

    }

    //Continue as the guest to check out
    @Test(priority = 19)
    public void gustMode () {
        WebElement gustmode = driver.findElement(By.xpath("//*[@id=\"gxo-btn\"]"));
        gustmode.click();
        System.out.println("gustMode on");

    }

    //Assert page Title
    @Test(priority = 20)
    public void assertCheckout(){
        String checkoutTitle = driver.findElement(By.cssSelector(".item-title-header")).getText().trim();
        Assert.assertEquals(checkoutTitle, itemTitleText);
        System.out.println("Tile of item:"+itemTitleText);
    }

    //End The Test
    @AfterTest
    public void afterTest(){
        driver.quit();
    }




}
