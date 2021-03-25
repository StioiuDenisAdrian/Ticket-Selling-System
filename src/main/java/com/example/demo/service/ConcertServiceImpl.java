package com.example.demo.service;

import com.example.demo.model.Concert;
import com.example.demo.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcertServiceImpl implements ConcertService {

    @Autowired
    private ConcertRepository concertRepository;

    @Override
    public List<Concert> getAll() {
        return concertRepository.findAll();
    }

    @Override
    public Concert getById(int id) {
        return concertRepository.getOne(id);
    }

    @Override
    public void add(Concert concert) {
        concertRepository.save(concert);
    }

    @Override
    public void update(int id, Concert concert) {
        concertRepository.save(concert);
    }

    @Override
    public void delete(int id) {
        concertRepository.deleteById(id);
    }
}
