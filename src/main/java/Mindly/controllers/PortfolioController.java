package Mindly.controllers;

import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import Mindly.Crypto.PortfolioItem;
import Mindly.postgres.portfolio;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortfolioController {
    @RequestMapping("/portfolio")
    public ResponseEntity<String> PortfolioItems() {
        try {
            ObjectWriter ow = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();

            return ResponseEntity.ok(ow.writeValueAsString(portfolio.select()));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(404).body("Failed to get portfolio");
        }
    }

    @RequestMapping(value = "/portfolio/add", method = RequestMethod.POST)
    public ResponseEntity<String> PortfiolioItemAdd(@RequestBody Map<String, Object> payload) {
        try {

            JSONObject obj = new JSONObject(payload);

            if (!obj.has("currencyID") || obj.isNull("currencyID")) return ResponseEntity.status(404).body("Invalid currency ID");
            if (!obj.has("amount") || obj.isNull("amount")) return ResponseEntity.status(404).body("Invalid amount");
            if (!obj.has("description") || obj.isNull("description")) return ResponseEntity.status(404).body("Invalid description");


            ObjectWriter ow = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();

            ArrayList<PortfolioItem> items = portfolio.insert(obj.getInt("currencyID"), obj.getLong("amount"), obj.getString("description"));

            return ResponseEntity.ok(ow.writeValueAsString(items));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(404).body("Failed to add portfolio");
        }
    }

    @RequestMapping(value = "/portfolio/delete", method = RequestMethod.POST)
    public ResponseEntity<String> PortfolioDelete(@RequestBody Map<String, Object> payload) {
        JSONObject obj = new JSONObject(payload);
        try {
            if (!obj.has("id") || obj.isNull("id")) return ResponseEntity.status(404).body("Invalid id");

            boolean result = portfolio.delete(obj.getInt("id"));
            if (result) {
                return ResponseEntity.ok("Successfully deleted");
            } else {
                return ResponseEntity.status(404).body("Wasn't able to delete this item");
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(404).body("Failed to delete portfolio");

        }
    }
}