package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(int id) {
        return userRepository.getOne(id);
    }



    @Override
    public void update(int id,User user) {
        userRepository.save(user);
    }

    @Override
    public void add(User user) {
        user.setEnabled(true);
        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        System.out.println(user.getPassword());
        Role role = new Role();
        role.setLogin(user.getUsername());
        role.setRole("ROLE_CASHIER");
        roleRepository.save(role);
    }

    @Override
    public void delete(int id) {
        roleRepository.deleteById(id);
        userRepository.deleteById(id);
    }


}
