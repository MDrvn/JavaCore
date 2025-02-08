package homeworks.homework12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Person {
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate; // Используем LocalDate
    private long phoneNumber;
    private char gender;
    private int age;

    public Person(String lastName, String firstName, String middleName, LocalDate birthDate, long phoneNumber, char gender, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return "<" + lastName + "><" + firstName + "><" + middleName + "><" + birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ">" +
                "<" + phoneNumber + "><" + gender + "><" + age + ">";
    }

    public String getLastName() {
        return lastName;
    }

    // Собственные исключения (более конкретные):
    public static class InvalidDateFormatException extends Exception {
        public InvalidDateFormatException(String message) {
            super(message);
        }
    }

    public static class InvalidPhoneNumberException extends Exception {
        public InvalidPhoneNumberException(String message) {
            super(message);
        }
    }

    public static class InvalidGenderException extends Exception {
        public InvalidGenderException(String message) {
            super(message);
        }
    }

    public static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    public static class InvalidNameException extends Exception {
        public InvalidNameException(String message) {
            super(message);
        }
    }
}
