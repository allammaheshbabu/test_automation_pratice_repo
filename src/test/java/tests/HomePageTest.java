package tests;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import pages.HomePage;
import utils.BaseTest;
import testdata.TestDataProvider;

@Listeners(utils.TestListener.class)
public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @Test(dataProvider = "formData", dataProviderClass = TestDataProvider.class)
    public void testFillCompleteForm(
            String name,
            String email,
            String phone,
            String address,
            String gender,
            String day,
            String country,
            String color,
            String animal,
            String date1,
            String date2,
            String startDate,
            String endDate,
            String filePath
    ) {
        homePage = new HomePage(driver);
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
