package Mindly.postgres;

public class ConnectionSettings {
    private final String url;
    private final String username;
    private final String password;

    public ConnectionSettings(){
        this.url = "jdbc:postgresql://46.101.140.99:5432/mindlycryptoportfolio";
        this.username = "postgres";
        this.password = "";
    }

    public String getUrl() {
        return this.url;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
