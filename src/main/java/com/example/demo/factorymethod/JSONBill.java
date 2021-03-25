package com.example.demo.factorymethod;


import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class JSONBill implements Bills {


    @Autowired
    private TicketService ticketService;


    public void sendBill(){
        List<Ticket> tickets = ticketService.getAll();
        ObjectMapper mapper = new ObjectMapper();
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Denis\\Desktop\\Assignment1\\JSONBills.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            for(Ticket ticket:tickets){
                mapper.writeValue(fw,ticket);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
