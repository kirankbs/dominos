package com.bitwise.dominos.functionaltests.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by samarthsi on 8/23/2016.
 */
public class DashboardPage {

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardBtn;

    @FindBy(linkText = "PIZZAS")
    private WebElement pizzasBtn;

    @FindBy(id = "Zesty Chicken")
    private WebElement zestyChickenPizza;

    @FindBy(xpath = "//button[2]")
    private WebElement saveBtn;

    @FindBy(css = "h3")
    private WebElement dashboardHeading;


    public DashboardPage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }

    public void createZestyChickenPizzaOrder(){
        dashboardBtn.click();
        zestyChickenPizza.click();
        saveBtn.click();
    }

    public Boolean isOnDashboardPage(){
        return dashboardHeading.getText().equals("Top Pizzas");
    }


}
