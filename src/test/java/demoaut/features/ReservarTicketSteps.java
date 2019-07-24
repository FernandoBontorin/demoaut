package demoaut.features;

import demoaut.features.mercuryflight.entities.Person;
import demoaut.features.mercuryflight.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

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
    private String user = "mercury", password = user;


    @Before
    public void setUp() {
        ChromeDriverManager.chromedriver().setup();
        navigator = new ChromeDriver();
        person = new Person();
    }

    @After
    public void cleanUp() {
        System.out.println("    Order: #" + orderPage.getOrder());
        navigator.quit();
        person = null;
    }

    @Given("a URL")
    public void aURL() {
        navigator.navigate().to(SITE_URL);
    }

    @And("login screen of application")
    public void loginScreenOfApplication() {
        loginPage = new LoginPage(navigator);
    }

    @And("input some valid user")
    public void someValidUser() {
        loginPage.getUsernameInput().sendKeys(user);
    }

    @When("input his password")
    public void putHisPassword() {
        loginPage.getPasswordInput().sendKeys(password);
    }

    @Then("password is filled")
    public void passwordIsFilled() throws InterruptedException {
        sleep(2000);
        assertEquals(password, loginPage.getPasswordInput().getAttribute("value"));
    }

    @When("try Sing-in")
    public void trySingIn() throws InterruptedException {
        String u = navigator.getCurrentUrl();
        loginPage.getSingInButton().click();
        while (u.equals(navigator.getCurrentUrl())) {
            sleep(2000);
        }
        sleep(2000);
    }

    @Then("should go to Flight Finder screen")
    public void shouldGoToFlightFinder() {
        flightFinderPage = new FlightFinderPage(navigator);
        assertTrue(flightFinderPage.isPage());
    }

    @Given("I am logged")
    public void iAmLogged() {
        assertTrue(flightFinderPage.isPage());
    }

    @When("select city from")
    public void selectCityFrom() {
        flightFinderPage.getFromOptions().get(flightFinderPage.getFromOptions().size() % 3).click();
    }

    @And("select city in")
    public void selectCityIn() {
        flightFinderPage.getToOptions().get(3 + flightFinderPage.getToOptions().size() % 4).click();
    }

    @And("select postpone data")
    public void selectPostponeData() {
        flightFinderPage.travelDatePostpone();
    }

    @And("select class")
    public void selectClass() {
        flightFinderPage.getSelectClass().click();
    }

    @When("select passengers {int}")
    public void selectPassengers(int arg0) {
        flightFinderPage.getPassengers().selectByValue(String.format("%d", arg0));
    }

    @Then("should passengers are {int}")
    public void shouldPassengersAre(int arg0) {
        assertTrue(flightFinderPage.getPassengers().getFirstSelectedOption().getAttribute("value").equalsIgnoreCase(String.format("%d", arg0)));
    }

    @When("click on flight finder continue button")
    public void clickOnFlightFinderContinueButton() throws InterruptedException {
        String u = navigator.getCurrentUrl();
        flightFinderPage.getContinueButton().click();
        while (u.equals(navigator.getCurrentUrl())) {
            sleep(2000);
        }
        sleep(2000);
    }

    @Then("should appears select flight screen")
    public void shouldAppearsSelectFlightScreen() {
        flightOptionPage = new FlightOptionPage(navigator);
        assertTrue(flightOptionPage.isPage());
    }

    @When("select flight option")
    public void selectFlightOption() {
        flightOptionPage.getOutFlight().get(1).click();
        flightOptionPage.getInFlight().get(1).click();
    }

    @Then("should flight is selected")
    public void shouldFlightIsSelected() {
        assertTrue(Boolean.TRUE);
    }

    @When("click on select flight continue button")
    public void clickOnSelectFlightContinueButton() throws InterruptedException {
        String u = navigator.getCurrentUrl();
        flightOptionPage.getContinueButton().click();
        while (u.equals(navigator.getCurrentUrl())) {
            sleep(2000);
        }
        sleep(2000);
    }

    @Then("should appears book a flight")
    public void shouldAppearsBookAFlight() {
        bookAFlightPage = new BookAFlightPage(navigator);
    }

    @And("summary with select options")
    public void summaryWithSelectOptions() {
        assertTrue(bookAFlightPage.isPage());
    }

    @When("fill first name")
    public void fillFirstName() {
        bookAFlightPage.getFirstName().sendKeys(person.getFirstName());
    }

    @And("fill last name")
    public void fillLastName() {
        bookAFlightPage.getLastName().sendKeys(person.getLastName());
    }

    @And("fill cred card bank number")
    public void fillCredCardBankNumber() {
        bookAFlightPage.getCreditNumber().sendKeys(person.getCredCard());
    }

    @When("fill passenger name")
    public void fillPassengerName() {
        bookAFlightPage.getFirstNamePassengers().sendKeys(person.getFirstName());
    }

    @Then("passenger name is the same name")
    public void passengerNameIsTheSameName() {
        bookAFlightPage.getLastNamePassengers().sendKeys(person.getLastName());
    }

    @When("click on Secure Purchase")
    public void clickOnSecurePurchase() throws InterruptedException {
        String u = navigator.getCurrentUrl();
        bookAFlightPage.getContinueButton().click();
        while (u.equals(navigator.getCurrentUrl())) {
            sleep(2000);
        }
        sleep(2000);
    }

    @Then("should receive some order")
    public void shouldReceiveSomeOrder() {
        orderPage = new OrderPage(navigator);
        assertTrue(orderPage.isPage());
    }
}
