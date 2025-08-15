package qa.reinaldo._core

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.WebElement
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.support.PageFactory
import java.time.Duration

open class BaseScreen : Capabilities() {
    val driver: AppiumDriver? = inicializarAppiumDriver()
    protected val wait = WebDriverWait(driver, Duration.ofSeconds(30))

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }
    fun click(element: WebElement) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click()
        } catch (e: Exception) {
            wait.until(ExpectedConditions.visibilityOf(element)).click()
        }
    }

    fun sendKeys(element: WebElement?, text: String?) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text)
        } catch (e: Exception) {
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text)
        }
    }

}