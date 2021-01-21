package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "fileUpload")
    private WebElement fileUpload;

    @FindBy(id = "fileSubmitButton")
    private WebElement fileSubmitButton;

    @FindBy(id = "showNoteButton")
    private WebElement showNoteButton;

    @FindBy(id = "showCredentialButton")
    private WebElement showCredentialButton;

    @FindBy(id = "edit_note")
    private WebElement editButton;

    @FindBy(id = "delete_note")
    private WebElement deleteNoteButton;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "logout_button")
    private WebElement logoutButton;

    @FindBy(id = "note_title")
    private WebElement title;

    @FindBy(id = "note_description")
    private WebElement noteDescription;

    @FindBy(id = "note_title_text")
    private WebElement noteTitleText;

    @FindBy(id = "note_description_text")
    private WebElement noteDescText;

    @FindBy(id = "credentialSubmit")
    private WebElement credSubmit;

    @FindBy(id = "credential_password")
    private WebElement credential_password;

    @FindBy(id = "credential_url")
    private WebElement credential_url;

    @FindBy(id = "credential_username")
    private WebElement credential_username;

    @FindBy(id = "credential_password_input")
    private WebElement credential_password_input;

    @FindBy(id = "credential_url_input")
    private WebElement credential_url_input;

    @FindBy(id = "credential_username_input")
    private WebElement credential_username_input;

    @FindBy(id = "edit_credential")
    private WebElement editCredentialButton;

    @FindBy(id = "delete_credential")
    private WebElement deleteCredentialButton;



    public String getNoteTitle() {
        noteTitleText = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("note_title_text")));
        return noteTitleText.getText();
    }

    public String getNoteDescription() {
        noteDescText = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("note_description_text")));
        return noteDescText.getText();
    }

    public void executeLogout() {
        logoutButton.click();
    }

    public void showNoteModal() {
        showNoteButton.click();
    }

    public void showCredentialModal() {
        showCredentialButton = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("showCredentialButton")));
        showCredentialButton.click();
    }

    public void clickNotesTab() {
        notesTab.click();
        showNoteButton = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("showNoteButton")));
    }

    public void clickCredentialsTab() {
        credentialTab.click();
    }

    public boolean isElementPresent(WebDriver driver, String identifier) {
        try {
            driver.findElement(By.id(identifier));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void typeNoteInput(String noteTitle, String noteDesc) {
        title = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("note_title")));
        noteDescription = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("note_description")));
        title.sendKeys(noteTitle);
        noteDescription.sendKeys(noteDesc);
    }

    public void typeCredInput(String url, String username, String password) {
        credential_url_input = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("credential_url_input")));
        credential_username_input = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("credential_username_input")));
        credential_password_input = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("credential_password_input")));
        credential_url_input.sendKeys(url);
        credential_username_input.sendKeys(username);
        credential_password_input.sendKeys(password);
    }

    public void submitNote() {
        // TODO: Why no need to reassign 'noteSubmit' although it is also button in the modal?
        // TODO: Even it stops if I reassign the WebElement of 'noteSubmit' Why??
        noteSubmit.submit();
    }

    public void submitCredential() {
        credSubmit.submit();
    }

    public void clickNoteEditButton() {
        editButton = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("edit_note")));
        editButton.click();
    }

    public void deleteNote() {
        deleteNoteButton = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("delete_note")));
        deleteNoteButton.click();
    }

    public String getUrl() {
        credential_url = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("credential_url")));
        return credential_url.getText();
    }

    public String getUsername() {
        credential_username = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("credential_username")));
        return credential_username.getText();
    }

    public String getPassword() {
        credential_password = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("credential_password")));
        return credential_password.getText();
    }

    public String getUrlInput() {
        credential_url_input = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("credential_url_input")));
        return credential_url.getText();
    }

    public String getUsernameInput() {
        credential_username_input = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("credential_username_input")));
        return credential_username.getText();
    }

    public String getPasswordInput() {
        credential_password_input = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("credential_password_input")));
        return credential_password.getText();
    }

    public void clickCredEditButton() {
        editCredentialButton = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("edit_credential")));
        editCredentialButton.click();
    }

    public void clickCredDeleteButton() {
        deleteCredentialButton = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("delete_credential")));
        deleteCredentialButton.click();
    }
}
