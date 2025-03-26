import model.User;
import repositories.UsersRepository;
import repositories.UsersRepositoryFileImpl;

public class App {
    public static void main(String[] args) {
        UsersRepository repository = new UsersRepositoryFileImpl();

        User user = new User();
        user.setLogin("test_user");
        user.setPassword("pass123_");
        user.setConfirmPassword("pass123_");
        user.setLastName("Иванов");
        user.setFirstName("Иван");
        user.setMiddleName("Иванович");
        user.setAge(30);
        user.setWorker(true);

        repository.create(user);
        System.out.println("Создан пользователь с ID: " + user.getId());

        System.out.println("Поиск пользователя...");
        System.out.println("Найден: " + repository.findById(user.getId()));
    }
}