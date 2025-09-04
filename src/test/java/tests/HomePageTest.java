package tests;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import pages.HomePage;
import utils.BaseTest;
import testdata.TestDataProvider;
import org.openqa.selenium.NoSuchElementException;

@Listeners(utils.TestListener.class)
public class HomePageTest extends BaseTest {

    private HomePage homePage;

    // Test with valid data
    @Test(dataProvider = "validFormData", dataProviderClass = TestDataProvider.class)
    public void testFillFormWithValidData(
            String name, String email, String phone, String address, String gender,
            String day, String country, String color, String animal,
            String date1, String date2, String startDate, String endDate, String filePath
    ) {
        homePage = new HomePage(driver);
        fillForm(name, email, phone, address, gender, day, country, color, animal, date1, date2, startDate, endDate, filePath);
        // optionally add assertions to verify successful submission
    }

    // Test with invalid data
    @Test(dataProvider = "invalidFormData", dataProviderClass = TestDataProvider.class)
    public void testFillFormWithInvalidData(
            String name, String email, String phone, String address, String gender,
            String day, String country, String color, String animal,
            String date1, String date2, String startDate, String endDate, String filePath
    ) {
        homePage = new HomePage(driver);
        try {
            fillForm(name, email, phone, address, gender, day, country, color, animal, date1, date2, startDate, endDate, filePath);
        } catch (NoSuchElementException e) {
            System.out.println("Expected error for invalid data: " + e.getMessage());
        }
        // optionally assert error messages in the UI for invalid inputs
    }

    // common method to fill the form
    private void fillForm(
            String name, String email, String phone, String address, String gender,
            String day, String country, String color, String animal,
            String date1, String date2, String startDate, String endDate, String filePath
    ) {
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
        homePage.file1submit(filePath);
    }
}
