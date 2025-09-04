package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // --- Form Fields ---
    private By nameInput = By.id("name");
    private By emailInput = By.id("email");
    private By phoneInput = By.id("phone");
    private By addressTextarea = By.id("textarea");

    private By genderMale = By.id("male");
    private By genderFemale = By.id("female");

    private By daysCheckboxes = By.xpath("//input[@type='checkbox' and @class='form-check-input']");

    private By countrySelect = By.id("country");
    private By colorsSelect = By.id("colors");
    private By animalsSelect = By.id("animals");

    private By datepicker1 = By.id("datepicker");
    private By datepicker2 = By.id("txtDate");
    private By dateRangeStart = By.id("start-date");
    private By dateRangeEnd = By.id("end-date");

    private By submitButton = By.xpath("//button[text()='Submit']");

    private By fileUploadInput = By.id("singleFileInput");
    private By fileSubmitButton = By.xpath("//button[text()='Upload Single File']");

    private By file2UploadInput = By.id("multipleFilesInput");
    private By file2SubmitButton = By.xpath("//button[text()='Upload Multiple Files']");

    // --- Input Methods ---
    public void setName(String name) { driver.findElement(nameInput).sendKeys(name); }
    public void setEmail(String email) { driver.findElement(emailInput).sendKeys(email); }
    public void setPhone(String phone) { driver.findElement(phoneInput).sendKeys(phone); }
    public void setAddress(String address) { driver.findElement(addressTextarea).sendKeys(address); }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            driver.findElement(genderMale).click();
        } else {
            driver.findElement(genderFemale).click();
        }
    }

    public void selectDay(String day) {
        List<WebElement> days = driver.findElements(daysCheckboxes);
        for (WebElement d : days) {
            if (d.getAttribute("value").equalsIgnoreCase(day)) {
                d.click();
                break;
            }
        }
    }

    public void selectCountry(String country) {
        new Select(driver.findElement(countrySelect)).selectByVisibleText(country);
    }
    public void selectColor(String color) {
        new Select(driver.findElement(colorsSelect)).selectByVisibleText(color);
    }
    public void selectAnimal(String animal) {
        new Select(driver.findElement(animalsSelect)).selectByVisibleText(animal);
    }

    public void setDatePicker1(String date) { driver.findElement(datepicker1).sendKeys(date); }
    public void setDatePicker2(String date) { driver.findElement(datepicker2).sendKeys(date); }
    public void setDateRange(String start, String end) {
        driver.findElement(dateRangeStart).sendKeys(start);
        driver.findElement(dateRangeEnd).sendKeys(end);
    }

    // --- File Uploads ---
    public void file1submit(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            driver.findElement(By.id("singleFileInput")).sendKeys(file.getAbsolutePath());
        } else {
            throw new RuntimeException("File not found at: " + filePath);
        }
    }

    public void file2submit(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            driver.findElement(By.id("multipleFilesInput")).sendKeys(file.getAbsolutePath());
        } else {
            throw new RuntimeException("File not found at: " + filePath);
        }
    }


    // --- Submit Form ---
    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    // --- Success Message ---
    public String getSuccessMessage() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (NoAlertPresentException e) {
            try {
                WebElement success = driver.findElement(By.id("successMessage")); // adjust locator
                return success.getText();
            } catch (Exception ex) {
                return "Success message not found!";
            }
        }
    }
}
