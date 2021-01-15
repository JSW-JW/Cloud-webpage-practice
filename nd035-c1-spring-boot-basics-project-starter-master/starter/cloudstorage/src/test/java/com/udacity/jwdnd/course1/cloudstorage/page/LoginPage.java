package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "login_submit_button")
    private WebElement submitButton;

    public void typeLoginInput(String username, String password) {
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
    }

    public void submitLogin() {
        submitButton.submit();
    }
}
