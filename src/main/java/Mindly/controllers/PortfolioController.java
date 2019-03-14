package Mindly.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.json.*;

import Mindly.Crypto.Currency;
import Mindly.Crypto.PortfolioItem;
import Mindly.postgres.currency;

import Mindly.postgres.portfolio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortfolioController {
    @RequestMapping("/portfolio")
    public ArrayList<PortfolioItem> PortfolioItems() {
        return portfolio.select();
    }

    @RequestMapping(value = "/portfolio/add", method = RequestMethod.POST)
    public ArrayList<PortfolioItem> PortfiolioItemAdd(@RequestBody Map<String, Object> payload) {

        JSONObject obj = new JSONObject(payload);

        return portfolio.insert(obj.getInt("currencyID"), obj.getLong("amount"), obj.getString("description"));
    }

    @RequestMapping(value = "/portfolio/delete", method = RequestMethod.POST)
    public ResponseEntity PortfolioDelete(@RequestBody Map<String, Object> payload) {
        JSONObject obj  =new JSONObject(payload);
        try {
            boolean result = portfolio.delete(obj.getInt("id"));
            if (result) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return  new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            }
        } catch(Exception e) {
            System.out.println(e);
            return  new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @RequestMapping("/currencies")
    public ArrayList<Currency> Currencies() {
        return currency.select();
    }
}