package com.example.demo.service;

import com.example.demo.model.Concert;


import java.util.List;

public interface ConcertService {
    public List<Concert> getAll();
    public Concert getById(int id);
    public void add(Concert concert);
    public void update(int id, Concert concert);
    public void delete(int id);
}
