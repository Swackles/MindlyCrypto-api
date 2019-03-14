package Mindly.Crypto;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Currency {
    private final long id;
    private final String name;
    private final String shortName;
    private final Float value;

    public Currency(long id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;

        try {
            System.out.println("https://api.bitfinex.com/v1/pubticker/"+shortName+"EUR");

            URL url = new URL("https://api.bitfinex.com/v1/pubticker/"+shortName+"eur");

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.setRequestProperty("Content-Type", "application/java");

            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            Pattern pattern = Pattern.compile(".*?\"last_price\":\"([0-9.]+\\w)\",.*?");
            Matcher matcher = pattern.matcher(content.toString());
            if(matcher.find()) {
                this.value = Float.parseFloat(matcher.group(1));
            } else {
                this.value = null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getValue() {
        return value;
    }

    public String getShortName() {
        return shortName;
    }
}
