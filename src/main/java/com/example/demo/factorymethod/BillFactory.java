package com.example.demo.factorymethod;

import com.opencsv.exceptions.CsvBadConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillFactory {

    @Autowired
    private CSVBill csvBill;
    @Autowired
    private JSONBill jsonBill;

    public void createBills(String channel){
        if("CSV".equals(channel)){
             csvBill.sendBill();
        }
        if("JSON".equals(channel)){
             jsonBill.sendBill();
        }

    }
}
