package ppss;

import java.time.LocalDate;
import java.util.List;


public class MailServer {
    private String login;
    private String password;
    MailServer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public List<String> findMailItemsWithDate(LocalDate fecha) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
