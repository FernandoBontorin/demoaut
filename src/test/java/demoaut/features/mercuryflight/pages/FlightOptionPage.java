package demoaut.features.mercuryflight.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.LinkedList;
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

    public WebElement getOptionFlight(String voo) {
        String[] s = voo.split(" ");
        String formattedVoo = s[0];
        for (int i = 1; i < s.length - 1; i++) {
            formattedVoo += " " + s[i];
        }
        formattedVoo += "$" + s[s.length - 1];

        List<WebElement> flights = new LinkedList<>();
        flights.addAll(getOutFlight());
        flights.addAll(getInFlight());

        for (WebElement flight : flights) {
            if (flight.getAttribute("value").startsWith(formattedVoo)) {
                return flight;
            }
        }
        return flights.get(0);
    }
}
