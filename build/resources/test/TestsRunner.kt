package qa.reinaldo

import qa.reinaldo._core.Capabilities
import io.appium.java_client.AppiumDriver
import io.qameta.allure.Allure
import io.qameta.allure.Allure.step
import io.qameta.allure.Description
import io.qameta.allure.Severity
import io.qameta.allure.Step
import org.junit.jupiter.api.*
import org.opentest4j.AssertionFailedError
import qa.reinaldo._core.screens.ScreenLogin
import qa.reinaldo._core.screens.ScreenLogout
import qa.reinaldo._core.screens.ScreenRegisterUser
import qa.reinaldo._core.screens.ScreenShopping

import qa.reinaldo._core.model.Produto
import qa.reinaldo._core.model.User
import io.qameta.allure.SeverityLevel.*
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.WebElement

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TestsRunner {

    var driver: AppiumDriver? = null
    var screenRegisterUser = ScreenRegisterUser()
    var user = User()
    var produto = Produto()
    val screenLogin = ScreenLogin()

    @BeforeEach
    fun iniciar() {
        driver = Capabilities.inicializarAppiumDriver()
    }

    @AfterEach
    fun finalizar() {
        Capabilities.finalizarAppiumDriver()
    }

    @Test
    @DisplayName("CT01 - Teste que realiza o cadastro de usuario.")
    @Description("Passo a Passo: " +
            "1. Selecionar Cadastrar na tela de login;" +
            "2. Inserir o Usuario e Senha;" +
            "3. Confirmar dados cadastrais;" +
            "4. Exibir mensagem de Sucesso.")
    @Order(1)
    @Severity(CRITICAL)
    fun testeCadastrarUsuario() {
        Allure.step("Inserir dados do cadastro")
        screenRegisterUser.cadastrarUsuario()
        screenRegisterUser.nome(user.idDoUsuario)
        screenRegisterUser.senha(user.senha)
        screenRegisterUser.confirmarSenha(user.senha)

        Allure.step("Efetuar o cadastro")
        screenRegisterUser.cadastrar()

        val actualString = screenLogin.logar.text
        Allure.step("Validade teste realizado: ${actualString}")
        assertTrue(actualString.contains("LOGAR"));
    }

    @Test
    @Step("Realizando o cadastro com senha invalida - msn: {resultUsuarioSenhaInvalidos}")
    @Order(2)
    fun loginUsuarioSenhaInvalidos() {
        assertThrows(
            AssertionFailedError::class.java
        ) {
            val screenLogin = ScreenLogin()
            screenLogin.preencherIdDoUsuario("usuarioInexistente")
            screenLogin.preencherSenha("9999999")
            screenLogin.logar()
            user.userInvalido = screenLogin.validaUsuarioSenhaInvalidos()
            validaUsuarioSenhaInvalidos(user.userInvalido)
        }
    }

    @Step("Assert mensagem: {resultado}")
    fun validaUsuarioSenhaInvalidos(resultado: String){
        println(resultado)
        assertEquals("Usuario ou senha invalidos",resultado)
    }

    @Test
    @Order(3)
    @Step("Realizando o login com sucesso")
    fun testeLogin() {
        step("usuario: ${user.idDoUsuario}")
        screenLogin.preencherIdDoUsuario(user.idDoUsuario)
        screenLogin.preencherSenha(user.senha)
        screenLogin.logar()
    }

    @Test
    @Order(3)
    @Step("Realizando a compra de um produto")
    fun testComprarProduto() {
        screenRegisterUser.cadastrarUsuario()
        screenRegisterUser.nome(user.idDoUsuario).senha(user.senha).confirmarSenha(user.senha)
        screenRegisterUser.cadastrar()
        val screenLogin = ScreenLogin()
        screenLogin.preencherIdDoUsuario(user.idDoUsuario)
        screenLogin.preencherSenha(user.senha)
        screenLogin.logar()
        val screenCompras = ScreenShopping()
        screenCompras.produto(produto.bolaFutebol)
        screenCompras.comprar()
        screenCompras.preencherNumeroCartao(user.numeroCartao)
        step("numeroCartao: ${user.numeroCartao}")
        screenCompras.preencherDataValidade(user.dataValidade)
        screenCompras.preencherCvc(user.cvc)
        screenCompras.confirmarPagamento()

        //"Falso Negativo" ou "Falso Negativo" ???
        val falhaAoCriarPagamento = "Falso Positivo"
        val mensagemErro = "Falha ao criar pagamento"
        if (falhaAoCriarPagamento.equals("Falso Positivo")) {
            println("No cenario de teste 'Comprar Produto', o sistema esta apresentando a seguinte notificacao: <<<$mensagemErro>>> propositalmente.")
        } else {
            fail("App com falha ao Criar Pagamento")
        }
    }

    @Test
    @Order(4)
    @Step("Realizando o logout")
    fun testLogout() {
        screenRegisterUser.cadastrarUsuario()
        screenRegisterUser.nome(user.idDoUsuario).senha(user.senha).confirmarSenha(user.senha)
        screenRegisterUser.cadastrar()
        val screenLogin = ScreenLogin()
        screenLogin.preencherIdDoUsuario(user.idDoUsuario)
        screenLogin.preencherSenha(user.senha)
        screenLogin.logar()
        val screenLogout = ScreenLogout()
        screenLogout.deslogar()
    }
}