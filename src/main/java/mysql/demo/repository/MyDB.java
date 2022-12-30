package mysql.demo.repository;

import mysql.demo.model.User;

import java.util.List;

public interface MyDB {
    // create
    void save(User user);
    // read
    User find(long id);
    List<User> findAll();
    // update
    int update(User user);
    // delete
    boolean delete(long id);
    // other
    void clearTable();
    void createTable();
}
