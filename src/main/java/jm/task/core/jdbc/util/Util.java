package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        return sessionFactory == null ?
                connectBaseDataHibernate() : sessionFactory;
    }

    public static Connection getConnection() {
        return connection == null ?
                connectBaseDataJDBC() : connection;
    }

    private static SessionFactory connectBaseDataHibernate() {
        return new Configuration()
                .configure("hibernate.config.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    private static Connection connectBaseDataJDBC() {
        if (connection != null) {
            return connection;
        }

        final String CONFIGURATION_PATHS = "src/main/java/jm/task/core/jdbc/Configuration";
        final Path path = Paths.get(CONFIGURATION_PATHS);

        try (InputStream inputStream = Files.newInputStream(path)) {
            final Properties props = new Properties();
            props.load(inputStream);

            final String URL_KEY = "url";
            final String USERNAME_KEY = "username";
            final String PASSWORD_KEY = "password";

            final String url = props.getProperty(URL_KEY);
            final String username = props.getProperty(USERNAME_KEY);
            final String password = props.getProperty(PASSWORD_KEY);

            return connection = DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
