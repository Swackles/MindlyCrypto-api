package Mindly.controllers;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import Mindly.Crypto.Currency;
import Mindly.postgres.currency;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioController {
    @RequestMapping("/currencies")
    public ArrayList<Currency> Currencies() {
        return currency.select();
    }
}