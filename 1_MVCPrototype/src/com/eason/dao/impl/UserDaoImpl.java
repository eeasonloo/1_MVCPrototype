package com.eason.dao.impl;

import com.eason.dao.UserDao;
import com.eason.domain.User;
import com.eason.util.JDBCUtils;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public User userlogin(User loginUser) {
        try {
            String sql ="select * from mvc where username = ? and password = ?";
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());

            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//record log
            return null;
        }

    }

    @Override
    public List<User> findAll() {

        String sql="select * from mvc";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public boolean addUser(User addUser) {
        String sql = "insert into mvc values(null,?,?,?,?,?,?,null,null)";
        int update = template.update(sql, addUser.getName(), addUser.getGender(),
                addUser.getAge(), addUser.getNationality(),
                addUser.getQq(), addUser.getEmail());

        if(update>0) return true;
        else return false;
    }

    @Override
    public boolean delSeletedUser(int i) {
        String sql ="delete from mvc where id = ?";
        int update = template.update(sql, i);

        if(update>0) return true;
        else return false;
    }

    @Override
    public User findUser(int id) {
        String sql = "select * from mvc where id = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),id);
        return user;
    }

    @Override
    public void updateUser(User updateUser) {
        String sql ="update mvc set name = ?,gender = ? ,age = ? , nationality = ? , qq = ?, email = ? where id = ?";
        template.update(sql, updateUser.getName(),updateUser.getGender(),
                updateUser.getAge(),updateUser.getNationality(),
                updateUser.getQq(),updateUser.getEmail(),updateUser.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> conditions) {
        String sql = "select count(*) from mvc where 1=1";

        StringBuilder sb = new StringBuilder(sql);

        ArrayList<Object> params = new ArrayList<>();


        Set<String> keys = conditions.keySet();
        for (String key : keys) {
            if(key.equals("currentPage") || key.equals("row")){
                continue;
            }
            String value = conditions.get(key)[0];

            if(value != null && !value.equals("")){
                sb.append(" and " + key + " like ?");
                params.add("%"+value+"%");
            }

        }

        return template.queryForObject(sb.toString(), Integer.class, params.toArray());

    }

    @Override
    public List<User> findUsersByPage(int pageBegin, int row, Map<String, String[]> conditions) {

        String sql = "select * from mvc where 1=1";
        StringBuilder sb = new StringBuilder(sql);

        ArrayList<Object> params = new ArrayList<>();


        Set<String> keys = conditions.keySet();
        for (String key : keys) {
            if(key.equals("currentPage") || key.equals("row")){
                continue;
            }
            String value = conditions.get(key)[0];

            if(value != null && !value.equals("")){
                sb.append(" and " + key + " like ?");
                params.add("%"+value+"%");
            }

        }
        sb.append(" limit ?,?");
        params.add(pageBegin);
        params.add(row);

        System.out.println(sb);
        System.out.println(params);


        List<User> query = template.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), params.toArray());
        System.out.println(query);
        return query;
    }

}
