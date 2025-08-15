package qa.reinaldo._core

import _core.Constantes
import io.appium.java_client.AppiumDriver
import io.appium.java_client.remote.options.BaseOptions
import org.openqa.selenium.MutableCapabilities
import java.net.MalformedURLException
import java.net.URL
import java.time.Duration

open class Capabilities : Constantes {
    companion object {
        var driver: AppiumDriver? = null
        
        fun inicializarAppiumDriver(): AppiumDriver? {
            if (driver == null) {
                createDriver()
            }
            return driver
        }
        
        fun createDriver() {
            val options = io.appium.java_client.android.options.UiAutomator2Options()
                .setPlatformName(Constantes.platformName_value)
                .setDeviceName(Constantes.deviceName_value)
                .setAutomationName(Constantes.automationName_value)
                .setNewCommandTimeout(Duration.ofSeconds(Constantes.newCommandTimeout.toLong()))
                .setAppPackage(Constantes.appPackage_value)
                .setAppActivity(Constantes.appActivity_value)
                .setApp(Constantes.APP_value)
                .setUiautomator2ServerLaunchTimeout(Duration.ofMillis(60000))
                .setUiautomator2ServerInstallTimeout(Duration.ofMillis(60000))
            
            try {
                driver = AppiumDriver(URL("http://127.0.0.1:4723/wd/hub"), options)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
                throw RuntimeException("Falha ao inicializar o driver Appium", e)
            } catch (e: Exception) {
                e.printStackTrace()
                throw RuntimeException("Erro inesperado ao criar o driver", e)
            }
        }
        
        fun finalizarAppiumDriver() {
            try {
                driver?.quit()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                driver = null
            }
        }
        
        // Método auxiliar para verificar se o driver está ativo
        fun isDriverActive(): Boolean {
            return try {
                driver?.sessionId != null
            } catch (e: Exception) {
                false
            }
        }
    }
}
