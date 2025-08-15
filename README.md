Exemplo: Testes Mobile com Kotlin + Appium + JUnit + Allure 🚀

### Descrição:

Este repositório contém um exemplo de automação mobile para Android usando Kotlin, Appium, JUnit e Allure Report.
O objetivo é demonstrar o uso de seletores nativos do Android (UiSelector) em vez de XPATH, reduzindo complexidade e aumentando estabilidade dos testes.
Exemplo baseado na APK alura_esporte.

Cenários de teste identificados ✅

### Cadastrar Usuário
### Login com credenciais inválidas
### Login bem-sucedido
### Comprar produto
### Logout

### Pré-requisitos (instalação) 🛠️

Java 8 (JDK) — https://www.java.com/pt-BR/download/iemanual.jsp?locale=ptBR
Android Studio — https://developer.android.com/studio

Eclipse IDE ou outra IDE de sua preferência (IntelliJ/Android Studio recomendado) — https://www.eclipse.org/downloads/

Appium Desktop (versão recomendada 1.20.2 ou compatível) — https://github.com/appium/appium-desktop/releases
Maven (gerenciador de dependências) — https://maven.apache.org/

Configuração do ambiente (Windows) ⚙️

Variáveis de sistema recomendadas:
JAVA_HOME = C:\Program Files\Java\jdk1.8.x (ajuste conforme sua instalação)
ANDROID_HOME = C:\Users\\AppData\Local\Android\Sdk
Adicione ao PATH:
%JAVA_HOME%\bin
%ANDROID_HOME%\emulator
%ANDROID_HOME%\platform-tools
%ANDROID_HOME%\tools\bin

Passo a passo para iniciar (setup inicial) ▶️

Instale as dependências listadas nos pré-requisitos.

Configure as variáveis de ambiente (JAVAHOME, ANDROIDHOME).

Inicie o Appium Server: (ver seção comando Appium abaixo).
Abra o emulador Android ou conecte um dispositivo físico (USB + depuração USB ativada).

Clone o repositório e importe o projeto na IDE (Eclipse, IntelliJ ou Android Studio).

Executando os testes (via terminal) 🧪

Para rodar os testes via Maven no terminal:

mvn clean -Dtest=Runner test

Executando pela IDE

Importe o projeto para a IDE.

Localize a classe de suíte (ex.: Suite.java ou Runner).

Clique com o botão direito -> Run As -> JUnit Test.

Comando para iniciar o Appium Server 🖥️

Comando sugerido (ajuste conforme sua instalação do Appium):

appium -p 4723 -a 127.0.0.1 --base-path /wd/hub --allow-cors

Observação: em versões mais antigas a flag podia ser -pa wd/hub; a forma mais atual é --base-path /wd/hub.

Boas práticas e recomendações ✅

Prefira UiSelector para localizar elementos nativos Android ao invés de XPATH quando possível (mais rápido e mais estável).
Mantenha IDs e resource-ids no app sempre que possível (facilita automação).
Utilize Page Object Pattern para organizar elementos e ações.
Integre Allure para relatórios legíveis e com anexos (screenshots, logs).

Exemplos de UiSelector (Kotlin / Appium) 🔎

Localizar por className e textContains:

"new UiSelector().className(\"android.widget.EditText\").textContains(\"mero cart\")"

Localizar por className e texto exato:

"new UiSelector().className(\"android.widget.EditText\").text(\"Data de validade\")"

Localizar por resourceId e textContains:

"new UiSelector().resourceId(\"br.com.alura.aluraesporte:id/itemprodutonome\").textContains(\"Bola de futebol\")"

Elemento pai e filho:

"new UiSelector().resourceId(\"XXXX:id/TEST_ID\").childSelector(new UiSelector().className(\"android.widget.LinearLayout\"))"

Uso com listas (ArrayList de MobileElement injeção com PageFactory):

@AndroidFindBy(accessibility = "A")

@iOSXCUITFindBy(accessibility = "B")

private lateinit var btnArrival0to6: ArrayList

Exemplo cross-platform (Web + Android + iOS) — Java style

import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.pagefactory.*;

import org.openqa.selenium.support.FindBy;

// fully cross platform example

@FindBy(someStrategy) // browser / webview

@AndroidFindBy(someStrategy) // Android native

@iOSFindBy(someStrategy) // iOS native

RemoteWebElement someElement;

// list example

@FindBy(someStrategy)

@AndroidFindBy(someStrategy)

@iOSFindBy(someStrategy)

List someElements;

Estrutura recomendada do projeto (sugestão)

src
main
kotlin (código compartilhado, utils)
test
kotlin
  - screens (page objects)
  - tests (suites e testes)
  - core (setup do AppiumDriver, capabilities, listeners)

resources
allure-results (gerado em runtime)
pom.xml (dependências e plugins)

Integração Allure (relatórios) 📊

Configure plugin do Maven para Allure ou gere relatórios via CLI:
Exemplo de comandos (após execução dos testes):

mvn allure:report

mvn allure:serve

Certifique-se de que os adaptadores do JUnit e allure estão configurados no pom.xml.

Dicas de debugging 🐞

Verifique logs do Appium (console) e do device (adb logcat).
Faça screenshots nas falhas e anexe ao Allure.
Teste seletores no UiAutomatorViewer (Android SDK tool) para validar resource-id/class/text.

Problemas comuns e soluções rápidas ⚠️

Problema: driver não conecta ao Appium

Solução: validar host/porta, versão do Appium, e capabilities (deviceName, platformVersion, appPackage, appActivity).
Problema: elemento não encontrado

Solução: testar outros strategies (resource-id, accessibilityId, UiSelector) e aumentar timeouts/esperas explícitas.
Problema: permissões no Android

Solução: conceder permissões manualmente ou via adb: adb shell pm grant <package> <permission>

Referências e links úteis 🔗

Kotlin unresolved reference (IDE): https://stackoverflow.com/questions/31712046/kotlin-unresolved-reference-in-intellij
Appium Java Client docs: https://github.com/appium/java-client/wiki
Page Objects (Appium Java Client): https://github.com/appium/java-client/blob/master/docs/Page-objects.md
Allure JUnit5 docs: https://allure.rs/docs/ or https://allurereport.org/docs/junit5/

Contato / Créditos ✉️

Autor: Reinaldo (ajuste conforme necessário)
Projeto de exemplo para fins educacionais e de demonstração.

Observações finais 📝

Este README foi organizado para servir como guia de implantação e execução passo a passo. Se precisar, posso:
Gerar o arquivo README.md pronto;
Ajustar trechos para IntelliJ/Android Studio (se preferir essa IDE);
Incluir exemplos de capabilities e configuração do pom.xml.
