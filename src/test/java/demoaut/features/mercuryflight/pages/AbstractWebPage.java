package demoaut.features.mercuryflight.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public abstract class AbstractWebPage {

    RemoteWebDriver navigator;

    private AbstractWebPage() {
    }

    public AbstractWebPage(RemoteWebDriver navigator) {
        this.navigator = navigator;
    }

    public RemoteWebDriver getNavigator() {
        return navigator;
    }

    public Select getSelect(By by) {
        return new Select(getNavigator().findElement(by));
    }

    public abstract Boolean isPage();
}
