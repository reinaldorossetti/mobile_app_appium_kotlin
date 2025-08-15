package qa.reinaldo._core.screens

import qa.reinaldo._core.BaseScreen
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import org.openqa.selenium.WebElement

open class ScreenLogin : BaseScreen() {

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/input_usuario")
    lateinit var idDoUsuario: WebElement 

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/input_senha")
    lateinit var senha: WebElement 

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/login_botao_logar")
    lateinit var logar: WebElement 

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/mensagem_erro_login")
    lateinit var usuarioSenhaInvalidos: WebElement 

    fun preencherIdDoUsuario(text: String?) {
        sendKeys(idDoUsuario, text)
    }

    fun preencherSenha(text: String?) {
        senha.sendKeys(text)
    }

    fun logar() {
        logar.click()
    }

    fun validaUsuarioSenhaInvalidos(): String {
        return usuarioSenhaInvalidos.text
    }
}