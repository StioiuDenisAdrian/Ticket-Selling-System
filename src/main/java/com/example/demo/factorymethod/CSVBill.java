package com.example.demo.factorymethod;

import com.example.demo.model.Concert;
import com.example.demo.model.Ticket;
import com.example.demo.service.ConcertService;
import com.example.demo.service.TicketService;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CSVBill implements Bills{

    @Autowired
    private ConcertService concertService;
    @Autowired
    private TicketService ticketService;

    @Override
    public void sendBill()  {
        List<String[]> csvData = createCsvData();
        try(CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\Denis\\Desktop\\Assignment1\\soldtickets.csv"))){
            writer.writeAll(csvData);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private  List<String[]> createCsvData(){
        List<String[]> list = new ArrayList<>();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormatter1 = new SimpleDateFormat("HH-mm");
        List<Ticket> tickets = ticketService.getAll();
        String[] header = {"Concert Name","Artist","Genre","Date","Time","Participant","Email","Tickets Bought"};
        list.add(header);
        for(Ticket ticket: tickets){
            Concert concert = concertService.getById(ticket.getConcertId());
            String[] record = {concert.getTitle(),concert.getArtist(),concert.getGenre(), dateFormatter.format(concert.getDate()),dateFormatter1.format(concert.getTime()),ticket.getParticipant(),ticket.getEmail(), String.valueOf(ticket.getTickets())};
            list.add(record);
        }
        return list;
    }

}
