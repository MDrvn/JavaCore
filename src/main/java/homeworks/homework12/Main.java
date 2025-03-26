package homeworks.homework12;

/*
Формулировка задания:
1. Напишите приложение, которое будет запрашивать у пользователя
следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол возраст
Форматы данных:
• фамилия, имя, отчество — строка;
• датарождения — строка формата dd.mm.yyyy;
• номертелефона - целое беззнаковое число без форматирования;
• пол - символ латиницей f или m;
• возраст — целое число.
Данные читаются из консоли.
2. Приложение должно проверить введенные данные. Если количество
полей   не   совпадает   с   требуемым,   вернуть   код   ошибки,   обработать   его   и
показать пользователю сообщение, что в файле меньше и больше данных, чем
требуется.
3. Приложение должно попытаться распарсить полученные значения и
выделить из них требуемые параметры. Параметры записываются в поля класса
Person.  Если   форматы   данных   не   совпадают,   нужно   бросить   исключение,
соответствующее типу проблемы.
Можно использовать встроенные типы java и создать свои. Исключение
должно   быть   корректно   обработано,   пользователю   выведено   сообщение   с
информацией, что именно неверно.
4.   Если   всё   введено   и   обработано   верно,   должен   создаться   файл   с
названием,   равным   фамилии,   в   него   в   одну   строку   должны   записаться
полученные данные, вида
<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
5. Дополнительно. Однофамильцы должны записаться в один и тот же
файл, в отдельные строки.
6.   Не   забудьте   закрыть   соединение   с   файлом.   При   возникновении
проблемы с  чтением-записью  в  файл,  исключение должно  быть  корректно
обработано, пользователь должен увидеть стектрейс ошибки.
*/

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int MAX_AGE = 150;
    private static final int MIN_PHONE_DIGITS = 7;
    private static final int MAX_PHONE_DIGITS = 15;
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я-]+");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные в указанном формате (через пробел!):");
        System.out.println("Фамилия Имя Отчество датарождения номертелефона пол возраст");
        System.out.println("Дату рождения необходимо ввести в формате дд.мм.гггг; пол - буквами f или m!");
        String input = scanner.nextLine();
        String[] data = input.split(" ");

        // Проверка количества полей:
        if (data.length != 7) {
            System.err.println("Ошибка: количество введенных данных некорректно. Ожидается 7 полей.");
            return;
        }

        try {
            // Парсим данные:
            String lastName = parseName(data[0], "Фамилия");
            String firstName = parseName(data[1], "Имя");
            String middleName = parseName(data[2], "Отчество");
            LocalDate birthDate = parseDate(data[3]);
            long phoneNumber = parsePhoneNumber(data[4]);
            char gender = parseGender(data[5]);
            int age = parseAge(data[6]);

            // Создаем объект Person:
            Person person = new Person(lastName, firstName, middleName, birthDate, phoneNumber, gender, age);

            // Записываем в файл:
            writeToFile(person);

            System.out.println("Данные успешно сохранены.");

        } catch (Person.InvalidDateFormatException e) {
            System.err.println("Ошибка в дате рождения: " + e.getMessage());
        } catch (Person.InvalidPhoneNumberException e) {
            System.err.println("Ошибка в номере телефона: " + e.getMessage());
        } catch (Person.InvalidGenderException e) {
            System.err.println("Ошибка в поле 'пол': " + e.getMessage());
        } catch (Person.InvalidAgeException e) {
            System.err.println("Ошибка в поле 'возраст': " + e.getMessage());
        } catch (Person.InvalidNameException e) {
            System.err.println("Ошибка в ФИО: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Не удалось сохранить данные в файл. Пожалуйста, проверьте, есть ли место на диске и права доступа.");
        } finally {
            scanner.close();
        }
    }

    private static String parseName(String name, String fieldName) throws Person.InvalidNameException {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new Person.InvalidNameException("Недопустимые символы в поле '" + fieldName + "'. Разрешены только буквы и дефис.");
        }
        return name;
    }

    // Проверка и парсинг даты:
    private static LocalDate parseDate(String date) throws Person.InvalidDateFormatException {
        try {
            return LocalDate.parse(date, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new Person.InvalidDateFormatException("Неверный формат даты. Ожидается формат дд.мм.гггг.");
        }
    }

    // Проверка и парсинг номера телефона:
    private static long parsePhoneNumber(String phone) throws Person.InvalidPhoneNumberException {
        try {
            long phoneNumber = Long.parseLong(phone);
            String phoneStr = String.valueOf(phoneNumber);
            if (phoneStr.length() < MIN_PHONE_DIGITS || phoneStr.length() > MAX_PHONE_DIGITS) {
                throw new Person.InvalidPhoneNumberException("Номер телефона должен содержать от " + MIN_PHONE_DIGITS + " до " + MAX_PHONE_DIGITS + " цифр.");
            }
            return phoneNumber;
        } catch (NumberFormatException e) {
            throw new Person.InvalidPhoneNumberException("Номер телефона должен быть целым числом.");
        }
    }

    // Проверка и парсинг пола:
    private static char parseGender(String gender) throws Person.InvalidGenderException {
        if (gender.length() == 1 && (gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("m"))) {
            return gender.toLowerCase().charAt(0); // Приводим к нижнему регистру
        } else {
            throw new Person.InvalidGenderException("Пол должен быть указан одной буквой: 'f' или 'm'.");
        }
    }

    // Проверка и парсинг возраста:
    private static int parseAge(String age) throws Person.InvalidAgeException {
        try {
            int parsedAge = Integer.parseInt(age);
            if (parsedAge < 0) {
                throw new Person.InvalidAgeException("Возраст должен быть положительным.");
            }
            if (parsedAge > MAX_AGE) {
                throw new Person.InvalidAgeException("Возраст не может быть больше " + MAX_AGE + ".");
            }
            return parsedAge;
        } catch (NumberFormatException e) {
            throw new Person.InvalidAgeException("Возраст должен быть целым числом.");
        }
    }

    // Запись в файл:
    private static void writeToFile(Person person) throws IOException {
        String fileName = person.getLastName() + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(person.toString() + "\n");
        }
    }
}