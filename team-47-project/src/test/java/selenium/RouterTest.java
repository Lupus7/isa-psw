package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import team47pack.pages.SearchPage;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertNotNull;


public class RouterTest {

    private WebDriver browser;

    private SearchPage searchPage;
    private  Actions builder;

    @BeforeMethod
    public void setUp() {
        // instantiate chrome browser
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        browser = new ChromeDriver();
        builder = new Actions(browser);
        browser.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        // instantiate firefox browser
//        System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver");
//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.setCapability("marionette",true);


       // browser = new FirefoxDriver(firefoxOptions);

        //maximize window
        browser.manage().window().maximize();

        //navigate
        browser.navigate().to("http://localhost:8081/#/login");

        searchPage = PageFactory.initElements(browser, SearchPage.class);

    }
    // 3.12
    @Test
    public void ArrangeAndDisarangeExamination() {
        //this.browser.get("http://localhost:8081/#/login");

        WebElement element = this.browser.findElement(By.name("email"));
        assertNotNull(element);
        element.sendKeys("test2");
        WebElement element2 = this.browser.findElement(By.name("password"));
        element2.sendKeys("test");
        WebElement button = this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[2]/form/fieldset/center/input"));
        button.click();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement element3 = this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[1]/nav/a[1]"));
        element3.click();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Action mousetoVisit = builder.moveToElement(this.browser.findElement(By.id("smrdljivodugme"))).build();
        WebElement visitButton = this.browser.findElement(By.xpath("//*[@id=\"card\"]/div/button"));
        mousetoVisit.perform();
        visitButton.click();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement clickToDisaragne = this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[2]/table/tbody/tr[1]/td[7]/button"));
        clickToDisaragne.click();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Action mousetoVisit2 = builder.moveToElement(this.browser.findElement(By.id("smrdljivodugme"))).build();
        WebElement visitButton2 = this.browser.findElement(By.xpath("//*[@id=\"card\"]/div/button"));
        mousetoVisit2.perform();
        visitButton2.click();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement clickToArrange = this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[2]/table/tbody/tr/td[7]/button"));
        clickToArrange.click();


    }
    // 3.13
    @Test
    public void serachClinicsAndArrangeExams() throws AWTException, InterruptedException {
        //this.browser.get("http://localhost:8081/#/login");

        WebElement element = this.browser.findElement(By.name("email"));
        assertNotNull(element);
        element.sendKeys("test2");
        WebElement element2 = this.browser.findElement(By.name("password"));
        element2.sendKeys("test");
        WebElement button = this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[2]/form/fieldset/center/input"));
        button.click();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement element3 = this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[1]/nav/a[1]"));
        element3.click();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Action clinicSearch = builder.moveToElement(this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/table[1]/tr/td[2]/button"))).build();
        WebElement clinicSearchButton = this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/table[1]/tr/td[2]/button"));
        clinicSearch.perform();
        clinicSearchButton.click();
        //
        WebElement typeOFExaminationINputField = this.browser.findElement(By.xpath("//*[@id=\"type\"]"));
        typeOFExaminationINputField.sendKeys("Co");
        WebElement searchButton = this.browser.findElement(By.xpath("//*[@id=\"clinicsearch\"]/table/tr[2]/td[3]/button"));
        searchButton.click();
        WebElement seeAvailableDoctorsButton = this.browser.findElement(By.xpath("//*[@id=\"clinics\"]/tbody/tr[1]/td[5]/button"));
        Point coordinates = seeAvailableDoctorsButton.getLocation();
        Robot robot = new Robot();
        robot.mouseMove(coordinates.getX(),coordinates.getY()+120);
        Actions a = new Actions(this.browser);
        a.moveToElement(seeAvailableDoctorsButton);
        seeAvailableDoctorsButton.click();

        WebElement visitDoctorButton = this.browser.findElement(By.xpath("//*[@id=\"doctorresult\"]/tbody/tr/td[6]/button"));
        visitDoctorButton.click();
        //wait to render
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement clickOnCalendar = this.browser.findElement(By.xpath("        //*[@id=\"app\"]/div[2]/div/div[2]/div/div/div[2]/div/table/tbody/tr/td/div/div/div[3]/table/tbody/tr/td[2]/div/div[2]/a[6]/div/div[2]\n"));
        clickOnCalendar.click();

    }
    //3.18
    @Test
    public void ArrangeRoomProcedure() {

        WebElement element = this.browser.findElement(By.name("email"));
        assertNotNull(element);
        element.sendKeys("cadmin");
        WebElement element2 = this.browser.findElement(By.name("password"));
        element2.sendKeys("test");
        WebElement button = this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[2]/form/fieldset/center/input"));
        button.click();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement element3 = this.browser.findElement(By.xpath("//*[@id=\\\"app\\\"]/div[1]/nav/a[2]"));
        element3.click();
        
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Action mousetoVisit = builder.moveToElement(this.browser.findElement(By.id("clinicButton"))).build();
        WebElement visitButton = this.browser.findElement(By.xpath("//*[@id=\"clinicButtoon\"]"));
        mousetoVisit.perform();
        visitButton.click();
        
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Action mousetoVisit1 = builder.moveToElement(this.browser.findElement(By.id("examButton"))).build();
        WebElement visitButton1 = this.browser.findElement(By.xpath("//*[@id=\"examButtoon\"]"));
        mousetoVisit1.perform();
        visitButton1.click();
        /////
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement visitButton2 = this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/table[1]/tbody/tr[1]/td[6]/button"));
        visitButton2.click();
        
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement visitButton3 = this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/table/tbody/tr[2]/td[7]/button"));
        visitButton3.click();
        
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement visitButton4 = this.browser.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div[2]/div/div[2]/form/center[1]/button"));
        visitButton4.click();
        
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

      
     
    }

    @AfterMethod
    public void tearDown() {
        browser.close();
    }


}
