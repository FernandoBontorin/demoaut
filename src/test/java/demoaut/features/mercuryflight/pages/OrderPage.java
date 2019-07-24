package demoaut.features.mercuryflight.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OrderPage extends AbstractWebPage {
    public OrderPage(RemoteWebDriver navigator) {
        super(navigator);
    }

    public String getOrder(){
        String s = "";
        s = getNavigator().findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[1]/td/table/tbody/tr/td[1]/b/font/font/b/font[1]")).getText().split("#")[1];
        return s;
    }

    @Override
    public Boolean isPage() {
        String s = "itinerary has been booked!";
        boolean b;
        try {
            b = getNavigator().findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/p/font/b/font[2]")).getText().contains("itinerary");
        } catch (NoSuchElementException ex) {
            b = false;
        }
        return b;
    }
}
