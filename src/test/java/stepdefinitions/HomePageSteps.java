package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.HomePage;

public class HomePageSteps {
    WebDriver driver;
    HomePage homePage;

    @Given("the user is on the Home Page")
    public void the_user_is_on_the_home_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
        homePage = new HomePage(driver);
    }

    @When("the user enters {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void the_user_enters(String name, String email, String phone, String address, String gender, String day,
                                String country, String color, String animal, String date1, String date2,
                                String startDate, String endDate, String file1, String file2) {

        homePage.setName(name);
        homePage.setEmail(email);
        homePage.setPhone(phone);
        homePage.setAddress(address);
        homePage.selectGender(gender);
        homePage.selectDay(day);
        homePage.selectCountry(country);
        homePage.selectColor(color);
        homePage.selectAnimal(animal);
        homePage.setDatePicker1(date1);
        homePage.setDatePicker2(date2);
        homePage.setDateRange(startDate, endDate);

        homePage.file1submit(file1);
        homePage.file2submit(file2);

        homePage.clickSubmit();
    }

    @Then("the form should be submitted successfully")
    public void the_form_should_be_submitted_successfully() {
        String actualMessage = homePage.getSuccessMessage();
        Assert.assertTrue(actualMessage.toLowerCase().contains("success"),
                "Form submission validation failed! Found: " + actualMessage);
        driver.quit();
    }
}
