package com.example.demo.controller;

import com.example.demo.model.Concert;
import com.example.demo.model.Ticket;
import com.example.demo.service.ConcertService;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manageTickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ConcertService concertService;

    @GetMapping("/showSoldTickets")
    public List getAllTickets() {
        List<Ticket> tickets = ticketService.getAll();
        for(Ticket ticket:tickets){
            Concert concert = concertService.getById(ticket.getConcertId());
            if(concert == null){
                ticketService.cancelTicket(ticket.getId());
            }
        }
        return ticketService.getAll();
    }

    @PostMapping("/sellTicket")
    public ResponseEntity<String> addTicket(@RequestBody Ticket ticket) {
        Concert concert = concertService.getById(ticket.getConcertId());
        int actualNumberofTickets = concert.getTicketNumber();
        if(ticket == null){
            return new ResponseEntity<>("Inexistent ticket", HttpStatus.BAD_REQUEST);
        }
        if (concert.getTicketNumber() == 0) {
            return new ResponseEntity<>("The concert is sold out", HttpStatus.INSUFFICIENT_STORAGE);
        } else if (concert == null) {
            return new ResponseEntity<>("The concert does not exist", HttpStatus.BAD_REQUEST);
        } else if (ticket.getTickets() > concert.getTicketNumber()) {
            return new ResponseEntity<>("Not enough tickets available", HttpStatus.BAD_REQUEST);
        }
        actualNumberofTickets -= ticket.getTickets();
        concert.setTicketNumber(actualNumberofTickets);
        ticketService.sellTicket(ticket);
        concertService.update(concert.getId(), concert);
        return new ResponseEntity<>("Ticket sold!", HttpStatus.OK);
    }

    @GetMapping("/cancelTicket/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable int id) {
        Ticket ticket = ticketService.getById(id);
        Concert concert = concertService.getById(ticket.getConcertId());
        if (ticket == null) {
            return new ResponseEntity<>("Ticket not found", HttpStatus.BAD_REQUEST);
        }
        concert.setTicketNumber(concert.getTicketNumber() + ticket.getTickets());
        concertService.update(concert.getId(), concert);
        ticketService.cancelTicket(id);
        return new ResponseEntity<>("Reservation cancelled!", HttpStatus.OK);
    }

    @PutMapping("/editTicket")
    public ResponseEntity<String> editTicket(@RequestBody Ticket ticket) {
        Ticket t = ticketService.getById(ticket.getId());
        Concert c = concertService.getById(ticket.getConcertId());
        Concert oldConcert = concertService.getById(t.getConcertId());
        if(ticket == null){
            return new ResponseEntity<>("Inexistent ticket", HttpStatus.BAD_REQUEST);
        }
        if (t == null) {
            return new ResponseEntity<>("This is not an existing ticket", HttpStatus.BAD_REQUEST);
        } else if (c == null) {
            return new ResponseEntity<>("Concert not found", HttpStatus.BAD_REQUEST);
        } else if (c.getId()==oldConcert.getId()){
            if(c.getTicketNumber()+t.getTickets()<ticket.getTickets()){
                return new ResponseEntity<>("Not enough tickets", HttpStatus.BAD_REQUEST);
            }else{
                c.setTicketNumber(c.getTicketNumber()+t.getTickets()- ticket.getTickets());
                concertService.update(c.getId(),c);
                ticketService.sellTicket(ticket);
            }
        } else if(c.getTicketNumber()<ticket.getTickets()){
            return new ResponseEntity<>("Not enough tickets", HttpStatus.BAD_REQUEST);
        }
        oldConcert.setTicketNumber(t.getTickets()+oldConcert.getTicketNumber());
        c.setTicketNumber(c.getTicketNumber()-ticket.getTickets());
        concertService.update(c.getId(),c);
        concertService.update(oldConcert.getId(),oldConcert);
        ticketService.sellTicket(ticket);
        return new ResponseEntity<>("Ticket Updated", HttpStatus.OK);
    }

}
