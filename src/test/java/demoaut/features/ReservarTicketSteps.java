package demoaut.features;

import demoaut.features.mercuryflight.entities.Person;
import demoaut.features.mercuryflight.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ReservarTicketSteps {
    private static final String SITE_URL = "http://newtours.demoaut.com";
    RemoteWebDriver navigator;
    Person person;
    LoginPage loginPage;
    FlightFinderPage flightFinderPage;
    FlightOptionPage flightOptionPage;
    BookAFlightPage bookAFlightPage;
    OrderPage orderPage;
    HashMap<String, String> testData = new HashMap<>();


    @Before
    public void setUp() {
        ChromeDriverManager.chromedriver().setup();
        navigator = new ChromeDriver();
        person = new Person();
        testData.put("UserName", "mercury");
        testData.put("Password", "mercury");
    }

    @After
    public void cleanUp() {
        if (orderPage != null) {
            System.out.println("    Order: #" + orderPage.getOrder());
        }
        navigator.quit();
        person = null;
    }

    @Dado("Acessar a URL")
    public void aURL() throws InterruptedException {
        String u = navigator.getCurrentUrl();
        navigator.navigate().to(SITE_URL);
        while (u.equals(navigator.getCurrentUrl())) {
            sleep(2000);
        }
        sleep(2000);
        loginPage = new LoginPage(navigator);
    }

    @E("Informar {string}")
    public void loginScreenOfApplication(String name) {
        loginPage.getInputName(name).sendKeys(testData.get(name));
        if (name.equalsIgnoreCase("Password")) {
            assertEquals(testData.get(name), loginPage.getPasswordInput().getAttribute("value"));
        }
    }

    @Quando("Clicar no botao Sign-In")
    public void whenClicarNoBotao() throws InterruptedException {
        String u = navigator.getCurrentUrl();
        loginPage.getSingInButton().click();
        while (u.equals(navigator.getCurrentUrl())) {
            sleep(2000);
        }
        sleep(2000);
        flightFinderPage = new FlightFinderPage(navigator);
    }

    @Entao("Logado")
    public void thenLogado() {
        assertTrue(flightFinderPage.isPage());
    }

    @Quando("Selecionar a cidade de origem - Departing From")
    public void whenSelecionarACidadeDeOrigem() {
        flightFinderPage.getFromOptions().get(flightFinderPage.getFromOptions().size() % 3).click();
    }

    @E("Selecionar a cidade de destino - Arriving In")
    public void whenSelecionarACidadeDeDestino() throws InterruptedException {
        flightFinderPage.getToOptions().get(3 + flightFinderPage.getToOptions().size() % 4).click();
    }

    @Quando("Selecionar a cidade de origem - Departing From {string}")
    public void whenSelecionarACidadeDeOrigem(String cidade) {
        for (WebElement webElement : flightFinderPage.getFromOptions()) {
            if (webElement.getAttribute("value").equalsIgnoreCase(cidade)) {
                webElement.click();
            }
        }
    }

    @E("Selecionar a cidade de destino - Arriving In {string}")
    public void whenSelecionarACidadeDeDestino(String cidade) throws InterruptedException {
        for (WebElement webElement : flightFinderPage.getToOptions()) {
            if (webElement.getAttribute("value").equalsIgnoreCase(cidade)) {
                webElement.click();
            }
        }
    }

    @E("Selecionar data > que data corrente")
    public void selectPostponeData() {
        flightFinderPage.travelDatePostpone();
    }

    @E("Selecionar data {string}")
    public void selectPostponeData(String data) {
        flightFinderPage.travelDatePostpone(data);
    }

    @E("Selecionar a Class")
    public void selecionarAClass() {
        flightFinderPage.getSelectClass().click();
    }

    @E("Selecionar a Class {string}")
    public void selecionarAClass(String classT) {
        flightFinderPage.getSelectClass(classT).click();
    }

    @E("Selecionar Passengers = {int}")
    public void selectPassengers(int qnt) {
        flightFinderPage.getPassengers().selectByValue(String.format("%d", qnt));
        assertTrue(flightFinderPage.getPassengers().getFirstSelectedOption().getAttribute("value").equalsIgnoreCase(String.format("%d", qnt)));
    }

    @Entao("Clicar no botao Continue do Flight Finder")
    public void clicarNoBotaoContinueDoFlightFinder() throws InterruptedException {
        String u = navigator.getCurrentUrl();
        flightFinderPage.getContinueButton().click();
        while (u.equals(navigator.getCurrentUrl())) {
            sleep(2000);
        }
        sleep(2000);
        flightOptionPage = new FlightOptionPage(navigator);
        assertTrue(flightOptionPage.isPage());
    }

    @E("Selecionar o voo")
    public void selecionarOVoo() {
        flightOptionPage.getOutFlight().get(1).click();
        flightOptionPage.getInFlight().get(1).click();
        int b = 0;
        if (!flightOptionPage.getOutFlight().get(1).getAttribute("checked").isEmpty()) b++;
        if (!flightOptionPage.getInFlight().get(1).getAttribute("checked").isEmpty()) b++;

        assertEquals(2, b);
    }

    @E("Selecionar o voo {string}")
    public void selecionarOVoo(String voo) {
        flightOptionPage.getOptionFlight(voo).click();
    }

    @Entao("Clicar no botao Continue do Select Flight")
    public void clicarNoBotaoContinueDoSelectFlight() throws InterruptedException {
        String u = navigator.getCurrentUrl();
        flightOptionPage.getContinueButton().click();
        while (u.equals(navigator.getCurrentUrl())) {
            sleep(2000);
        }
        sleep(2000);
        bookAFlightPage = new BookAFlightPage(navigator);
        assertTrue(bookAFlightPage.isPage());
    }

    @E("Preencher First name e Last Name")
    public void fillFirstNamendLast() {
        bookAFlightPage.getFirstName().sendKeys(person.getFirstName());
        bookAFlightPage.getLastName().sendKeys(person.getLastName());
    }

    @E("Preencher numero do cartao")
    public void fillCredCardBankNumber() {
        bookAFlightPage.getCreditNumber().sendKeys(person.getCredCard());
    }

    @E("Preencher o nome do passageiro {string}")
    public void fillPassengerName(String namePassenger) {
        bookAFlightPage.getFirstNamePassengers().sendKeys(person.getFirstName());
        bookAFlightPage.getLastNamePassengers().sendKeys(person.getLastName());
        assertTrue(bookAFlightPage.getFirstNamePassengers().getAttribute("value").equalsIgnoreCase(person.getFirstName()));
    }

    @Entao("Clicar em Secure Purchase")
    public void passengerNameIsTheSameName() throws InterruptedException {
        String u = navigator.getCurrentUrl();
        bookAFlightPage.getContinueButton().click();
        while (u.equals(navigator.getCurrentUrl())) {
            sleep(2000);
        }
        sleep(2000);
        orderPage = new OrderPage(navigator);
        assertTrue(orderPage.isPage());
    }

    @Quando("Selecionar tipo viagem {string}")
    public void selecionarTipoViagem(String arg0) {
        flightFinderPage.getOneWay().click();
    }

}
