package testdata;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "validFormData")
    public Object[][] validFormData() {
        return new Object[][] {
            {
                "Mahesh Babu",
                "mahesh@test.com",
                "9876543210",
                "KPHB, Hyderabad",
                "Male",
                "Sunday",
                "India",
                "Red",
                "Dog",
                "09/01/2025",
                "01/09/2025",
                "01-09-2025", "10-09-2025",
                "C:\\Users\\allam\\OneDrive\\Desktop\\Java_String_Class_Guide.pdf"
            },
            {
                "Ram",
                "ram@test.com",
                "9123456789",
                "Ameerpet, Hyderabad",
                "Female",
                "Monday",
                "United States",
                "Green",
                "Cat",
                "10/01/2025",
                "15/09/2025",
                "05-09-2025", "12-09-2025",
                "C:\\Users\\allam\\OneDrive\\Desktop\\Mahesh Babu Allam - Resume.pdf"
            }
        };
    }

    @DataProvider(name = "invalidFormData")
    public Object[][] invalidFormData() {
        return new Object[][] {
            {
                "",
                "invalid-email",
                "12345",
                "",
                "Unknown",
                "Funday",
                "Neverland", // intentionally invalid country
                "Blue",
                "Deer",
                "99/99/9999",
                "32/13/2025",
                "15-09-2025", "10-09-2025",
                "C:\\Invalid\\Path\\file.pdf"
            }
        };
    }
}
