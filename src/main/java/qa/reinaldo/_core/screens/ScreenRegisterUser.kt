package qa.reinaldo._core.screens

import qa.reinaldo._core.BaseScreen
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import org.openqa.selenium.WebElement


class ScreenRegisterUser : BaseScreen() {

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/login_botao_cadastrar_usuario")
    lateinit var cadastrarUsuario: WebElement

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/input_nome")
    private lateinit var nome: WebElement

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/input_senha")
    private lateinit var senha: WebElement

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/input_confirmar_senha")
    private lateinit var confirmarSenha: WebElement

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/cadastro_usuario_botao_cadastrar")
    private lateinit var cadastrar: WebElement

    fun cadastrarUsuario(): ScreenRegisterUser {
        cadastrarUsuario.click()
        return this
    }

    fun nome(text: String?): ScreenRegisterUser {
        nome.sendKeys(text)
        return this
    }

    fun senha(text: String?): ScreenRegisterUser {
        senha.sendKeys(text)
        return this
    }

    fun confirmarSenha(text: String?): ScreenRegisterUser {
        confirmarSenha.sendKeys(text)
        return this
    }

    fun cadastrar() {
        cadastrar.click()
    }
}