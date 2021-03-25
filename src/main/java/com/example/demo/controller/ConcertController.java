package com.example.demo.controller;

import com.example.demo.model.Concert;
import com.example.demo.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manageConcerts")
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    @GetMapping("/concerts")
    public List getAllUsers(){
        return concertService.getAll();
    }

    @PostMapping("/addConcert")
    public void addConcert(@RequestBody Concert concert){
        concertService.add(concert);
    }

    @PutMapping("/editConcert/{id}")
    public void updateConcert(@RequestBody Concert concert, @PathVariable int id){
        concertService.update(id, concert);
    }

    @GetMapping("/deleteConcert/{id}")
    public void deleteConcert(@PathVariable int id){
        concertService.delete(id);
    }
}
