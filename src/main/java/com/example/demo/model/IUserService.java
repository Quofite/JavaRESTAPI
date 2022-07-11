package com.example.demo.model;

import java.util.List;

public interface IUserService {
    List<UserModel> getAllUsers();

    void setUser(UserModel user);

    UserModel getUserById(int id);
}
