package com.eason.dao;

import com.eason.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    public User userlogin(User loginUser);

    public List<User> findAll();

    boolean addUser(User addUser);

    boolean delSeletedUser(int i);

    User findUser(int id);

    void updateUser(User updateUser);

    int findTotalCount(Map<String, String[]> conditions);

    List<User> findUsersByPage(int pageBegin, int row, Map<String, String[]> conditions);
}
