package mysql.demo.service;

import mysql.demo.model.User;
import mysql.demo.repository.MyDB;

import java.util.List;


public class UserServiceImpl implements UserService {
    private final MyDB repo;

    public UserServiceImpl(MyDB repo) {
        this.repo = repo;
    }

    @Override
    public void save(User user) {
        repo.save(user);
    }

    @Override
    public User find(long id) {
        return repo.find(id);
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public int update(User user) {
        return repo.update(user);
    }

    @Override
    public boolean delete(long id) {
        return repo.delete(id);
    }

    @Override
    public void clearTable() {
        repo.clearTable();
    }

    @Override
    public void createTable() {
        repo.createTable();
    }
}
