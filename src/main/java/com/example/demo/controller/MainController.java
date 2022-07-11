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

    @GetMapping("/customers")
    public ResponseEntity<List<UserModel>> show() {
        List<UserModel> users = userService.getAllUsers();

        return users != null && !users.isEmpty() ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<UserModel> showById(@PathVariable(name="id") int id) {
        UserModel user = userService.getUserById(id);

        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> create(@RequestBody UserModel dataModel) {
        userService.setUser(dataModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
