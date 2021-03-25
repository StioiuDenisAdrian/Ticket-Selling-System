package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manageUsers")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List getAllUsers(){
        return userService.getAll();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        userService.add(user);
    }

    @PutMapping("/editUser/{id}")
    public void updateUser(@RequestBody User user, @PathVariable int id){
       userService.update(id,user);
    }

    @GetMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable int id){
        userService.delete(id);
    }



}
