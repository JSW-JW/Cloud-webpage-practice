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
    private ResultPage resultPage;

    private WebDriverWait wait;


    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    /* ------------------- Signup and Login --------------------- */

    @Test
    public void getSignupPage() {
        driver.get("http://localhost:" + this.port + "/signup");
        wait = new WebDriverWait(driver, 1000);
        signupPage = wait.until(webDriver -> new SignupPage(driver));
        Assertions.assertEquals("Sign Up", driver.getTitle());
    }

    @Test
    public void postSignup() {
        signupPage.typeSignupInput(firstName, lastName, username, password);
        signupPage.submitSignup();
    }

    @Test
    public void executeSignup() {
        getSignupPage();
        postSignup();
    }

    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        wait = new WebDriverWait(driver, 1000);
        loginPage = wait.until(webDriver -> new LoginPage(driver));
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void postLogin() {
        loginPage.typeLoginInput(username, password);
        loginPage.submitLogin();
    }

    @Test
    public void executeLogin() {
        getLoginPage();
        postLogin();
    }

    @Test
    public void SignupAndLogin() {
        executeSignup();
        executeLogin();
    }

    /* ------------------- File, Note, Credential Page --------------------- */

    @Test
    public void getHomePage() {
        driver.get("http://localhost:" + this.port + "/home");
        wait = new WebDriverWait(driver, 1000);
        homePage = wait.until(webDriver -> new HomePage(driver));
//        Assertions.assertEquals("Home", driver.getTitle());
    }

    @Test
    public void addNote() {
        SignupAndLogin();
        getHomePage();
        homePage.clickNotesTab();
        homePage.showNoteModal();
        homePage.typeNoteInput(noteTitle, noteDesc);
        homePage.submitNote();
    }

    @Test
    public void editNote() {
        homePage.clickEditButton();
        homePage.typeNoteInput(updatedNoteTitle, updatedNoteDesc);
        homePage.submitNote();
    }

    @Test
    public void deleteNote() {
        SignupAndLogin();
        getHomePage();
        homePage.deleteNote();
    }

    @Test
    public void getResultPage() {
        driver.get("http://localhost:" + this.port + "/result");
        wait = new WebDriverWait(driver, 1000);
        resultPage = wait.until(webDriver -> new ResultPage(driver));
        Assertions.assertEquals("Result", driver.getTitle());

    }

}
