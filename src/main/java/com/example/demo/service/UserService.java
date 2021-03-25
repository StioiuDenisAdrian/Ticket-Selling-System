package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();
    public User getById(int id);
    public void add(User user);
    public void update(int id,User user);
    public void delete(int id);

}
