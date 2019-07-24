package demoaut.features.mercuryflight.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class FlightOptionPage extends AbstractWebPage {

    public FlightOptionPage(RemoteWebDriver navigator) {
        super(navigator);
    }

    public List<WebElement> getOutFlight() {
        return getNavigator().findElements(By.name("outFlight"));
    }

    public List<WebElement> getInFlight() {
        return getNavigator().findElements(By.name("inFlight"));
    }

    public WebElement getContinueButton() {
        return getNavigator().findElement(By.name("reserveFlights"));
    }

    @Override
    public Boolean isPage() {
        boolean b;
        try {
            WebElement element = getNavigator().findElement(By.name("reserveFlights"));
            b = true;
        } catch (NoSuchElementException ex) {
            b = false;
        }
        return b;
    }
}
