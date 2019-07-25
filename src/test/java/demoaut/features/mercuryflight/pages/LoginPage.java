package demoaut.features.mercuryflight.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPage extends AbstractWebPage {

    public LoginPage(RemoteWebDriver navigator) {
        super(navigator);
    }

    @Override
    public Boolean isPage() {
        boolean b;
        try {
            b = getNavigator().findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[1]/p[1]/img")).getAttribute("alt").contains("Featured Destination");
        } catch (NoSuchElementException ex) {
            b = false;
        }
        return b;
    }

    public WebElement getUsernameInput() {
        return getNavigator().findElement(By.name("userName"));
    }

    public WebElement getPasswordInput() {
        return getNavigator().findElement(By.name("password"));
    }

    public WebElement getInputName(String name) {
        name = StringUtils.uncapitalize(name);
        return getNavigator().findElement(By.name(name));
    }

    public WebElement getSingInButton() {
        return getNavigator().findElement(By.name("login"));
    }
}
