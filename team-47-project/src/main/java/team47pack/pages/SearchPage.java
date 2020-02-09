package team47pack.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"origin_destination_from\"]")
    private WebElement inputFrom;

    @FindBy(xpath = "//*[@id=\"origin_destination_to\"]")
    private WebElement inputTo;

    @FindBy(xpath = "//*[@id=\"findTravelForm\"]/div[4]/input")
    private WebElement buttonFind;

    @FindBy(xpath = "//*[@id=\"InputError_origin_destination_from\"]")
    private WebElement spanError;

    //*[@id="typeahead-00E-9662-option-0"]
    //*[@id="typeahead-00E-9662-option-0"]
    //*[@id="typeahead-00E-2308-option-0"]
    //#typeahead-00E-2308-option-0

    @FindBy(xpath = "//*[@id=\"typeahead-00E-2170-option-0\"]/div/span[1]")
    private WebElement selectItemFrom;

    @FindBy(xpath = "//*[@id=\"typeahead-00E-2170-option-0\"]/div/span[1]")
    private WebElement selectItemTo;

    public SearchPage() {
    }

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(inputFrom));
    }

    public void ensureIsDisplayedError(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(spanError));
    }

    public void ensureIsDisplayedSelectFrom(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(selectItemFrom));
    }

    public void ensureIsDisplayedSelectTo(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(selectItemTo));
    }

    public WebElement getInputFrom() {
        return inputFrom;
    }

    public WebElement getInputTo() {
        return inputTo;
    }

    public WebElement getButtonFind() {
        return buttonFind;
    }

    public WebElement getSpanError() {
        return spanError;
    }

    public WebElement getSelectItemFrom() {
        return selectItemFrom;
    }

    public WebElement getSelectItemTo() {
        return selectItemTo;
    }
}
