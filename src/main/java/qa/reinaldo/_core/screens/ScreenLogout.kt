package qa.reinaldo._core.screens

import qa.reinaldo._core.BaseScreen
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import org.openqa.selenium.WebElement

class ScreenLogout : BaseScreen() {

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/menu_principal_deslogar")
    private lateinit var deslogar: WebElement

    fun deslogar() {
        deslogar.click()
    }
}