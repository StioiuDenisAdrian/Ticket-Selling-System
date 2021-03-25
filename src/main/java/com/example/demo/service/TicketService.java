package com.example.demo.service;

import com.example.demo.model.Ticket;

import java.util.List;

public interface TicketService {
    public List<Ticket> getAll();
    public void sellTicket(Ticket ticket);
    public void cancelTicket(int id);
    public Ticket getById(int id);
}
