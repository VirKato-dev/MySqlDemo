package mysql.demo.repository;

import mysql.demo.config.DBConnection;
import mysql.demo.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyDBImpl implements MyDB {
    private static final Connection DB = DBConnection.getConnection();

    @Override
    public void save(User user) {
        try (Statement st = DB.createStatement()) {
            DB.beginRequest();
            st.execute(String.format("INSERT INTO users(name) VALUES ('%s')", user.getName()));
            DB.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                DB.endRequest();
            } catch (SQLException ignored) {
            }
        }
    }

    @Override
    public User find(long id) {
        ResultSet rs;
        try (Statement st = DB.createStatement()) {
            rs = st.executeQuery("SELECT * FROM users WHERE id = " + id);
            rs.next();
            User user = new User();
            user.setId(rs.getLong(1));
            user.setName(rs.getString(2));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        ResultSet rs;
        try (Statement st = DB.createStatement()) {
            rs = st.executeQuery("SELECT * FROM users ORDER BY id ASC");
            List<User> users = new ArrayList<>();
            User user;
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        try (Statement st = DB.createStatement()) {
            DB.beginRequest();
            st.execute(String.format("UPDATE users SET name='%s' WHERE id = %d", user.getName(), user.getId()));
            DB.commit();
            return st.getUpdateCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                DB.endRequest();
            } catch (SQLException ignored) {
            }
        }
    }

    @Override
    public boolean delete(long id) {
        try (Statement st = DB.createStatement()) {
            DB.beginRequest();
            st.execute(String.format("DELETE FROM users WHERE id = %d", id));
            DB.commit();
            return st.getUpdateCount() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                DB.endRequest();
            } catch (SQLException ignored) {
            }
        }
    }

    @Override
    public void clearTable() {
        try (Statement st = DB.createStatement()) {
            DB.beginRequest();
            st.execute("TRUNCATE users");
            DB.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                DB.endRequest();
            } catch (SQLException ignored) {
            }
        }
    }

    @Override
    public void createTable() {
        try (Statement st = DB.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "  id int unsigned NOT NULL AUTO_INCREMENT," +
                    "  name varchar(45) COLLATE utf8mb3_unicode_ci DEFAULT NULL," +
                    "  PRIMARY KEY (id)" +
                    ") ENGINE=MyISAM DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
