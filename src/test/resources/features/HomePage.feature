Feature: Fill the complete registration form

  Scenario Outline: User fills the form with valid and invalid data
    Given the user is on the Home Page
    When the user enters "<name>", "<email>", "<phone>", "<address>", "<gender>", "<day>", "<country>", "<color>", "<animal>", "<date1>", "<date2>", "<startDate>", "<endDate>", "<file1>", "<file2>"
    Then the form should be submitted successfully

    Examples:
      | name          | email              | phone       | address          | gender | day     | country       | color | animal | date1      | date2      | startDate  | endDate    | file1                                                       | file2                                                       |
      | Mahesh Babu   | mahesh@test.com    | 9876543210  | KPHB, Hyderabad  | Male   | Sunday  | India         | Red   | Dog    | 09/01/2025 | 09/01/2025 | 01-09-2025 | 10-09-2025 | C:\Users\allam\OneDrive\Desktop\Java_String_Class_Guide.pdf | C:\Users\allam\OneDrive\Desktop\Java_String_Class_Guide.pdf |
      | Rajesh Kumar  | rajesh.k@test.com  | 9123456780  | MG Road, Bangalore | Male  | Monday  | United States | Green | Cat    | 10/01/2025 | 10/01/2025 | 05-09-2025 | 12-09-2025 | C:\Users\allam\OneDrive\Desktop\Java_String_Class_Guide.pdf      | C:\Users\allam\OneDrive\Desktop\Java_String_Class_Guide.pdf      |
