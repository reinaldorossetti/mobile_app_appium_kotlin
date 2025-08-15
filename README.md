# Automação de Testes Mobile com Kotlin e Appium 🚀

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.x-blue.svg?logo=kotlin)
![Appium](https://img.shields.io/badge/Appium-9.4.0-green.svg?logo=appium)
![Selenium](https://img.shields.io/badge/Selenium-4.33.0-blue.svg)
![JUnit5](https://img.shields.io/badge/JUnit-5-blue.svg?logo=junit5)
![Gradle](https://img.shields.io/badge/Gradle-8.x-blue.svg?logo=gradle)
![Allure](https://img.shields.io/badge/Allure-Report-orange.svg?logo=allure-framework)

## 📖 Descrição

Este repositório é um projeto de exemplo para automação de testes mobile em Android, utilizando uma stack moderna com **Kotlin, Appium, JUnit 5 e Allure Reports**. O foco é demonstrar a implementação do padrão **Page Object Model** e o uso de boas práticas para criar testes estáveis e de fácil manutenção.

O aplicativo de exemplo utilizado para os testes é o **Alura Esporte**.

---

## ✅ Cenários de Teste

-   [x] Cadastro de novo usuário
-   [x] Login com credenciais inválidas
-   [x] Login com sucesso
-   [x] Lógica de compra de produto
-   [x] Logout da aplicação

---

## 🛠️ Tecnologias Utilizadas

*   **Linguagem:** [Kotlin](https://kotlinlang.org/)
*   **Test Runner:** [JUnit 5](https://junit.org/junit5/)
*   **Automação Mobile:** [Appium (Java Client) 9.4.0](http://appium.io/)
*   **Suporte de Automação:** [Selenium 4.33.0](https://www.selenium.dev/)
*   **Build & Dependências:** [Gradle](https://gradle.org/)
*   **Relatórios:** [Allure Framework](https://allurereport.org/)

---

## ⚙️ Pré-requisitos

*   **Java (JDK)**: Versão 11 ou superior.
*   **Android Studio**: Para o SDK do Android e gerenciamento de emuladores.
*   **Appium Server**: `npm install -g appium`
*   **Appium Drivers**: `appium driver install uiautomator2`
*   **IDE**: IntelliJ IDEA ou Android Studio.

---

## 셋 Ambiente de Desenvolvimento (Windows)

1.  **Configure as Variáveis de Ambiente:**
    *   `JAVA_HOME`: Caminho para a instalação do JDK (ex: `C:\Program Files\Java\jdk-11`).
    *   `ANDROID_HOME`: Caminho para o SDK do Android (ex: `C:\Users\SEU_USUARIO\AppData\Local\Android\Sdk`).
    *   `GRADLE_HOME`: Caminho para a instalação do Gradle (ex: `C:\Gradle\gradle-8.5`).

2.  **Adicione as seguintes entradas ao `Path` do sistema:**
    *   `%JAVA_HOME%\bin`
    *   `%ANDROID_HOME%\emulator`
    *   `%ANDROID_HOME%\platform-tools`
    *   `%ANDROID_HOME%\tools`
    *   `%ANDROID_HOME%\tools\bin`
    *   `%GRADLE_HOME%\bin`

---

## ▶️ Executando o Projeto

1.  **Inicie o Appium Server:**
    *   Abra um terminal e execute o comando:
        ```bash
        appium -p 4723 -a 127.0.0.1 -pa wd/hub --allow-cors
        ```

2.  **Inicie o Emulador Android:**
    *   Abra o Android Studio, vá para o **Device Manager** e inicie um emulador com uma API recente.

3.  **Clone o Repositório:**
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    cd mobile_app_appium_kotlin
    ```

4.  **Execute os Testes:**
    *   **Via Terminal (Gradle):**
        *   No Windows, use o Gradle Wrapper para executar os testes.
        ```bash
        gradlew clean test
        ```
    *   **Via IDE (IntelliJ/Android Studio):**
        *   Importe o projeto como um projeto Gradle.
        *   Aguarde o download das dependências.
        *   Navegue até a classe de teste em `src/test/java` e execute-a.

---

## 📂 Estrutura do Projeto

O projeto segue uma estrutura organizada para separar responsabilidades, facilitando a manutenção.

```
mobile_app_appium_kotlin/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── qa/
│   │           └── reinaldo/
│   │               ├── _core/            # Classes base e configuração do driver
│   │               │   ├── BaseScreen.kt
│   │               │   └── DriverFactory.kt
│   │               └── screens/          # Page Objects de cada tela
│   │                   ├── ScreenLogin.kt
│   │                   └── ScreenRegisterUser.kt
│   └── test/
│       └── java/
│           └── qa/
│               └── reinaldo/
│                   └── tests/            # Classes de teste com os cenários
│                       └── LoginTest.kt
├── build.gradle.kts                      # Dependências e plugins do Gradle
├── gradlew                               # Gradle Wrapper (Linux/Mac)
├── gradlew.bat                           # Gradle Wrapper (Windows)
└── README.md
```

---

## 🏗️ Entendendo o `build.gradle.kts`

O arquivo `build.gradle.kts` é o coração do gerenciamento de dependências e build do projeto. Ele usa a sintaxe do Kotlin (Kotlin DSL) para definir como o projeto é construído e quais bibliotecas ele utiliza.

*   **`plugins { ... }`**: Ativa funcionalidades extras. `kotlin("jvm")` adiciona o suporte a Kotlin e `io.qameta.allure` integra o Allure para geração de relatórios.

*   **`repositories { ... }`**: Define de onde o Gradle deve baixar as bibliotecas. `mavenCentral()` é o repositório público mais comum.

*   **`dependencies { ... }`**: Aqui listamos todas as bibliotecas (dependências) do projeto. As principais são:
    *   `implementation("io.appium:java-client:9.4.0")`: Cliente Java do **Appium** para interagir com o servidor e os dispositivos.
    *   `implementation("org.seleniumhq.selenium:selenium-java:4.33.0")`: **Selenium**, do qual o Appium depende para muitas de suas funcionalidades base, como `WebDriverWait` e `ExpectedConditions`.
    *   `testImplementation(...)`: Define bibliotecas usadas apenas para testes, como **JUnit 5** e **Allure**.

*   **`tasks.test { ... }`**: Configura como os testes são executados. `useJUnitPlatform()` instrui o Gradle a usar o JUnit 5 como a plataforma de testes.

---

## 💻 Exemplos de Código

### BaseScreen (Core)

A classe `BaseScreen` centraliza ações comuns como `click` e `sendKeys`, utilizando esperas explícitas para maior estabilidade.

```kotlin
// filepath: src/main/java/qa/reinaldo/_core/BaseScreen.kt
open class BaseScreen {
    // ... inicialização do driver e PageFactory ...

    fun click(element: WebElement) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click()
    }



    fun sendKeys(element: WebElement?, text: String?) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text)
    }
}
```

### ScreenLogin (Page Object)

Cada tela é mapeada em uma classe que herda de `BaseScreen`. Os elementos são declarados com anotações do Appium e os métodos representam as ações do usuário.

```kotlin
// filepath: src/main/java/qa/reinaldo/_core/screens/ScreenLogin.kt
class ScreenLogin : BaseScreen() {

    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/input_usuario")
    lateinit var idDoUsuario: WebElement

    @AndroidFindBy(id = "br.com.alura.aluraesporte:id/login_botao_logar")
    lateinit var logar: WebElement

    fun preencherIdDoUsuario(text: String?) {
        sendKeys(idDoUsuario, text)
    }

    fun clicarEmLogar() {
        click(logar)
    }
}
```

---

## 📊 Relatórios com Allure

Após a execução dos testes com o Gradle, gere e visualize o relatório de resultados do Allure.

1.  **Gerar o Relatório:**
    ```bash
    gradlew allureReport
    ```

2.  **Servir o Relatório (abre no navegador):**
    ```bash
    gradlew allureServe
    ```

---

## 💡 Dicas e Boas Práticas

*   **Seletores:** Dê preferência a seletores de `ID` ou `Accessibility ID`, pois são mais performáticos e resilientes a mudanças na UI do que `XPath`.
*   **Page Objects:** Mantenha a lógica de interação com a UI (cliques, preenchimento de campos) dentro das classes de tela (screens), e as asserções (`asserts`) dentro das classes de teste.
*   **Debug:** Utilize o **Appium Inspector** para inspecionar a hierarquia de elementos da tela e validar seus seletores.
