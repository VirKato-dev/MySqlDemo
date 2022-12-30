package mysql.demo;

import mysql.demo.config.DBConnection;
import mysql.demo.model.User;
import mysql.demo.repository.MyDBImpl;
import mysql.demo.service.UserService;
import mysql.demo.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class Main {

    private static UserService userService;

    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("STARTED");

        userService = new UserServiceImpl(new MyDBImpl());

        userService.createTable();

        userService.save(new User("Gena" + rand.nextInt(100)));

        try {
            User user = userService.find(13);
            user.setName("Viewrn fwef fwef");
            userService.update(user);
        } catch (RuntimeException e) {
            System.out.println(e.getCause().getMessage());
        }

        int id = 5;
        if (userService.delete(id)) System.out.println("Удалена запись с id = " + id);
        else System.out.println("Запись с id = " + id + " в базе не найдена.");

        userService.findAll().forEach(System.out::println);

//        userService.clear();

        try {
            DBConnection.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
