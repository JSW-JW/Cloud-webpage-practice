package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.By;
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

    @FindBy(id = "edit_note0")
    private WebElement editButton;

    @FindBy(id = "delete_note0")
    private WebElement deleteNoteButton;

    @FindBy(id = "note_title")
    private WebElement title;

    @FindBy(id = "note_description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    public void showNoteModal() {
        showNoteButton.click();
    }

    public void clickNotesTab() {
        notesTab.click();
        showNoteButton = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("showNoteButton")));
    }

    public void clickFilesTab() {
        filesTab.click();
    }

    public void clickCredentialsTab() {
        credentialTab.click();
    }

    public void typeNoteInput(String noteTitle, String noteDesc) {
        title = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("note_title")));
        noteDescription = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("note_description")));
        title.sendKeys(noteTitle);
        noteDescription.sendKeys(noteDesc);
    }

    public void submitNote() {
        // TODO: Why no need to findWebElement of 'noteSubmit' although it is also button in the modal?
        // TODO: Even it stops if I reassign the WebElement of 'noteSubmit' Why??
        noteSubmit.submit();
    }

    public void getNotesList() {
        notesTab.click();
    }

    public void clickEditButton() {
        editButton.click();
    }

    public void deleteNote() {
        deleteNoteButton.click();
    }
}
