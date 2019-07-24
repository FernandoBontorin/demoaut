package demoaut.features.mercuryflight.entities;

import com.github.javafaker.Faker;

import static com.github.javafaker.CreditCardType.MASTERCARD;

public class Person {
    private static final Faker faker = new Faker();
    private String firstName, middleName, lastName, fullName, credCard;

    public Person() {
        firstName = faker.name().firstName();
        middleName = faker.name().lastName();
        lastName = faker.name().lastName();
        fullName = String.format("%s %s %s", firstName, middleName, lastName);
        credCard = faker.finance().creditCard(MASTERCARD).replace("-", "");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCredCard() {
        return credCard;
    }
}
