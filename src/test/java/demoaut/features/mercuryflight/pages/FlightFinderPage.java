package demoaut.features.mercuryflight.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class FlightFinderPage extends AbstractWebPage {
    private final HashMap<String, String> classTs;

    public FlightFinderPage(RemoteWebDriver navigator) {
        super(navigator);
        HashMap<String, String> map = new HashMap<>();
        map.put("Economy class", "Coach");
        map.put("Business class", "Business");
        map.put("First class", "First");
        classTs = map;
    }

    public WebElement getRoundTrip() {
        WebElement e = null;
        List<WebElement> elements = getNavigator().findElements(By.name("tripType"));
        for (WebElement element : elements) {
            if (element.getAttribute("value").equalsIgnoreCase("roundtrip")) {
                e = element;
            }
        }
        return e;
    }

    public WebElement getOneWay() {
        WebElement e = null;
        List<WebElement> elements = getNavigator().findElements(By.name("tripType"));
        for (WebElement element : elements) {
            if (element.getAttribute("value").equalsIgnoreCase("oneway")) {
                e = element;
            }
        }
        return e;
    }

    public Select getPassengers() {
        return new Select(getNavigator().findElement(By.name("passCount")));
    }

    public List<WebElement> getFromOptions() {
        return getSelect(By.name("fromPort")).getOptions();
    }

    public List<WebElement> getToOptions() {
        return getSelect(By.name("toPort")).getOptions();
    }

    public void travelDatePostpone() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now().plusDays(1L);
        for (WebElement monthF : getSelect(By.name("fromMonth")).getOptions()) {
            if (monthF.getAttribute("value").equalsIgnoreCase(zonedDateTime.getMonth().name())) {
                monthF.click();
            }
        }
        for (WebElement dayF : getSelect(By.name("fromDay")).getOptions()) {
            if (dayF.getAttribute("value").equalsIgnoreCase(String.valueOf(zonedDateTime.getDayOfMonth()))) {
                dayF.click();
            }
        }

        zonedDateTime = zonedDateTime.plusDays(1L);
        for (WebElement monthT : getSelect(By.name("toMonth")).getOptions()) {
            if (monthT.getAttribute("value").equalsIgnoreCase(zonedDateTime.getMonth().name())) {
                monthT.click();
            }
        }
        for (WebElement dayT : getSelect(By.name("toDay")).getOptions()) {
            if (dayT.getAttribute("value").equalsIgnoreCase(String.valueOf(zonedDateTime.getDayOfMonth()))) {
                dayT.click();
            }
        }
    }

    public void travelDatePostpone(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(data, formatter);
        ZonedDateTime zonedDateTime = date.atStartOfDay(ZoneId.systemDefault());
        for (WebElement monthF : getSelect(By.name("fromMonth")).getOptions()) {
            if (monthF.getAttribute("value").equalsIgnoreCase(zonedDateTime.getMonth().name())) {
                monthF.click();
            }
        }
        for (WebElement dayF : getSelect(By.name("fromDay")).getOptions()) {
            if (dayF.getAttribute("value").equalsIgnoreCase(String.valueOf(zonedDateTime.getDayOfMonth()))) {
                dayF.click();
            }
        }
    }

    public WebElement getSelectClass() {
        return getNavigator().findElements(By.name("servClass")).get(0);
    }

    public WebElement getContinueButton() {
        return getNavigator().findElement(By.name("findFlights"));
    }

    @Override
    public Boolean isPage() {
        boolean b = false;
        try {
            WebElement element = getNavigator().findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[1]/td/font/font/b/font/font"));
            b = (element.getText().contains("Flight") && element.getText().contains("Details"));
        } catch (NoSuchElementException ex) {
            b = false;
        }
        return b;
    }

    public WebElement getSelectClass(String classT) {
        List<WebElement> elements = getNavigator().findElements(By.name("servClass"));
        WebElement e = elements.get(0);
        for (WebElement element : elements) {
            if (element.getAttribute("value").equalsIgnoreCase(classTs.get(classT))) {
                return element;
            }
        }
        return e;
    }
}
