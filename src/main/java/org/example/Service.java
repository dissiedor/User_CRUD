package org.example;

import org.example.UserDao;
import org.example.User;

import java.util.List;

public class Service {
    private final UserDao userDao = new UserDao();

    public void addUser(String name, String email, int age) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        userDao.createUser(user);
    }

    public List<User> listUsers() {
        return userDao.getAllUsers();
    }

    public User findUserById(Long id) {
        return userDao.getUser(id);
    }

    public void updateUser(Long id, String name, String email, int age) {
        User user = userDao.getUser(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setAge(age);
            userDao.updateUser(user);
        }
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
