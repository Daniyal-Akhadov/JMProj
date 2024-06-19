package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserService userService = new UserServiceImpl(new UserDaoJDBCImpl());

        userService.createUsersTable();
        System.out.println("Users table created")
        ;
        userService.saveUser("sggg", "Shhhjjjj", (byte) 0);
        userService.saveUser("Ева", "Коломейцева", (byte) 14);
        userService.saveUser("Иван", "Иванов", (byte) 21);
        userService.saveUser("Расул", "Ахадов", (byte) 8);

        System.out.println("Users table increased");

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        System.out.println("Users table cleaned");

        userService.dropUsersTable();
        System.out.println("Users table dropped");
    }
}
