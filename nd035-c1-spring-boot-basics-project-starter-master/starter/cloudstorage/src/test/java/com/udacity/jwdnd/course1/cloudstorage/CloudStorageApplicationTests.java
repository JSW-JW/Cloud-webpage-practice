package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.udacity.jwdnd.course1.cloudstorage.utils.TestUtils.*;
import static com.udacity.jwdnd.course1.cloudstorage.utils.TestUtils.lastName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    private SignupPage signupPage;
    private LoginPage loginPage;
    private HomePage homePage;

    private WebDriverWait wait;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    /* ------------------- Signup and Login --------------------- */

    @Test
    void getSignupPage() {
        driver.get("http://localhost:" + this.port + "/signup");
        wait = new WebDriverWait(driver, 1000);
        signupPage = wait.until(webDriver -> new SignupPage(driver));
        Assertions.assertEquals("Sign Up", driver.getTitle());
    }

    @Test
    void postSignup() {
        signupPage.typeSignupInput(firstName, lastName, username, password);
        signupPage.submitSignup();
    }

    @Test
    void executeSignup() {
        getSignupPage();
        postSignup();
    }

    @Test
    void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        wait = new WebDriverWait(driver, 1000);
        loginPage = wait.until(webDriver -> new LoginPage(driver));
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    void postLogin() {
        loginPage.typeLoginInput(username, password);
        loginPage.submitLogin();
    }

    @Test
    void executeLogin() {
        getLoginPage();
        postLogin();
    }

    @Test
    void executeLogout() {
        getHomePage();
        homePage.executeLogout();
    }

    @Test
    void getHomePage() {
        driver.get("http://localhost:" + this.port + "/home");
        wait = new WebDriverWait(driver, 1000);
        homePage = wait.until(webDriver -> new HomePage(driver));
        Assertions.assertEquals("Home", driver.getTitle());
    }

    @Test
    void signupAndLogin() {
        executeSignup();
        executeLogin();
    }

    @Test
    public void unauthorizedUserAccessibleTest() {
        getSignupPage();
        Assertions.assertEquals("Sign Up", driver.getTitle());
        getLoginPage();
        Assertions.assertEquals("Login", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", driver.getTitle()); // redirect to login page if not authorized
        driver.get("http://localhost:" + this.port + "/note/save");
        Assertions.assertEquals("Login", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/note/delete");
        Assertions.assertEquals("Login", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/file/add");
        Assertions.assertEquals("Login", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/file/download");
        Assertions.assertEquals("Login", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/file/view");
        Assertions.assertEquals("Login", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/file/delete");
        Assertions.assertEquals("Login", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/credential/save");
        Assertions.assertEquals("Login", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/credential/delete");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    void SignupLoginLogoutAndVerifyIfAccessible() {
        executeSignup();
        executeLogin();
        Assertions.assertEquals("Home", driver.getTitle());
        executeLogout();
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    /* -------------------  Note --------------------- */

    @Test
    void beforeEachAddNote() {  // Before testing these methods, ExecuteSignup test should be executed.
        // And Other entities should be empty. In this two principle,
        // all these methods perform adding, deleting entity before/after the test executiton.

        getLoginPage();
        postLogin();
        getHomePage();

        homePage.clickNotesTab();
        homePage.showNoteModal();
        homePage.typeNoteInput(noteTitle, noteDesc);
        homePage.submitNote();

        Assertions.assertEquals(noteTitle, homePage.getNoteTitle());
        Assertions.assertEquals(noteDesc, homePage.getNoteDescription());
    }

    @Test
    void afterEachDeleteNote() { // It would fail if execute this test alone.
        homePage.deleteNote();
        Assertions.assertFalse(homePage.isElementPresent(driver, "note_title_text"));
    }

    @Test
    void addNoteAndCheckIfAddedLovely() {
        beforeEachAddNote();

        afterEachDeleteNote();
    }

    @Test
    void editNoteAndCheckIfUpdatedLovely() {
        beforeEachAddNote();

        homePage.clickNoteEditButton();  // update note
        homePage.typeNoteInput(updatedNoteTitle, updatedNoteDesc);
        homePage.submitNote();
        Assertions.assertEquals(updatedNoteTitle, homePage.getNoteTitle());
        Assertions.assertEquals(updatedNoteDesc, homePage.getNoteDescription());

        afterEachDeleteNote();
    }

    @Test
    void deleteNoteAndCheckIfElementPresent() {
        beforeEachAddNote();

        homePage.deleteNote(); // delete note
        Assertions.assertFalse(homePage.isElementPresent(driver, "note_title_text"));
    }

    /* ------------------- Credential --------------------- */

    @Test
    void beforeEachAddCredential() {  // Before testing these methods, ExecuteSignup test should be executed.
        // And Other entities should be empty. In this two principle,
        // all these methods perform adding, deleting entity before/after the test executiton.
        getLoginPage();
        postLogin();
        getHomePage();

        homePage.clickCredentialsTab();
        homePage.showCredentialModal();
        homePage.typeCredInput(cred_url, cred_username, cred_password);
        homePage.submitCredential();

        Assertions.assertEquals(cred_url, homePage.getUrl());
        Assertions.assertEquals(cred_username, homePage.getUsername());
        Assertions.assertNotEquals(cred_password, homePage.getPassword());
    }

    @Test
    void afterEachDeleteCredential() { // It would fail if execute this test alone.
        homePage.clickCredDeleteButton();
        Assertions.assertFalse(homePage.isElementPresent(driver, "credential_password"));
    }

    @Test
    void addCredentialAndCheckIfEncryptedPassDisplayed() {
        beforeEachAddCredential();

        afterEachDeleteCredential();
    }

    @Test
    void viewUnencryptedPasswordAndUpdateCredential() {
        beforeEachAddCredential(); // For consistent testing. (It should start with empty db set)

        homePage.clickCredEditButton();
        String passwordInput = homePage.getPasswordInput();
        Assertions.assertNotEquals(cred_password, passwordInput); // check if edit view shows unencrypted value

        String passwordBefore = homePage.getPassword(); // remember passwordBefore to compared with passwordAfter.

        homePage.typeCredInput(updated_cred_url, updated_cred_username, updated_cred_password);
        homePage.submitCredential();

        String passwordAfter = homePage.getPassword(); // encoded password after it's updated.
        Assertions.assertNotEquals(passwordBefore, passwordAfter);

        afterEachDeleteCredential();
    }

    @Test
    void deleteCredential() {
        beforeEachAddCredential();

        homePage.clickCredDeleteButton();
        Assertions.assertFalse(homePage.isElementPresent(driver, "credential_password"));
    }

}
