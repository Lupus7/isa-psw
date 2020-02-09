package selenium;

import org.openqa.selenium.By;
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

//    @Test
//    public void testSearchRouteInvalidInput() {
//
//        searchPage.ensureIsDisplayed();
//
//        searchPage.getInputFrom().sendKeys("Belgerudveien 1 (Oslo)");
//        searchPage.getInputTo().sendKeys("Bergerveien (Asker)");
//        //searchPage.getButtonFind().click();
//
//        searchPage.ensureIsDisplayedError();
//
//        assertEquals(searchPage.getSpanError().getText(),
//                "Invalid stop or name in 'from' field. Make sure you spelled it correctly.");
//
//    }
//
//    @Test
//    public void testSearchRoute() {
//        searchPage.ensureIsDisplayed();
//
//        searchPage.getInputFrom().sendKeys("Belgerudveien");
//        (new WebDriverWait(browser, 10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By
//                        .cssSelector("#suggestionlist-for-origin_destination_from0")));
//        browser.findElements(By.cssSelector("#suggestionlist-for-origin_destination_from0")).get(0).click();
//
//
//        searchPage.getInputTo().sendKeys("Bergerveien");
//        (new WebDriverWait(browser, 10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By
//                        .cssSelector("#suggestionlist-for-origin_destination_to0")));
//        browser.findElements(By.cssSelector("#suggestionlist-for-origin_destination_to0")).get(0).click();
//
//        //searchPage.getButtonFind().click();
//
//        (new WebDriverWait(browser, 10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"labeltravelTime\"]")));
//
//        (new WebDriverWait(browser, 10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By
//                        .cssSelector("li.TripPattern:nth-child(1) > div:nth-child(1)")));
//
//        List<WebElement> routes = browser.findElements(By.cssSelector("div.Clickable.TripPattern__summary"));
//        assertEquals(routes.size(), 5);
//
//        routes.get(0).click();
//
//    }


}
