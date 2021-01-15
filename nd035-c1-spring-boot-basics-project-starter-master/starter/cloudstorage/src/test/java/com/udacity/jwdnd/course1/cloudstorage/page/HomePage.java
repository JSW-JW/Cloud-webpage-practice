package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "fileUpload")
    private WebElement fileUpload;

    @FindBy(id = "fileSubmitButton")
    private WebElement fileSubmitButton;

    @FindBy(id = "showNoteButton")
    private WebElement showNoteButton;

    @FindBy(id = "delete_note")
    private WebElement deleteNoteButton;

    @FindBy(id = "note-title")
    private WebElement note_title;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;

    public void getNoteModal() {
        showNoteButton.click();
    }

    public void typeNoteInput(String noteTitle, String noteDesc) {
        note_title.sendKeys(noteTitle);
        noteDescription.sendKeys(noteDesc);
    }

    public void submitNote() {
        noteSubmit.submit();
    }

    public void getNotesList() {

    }

    public void deleteNote() {
        deleteNoteButton.click();
    }
}
