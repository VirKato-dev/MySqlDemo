package mysql.demo.service;

import mysql.demo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    User find(long id);
    List<User> findAll();
    int update(User user);
    boolean delete(long id);
    void clearTable();
    void createTable();
}
