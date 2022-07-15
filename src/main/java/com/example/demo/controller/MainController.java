package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    // getting all users (GET /users)
    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> show() {
        List<UserModel> users = userService.getAllUsers();

        return users != null && !users.isEmpty() ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // setting one user (POST /users + json with data that valid to the model)
    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody UserModel dataModel) {
        userService.setUser(dataModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // getting single user by id (GET /users/id)
    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> showById(@RequestParam("id") int id) {
        UserModel user = userService.getUserById(id);

        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // deleting one user by id (GET /delete/id)
    @GetMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") int id) {
        boolean response = userService.deleteUserById(id);

        return response ? new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

}
