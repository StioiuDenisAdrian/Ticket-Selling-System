package com.example.demo.service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public void sellTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void cancelTicket(int id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Ticket getById(int id) {
        return ticketRepository.getOne(id);
    }
}
