package com.sb.auto.OrangeHRMSpringBootAutomation.framework.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.function.Function;

//@Service
@Lazy
@Component
public class SeleniumCoreUtil {

    @Autowired
    private WebDriver driver;

    @Autowired
    private WebDriverWait webDriverWait;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${default.timeout}")
    private int timeout;

    @Value("${polling.every.time}")
    private int pollingTime;

    @Autowired
    Actions actions;

    private static final Logger log = LogManager.getLogger(SeleniumCoreUtil.class);

    public String getDynamicElement(String xpath, String menuName) {
        return String.format(xpath, menuName);
    }

    @Autowired
    public SeleniumCoreUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToElementByJS(WebElement element)
    {
        //JavascriptExecutor js = this.applicationContext.getBean(JavascriptExecutor.class);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToElement(WebElement element)
    {
        //actions = this.applicationContext.getBean(Actions.class);
        actions.scrollToElement(element).perform();
    }

    public void isDisplay(WebElement element)
    {
        //WebDriverWait webDriverWait = this.applicationContext.getBean(WebDriverWait.class);
        webDriverWait.withTimeout(Duration.ofSeconds(this.timeout))
                .pollingEvery(Duration.ofSeconds(this.pollingTime))
                .ignoring(NoSuchElementException.class);
                /*.until(ExpectedConditions.visibilityOf(element))
                .until((d) -> element.isDisplayed());*/

        WebElement elements = webDriverWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {

                // Scroll to the element
                scrollToElement(element);

                // Check if the element is displayed
                if (element.isDisplayed()) {
                    return element;
                } else {
                    return null;
                }
            }
        });
        log.info("Element is display for: {}", elements);
    }

    public void sendKeys(WebElement element, String text)
    {
        //WebDriverWait webDriverWait = this.applicationContext.getBean(WebDriverWait.class);
        webDriverWait.withTimeout(Duration.ofSeconds(this.timeout))
                .pollingEvery(Duration.ofMillis(this.pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);

        WebElement elements = webDriverWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                scrollToElement(element);
                if (element.isDisplayed() && element.isEnabled()) {
                    return element;
                }
                return null;
            }
        });
        elements.sendKeys(text);
        log.info("Enter text: {} to elements: {} of {}", text, elements, elements.getDomProperty("label"));
        log.info("Enter text: {} to elements: {} of {}", text, elements, elements.getDomAttribute("label"));
    }

    public void click(WebElement element)
    {
        //WebDriverWait webDriverWait = this.applicationContext.getBean(WebDriverWait.class);
        webDriverWait.withTimeout(Duration.ofSeconds(this.timeout))
                .pollingEvery(Duration.ofMillis(this.pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class);

        WebElement elements = webDriverWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                scrollToElement(element);
                if (element.isDisplayed() && element.isEnabled()) {
                    return element;
                }
                return null;
            }
        });
        elements.click();
        log.info("Click on elements: {}", elements);
    }

    public WebElement findElement(By locator)
    {
        //WebDriverWait webDriverWait = this.applicationContext.getBean(WebDriverWait.class);
        webDriverWait.withTimeout(Duration.ofSeconds(this.timeout))
                .pollingEvery(Duration.ofMillis(this.pollingTime))
                .ignoring(NoSuchElementException.class);

       return webDriverWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement el = driver.findElement(locator);
                scrollToElement(el);
                if (el.isDisplayed()) {
                    log.info("find element does exist for: {}", el);
                    return el;
                }
                else {
                    log.info("find element does not exist for: {}", el);
                }
                return null;
            }
        });

    }

    public void selectRadioButton(WebElement element)
    {
        //WebDriverWait webDriverWait = this.applicationContext.getBean(WebDriverWait.class);
        webDriverWait.withTimeout(Duration.ofSeconds(this.timeout))
                .pollingEvery(Duration.ofMillis(this.pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);

        WebElement radioButton = webDriverWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                scrollToElement(element);
                if (element.isDisplayed() && element.isEnabled() && !element.isSelected()) {
                    return element;
                }
                return null;
            }
        });
        log.info("select radio button for: {}", element);
        log.info("select radio button for: {}", element.getDomAttribute("value"));
        radioButton.click();
    }

    public void selectCheckBox(WebElement element, boolean shouldSelect)
    {
        //WebDriverWait webDriverWait = this.applicationContext.getBean(WebDriverWait.class);
        webDriverWait.withTimeout(Duration.ofSeconds(this.timeout))
                .pollingEvery(Duration.ofMillis(this.pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);

        WebElement checkbox = webDriverWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                scrollToElement(element);
                if (element.isDisplayed() && element.isEnabled()) {
                    return element;
                }
                return null;
            }
        });
        if ((shouldSelect && !checkbox.isSelected()) || (!shouldSelect && checkbox.isSelected()))
        {
            log.info("select checkbox for: {}", element);
            checkbox.click();
        }
    }

    public void selectDropdownByVisibleText(WebElement element, String visibleText)
    {
        //WebDriverWait webDriverWait = this.applicationContext.getBean(WebDriverWait.class);
        webDriverWait.withTimeout(Duration.ofSeconds(this.timeout))
                .pollingEvery(Duration.ofMillis(this.pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);

        WebElement dropdownElement = webDriverWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                if (element.isDisplayed() && element.isEnabled()) {
                    return element;
                }
                return null;
            }
        });
        scrollToElement(dropdownElement);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
        log.info("select dropdown options by visbible text for: {}", element);
    }

    public void selectDropdownByIndex(WebElement element, int index)
    {
        //WebDriverWait webDriverWait = this.applicationContext.getBean(WebDriverWait.class);
        webDriverWait.withTimeout(Duration.ofSeconds(this.timeout))
                .pollingEvery(Duration.ofMillis(this.pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);

        WebElement dropdownElement = webDriverWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                if (element.isDisplayed() && element.isEnabled()) {
                    return element;
                }
                return null;
            }
        });
        scrollToElement(dropdownElement);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
        log.info("select dropdown options by index for: {}", element);
    }

    public void selectDropdownByValue(WebElement element, String value)
    {
        //WebDriverWait webDriverWait = this.applicationContext.getBean(WebDriverWait.class);
        webDriverWait.withTimeout(Duration.ofSeconds(this.timeout))
                .pollingEvery(Duration.ofMillis(this.pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);

        WebElement dropdownElement = webDriverWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                if (element.isDisplayed() && element.isEnabled()) {
                    return element;
                }
                return null;
            }
        });
        scrollToElement(dropdownElement);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
        log.info("select dropdown options by value for: {}", element);
    }

    public void enterTextUsingJS(WebElement element, String text)
    {
        //JavascriptExecutor jse = this.applicationContext.getBean(JavascriptExecutor.class);
        log.info("Enter text: {} using JS to elements: {}", text, element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='"+text+"';", element);
    }

    public String getText(WebElement element)
    {
        //WebDriverWait webDriverWait = this.applicationContext.getBean(WebDriverWait.class);
        webDriverWait.withTimeout(Duration.ofSeconds(this.timeout))
                .pollingEvery(Duration.ofMillis(this.pollingTime))
                .ignoring(NoSuchElementException.class);

        WebElement elements = webDriverWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                if (element.isDisplayed()) {
                    return element;
                }
                return null;
            }
        });
        log.info("Get text value for: {}", element+" and fetch text is: "+elements.getText());
        return elements.getText();
    }
}
