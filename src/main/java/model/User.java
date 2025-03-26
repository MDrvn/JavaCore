package model;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

public class User {
    private String id;
    private LocalDateTime createdAt;
    private String login;
    private String password;
    private String confirmPassword;
    private String lastName;
    private String firstName;
    private String middleName;
    private Integer age;
    private boolean isWorker;

    // Регулярные выражения для валидации
    private static final Pattern LOGIN_PATTERN = Pattern.compile("^(?=.*[a-zA-Z_])[a-zA-Z0-9_]{1,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z_])[a-zA-Z0-9_]{1,20}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[а-яА-Яa-zA-Z]+$");

    public User() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    // Валидация данных
    public void validate() {
        if (!LOGIN_PATTERN.matcher(login).matches()) {
            throw new IllegalArgumentException("Логин должен содержать буквы, цифры или '_', и быть не длиннее 20 символов.");
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("Пароль должен содержать буквы, цифры или '_', и быть не длиннее 20 символов.");
        }
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Пароли не совпадают.");
        }
        if (!NAME_PATTERN.matcher(lastName).matches() || !NAME_PATTERN.matcher(firstName).matches()) {
            throw new IllegalArgumentException("Имя и фамилия должны содержать только буквы.");
        }
        if (middleName != null && !NAME_PATTERN.matcher(middleName).matches()) {
            throw new IllegalArgumentException("Отчество должно содержать только буквы.");
        }
    }

    // Геттеры
    public String getId() { return id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getConfirmPassword() { return confirmPassword; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public Integer getAge() { return age; }
    public boolean isWorker() { return isWorker; }

    // Сеттеры
    public void setLogin(String login) { this.login = login; }
    public void setPassword(String password) { this.password = password; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setAge(Integer age) { this.age = age; }
    public void setWorker(boolean worker) { this.isWorker = worker; }

    @Override
    public String toString() {
        return id + "|" + createdAt + "|" + login + "|" + password + "|" + confirmPassword + "|" +
                lastName + "|" + firstName + "|" + (middleName != null ? middleName : "") + "|" +
                (age != null ? age : "") + "|" + isWorker;
    }
}