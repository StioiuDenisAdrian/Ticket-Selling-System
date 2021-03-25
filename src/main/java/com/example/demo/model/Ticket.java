package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String participant;

    private String email;

    private int tickets;

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    @Column(name = "concert_id")
    private int concertId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getConcertId() {
        return concertId;
    }

    public void setConcertId(int concertId) {
        this.concertId = concertId;
    }
}
