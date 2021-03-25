package com.example.demo.controller;

import com.example.demo.factorymethod.BillFactory;
import com.example.demo.factorymethod.Bills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillFactory billFactory;

    @GetMapping("/csv")
    public void sendCSV(){
        billFactory.createBills("CSV");

    }

    @GetMapping("/json")
    public void sendJSON(){
         billFactory.createBills("JSON");

    }
}
