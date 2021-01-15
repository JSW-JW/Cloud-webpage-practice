package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "sign_up_button")
    private WebElement signupButton;

    public void typeSignupInput(String firstName, String lastName, String username, String password){
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
    }

    public void submitSignup() {
        signupButton.submit();
    }
}
