package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        executeQuery(new UserDaoHibernateImpl());
//        executeQuery(new UserDaoJDBCImpl());

    }

    private static void executeQuery(UserDao userDao) {
        UserService userService = new UserServiceImpl(userDao);
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
