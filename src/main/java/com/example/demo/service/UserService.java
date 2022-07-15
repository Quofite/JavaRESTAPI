package com.example.demo.service;

import com.example.demo.model.IUserService;
import com.example.demo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {
    @Autowired
    public JdbcTemplate template;

    @Override
    public List<UserModel> getAllUsers() {
        String sql = "SELECT * FROM users;";
        List<UserModel> data = new ArrayList<>();
        List<Map<String, Object>> rows = template.queryForList(sql);

        for (Map row : rows) {
            UserModel user = new UserModel();
            user.setId((Integer) row.get("id"));
            user.setName(row.get("name").toString());
            user.setEmail(row.get("email").toString());
            data.add(user);
        }

        return data;
    }

    @Override
    public void setUser(UserModel user) {
        String sql = "INSERT INTO users (id, name, email) VALUES (" + user.getId() +  ", '" + user.getName() + "', '" + user.getEmail() + "');";
        template.execute(sql);
    }

    @Override
    public UserModel getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id=" + id;
        UserModel data = new UserModel();

        List<Map<String, Object>> rows = template.queryForList(sql);

        data.setId((Integer) rows.get(0).get("id"));
        data.setName(rows.get(0).get("name").toString());
        data.setEmail(rows.get(0).get("email").toString());

        return data;
    }

    @Override
    public boolean deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE id=" + id + ";";

        return template.update(sql) == 1;
    }
}
