package Mindly.postgres;

import Mindly.Crypto.Currency;
import Mindly.Crypto.PortfolioItem;

import javax.sound.sampled.Port;
import java.sql.*;
import java.util.ArrayList;

public class portfolio {
    public static ArrayList<PortfolioItem> select() {
        try {
            ConnectionSettings conSettings  = new ConnectionSettings();

            Connection con = DriverManager.getConnection(conSettings.getUrl(), conSettings.getUsername(), conSettings.getPassword());
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM public.\"portfolioItem\"");

            ResultSet rs = stmt.executeQuery();

            ArrayList<Currency> currencies = new currency().select();
            ArrayList<PortfolioItem> portfolioItems = new ArrayList<PortfolioItem>();

            while(rs.next()) {
                portfolioItems.add(new PortfolioItem(rs.getInt(1), currencies.get(rs.getInt(2) - 1).getValue(), rs.getInt(3), rs.getDate(5), rs.getString(4)));
                System.out.println(rs.getInt(1)+ " " + rs.getString(2) + " " + rs.getString(3));
            }
            return portfolioItems;

        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<PortfolioItem> select(int id) {
        try {
            ConnectionSettings conSettings  = new ConnectionSettings();

            Connection con = DriverManager.getConnection(conSettings.getUrl(), conSettings.getUsername(), conSettings.getPassword());
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM public.\"portfolioItem\" where id = "+id+"");

            ResultSet rs = stmt.executeQuery();

            ArrayList<Currency> currencies = new currency().select();
            ArrayList<PortfolioItem> portfolioItems = new ArrayList<PortfolioItem>();

            while(rs.next()) {
                portfolioItems.add(new PortfolioItem(rs.getInt(1), currencies.get(rs.getInt(2) - 1).getValue(), rs.getInt(3), rs.getDate(5), rs.getString(4)));
            }
            return portfolioItems;

        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<PortfolioItem> insert(int currencyID, long amount, String description) {
        try {
            ConnectionSettings conSettings  = new ConnectionSettings();

            Connection con = DriverManager.getConnection(conSettings.getUrl(), conSettings.getUsername(), conSettings.getPassword());
            PreparedStatement stmt = con.prepareStatement("INSERT INTO public.\"portfolioItem\" (currency_id, amount, description) VALUES ("+currencyID+", "+amount+", '"+description+"') RETURNING id");

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return select(rs.getInt(1));
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Boolean delete(int id) {
        try {
            ConnectionSettings conSettings = new ConnectionSettings();

            Connection con = DriverManager.getConnection(conSettings.getUrl(), conSettings.getUsername(), conSettings.getPassword());
            PreparedStatement stmt = con.prepareStatement("DELETE FROM public.\"portfolioItem\" WHERE id = " + id);

            stmt.executeQuery();
            return true;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
