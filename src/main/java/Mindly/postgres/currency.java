package Mindly.postgres;

import Mindly.Crypto.Currency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class currency {
    public static ArrayList<Currency> select() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://46.101.140.99:5432/mindlycryptoportfolio", "postgres", "");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM public.currencies");

            ResultSet rs = stmt.executeQuery();

            ArrayList<Currency> currencies = new ArrayList<Currency>();

            while(rs.next()) {
                currencies.add(new Currency(rs.getInt(1), rs.getString(2), rs.getString(3)));
                System.out.println(rs.getInt(1)+ " " + rs.getString(2) + " " + rs.getString(3));
            }
            return currencies;

        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
