package repositories;

import exceptions.UserNotFoundException;
import model.User;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class UsersRepositoryFileImpl implements UsersRepository {
    private static final String FILE_NAME = "users.txt";

    @Override
    public void create(User user) {
        user.validate();
        List<User> users = readUsersFromFile();
        users.add(user);
        saveUsersToFile(users);
    }

    @Override
    public User findById(String id) {
        return readUsersFromFile().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Пользователь с ID " + id + " не найден"));
    }

    @Override
    public List<User> findAll() {
        return readUsersFromFile();
    }

    @Override
    public void update(User user) {
        List<User> users = readUsersFromFile();
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new UserNotFoundException("Пользователь с ID " + user.getId() + " не найден");
        }
        user.validate();
        users.set(index, user);
        saveUsersToFile(users);
    }

    @Override
    public void deleteById(String id) {
        List<User> users = readUsersFromFile();
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (!removed) {
            throw new UserNotFoundException("Пользователь с ID " + id + " не найден");
        }
        saveUsersToFile(users);
    }

    @Override
    public void deleteAll() {
        saveUsersToFile(new ArrayList<>());
    }

    @Override
    public List<User> findByAge(Integer age) {
        return readUsersFromFile().stream()
                .filter(user -> age.equals(user.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findByIsWorker(boolean isWorker) {
        return readUsersFromFile().stream()
                .filter(user -> user.isWorker() == isWorker)
                .collect(Collectors.toList());
    }

    private List<User> readUsersFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            return lines.stream()
                    .filter(line -> !line.trim().isEmpty())
                    .map(this::parseUser)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveUsersToFile(List<User> users) {
        try {
            Files.write(Paths.get(FILE_NAME),
                    users.stream()
                            .map(User::toString)
                            .collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении пользователей в файл", e);
        }
    }

    private User parseUser(String line) {
        String[] parts = line.split("\\|");
        User user = new User();

        try {
            // Устанавливаем значения через сеттеры
            user.setLogin(parts[2]);
            user.setPassword(parts[3]);
            user.setConfirmPassword(parts[4]);
            user.setLastName(parts[5]);
            user.setFirstName(parts[6]);
            user.setMiddleName(parts[7].isEmpty() ? null : parts[7]);
            user.setAge(parts[8].isEmpty() ? null : Integer.parseInt(parts[8]));
            user.setWorker(Boolean.parseBoolean(parts[9]));

            // Используем рефлексию или другие методы для установки id и createdAt,
            // так как они приватные и должны быть неизменяемыми
            setPrivateField(user, "id", parts[0]);
            setPrivateField(user, "createdAt", LocalDateTime.parse(parts[1]));

            return user;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при парсинге пользователя: " + line, e);
        }
    }

    // Вспомогательный метод для установки приватных полей
    private void setPrivateField(User user, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = User.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(user, value);
            field.setAccessible(false);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при установке значения поля " + fieldName, e);
        }
    }
}