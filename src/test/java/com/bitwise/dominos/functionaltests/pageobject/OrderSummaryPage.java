package com.bitwise.dominos.functionaltests.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by samarthsi on 8/26/2016.
 */
public class OrderSummaryPage {

    @FindBy(css = "b.pizzaName")
    private WebElement firstOrderedPizzaName;

    public OrderSummaryPage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }

    public boolean getPizzaName(String pizzaName){
        return firstOrderedPizzaName.getText().equals(pizzaName);
    }
}
