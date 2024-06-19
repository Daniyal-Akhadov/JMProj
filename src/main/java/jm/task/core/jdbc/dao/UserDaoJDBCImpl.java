package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Statement statement;

    public UserDaoJDBCImpl() {
        try {
            statement = Util.Instance().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() {
        try {
            String sqlRequest = """                                  
                    CREATE TABLE users
                        (
                            id      SERIAL,
                            name    VARCHAR(255) NOT NULL,
                            surname VARCHAR(255) NOT NULL,
                            old     INT          NOT NULL
                        );
                    """;

            statement.execute(sqlRequest);
        } catch (SQLException e) {
            System.out.println("Таблица создана");
        }
    }

    public void dropUsersTable() {
        try {
            String sqlRequest = "DROP TABLE users";
            statement.execute(sqlRequest);
        } catch (SQLException e) {
            System.out.println("Таблица была удалена ранее");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String sqlRequest =
                    "INSERT INTO users(name, surname, old) VALUES ('%s', '%s', %s);".formatted(name, lastName, age);

            statement.execute(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            String sqlRequest = "DELETE FROM users WHERE id = " + id;
            statement.execute(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            String sqlRequest = """                                  
                    Select * from users;
                    """;

            ResultSet resultSet = statement.executeQuery(sqlRequest);

            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                byte age = resultSet.getByte(4);

                users.add(new User(name, surname, age));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void cleanUsersTable() {
        try {
            String sqlRequest = """                                  
                    DELETE FROM users;
                    """;

            statement.execute(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }}
