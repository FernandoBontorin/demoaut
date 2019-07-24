package demoaut.features.mercuryflight.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BookAFlightPage extends AbstractWebPage {

    public BookAFlightPage(RemoteWebDriver navigator) {
        super(navigator);
    }

    public WebElement getFirstName() {
        return getNavigator().findElement(By.name("cc_frst_name"));
    }

    public WebElement getLastName() {
        return getNavigator().findElement(By.name("cc_last_name"));
    }

    public WebElement getFirstNamePassengers() {
        return getNavigator().findElement(By.name("passFirst0"));
    }

    public WebElement getLastNamePassengers() {
        return getNavigator().findElement(By.name("passLast0"));
    }

    public WebElement getCreditNumber() {
        return getNavigator().findElement(By.name("creditnumber"));
    }

    public WebElement getSecurePurchaseButton() {
        return getNavigator().findElement(By.name("buyFlights"));
    }

    public WebElement getContinueButton() {
        return getSecurePurchaseButton();
    }

    @Override
    public Boolean isPage() {
        boolean b;
        try {
            WebElement element = getNavigator().findElement(By.name("buyFlights"));
            b = true;
        } catch (NoSuchElementException ex) {
            b = false;
        }
        return b;
    }
}
