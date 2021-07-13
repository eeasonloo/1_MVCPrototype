package com.eason.service.impl;

import com.eason.dao.impl.UserDaoImpl;
import com.eason.domain.User;
import com.eason.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return userDao.userlogin(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean addUser(User addUser) {
        return userDao.addUser(addUser);
    }
}
