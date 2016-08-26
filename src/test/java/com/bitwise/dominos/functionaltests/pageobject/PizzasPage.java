package com.bitwise.dominos.functionaltests.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by samarthsi on 8/24/2016.
 */
public class PizzasPage {

    @FindBy(linkText = "PIZZAS")
    private WebElement pizzasBtn;

    @FindBy(css = "button")
    private WebElement checkoutBtn;

    @FindBy(xpath = "//li[6]/b")
    private WebElement zestyChickenPizza;

    @FindBy(xpath = "//button[2]")
    private WebElement saveBtn;


    public PizzasPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void orderZestyChicken() {
        pizzasBtn.click();
        zestyChickenPizza.click();
        saveBtn.click();

    }

    public void checkOutOrder(){
        pizzasBtn.click();
        checkoutBtn.click();
    }


}
