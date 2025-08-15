package qa.reinaldo._core

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.WebElement
// import your Capabilities class from the correct package, for example:
import qa.reinaldo._core.Capabilities
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory

open class BaseScreen : Capabilities() {

    open var wait = WebDriverWait(driver, java.time.Duration.ofSeconds(30))

    fun click(by: By?) {
        try {
            val element = by?.let { driver?.findElement(it) }
            wait.until(ExpectedConditions.elementToBeClickable(element)).click()
        } catch (e: Exception) {
            val element = by?.let { driver?.findElement(it) }
            wait.until(ExpectedConditions.visibilityOf(element)).click()
        }
    }

    fun click(element: WebElement?) {
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