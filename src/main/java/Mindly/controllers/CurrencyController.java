package Mindly.controllers;

import Mindly.postgres.currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {
    @RequestMapping("/currencies")
    public ResponseEntity<String> Currencies() {
        try {
            ObjectWriter ow = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();

            return ResponseEntity.ok(ow.writeValueAsString(currency.select()));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(404).body("Failed to delete portfolio");
        }
    }
}
