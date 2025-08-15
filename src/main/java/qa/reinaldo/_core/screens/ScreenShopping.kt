package qa.reinaldo._core.screens

import qa.reinaldo._core.BaseScreen
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.pagefactory.ByAll
import org.openqa.selenium.By
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy


class ScreenShopping : BaseScreen() {

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/detalhes_produto_botao_comprar")
    private lateinit var comprar: WebElement

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").textContains(\"mero cart\")")
    private lateinit var numeroCartao: WebElement

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Data de validade\")")
    private lateinit var dataValidade: WebElement

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"CVC\")")
    private lateinit var cvc: WebElement

    @iOSXCUITFindBy(id = "")
    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/pagamento_botao_confirma_pagamento")
    private lateinit var confirmarPagamento: WebElement

    fun produto(text: String) {
        val WebElement =
            "new UiSelector().resourceId(\"br.com.alura.aluraesporte:id/item_produto_nome\").textContains(\"$text\")"
        click(ByAll(By.id("br.com.alura.aluraesporte:id/item_produto_nome"), By.xpath(WebElement)))
    }

    fun comprar() {
        click(comprar)
    }

    fun preencherNumeroCartao(text: String?) {
        sendKeys(numeroCartao, text)
    }

    fun preencherDataValidade(text: String?) {
        sendKeys(dataValidade, text)
    }

    fun preencherCvc(text: String?) {
        sendKeys(cvc, text)
    }

    fun confirmarPagamento() {
        confirmarPagamento.click()
    }

}